#include "pch.h"
#include "Data Structures.h"

void Node::setIndex(int index) {
	this->index = index;
}

void Node::setData(string data) {
	this->data = data;
}

int Node::getIndex() {
	return Node::index;
}

string Node::getData() {
	return Node::data;
}

LinkedList::LinkedList() {
	tail = current = head = NULL;
}

//Makes a node record to store into linkedlist
Node* LinkedList::makeNode(string data) {

	/*The C way of doing this: Node* node = (Node*)malloc(sizeof(Node))
		or NodePtr if there's a type for a structure pointer */
	Node* node = new Node;
	node->next = NULL;
	node->setData(data);
	return node;
}

//Adds node to the first position of the list
void LinkedList::addFirst(string data) {
	Node* node = makeNode(data);

	//Adds first node to the list
	if (current == NULL) {
		node->setIndex(0);
		tail = node;
		current = tail;
		head = current;
	}

	//Adds incoming nodes to the first position of the list while shifting all other elements down on the list
	else {
		node->setIndex(0);
		node->next = head;
		head = node;
		shiftNodes(head->next, 0);
	}
}

//Adds node to the end of the list
void LinkedList::addNode(string data) {
	Node* node = makeNode(data);

	if (current == NULL) {
		node->setIndex(0);
		tail = node;
		current = tail;
		head = current;
	}

	else {
		node->setIndex(current->getIndex() + 1);
		current->next = node;
		tail = current = node;
	}
}

//Inserts node into a specified position within the list
void LinkedList::insertNode(int index, string data) {
	Node* node = makeNode(data);
	current = head;

	if (current == NULL) {
		node->setIndex(index);
		tail = node;
		current = tail;
		head = current;
	}

	/*Travereses through the list, comparing the index of the new node with the index of the existing nodes to properly sort the new node within the 
		list*/
	else {
		node->setIndex(index);

		while (current != NULL) {
			if (current->next != NULL && current->next->getIndex() >= index) {
				node->next = current->next;
				current->next = node;
				current = node;
				break;
			}
			current = current->next;
		}

		if (current == NULL) {
			tail = current = node;
		}

		else {
			shiftNodes(current->next, index);
		}
	}
}

//Removes the node in the first element from the list
void LinkedList::removeFirst() {
	Node* node = head;
	head = head->next;
	delete node;
}

//Removes node with the specified data
void LinkedList::removeNode(string data) {
	current = head;

	while (current != NULL) {
		if (current->next != NULL && current->next->getData() == data) {
			Node* node = current->next;
			current->next = current->next->next;
			delete node;
			break;
		}
		current = current->next;
	}
}

//Removes last node in the list
void LinkedList::removeLast() {
	Node* node = tail;
	current = head;

	while (current != NULL) {
		if (current->next == tail) {
			tail = current;
			tail->next = NULL;
			break;
		}
		current = current->next;
	}
	delete node;
}

//Shifts all nodes by incrementing each node's index values from the given index local value
void LinkedList::shiftNodes(Node* ptr, int index) {
	if (ptr != NULL) {
		if (ptr->getIndex() >= index) {
			ptr->setIndex(ptr->getIndex() + 1);
			shiftNodes(ptr->next, index);
		}
	}
}

//Travereses through list to display each node information
void LinkedList::displayNode(Node* ptr) {
	if (ptr != NULL) {
		cout << "Index: " << ptr->getIndex() << "\tData: " << ptr->getData() << endl;
		displayNode(ptr->next);
	}
}

void LinkedList::displayNodes() {
	current = head;
	displayNode(current);
	cout << "End of list" << endl << endl;
}