// Knapsack Problem.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "stdc++.h"

using namespace std;

struct Item {
	int value, weight;
};

int main()
{
	vector<Item> items = {
		{1,1},
		{4,3},
		{5,4},
		{7,5}
	};
	vector<vector<int>> shelf(items.size(), vector<int>(8));

	items[0] = { 1, 1 }; items[1] = { 4, 3 }; items[2] = { 5, 4 }; items[3] = { 7, 5 };

	for (int i = 0; i < items.size(); ++i) {
		for (int j = 0; j < shelf[i].size(); ++j) {
			shelf[i][j] = 0;
		}
	}

	for (int i = 0; i < items.size(); ++i) {
		for (int j = 0; j < shelf[i].size(); ++j) {
			if (items[i].weight <= j) {
				if (i > 0 && j > 0) {
					int calculatedWeight = items[i].value + shelf[i - 1][j - items[i].weight];
					shelf[i][j] = max(calculatedWeight, max(shelf[i][j - 1], shelf[i - 1][j]));
				}

				else if (items[i].weight == j) {
					shelf[i][j] = items[i].weight;
				}

				else if (j > 0 && items[i].weight < j) {
					shelf[i][j] = shelf[i][j - 1];
				}
			}

			else if (items[i].weight > j) {
				if (i != 0) {
					shelf[i][j] = shelf[i - 1][j];
				}
			}
		}
	}

	for (int i = 0; i < items.size(); ++i) {
		for (int j = 0; j < shelf[i].size(); ++j) {
			cout << shelf[i][j] << "\t";
		}
		cout << endl;
	}
    return 0;
}

