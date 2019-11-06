// Lists.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include "Data Structures.h"
#include "BinaryTree.h"

int main()
{
	LinkedList list;
	list.addNode("Mugiesshan");
	list.addNode("Sean");
	list.addNode("Meraz");
	list.addNode("TK");
	list.addNode("Kayla");
	list.addNode("Blaise");
	list.addFirst("Paloma");
	list.insertNode(3, "Andrew");
	list.removeNode("Meraz");
	list.removeLast();
	list.displayNodes();

	Stack s;
	s.push("Mugiesshan");
	s.push("Sean");
	s.push("Krishna");
	s.push("Gaurav");
	s.pop();
	s.displayStacks();

	Queue q;
	q.queue("Victor");
	q.queue("Taylor");
	q.queue("John");
	q.queue("Albert P");
	q.queue("Albert A");
	q.queue("Tyler");
	q.dequeue();
	q.dequeue();
	q.displayQueues();

	BinaryTree b;
	b.addNode(5);
	b.addNode(10);
	b.addNode(3);
	b.addNode(15);
	b.addNode(1);
	b.addNode(13);
	b.addNode(2);

	cout << "Pre-Ordered list" << endl;
	b.preOrder();
	cout << endl << "Post-Ordered list" << endl;
	b.postOrder();
}
//}
// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
