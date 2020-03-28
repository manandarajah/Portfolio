// DifferentSetOfShapes.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdlib.h>

#ifdef __APPLE__
#include <OpenGL/OpenGL.h>
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

using namespace std;

void drawSet1();
void drawSet2();
void drawSet3();

GLuint _displayListId[3];
float setX, setY;
int index = 0;

void handleKeypress(unsigned char key, int x, int y) {
	switch (key) {
	case 27:
		exit(0);
	case ' ':
		++index;

		if (index > size(_displayListId) - 1)
			index = 0;
	}
}

void initRendering() {
	glEnable(GL_DEPTH_TEST);

	for (int i = 0; i < size(_displayListId); ++i) {
		_displayListId[i] = glGenLists(1);
		glNewList(_displayListId[i], GL_COMPILE);
		
		if (i == 0)
			drawSet1();
		else if (i == 1)
			drawSet2();
		else
			drawSet3();

		glEndList();
	}
}

void drawSet1() {
	setX = -1.75f;
	setY = 0.25f;

	glColor3f(255, 255, 255);

	glBegin(GL_POLYGON);

	//2D Square
	glVertex2f(0 + setX, 0 + setY);
	glVertex2f(0.5f + setX, 0 + setY);
	glVertex2f(0.5f + setX, 0.5f + setY);
	glVertex2f(0 + setX, 0.5f + setY);

	glEnd();

	//2D Triangle
	glBegin(GL_TRIANGLES);

	glVertex2f(0 + setX, -0.75f + setY);
	glVertex2f(0.5f + setX, -0.75f + setY);
	glVertex2f(0.25f + setX, -0.25f + setY);

	glEnd();
}

void drawSet2() {
	setX = -0.25f;
	setY = 0;

	glColor3f(0.863f, 0.078f, 0.235f);

	glBegin(GL_POLYGON);

	//Pentagon
	glVertex2f(0 + setX, 0 + setY);
	glVertex2f(0.5f + setX, 0 + setY);
	glVertex2f(0.5f + setX, 0.5f + setY);
	glVertex2f(0 + setX, 0.5f + setY);

	glEnd();

	glBegin(GL_TRIANGLES);

	glVertex2f(0 + setX, 0.5f + setY);
	glVertex2f(0.5f + setX, 0.5f + setY);
	glVertex2f(0.25f + setX, 0.75f + setY);

	glEnd();
}

void drawSet3() {
	setX = 0.5f;

	//Sword
	glBegin(GL_POLYGON);

	glColor3f(0.753f, 0.753f, 0.753f);

	//Blade
	glVertex2f(0 + setX, -0.75f + setY);
	glVertex2f(0.3f + setX, -0.75f + setY);
	glVertex2f(0.3f + setX, 0.5f + setY);
	glVertex2f(0 + setX, 0.5f + setY);

	glEnd();

	glBegin(GL_POLYGON);

	glColor3f(0.255f, 0.412f, 0.882f);

	//Handle
	glVertex2f(0.05f + setX, -1 + setY);
	glVertex2f(0.05f + setX, -1.5f + setY);
	glVertex2f(0.25f + setX, -1.5f + setY);
	glVertex2f(0.25f + setX, -1 + setY);

	glEnd();

	glBegin(GL_TRIANGLES);

	glColor3f(0.753f, 0.753f, 0.753f);

	//Blade tip
	glVertex2f(0 + setX, 0.5f + setY);
	glVertex2f(0.3f + setX, 0.5f + setY);
	glVertex2f(0.15f + setX, 0.75f + setY);

	glColor3f(0.255f, 0.412f, 0.882f);

	//Blade cover
	glVertex2f(0 + setX, -0.75f + setY);
	glVertex2f(0.3f + setX, -0.75f + setY);
	glVertex2f(0.15f + setX, -1 + setY);
	
	//Blade cover - left side
	glVertex2f(0 + setX, -0.75f + setY);
	glVertex2f(0.15f + setX, -1 + setY);
	glVertex2f(-0.15f + setX, -1 + setY);

	//Blade cover - right side
	glVertex2f(0.3 + setX, -0.75f + setY);
	glVertex2f(0.15f + setX, -1 + setY);
	glVertex2f(0.45f + setX, -1 + setY);

	glEnd();
}

void handleResize(int w, int h) {
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, (double)w / (double)h, 1, 200);
}

void drawScene() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0, 0, -5);
	//gluOrtho2D(-10, 10, -10, 10);

	glCallList(_displayListId[index]);

	glutSwapBuffers();
}

//Called every 25 milliseconds
void update(int value) {
	glutPostRedisplay();
	glutTimerFunc(25, update, 0);
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 400);
	glutCreateWindow("Set of shapes");
	initRendering();
	glutDisplayFunc(drawScene);
	glutKeyboardFunc(handleKeypress);
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
