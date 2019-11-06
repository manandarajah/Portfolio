#pragma once
void selection_sort(int list[]);
void insertion_sort(int* list);
void quick_sort(int list[], int hi, int lo);
int partition(int* list, int hi, int lo);
void bubble_sort(int list[]);

bool linear_search(int list[], int key);
bool binary_search(int list[], int key, int low, int high);