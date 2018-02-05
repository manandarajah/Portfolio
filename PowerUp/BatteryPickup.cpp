// Fill out your copyright notice in the Description page of Project Settings.

#include "BatteryPickup.h"
#include "Components/StaticMeshComponent.h"


ABatteryPickup::ABatteryPickup() {
	GetMesh()->SetSimulatePhysics(true);

	//The base power level of battery
	BatteryPower = 150.f;
}

void ABatteryPickup::WasCollected_Implementation() {
	//Use the base pickup behaviour
	Super::WasCollected_Implementation();
	
	//Destroy the battery
	Destroy();
}

//report power level of battery
float ABatteryPickup::GetPower() {
	return BatteryPower;
}