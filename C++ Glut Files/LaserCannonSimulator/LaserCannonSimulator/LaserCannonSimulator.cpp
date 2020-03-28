// LaserCannonSimulator.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdlib.h>

#ifdef __APPLE__
#include <OpenGL/OpenGL.h>
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include "Sprite.h"

ControlObject* controller;

//Manages number of beams shot by the controller object
vector<Beam> beams;

//Variables to determine player input action and enables specific set of code to execute
bool areBeamsActive = false, isScaling = false, charMoving = false;

//An array that handles multiple inputs to simulate dynamic movements
int input[2];

//Value used to help determine weather scale value goes up or down
unsigned char keyIn;

//All 4 of these variables helps determine individual beam data upon beam instantiation
float timer = 0, maxTime = 2, beamW = 0.03f, beamH = 1;
GLuint display;

void displayList();
void resetInput();

void resetInput() {
	for (int i = 0; i < 2; ++i) {
		input[i] = 0;
	}
}

void handleKeypress(unsigned char key, int x, int y) {
	switch (key) {
	case 27:
		exit(0);
	case ' ':
		//Enables beams to be displayed
		areBeamsActive = true;

		//Generates data to create beam object
		float x, y;
		controller->GetBeamPoint(x, y);
		Color color{ 0.804, 0.361f, 0.361f };
		Beam beam(x, y, controller->GetScale(), 0, beamW, beamH, color, 0.3f, 2, controller->GetInput());

		//Determines if beam size is too small, if true than double the speed
		if (beam.GetScale() < 1)
			beam.SetSpeed(2);

		//Stores beam object into vector
		beams.push_back(beam);
	}

	//Gets scaling input
	if (key == 'w' || key == 's') {
		isScaling = true;
		keyIn = key;
	}
}

void handleKeyrelease(unsigned char key, int x, int y) {
	isScaling = false;
	key = '\0';
}

void handleSpecialKeyrelease(int key, int x, int y) {
	charMoving = false;
	resetInput();
}

void handleSpecialKeypress(int key, int x, int y) {
	//Stores keyboard movement value. First array stores up and down  values, and second array stores left and right.
	if (input[0] != key || input[1] != key) {
		if (key == GLUT_KEY_UP || key == GLUT_KEY_DOWN)
			input[0] = key;
		if (key == GLUT_KEY_RIGHT || key == GLUT_KEY_LEFT)
			input[1] = key;
	}

	//Code that configures beam size and direction depending on player input
	if (input[0] != 0 && (key == GLUT_KEY_UP || key == GLUT_KEY_DOWN)) {
		controller->SetInput(key);
		beamW = 0.03f;
		beamH = 1;

		//If player input is key down, invert beam height so beam is drawn down-wards
		if (key == GLUT_KEY_DOWN)
			beamH *= -1;
	}

	else if (input[1] != 0 && input[0] == 0) {
		controller->SetInput(key);
		beamW = 1;
		beamH = 0.03f;

		//If player input is key left, invert beam width to its left most side
		if (key == GLUT_KEY_LEFT)
			beamW *= -1;
	}

	//If there is any player input at all, enable player movement
	if (input[0] == key || input[1] == key)
		charMoving = true;
}

void initRendering() {
	glEnable(GL_DEPTH_TEST);

	resetInput();

	//Initialize controller
	Color color{ 0.545f, 0, 0 };
	controller = new ControlObject(0, 0, 1, 0, 0.5f, 0.5f, color, 0.3f);
	displayList();
}

void displayList() {
	display = glGenLists(1);
	glNewList(display, GL_COMPILE);

	controller->Draw();

	if (areBeamsActive) {
		for (vector<Beam>::iterator it = beams.begin(); it != beams.end(); ++it)
			it->Draw();
	}

	glEndList();
}

void handleResize(int w, int h) {
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, (double)(w / h), 1, 200);
}

void drawScene() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0, 0, -5);

	glCallList(display);

	glutSwapBuffers();
}

void update(int value) {
	//Collects input settings
	float objX, objY, scale;
	controller->GetPosition(objX, objY);
	scale = controller->GetScale();

	//Updates to player's new position
	if (charMoving) {

		if (input[0] == GLUT_KEY_UP)
			objY += 0.05f;
		if (input[0] == GLUT_KEY_DOWN)
			objY -= 0.05f;
		if (input[1] == GLUT_KEY_LEFT)
			objX -= 0.05f;
		if (input[1] == GLUT_KEY_RIGHT)
			objX += 0.05f;

		controller->SetPosition(objX, objY);
		controller->UpdateBeamPoint();
	}

	//Updates player's new size
	if (isScaling) {
		scale += keyIn == 'w' ? 0.05f : -0.05f;
		controller->SetScale(scale);
	}

	//Updates all beams if they're active
	if (areBeamsActive) {
		for (vector<Beam>::iterator it = beams.begin(); it != beams.end(); ++it) {
			it->Update();
			it->DelaySelfDestruct((float) value / 1000, beams);
		}

		if (beams.empty())
			areBeamsActive = false;
	}

	displayList();
	glutPostRedisplay();
	glutTimerFunc(25, update, 0);
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 400);
	glutCreateWindow("Laser Cannon Simulator");
	initRendering();
	glutDisplayFunc(drawScene);
	glutKeyboardFunc(handleKeypress);
	glutKeyboardUpFunc(handleKeyrelease);
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
