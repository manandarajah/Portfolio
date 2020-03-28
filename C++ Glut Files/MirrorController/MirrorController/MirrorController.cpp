// MirrorController.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdlib.h>

#ifdef __APPLE__
#include <OpenGL/OpenGL.h>
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include "Classes.h"

GameObject *object, *mirrorObj;
int input[2];

void resetInput();

void resetInput() {
	for (int i = 0; i < 2; ++i)
		input[i] = 0;
}

void handleSpecialKeypress(int key, int x, int y) {
	if (input[0] != key || input[1] != key) {
		if (key == GLUT_KEY_UP || key == GLUT_KEY_DOWN)
			input[0] = key;
		if (key == GLUT_KEY_RIGHT || key == GLUT_KEY_LEFT)
			input[1] = key;
	}
}

void handleSpecialKeyrelease(int key, int x, int y) {
	resetInput();
}

void handleResize(int w, int h) {
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, (double)(w / h), 1, 200);
}

void initRendering() {
	glEnable(GL_DEPTH_TEST);

	//Initialize objects
	Color color{ 0.545f, 0, 0 };
	object = new GameObject(0, -1.75f, 0, 0.5f, 0.5f, color, 0.3f);
	mirrorObj = new GameObject(0, 1.75f, 0, 0.5f, 0.5f, color, 0.3f);
}

void drawScene() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0, 0, -5);

	object->Draw();
	mirrorObj->Draw();

	glutSwapBuffers();
}

void update(int value) {
	float objX, objY;
	object->GetPosition(objX, objY);

	if (input[0] == GLUT_KEY_UP) 
		objY += 0.05f;
	if (input[0] == GLUT_KEY_DOWN)
		objY -= 0.05f;
	if (input[1] == GLUT_KEY_LEFT)
		objX -= 0.05f;
	if (input[1] == GLUT_KEY_RIGHT)
		objX += 0.05f;

	object->SetPosition(objX, objY);
	mirrorObj->SetPosition(objX, -objY);

	glutPostRedisplay();
	glutTimerFunc(25, update, 0);
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 400);
	glutCreateWindow("Mirror prototype");
	initRendering();
	glutDisplayFunc(drawScene);
	glutSpecialFunc(handleSpecialKeypress);
	glutSpecialUpFunc(handleSpecialKeyrelease);
	glutReshapeFunc(handleResize);
	glutTimerFunc(25, update, 0);
	glutMainLoop();
	return 0;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
