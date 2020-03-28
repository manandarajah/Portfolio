#include "Classes.h"

GameObject::GameObject() {
}

GameObject::GameObject(float pX, float pY, float pA, float pW, float pH, Color pColor, float pOffset) {
	_x = pX;
	_y = pY;
	_a = pA;
	_w = pW;
	_h = pH;
	_color = pColor;
	_offset = pOffset;
}

GameObject::~GameObject() {

}

void GameObject::Draw() {
	glBegin(GL_POLYGON);

	float r = _color.r, g = _color.g, b = _color.b,
		x1 = _x - _offset,
		x2 = (_x + _w) - _offset,
		y1 = _y - _offset,
		y2 = (_y + _h) - _offset;

	glColor3f(r, g, b);
	glVertex2f(x1, y1);
	glVertex2f(x2, y1);
	glVertex2f(x2, y2);
	glVertex2f(x1, y2);

	glEnd();
}

void GameObject::SetPosition(float pX, float pY) {
	_x = pX;
	_y = pY;
}

void GameObject::SetAngle(float pA) {
	_a = pA;
}

void GameObject::SetColor(Color pColor) {
	_color = pColor;
}

void GameObject::GetPosition(float &pX, float &pY) {
	pX = _x;
	pY = _y;
}

float GameObject::GetAngle() {
	return _a;
}

float GameObject::GetWidth() {
	return _w;
}

float GameObject::GetHeight() {
	return _h;
}