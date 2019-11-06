#include "pch.h"
#include "BinaryTree.h"

void TreeNode::setData(int data) {
	TreeNode::data = data;
}

int TreeNode::getData() {
	return TreeNode::data;
}

BinaryTree::BinaryTree() {
	current = root = NULL;
}

TreeNode* BinaryTree::makeTree(int data) {
	TreeNode* tree = new TreeNode;
	tree->left = NULL;
	tree->right = NULL;
	tree->setData(data);
	return tree;
}

void BinaryTree::addNode(int data) {
	TreeNode* tree = makeTree(data);

	if (root == NULL) {
		root = tree;
		current = root;
	}

	else {
		addNode(tree, current);
	}
}

void BinaryTree::addNode(TreeNode* tree, TreeNode* ptr) {

	if (ptr->getData() > tree->getData()) {
		if (ptr->left == NULL) {
			ptr->left = tree;
		}

		else {
			addNode(tree, ptr->left);
		}
	}

	else if (ptr->getData() < tree->getData()) {
		if (ptr->right == NULL) {
			ptr->right = tree;
		}

		else {
			addNode(tree, ptr->right);
		}
	}
}

void BinaryTree::preOrder(TreeNode* tree, string action) {
	if (tree != NULL) {
		displayTree(tree, action);
		preOrder(tree->left, "Left");
		preOrder(tree->right, "Right");
	}
}

void BinaryTree::preOrder() {
	preOrder(root, "At root");
}

void BinaryTree::postOrder(TreeNode* tree, string action) {
	if (tree != NULL) {
		postOrder(tree->left, "Left");
		displayTree(tree, action);
		postOrder(tree->right, "Right");
	}
}

void BinaryTree::postOrder() {
	postOrder(root, "Left");
}

void BinaryTree::displayTree(TreeNode* tree, string action) {
	cout << action << endl << "Data: " << tree->getData() << endl;
}