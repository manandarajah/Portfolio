#pragma once
#include <iostream>
#include <string>

#define MaxSize 20
#define MaxVertices 21

using namespace std;

class GEdge {
private:
	int child, weight;

public:
	GEdge(int child, int weight);
	GEdge* next;
	int getChild();
	int getWeight();
};

class GVertex {
private:
	char id[MaxSize + 1], color;
	int parent, cost, inDegree;

public:
	GVertex();
	GVertex(char name[]);
	GEdge* firstEdge;
	char* getId();
};

class Graph {
private:
	int numV;  
	int index = 0;
	GVertex newVertex(char name[]);
	GVertex vertex[MaxVertices + 1];

public:
	Graph(int n);
	GEdge* newEdge(int c, int w);
	void addVertex(char name[]);
	int getNumV();
};