#include "pch.h"
#include "Graphs.h"

GEdge::GEdge(int c, int w) {
	child = c;
	weight = w;
}

GEdge* Graph::newEdge(int c, int w) {
	GEdge* edge = new GEdge(c, w);
	edge->next = NULL;
	return edge;
}

int GEdge::getChild() {
	return child;
}

int GEdge::getWeight() {
	return weight;
}

GVertex::GVertex() {

}

GVertex::GVertex(char name[]) {
	strcpy_s(id, name);
}

GVertex Graph::newVertex(char name[]) {
	GVertex vertex(name);
	vertex.firstEdge = NULL;
	return vertex;
}

char* GVertex::getId() {
	return id;
}

Graph::Graph(int n) {
	if (n > MaxVertices) {
		cout << "Error" << endl;
		exit(1);
	}
	numV = n;
}

void Graph::addVertex(char name[]) {
	GVertex vertex = newVertex(name);

	if (index <= numV) {
		Graph::vertex[index] = vertex;
		++index;
	}

	else {
		cout << "Limit already reached!" << endl;
	}
}

int Graph::getNumV() {
	return numV;
}