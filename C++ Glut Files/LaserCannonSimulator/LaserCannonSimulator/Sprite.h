#pragma once

#ifdef __APPLE__
#include <OpenGL/OpenGL.h>
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <vector>

using namespace std;

class Sprite {
private:
	GLuint _sprite;
	float _x, _y, _a = 0, _w, _h, _uvCoords[8], _points[8];
	int _row, _col;
	unsigned int _texWidth = 0, _texHeight = 0, _f;

public:
	Sprite(const char *pFileName, unsigned int pCol = 1, unsigned int pRow = 1);
	void Draw();
	void SetPosition(float pX, float pY);
	void SetAngle(float pA);
	void SetFrame(unsigned int pF);
	void GetPosition(float &pX, float &pY);
	float GetWidth();
	float GetHeight();
	float GetAngle();
	unsigned int GetFrame();
	void CalculateUVs();
};

struct Color {
	float r, g, b;
};

class GameObject {
protected:
	float _x, _y, _scale, _a, _w, _h, _offset;
	Color _color;

public:
	GameObject();
	GameObject(float pX, float pY, float pScale, float pA, float pW, float pH, Color pColor, float pOffset);
	~GameObject();
	void Draw();
	void SetPosition(float pX, float pY);
	void SetScale(float scale);
	void SetAngle(float pA);
	void SetSize(float pW, float pH);
	void SetColor(Color pColor);
	void GetPosition(float &pX, float &pY);
	float GetScale();
	float GetAngle();
	float GetWidth();
	float GetHeight();
};

class ControlObject : public GameObject {
private:
	float _beamPointX, _beamPointY;
	int _input = GLUT_KEY_UP;

public:
	ControlObject();
	ControlObject(float pX, float pY, float pScale, float pA, float pW, float pH, Color pColor, float pOffset);
	void UpdateBeamPoint();
	void GetBeamPoint(float &pX, float &pY);
	void SetInput(int pInput);
	int GetInput();
};

class Beam : public GameObject {
private:
	float _timer = 0, _maxTime, _speed = 1;
	int _direction;

public:
	Beam();
	Beam(float pX, float pY, float pScale, float pA, float pW, float pH, Color pColor, float pOffset, float pMaxTime, int pInput);
	void Update();
	void SetSpeed(float pSpeed);
	void DelaySelfDestruct(float deltaTime, vector<Beam> &beams);
};