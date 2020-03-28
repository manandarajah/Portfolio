#pragma once

#ifdef __APPLE__
#include <OpenGL/OpenGL.h>
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

using namespace std;

struct Color {
	float r, g, b;
};

class GameObject {
private:
	float _x, _y, _a, _w, _h, _offset;
	Color _color;

public:
	GameObject();
	GameObject(float pX, float pY, float pA, float pW, float pH, Color pColor, float pOffset);
	~GameObject();
	void Draw();
	void SetPosition(float pX, float pY);
	void SetAngle(float pA);
	void SetColor(Color pColor);
	void GetPosition(float &pX, float &pY);
	float GetWidth();
	float GetHeight();
	float GetAngle();
};