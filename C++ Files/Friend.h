#pragma once
#include <string>

using namespace std;



class Friend {

protected:
	string name;
	string occupation;
	int age;

public:
	Friend(string name, string occupation, int age);
	Friend(Friend& f);
	Friend();

	void WriteToConstructor(string name, string occupation, int age);
	void WriteToConstructor(Friend& f);
	void Action();

	string GetName();
	string GetOccupation();
	int GetAge();

	bool operator>(const Friend &comp);
	bool operator==(const Friend &comp);
};