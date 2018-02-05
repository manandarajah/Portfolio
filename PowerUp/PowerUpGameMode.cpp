// Copyright 1998-2017 Epic Games, Inc. All Rights Reserved.
//Game mode sets the rules of the game

#include "PowerUpGameMode.h"
#include "PowerUpCharacter.h"
#include "UObject/ConstructorHelpers.h"
#include "Kismet/GameplayStatics.h"
#include "Blueprint/UserWidget.h"
#include "SpawnVolume.h"
#include "Components/StaticMeshComponent.h"
#include "Components/SkeletalMeshComponent.h"
#include "GameFramework/PawnMovementComponent.h"

APowerUpGameMode::APowerUpGameMode()
{
	PrimaryActorTick.bCanEverTick = true;

	// set default pawn class to our Blueprinted character
	static ConstructorHelpers::FClassFinder<APawn> PlayerPawnBPClass(TEXT("/Game/ThirdPersonCPP/Blueprints/ThirdPersonCharacter"));
	if (PlayerPawnBPClass.Class != NULL)
	{
		DefaultPawnClass = PlayerPawnBPClass.Class;
	}

	//Base decay rate
	DecayRate = .05f;
}

void APowerUpGameMode::BeginPlay() {
	Super::BeginPlay();

	//Find all spawn volume actors
	TArray<AActor*> FoundActors;
	UGameplayStatics::GetAllActorsOfClass(GetWorld(), ASpawnVolume::StaticClass(), FoundActors);

	for (auto Actor : FoundActors) {
		ASpawnVolume* SpawnVolumeActor = Cast<ASpawnVolume>(Actor);

		if (SpawnVolumeActor) {
			SpawnVolumeActors.AddUnique(SpawnVolumeActor);
		}
	}

	SetCurrentState(EBatteryPlayState::EPlaying);

	//Set the score to beat
	APowerUpCharacter* MyCharacter = Cast<APowerUpCharacter>(UGameplayStatics::GetPlayerPawn(this, 0));

	if (MyCharacter) {
		PowerToWin = (MyCharacter->GetInitialPower()) * 1.25f;
	}

	if (HUDWidgetClass != nullptr) {
		CurrentWidget = CreateWidget<UUserWidget>(GetWorld(), HUDWidgetClass);

		if (CurrentWidget != nullptr) {
			CurrentWidget->AddToViewport();
		}
	}
}

void APowerUpGameMode::Tick(float DeltaTime) {
	Super::Tick(DeltaTime);

	//Check that we are using the battery collector character
	APowerUpCharacter* MyCharacter = Cast<APowerUpCharacter>(UGameplayStatics::GetPlayerPawn(this, 0));

	if (MyCharacter) {
		
		//If power is greater than needed to win, set state to won
		if (MyCharacter->GetCurrentPower() > PowerToWin) {
			SetCurrentState(EBatteryPlayState::EWon);
		}

		//If the character's power is positive
		else if (MyCharacter->GetCurrentPower() > 0) {
			//Decrease the character's power using the decay rate
			MyCharacter->UpdatePower(-DeltaTime * DecayRate *(MyCharacter->GetInitialPower()));
		}

		else {
			SetCurrentState(EBatteryPlayState::EGameOver);
		}
	}
}

float APowerUpGameMode::GetPowerToWin() const {
	return PowerToWin;
}

EBatteryPlayState APowerUpGameMode::GetCurrentState() const {
	return CurrentState;
}

void APowerUpGameMode::SetCurrentState(EBatteryPlayState NewState) {
	CurrentState = NewState;
	HandleNewState(CurrentState);
}

void APowerUpGameMode::HandleNewState(EBatteryPlayState NewState) {
	switch (NewState) {
		case EBatteryPlayState::EPlaying:{
			for (ASpawnVolume* Volume : SpawnVolumeActors) {
				Volume->SetSpawningActive(true);
			}
		}
		break;
		case EBatteryPlayState::EWon:{
			for (ASpawnVolume* Volume : SpawnVolumeActors) {
				Volume->SetSpawningActive(false);
			}
		}
		break;
		case EBatteryPlayState::EGameOver:{
			for (ASpawnVolume* Volume : SpawnVolumeActors) {
				Volume->SetSpawningActive(false);

				//block player input
				APlayerController* PlayerController = UGameplayStatics::GetPlayerController(this, 0);

				if (PlayerController) {
					PlayerController->SetCinematicMode(true, false, false, true, true);
				}

				//Ragdoll the character
				ACharacter* MyCharacter = UGameplayStatics::GetPlayerCharacter(this, 0);

				if (MyCharacter) {
					MyCharacter->GetMesh()->SetSimulatePhysics(true);
					MyCharacter->GetMovementComponent()->MovementState.bCanJump = false;

				}
			}


		}
		break;

		default:
			case EBatteryPlayState::EUnknown:{

			}
			break;
	}
}