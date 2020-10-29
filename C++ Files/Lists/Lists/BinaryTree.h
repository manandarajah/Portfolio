#pragma once
#include <iostream>
#include <string>

using namespace std;

class TreeNode {
private:
	int data;

public:
	TreeNode* left;
	TreeNode* right;

	void setData(int data);
	int getData();
};

class BinaryTree {
private:
	TreeNode* root;
	TreeNode* current;
	TreeNode* makeTree(int data);
	void addNode(TreeNode* tree, TreeNode* ptr);
	void preOrder(TreeNode* tree, string action);
	void postOrder(TreeNode* tree, string action);
	void displayTree(TreeNode* tree, string action);

public:
	BinaryTree();
	void addNode(int data);
	void preOrder();
	void postOrder();
};