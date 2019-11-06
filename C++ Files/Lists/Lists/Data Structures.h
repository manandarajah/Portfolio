#pragma once
#include <iostream>
#include <string>

using namespace std;

class Node {
private:
	int index;
	string data;

public:
	Node* next;
	void setIndex(int index);
	void setData(string data);
	int getIndex();
	string getData();
};

//LinkedLists
class LinkedList {

private:
	Node* head;
	Node* current;
	Node* tail;
	void shiftNodes(Node* ptr, int index);
	Node* makeNode(string data);
	void displayNode(Node* ptr);

public:
	LinkedList();
	void addFirst(string data);
	void addNode(string data);
	void insertNode(int index, string data);
	void removeFirst();
	void removeNode(string data);
	void removeLast();
	void displayNodes();
};

class Stack : LinkedList {
public:
	void push(string data);
	void pop();
	void displayStacks();
};

class Queue : LinkedList {
public:
	void queue(string data);
	void dequeue();
	void displayQueues();
};