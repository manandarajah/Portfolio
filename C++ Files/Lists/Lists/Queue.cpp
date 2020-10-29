#include "pch.h"
#include "Data Structures.h"

void Queue::queue(string data) {
	addFirst(data);
}

void Queue::dequeue() {
	removeLast();
}

void Queue::displayQueues() {
	displayNodes();
}