// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Actor.h"
#include "Pickup.generated.h"

UCLASS()
class POWERUP_API APickup : public AActor
{
	GENERATED_BODY()
	
public:	
	// Sets default values for this actor's properties
	APickup();

protected:
	// Called when the game starts or when spawned
	virtual void BeginPlay() override;

public:	
	// Called every frame
	virtual void Tick(float DeltaTime) override;

public:
	//Return the mesh for pickup
	FORCEINLINE class UStaticMeshComponent* GetMesh()  const { return PickupMesh; }

	//Return whether or not the pickup is active
	UFUNCTION(BlueprintPure, Category = "Pickup")
	bool IsActive();
	
	//Allows other classes to safely change whether or not pickup is active
	UFUNCTION(BlueprintCallable, Category = "Pickup")
	void SetActive(bool PickupState);

	//Function to call when pickup is collected
	UFUNCTION(BlueprintNativeEvent)
	void WasCollected();
	virtual void WasCollected_Implementation();

protected:
	//True when pickup can be used, and false when pickup is deactivated
	bool bIsActive;
	
public:
	/** Static mesh to represent pickup in the level */
	UPROPERTY(VisibleAnywhere, BlueprintReadOnly, Category = "Pickup", meta = (AllowPrivateAcces = "true"))
	class UStaticMeshComponent* PickupMesh;
};
