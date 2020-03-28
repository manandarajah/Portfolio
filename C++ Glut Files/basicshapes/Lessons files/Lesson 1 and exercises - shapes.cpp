/* Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
 /* File for "Basic Shapes" lesson of the OpenGL tutorial on
  * www.videotutorialsrock.com
  */



#include <iostream>
#include <stdlib.h> //Needed for "exit" function

  //Include OpenGL header files, so that we can use OpenGL
#ifdef __APPLE__
#include <OpenGL/OpenGL.h>
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

using namespace std;

//Called when a key is pressed
void handleKeypress(unsigned char key, //The key that was pressed
	int x, int y) {    //The current mouse coordinates
	switch (key) {
	case 27: //Escape key
		exit(0); //Exit the program
	}
}

//Initializes 3D rendering
void initRendering() {
	//Makes 3D drawing work when something is in front of something else
	glEnable(GL_DEPTH_TEST);
}

//Called when the window is resized
void handleResize(int w, int h) {
	//Tell OpenGL how to convert from coordinates to pixel values
	glViewport(0, 0, w, h);

	glMatrixMode(GL_PROJECTION); //Switch to setting the camera perspective

	//Set the camera perspective
	glLoadIdentity(); //Reset the camera
	gluPerspective(45.0,                  //The camera angle
		(double)w / (double)h, //The width-to-height ratio
		1.0,                   //The near z clipping coordinate
		200.0);                //The far z clipping coordinate
}

//Draws the 3D scene
void drawScene() {
	//Clear information from last draw
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glMatrixMode(GL_MODELVIEW); //Switch to the drawing perspective
	glLoadIdentity(); //Reset the drawing perspective
	glTranslatef(0, 0, -5);

	/*glBegin(GL_QUADS); //Begin quadrilateral coordinates

	//Trapezoid
	glVertex3f(-0.7f, -1.5f, 0.0f);
	glVertex3f(0.7f, -1.5f, 0.0f);
	glVertex3f(0.4f, -0.5f, 0.0f);
	glVertex3f(-0.4f, -0.5f, 0.0f);

	glEnd(); */ //End quadrilateral coordinates

	glBegin(GL_TRIANGLES); //Begin triangle coordinates

	//Trapezoid drawn using triangles - Exercise 3
	glVertex3f(-0.7f, -1.5f, 0);
	glVertex3f(0, -1.5f, 0);
	glVertex3f(-0.35f, -0.5f, 0);

	glVertex3f(-0.35f, -0.5f, 0);
	glVertex3f(0.35f, -0.5f, 0);
	glVertex3f(0, -1.5f, 0);

	glVertex3f(0, -1.5f, 0);
	glVertex3f(0.7f, -1.5f, 0);
	glVertex3f(0.35f, -0.5f, 0);

	//Pentagon

	//Make the shape look like it's going away from the eye - Exercise 1
	glVertex3f(0.5f, 0.5f, -7);
	glVertex3f(1.5f, 0.5f, -7);
	glVertex3f(0.5f, 1.0f, -7);

	glVertex3f(0.5f, 1.0f, -7);
	glVertex3f(1.5f, 0.5f, -7);
	glVertex3f(1.5f, 1.0f, -7);

	glVertex3f(0.5f, 1.0f, -7);
	glVertex3f(1.5f, 1.0f, -7);
	glVertex3f(1.0f, 1.5f, -7);

	glEnd();

	//Square - Exercise 2
	glBegin(GL_POLYGON);

	/*
		What I learned here is whatever points you lay out, it will draw from the previous point to the next point.
		That means if you want to draw a specific shape, the glut program has to connect the points in a specific
		order. So for the square for example, I layed out my points, started from the top left of that point,
		and drawn the line to the next point beside it, then the next point below that, and the last point which is
		below the point we started from. You can program this in any way, but it needs to follow a specific to
		acheive a specific shape.
	*/

	//Way #1

	glVertex3f(-0.5f, 0.5f, 0.0f); //1
	glVertex3f(-1.5f, 0.5f, 0.0f); //2
	glVertex3f(-1.5f, 1.5f, 0.0f); //3
	glVertex3f(-0.5f, 1.5f, 0.0f); //4

	//Way #2

	/*glVertex3f(-0.5f, 0.5f, 0);
	glVertex3f(-0.5f, 1.5f, 0);
	glVertex3f(-1.5f, 1.5f, 0);
	glVertex3f(-1.5f, 0.5f, 0);*/

	//In that order

	glEnd(); //End triangle coordinates

	glutSwapBuffers(); //Send the 3D scene to the screen
}

int main(int argc, char** argv) {
	//Initialize GLUT
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 400); //Set the window size

	//Create the window
	glutCreateWindow("Basic Shapes - videotutorialsrock.com");
	initRendering(); //Initialize rendering

	//Set handler functions for drawing, keypresses, and window resizes
	glutDisplayFunc(drawScene);
	glutKeyboardFunc(handleKeypress);
	glutReshapeFunc(handleResize);

	glutMainLoop(); //Start the main loop.  glutMainLoop doesn't return.
	return 0; //This line is never reached
}