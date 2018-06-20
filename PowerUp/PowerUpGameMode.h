// Copyright 1998-2017 Epic Games, Inc. All Rights Reserved.
//Game mode sets the rules of the game

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/GameModeBase.h"
#include "PowerUpGameMode.generated.h"

//enum to store the current state of gameplay
UENUM(BlueprintType)
enum class EBatteryPlayState : uint8 {
	EPlaying,
	EGameOver,
	EWon,
	EUnknown
};

//AGameModeBase
UCLASS(minimalapi)
class APowerUpGameMode : public AGameModeBase
{
	GENERATED_BODY()

public:
	APowerUpGameMode();

	virtual void Tick(float DeltaTime) override;

	//Returns power needed to win - needed for the HUD
	UFUNCTION(BlueprintPure, Category = "Power")
	float GetPowerToWin() const;

	virtual void BeginPlay() override;

	//Returns the current playing state
	UFUNCTION(BlueprintPure, Category = "Power")
	EBatteryPlayState GetCurrentState() const;

	//Sets a new playing state
	void SetCurrentState(EBatteryPlayState NewState);

protected:

	//Rate at which the character loses power
	UPROPERTY(EditDefaultsOnly, BlueprintReadWrite, Category = "Power", Meta = (BlueprintProtected = "true"))
	float DecayRate;

	//The power needed to win the game
	UPROPERTY(EditDefaultsOnly, BlueprintReadWrite, Category = "Power", Meta = (BlueprintProtected = "true"))
	float PowerToWin;

	//The widget class to use for our HUD screen
	UPROPERTY(EditDefaultsOnly, BlueprintReadWrite, Category = "Power", Meta = (BlueprintProtected = "true"))
	TSubclassOf<class UUserWidget> HUDWidgetClass;

	//The instance of the HUD
	UPROPERTY(EditDefaultsOnly, BlueprintReadWrite, Category = "Power", Meta = (BlueprintProtected = "true"))
	class UUserWidget* CurrentWidget;

private:
	//Keep track of the current playin state
	EBatteryPlayState CurrentState;

	TArray<class ASpawnVolume*> SpawnVolumeActors;

	//Handle any function calls that rely upon changing the playing state of the game
	void HandleNewState(EBatteryPlayState NewState);
};



