#pragma once

typedef struct node {
	int index;
	struct node* next;
}Node, *NodePtr;

void addNode(NodePtr ptr);