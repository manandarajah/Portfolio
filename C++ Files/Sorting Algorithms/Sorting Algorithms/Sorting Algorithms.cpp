// Sorting Algorithms.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include "Sorting Algorithms.h"
#include <iostream>

using namespace std;

int main()
{
	int arr[]{ 4,9,7,1,3,6,5 };
	selection_sort(arr);
	//insertion_sort(arr);
	//bubble_sort(arr);

	for (int i = 0; i < 7; ++i) {
		cout << arr[i] << ", ";
	}

	binary_search(arr, 5, 0, 7);
	return 0;
}

void selection_sort(int list[]) {
	for (int i = 0; i < 7; ++i) {
		int index = 0;
		int smallest = list[i];

		for (int j = i; j < 7; ++j) {
			if (list[j] < smallest) {
				smallest = list[j];		
				index = j;
			}
		}

		if (smallest < list[i]) {
			int tmp = list[i];
			list[i] = smallest;
			list[index] = tmp;
		}
	}
}

void insertion_sort(int* list) {

	for (int i = 1; i < 7; ++i) {
		int key = list[i];
		int index = i - 1;

		while (index >= 0 && list[index] > key) {
			list[index + 1] = list[index];
			index--;
		}
		list[index + 1] = key;
	}
}

void quick_sort(int list[], int hi, int lo) {
	if (lo < hi) {
		int p = partition(list, hi, lo);
		quick_sort(list, p - 1, lo);
		quick_sort(list, hi, p + 1);
	}
}

int partition(int* list, int hi, int lo) {
	int pivot = list[lo], lastsmall = lo;

	for (int i = lo + 1; i <= hi; ++i) {
		if (list[i] < pivot) {
			++lastsmall;
			int tmp = list[lastsmall];
			list[lastsmall] = list[i];
			list[i] = tmp;
		}
	}

	return lastsmall;
}

void bubble_sort(int list[]) {
	bool swapped;

	do {
		swapped = false;
		for (int i = 0; i < 6; ++i) {
			if (list[i] > list[i + 1]) {
				int tmp = list[i + 1];
				list[i + 1] = list[i];
				list[i] = tmp;
				swapped = true;
			}
		}
	} while (swapped);
}

bool linear_search(int list[], int key) {
	for (int i = 0; i < 7; ++i) {
		if (list[i] == key) {
			cout << "Element found!";
			return true;
		}
	}
	cout << "Element not found";
	return false;
}

bool binary_search(int list[], int key, int low, int high) {
	if (low > high) {
		cout << "Can't find the number";
		return false;
	}
	int middle = (low + high) / 2;

	if (key == list[middle]) {
		cout << "Found! it's at position " << middle << endl;
		return true;
	}

	else if (key > list[middle]) {
		binary_search(list, key, middle + 1, high);
	}

	else {
		binary_search(list, key, low, middle - 1);
	}
}
// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
