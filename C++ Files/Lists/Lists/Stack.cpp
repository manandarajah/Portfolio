#include "pch.h"
#include "Data Structures.h"

void Stack::push(string data) {
	addFirst(data);
}

void Stack::pop() {
	removeFirst();
}

void Stack::displayStacks() {
	displayNodes();
}