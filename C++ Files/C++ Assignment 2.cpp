// C++ Assignment 2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Friend.h"
#include <iostream>

Friend* current = nullptr;
Friend* head = nullptr;

int main()
{
	int number; // 1

	cout << "Please enter the number of friends you have: "; // 1
	cin >> number; // 1

	while (number < 2) { // n
		cout << endl << "The count of integers must be higer than 2! Try again: "; // 1
		cin >> number; // 1
	}

	/*this is a literal data type, meaning anything inside is read-only.
		If you want to write data into it, then you'll have to write inside the properties, or instead use this:
		Friend frineds[1024] 
	*/
	//Friend* friends = (Friend*)malloc(sizeof(Friend)*number);
	Friend friends[1024]; // 2
	Friend *fptr = (Friend*)malloc(sizeof(Friend)*number);

	/*Use this if you want to use data as a data structure
		Friend *head = (Friend*)malloc(sizeof(Friend));
		Friend *current = head;
	*/

	for (int i = 0; i < number; ++i) { // n
		string name;
		string occupation;
		int age;

		cout << endl << "What's the name of friend " << (i+1) << ": "; // 3
		getline(cin, name); // n Note: in C++, when you ask for a string after asking for an int, write it again to ensure the program takes it in
		getline(cin, name);
		cout << "What's their occupation: ";
		getline(cin, occupation);
		cout << "How old is that person: ";
		cin >> age;
		cout << endl;

		Friend f(name, occupation, age);

		//When you store a class in an array and pass the record to a pointer
		friends[i] = Friend(f);
		fptr = &friends[i];

		//Changes index, ++fptr changes the value
		if (i != number - 1) {
			fptr++;
		}
	}

	//Resets pointer to the address of the first element of the array
	fptr = &friends[0];

	//Traverse through the pointer array and print value
	for (int i = 0; i < number; ++i) { // n
		fptr->Action(); // n

		if (i != number - 1) {
			fptr++;
		}
	}
	cout << endl;
	fptr = &friends[0];

	int i = 0;

	while (i < number) {
		if (friends[i+1] > *fptr && i != number - 1) {
			cout << friends[i+1].GetName() << " is older than " << fptr->GetName() << endl;
		}

		else if (*fptr == friends[i + 1]) {
			cout << "Same person" << endl;
		}
		fptr++;
		++i;
	}

	getchar();
	getchar();
    return 0;
}