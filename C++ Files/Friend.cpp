#include "stdafx.h"
#include "Friend.h"
#include <iostream>

Friend::Friend(string name, string occupation, int age) {
	WriteToConstructor(name, occupation, age);
}

Friend::Friend(Friend& f) {
	WriteToConstructor(f);
}

Friend::Friend() {
	name = "";
	occupation = "";
	age = 0;
}

void Friend::WriteToConstructor(string name, string occupation, int age) {
	this->name = name;
	this->occupation = occupation;
	this->age = age;
}

void Friend::WriteToConstructor(Friend& f) {
	WriteToConstructor(f.GetName(), f.GetOccupation(), f.GetAge());
}

string Friend::GetName() {
	return name;
}

string Friend::GetOccupation() {
	return occupation;
}

int Friend::GetAge() {
	return age;
}

void Friend::Action() {
	cout << "Hello, my name is " << name << " and I am a " << age << " year old " << occupation << endl;
}

bool Friend::operator>(const Friend& comp) {
	return age > comp.age;
}

bool Friend::operator==(const Friend& comp) {
	return age == comp.age;
}