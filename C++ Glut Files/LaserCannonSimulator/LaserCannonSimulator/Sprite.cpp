#include "Sprite.h"
#include <stdio.h>
#include <corecrt_malloc.h>

/*Sprite::Sprite(const char *pFileName, unsigned int pCol, unsigned int pRow) {
	unsigned char header[54];
	unsigned int dataPos, imageSize;
	FILE* file = fopen(pFileName, "rb");

	if (!file) {
		printf("File not found\n");
		return;
	}

	if (fread(header, 1, 54, file) != 54) {
		printf("Not a valid BMP file\n");
		return;
	}
	
	/*if (header[0] != 'B' || header[1] != 'M') {
		printf("Not a correct BMP file\n");
		return;
	}

	dataPos = *(int*)&(header[0x0A]);
	imageSize = *(int*)&(header[0x22]);
	imageSize *= 8;
	_texWidth = *(int*)&(header[0x12]);
	_texHeight = *(int*)&(header[0x16]);

	imageSize = _texWidth * _texHeight * 4;

	if (dataPos == 0)
		dataPos = 54;

	unsigned char* data = new unsigned char[imageSize];

	fseek(file, dataPos, 0);
	fread(data, 1, imageSize, file);
	fclose(file);
	unsigned char* colorData = data;

	for (unsigned int i = 0; i < imageSize; i += 4) {
		unsigned char b = colorData[0];
		unsigned char g = colorData[1];
		unsigned char r = colorData[2];
		unsigned char a = colorData[3];
		colorData[0] = r;
		colorData[1] = g;
		colorData[2] = b;
		colorData[3] = a;
		colorData += 4;
	}

	if (data) {
		GLuint texture;
		glGenTextures(1, &texture);
		glBindTexture(GL_TEXTURE_2D, texture);
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		gluBuild2DMipmaps(GL_TEXTURE_2D, 4, _texWidth, _texHeight, GL_RGBA, GL_UNSIGNED_BYTE, data);
		free(data);
		_sprite = texture;

		CalculateUVs();
		_points[0] = -(_w / 2.0f);
		_points[1] = -(_h / 2.0f);
		_points[2] = _w / 2.0f;
		_points[3] = -(_h / 2.0f);
		_points[4] = _w / 2.0f;
		_points[5] = _h / 2.0f;
		_points[6] = -(_w / 2.0f);
		_points[7] = _h / 2.0f;
	}
}

void Sprite::CalculateUVs()
{
	float u = 1.0f / _col;
	float v = 1.0f / _row;
	int row = _f / _col;
	int column = _f % _col;

	_w = _texWidth * u;
	_h = _texHeight * v;
	_uvCoords[0] = u * column;
	_uvCoords[1] = v * (float)(row + 1);

	_uvCoords[2] = u * (float)(column + 1);
	_uvCoords[3] = v * (float)(row + 1);

	_uvCoords[4] = u * (float)(column + 1);
	_uvCoords[5] = v * row;

	_uvCoords[6] = u * column;
	_uvCoords[7] = v * row;
}

void Sprite::Draw() {
	glPushMatrix();
	glTranslatef(_x, _y, 0);
	glRotatef(_a, 0, 0, 0);
	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_DST_ALPHA);
	glEnable(GL_TEXTURE_2D);
	glBindTexture(GL_TEXTURE_2D, _sprite);
	glBegin(GL_QUADS);
	
	for (unsigned int i = 0; i < 8; i += 2) {
		glTexCoord2f(_uvCoords[i], _uvCoords[i + 1]);
		glVertex2f(_points[i], _points[i]);
	}
	glEnd();
	glPopMatrix();
	glDisable(GL_BLEND);
	glDisable(GL_TEXTURE_2D);
}

void Sprite::SetPosition(float pX, float pY) {
	_x = pX;
	_y = pY;
}

void Sprite::SetAngle(float pA) {
	_a = pA;
}

void Sprite::SetFrame(unsigned int pF) {
	_f = pF;
}

void Sprite::GetPosition(float &pX, float &pY) {
	pX = _x;
	pY = _y;
}

float Sprite::GetWidth() {
	return _w;
}

float Sprite::GetHeight() {
	return _h;
}

float Sprite::GetAngle() {
	return _a;
}

unsigned int Sprite::GetFrame() {
	return _f;
}*/

GameObject::GameObject() {
}

GameObject::GameObject(float pX, float pY, float pScale, float pA, float pW, float pH, Color pColor, float pOffset) {
	_x = pX;
	_y = pY;
	_scale = pScale;
	_a = pA;
	_w = pW;
	_h = pH;
	_color = pColor;
	_offset = pOffset;
}

GameObject::~GameObject() {
	
}

void GameObject::Draw() {
	glPushMatrix();
	glScalef(_scale, _scale, 0);
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

	glPopMatrix();
}

void GameObject::SetPosition(float pX, float pY) {
	_x = pX;
	_y = pY;
}

void GameObject::SetScale(float scale) {
	_scale = scale;
}

void GameObject::SetAngle(float pA) {
	_a = pA;
}

void GameObject::SetSize(float pW, float pH) {
	_w = pW;
	_h = pH;
}

void GameObject::SetColor(Color pColor) {
	_color = pColor;
}

void GameObject::GetPosition(float &pX, float &pY) {
	pX = _x;
	pY = _y;
}

float GameObject::GetScale() {
	return _scale;
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

ControlObject::ControlObject() {

}

ControlObject::ControlObject(float pX, float pY, float pScale, float pA, float pW, float pH, Color pColor, float pOffset) 
	: GameObject (pX, pY, pScale, pA, pW, pH, pColor, pOffset) {
	UpdateBeamPoint();
}

void ControlObject::UpdateBeamPoint() {
	_beamPointX = (_x + _w) - (_offset - 0.05f);
	_beamPointY = (_y + _h) - (_offset - 0.05f);
}

void ControlObject::GetBeamPoint(float &pX, float &pY) {
	pX = _beamPointX;
	pY = _beamPointY;
}

void ControlObject::SetInput(int pInput) {
	_input = pInput;
}

int ControlObject::GetInput() {
	return _input;
}

Beam::Beam() {

}

Beam::Beam(float pX, float pY, float pScale, float pA, float pW, float pH, Color pColor, float pOffset, float pMaxTime, int pInput)
	: GameObject(pX, pY, pScale, pA, pW, pH, pColor, pOffset) {
	_maxTime = pMaxTime;
	_direction = pInput;
}

void Beam::Update() {
	switch (_direction) {
	case GLUT_KEY_UP:
		_y += 0.05f * _speed;
		break;
	case GLUT_KEY_DOWN:
		_y -= 0.05f * _speed;
		break;
	case GLUT_KEY_RIGHT:
		_x += 0.05f * _speed;
		break;
	case GLUT_KEY_LEFT:
		_x -= 0.05f * _speed;
		break;
	}
}

void Beam::SetSpeed(float pSpeed) {
	_speed = pSpeed;
}

void Beam::DelaySelfDestruct(float deltaTime, vector<Beam> &beams) {
	_timer += deltaTime;

	if (_timer > _maxTime) {
		beams.erase(beams.begin());
		delete this;
	}
}