///////////////////////////////////////////////////////////////////////////////
// Filename: SimpleSprite.cpp
// Privides a very simple DSOUND wrapper to load and play wav files.
///////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------
#include "stdafx.h"
//-----------------------------------------------------------------------------
#include <windows.h>
#include <stdio.h>
#include <assert.h>

//-----------------------------------------------------------------------------

#include "app.h"
#include "AppSettings.h"
#include "TextureLoader.h"
#include "SimpleSprite.h"

#include "../glut/include/GL/freeglut_ext.h"

std::map<const char *, CSimpleSprite::sTextureDef > CSimpleSprite::m_textures;

//-----------------------------------------------------------------------------
CSimpleSprite::CSimpleSprite(const char *fileName, int p_health, int p_enemy_type, float p_offset, int p_bullet_type, unsigned int nColumns, unsigned int nRows)
	: m_nColumns(nColumns)
	, m_nRows(nRows)
{
	if (LoadTexture(fileName))
	{
		CalculateUVs();
		m_points[0] = -(m_width / 2.0f);
		m_points[1] = -(m_height / 2.0f);
		m_points[2] = m_width / 2.0f;
		m_points[3] = -(m_height / 2.0f);
		m_points[4] = m_width / 2.0f;
		m_points[5] = m_height / 2.0f;
		m_points[6] = -(m_width / 2.0f);
		m_points[7] = m_height / 2.0f;

		//Initializing my variables
		m_health = p_health;
		m_enemy_type = p_enemy_type;
		m_offset = p_offset;
		m_bullet_type = p_bullet_type;
	}
}

void CSimpleSprite::Update(float dt)
{
    if (m_currentAnim >= 0)
    {
        m_animTime += dt/1000.0f;
        sAnimation &anim = m_animations[m_currentAnim];
        float duration = anim.m_speed * anim.m_frames.size();
        if (m_animTime > duration)
        {
            m_animTime = m_animTime - duration;
        }
        int frame = (int)( m_animTime / anim.m_speed );
		frame %= anim.m_frames.size();
        SetFrame(anim.m_frames[frame]);        
    }
}

void CSimpleSprite::CalculateUVs()
{
    float u = 1.0f / m_nColumns;
    float v = 1.0f / m_nRows;
    int row = m_frame / m_nColumns;
    int column = m_frame % m_nColumns;

    m_width = m_texWidth * u;
    m_height = m_texHeight * v;
    m_uvcoords[0] = u * column;
    m_uvcoords[1] = v * (float)(row+1);

    m_uvcoords[2] = u * (float)(column+1);
    m_uvcoords[3] = v * (float)(row + 1);

    m_uvcoords[4] = u * (float)(column + 1);
    m_uvcoords[5] = v * row;

    m_uvcoords[6] = u * column;
    m_uvcoords[7] = v * row;
}

void CSimpleSprite::Draw()
{            
#if APP_USE_VIRTUAL_RES
    float scalex = m_scale / APP_VIRTUAL_WIDTH;
    float scaley = m_scale / APP_VIRTUAL_HEIGHT;
#else
    float scalex = m_scale;
    float scaley = m_scale;
#endif
    float x = m_xpos;
    float y = m_ypos;
#if APP_USE_VIRTUAL_RES
    APP_VIRTUAL_TO_NATIVE_COORDS(x, y);
#endif
    
    glPushMatrix();
    glTranslatef(x, y, 0.0f);   
    glScalef(scalex, scaley, 0.1f);    
    glRotatef(m_angle * 180 / PI, 0.0f, 0.0f, 1.0f);     
	glColor4f(m_red, m_green, m_blue, m_alpha);
    glEnable(GL_BLEND);    
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);    
    glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, m_texture);

    glBegin(GL_QUADS);       
    for (unsigned int i = 0; i < 8; i += 2)
    {
        glTexCoord2f(m_uvcoords[i], m_uvcoords[i + 1]);
        glVertex2f(m_points[i], m_points[i+1]);
    }

	glEnd();
    glPopMatrix();
    glDisable(GL_BLEND);
    glDisable(GL_TEXTURE_2D);
}

void CSimpleSprite::SetFrame(unsigned int f)
{
    m_frame = f;
    if (m_frame > m_nRows*m_nColumns)
    {
        m_frame = 0;
    }
    CalculateUVs();
}

void CSimpleSprite::SetAnimation(int id)
{
    for (int i = 0; i < m_animations.size(); i++)
    {
        if (m_animations[i].m_id == id)
        {
            m_currentAnim = i;
            return;
        }
    }
	m_currentAnim = -1;
}

void CSimpleSprite::CreateAnimation(unsigned int id, float speed, const std::vector<int> &frames)
{
	sAnimation anim;
	anim.m_id = id;
	anim.m_speed = speed;
	anim.m_frames = frames;
	m_animations.push_back(anim);
}

bool CSimpleSprite::LoadTexture(const char * filename)
{
    if (m_textures.find(filename) != m_textures.end())
    {        
        sTextureDef &texDef = m_textures[filename];
        m_texture = texDef.m_textureID;
        m_texWidth = texDef.m_width;
        m_texHeight = texDef.m_height;
		return true;
    }
    
    unsigned char *imageData = loadBMPRaw(filename, m_texWidth, m_texHeight, true);

    GLuint texture = 0;
	if (imageData)
	{
		glGenTextures(1, &texture);
		glBindTexture(GL_TEXTURE_2D, texture);
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		gluBuild2DMipmaps(GL_TEXTURE_2D, 4, m_texWidth, m_texHeight, GL_RGBA, GL_UNSIGNED_BYTE, imageData);
		free(imageData);
		sTextureDef textureDef = { m_texWidth, m_texHeight, texture };
		m_textures[filename] = textureDef;
		m_texture = texture;
		return true;
	}
	return false;
}

void CSimpleSprite::UpdateBulletPoint() {
	m_bullet_point_x = m_xpos;
	m_bullet_point_y = m_ypos;

	/*m_bullet_point_x += (m_offset + 0.05f);
	m_bullet_point_y += (m_offset + 0.05f);*/
}

void CSimpleSprite::GetBulletPoint(float &p_beam_xpos, float &p_beam_ypos) {
	p_beam_xpos = m_bullet_point_x;
	p_beam_ypos = m_bullet_point_y;
}

void CSimpleSprite::AddBullet(Bullet p_bullet) {
	m_bullets.push_back(p_bullet);
}

std::vector<Bullet> *CSimpleSprite::GetBullets() {
	return &m_bullets;
}

void CSimpleSprite::SetBulletType(int p_bullet_type) {
	m_bullet_type = p_bullet_type;
}

int CSimpleSprite::GetBulletType() {
	return m_bullet_type;
}

void CSimpleSprite::SetInput(float p_input) {
	m_input = p_input;
}

float CSimpleSprite::GetInput() {
	return m_input;
}

bool CSimpleSprite::CollideWith(Bullet &bullet) {
	for (int i = 0; i < 8; i += 2) {
		float xpos = m_xpos, ypos = m_ypos, xpos2 = xpos + m_width, ypos2 = ypos + m_height, scalex, scaley;
		float px, py, px2, py2, p_scale;
		bullet.GetPosition(px, py);
		bullet.GetSize(px2, py2);
		p_scale = bullet.GetScale() / 100;

#if APP_USE_VIRTUAL_RES
		scalex = m_scale / APP_VIRTUAL_WIDTH;
		scaley = m_scale / APP_VIRTUAL_HEIGHT;
#else
		scalex = m_scale;
		scaley = m_scale;
#endif

#if APP_USE_VIRTUAL_RES
		APP_VIRTUAL_TO_NATIVE_COORDS(xpos, ypos);
		APP_VIRTUAL_TO_NATIVE_COORDS(xpos2, ypos2);
		APP_VIRTUAL_TO_NATIVE_COORDS(px, py);
		APP_VIRTUAL_TO_NATIVE_COORDS(px2, py2);
#endif

		xpos2 += scalex;
		ypos2 += scaley;
		px2 += p_scale;
		py2 += p_scale;

		//bool collision1 = (px >= xpos && px <= xpos2) || (px2 >= xpos && px2 <= xpos2) || (xpos >= px && xpos <= px2) || (xpos2 >= px && xpos <= px2);
		bool collisionX = xpos + xpos2 >= px && px + px2 >= xpos;

		//bool collision2 = (py >= ypos && py <= ypos2) || (py2 >= ypos && py2 <= ypos2) || (ypos >= py && ypos2 <= py2) || (ypos2 >= py && ypos <= py2);
		bool collisionY = ypos + ypos2 >= py && py + py2 >= ypos;

		return collisionX && collisionY;
	}
}

bool CSimpleSprite::CollideWith(Pickup &pickup) {
	for (int i = 0; i < 8; i += 2) {
		float xpos = m_xpos, ypos = m_ypos, xpos2 = xpos + m_width, ypos2 = ypos + m_height, scalex, scaley;
		float px, py, px2, py2, p_scale;
		pickup.GetPosition(px, py);
		pickup.GetSize(px2, py2);
		p_scale = pickup.GetScale() / 100;

#if APP_USE_VIRTUAL_RES
		scalex = m_scale / APP_VIRTUAL_WIDTH;
		scaley = m_scale / APP_VIRTUAL_HEIGHT;
#else
		scalex = m_scale;
		scaley = m_scale;
#endif

#if APP_USE_VIRTUAL_RES
		APP_VIRTUAL_TO_NATIVE_COORDS(xpos, ypos);
		APP_VIRTUAL_TO_NATIVE_COORDS(xpos2, ypos2);
		APP_VIRTUAL_TO_NATIVE_COORDS(px, py);
		APP_VIRTUAL_TO_NATIVE_COORDS(px2, py2);
#endif

		xpos2 += scalex;
		ypos2 += scaley;
		px2 += p_scale;
		py2 += p_scale;

		//bool collision1 = (px >= xpos && px <= xpos2) || (px2 >= xpos && px2 <= xpos2) || (xpos >= px && xpos <= px2) || (xpos2 >= px && xpos <= px2);
		bool collisionX = xpos + xpos2 >= px && px + px2 >= xpos;

		//bool collision2 = (py >= ypos && py <= ypos2) || (py2 >= ypos && py2 <= ypos2) || (ypos >= py && ypos2 <= py2) || (ypos2 >= py && ypos <= py2);
		bool collisionY = ypos + ypos2 >= py && py + py2 >= ypos;

		return collisionX && collisionY;
	}
}

void CSimpleSprite::SetBulletSize(float p_bullet_w, float p_bullet_h) {
	m_bullet_w = p_bullet_w;
	m_bullet_h = p_bullet_h;
}

void CSimpleSprite::SetBulletColor(float r, float g, float b) {
	m_bullet_color.r = r;
	m_bullet_color.g = g;
	m_bullet_color.b = b;
}

Color CSimpleSprite::GetBulletColor() {
	return m_bullet_color;
}

void CSimpleSprite::GetBulletSize(float &p_bullet_w, float &p_bullet_h) {
	p_bullet_w = m_bullet_w;
	p_bullet_h = m_bullet_h;
}

int CSimpleSprite::GetHealth() {
	return m_health;
}

void CSimpleSprite::TakeDamage(int p_damage) {
	m_health -= p_damage;
}

int CSimpleSprite::GetEnemyType() {
	return m_enemy_type;
}

void CSimpleSprite::ApplyShock(float dt) {
	shock_time += dt;

	if (shock_time > shock_max_time) {
		shock_time = 0;
		shock_interval_time = 1;
		is_shocked = false;
	}

	else if (shock_time > shock_interval_time) {
		m_health -= 2;
		++shock_interval_time;
	}
}

Bullet::Bullet(Color p_color, float p_xpos, float p_ypos, float p_offset, float p_scale, float p_width, float p_height, int p_key, float p_input) {
	m_color = p_color;
	m_xpos = p_xpos;
	m_ypos = p_ypos;
	m_offset = p_offset;
	m_scale = p_scale;
	m_width = p_width;
	m_height = p_height;
	m_direction_handler[p_key] = p_input;

	if (p_key == 1) {
		m_direction_handler[0] = 0.5f;
	}
	else
		m_direction_handler[1] = 0.5f;
}

void Bullet::Draw() {
	float x = m_xpos, y = m_ypos, width = m_width, height = m_height;

#if APP_USE_VIRTUAL_RES
	APP_VIRTUAL_TO_NATIVE_COORDS(x, y);
	APP_VIRTUAL_TO_NATIVE_COORDS(width, height);
#endif

	glPushMatrix();
	glTranslatef(x, y, 0);
	glScalef(m_scale, m_scale, 0);

	glBegin(GL_POLYGON);

	float r = m_color.r, g = m_color.g, b = m_color.b;

	glColor3f(r, g, b);
	glVertex2f(0, 0);
	glVertex2f(width, 0);
	glVertex2f(width, height);
	glVertex2f(0, height);

	glEnd();

	glPopMatrix();
}

void Bullet::SetPosition(float p_xpos, float p_ypos) {
	m_xpos = p_xpos;
	m_ypos = p_ypos;
}

void Bullet::SetScale(float p_scale) {
	m_scale = p_scale;
}

void Bullet::SetSize(float p_width, float p_height) {
	m_width = p_width;
	m_height = p_height;
}

void Bullet::SetColor(Color p_color) {
	m_color = p_color;
}

void Bullet::GetPosition(float &p_xpos, float &p_ypos) {
	p_xpos = m_xpos;
	p_ypos = m_ypos;
}

float Bullet::GetScale() {
	return m_scale;
}

void Bullet::GetSize(float &p_width, float &p_height) {
	p_width = m_width;
	p_height = m_height;
}

void Bullet::Update() {
	if (m_direction_handler[0] > 0.5f)
		m_ypos += m_incrementer * m_speed;
	else if (m_direction_handler[0] < 0.5f)
		m_ypos -= m_incrementer * m_speed;
	if (m_direction_handler[1] > 0.5f)
		m_xpos += m_incrementer * m_speed;
	else if (m_direction_handler[1] < 0.5f)
		m_xpos -= m_incrementer * m_speed;
}

void Bullet::SetDamage(int p_damage) {
	m_damage = p_damage;
}

int Bullet::GetDamage() {
	return m_damage;
}

void Bullet::SetSpeed(float p_speed) {
	m_speed = p_speed;
}

void Bullet::DelaySelfDestruct(float dt, std::vector<Bullet> &bullets) {
	m_time += dt;

	if (m_time > m_max_time) {
		bullets.erase(bullets.begin());
		//delete this;
	}
}

int Bullet::GetEnemyType() {
	return m_scale <= 2 ? 0 : 1;
}

Pickup::Pickup(Color p_color, int p_quantity, int p_bullet_type, float p_xpos, float p_ypos, float p_width, float p_height) {
	m_color = p_color;
	m_quantity = p_quantity;
	m_bullet_type = p_bullet_type;
	m_xpos = p_xpos;
	m_ypos = p_ypos;
	m_width = p_width;
	m_height = p_height;
}

void Pickup::Draw() {
	float x = m_xpos, y = m_ypos, width = m_width, height = m_height;

#if APP_USE_VIRTUAL_RES
	APP_VIRTUAL_TO_NATIVE_COORDS(x, y);
	APP_VIRTUAL_TO_NATIVE_COORDS(width, height);
#endif

	glPushMatrix();
	glTranslatef(x, y, 0);
	glScalef(m_scale, m_scale, 0);

	glBegin(GL_POLYGON);

	float r = m_color.r, g = m_color.g, b = m_color.b;

	glColor3f(r, g, b);
	glVertex2f(0, 0);
	glVertex2f(width, 0);
	glVertex2f(width, height);
	glVertex2f(0, height);

	glEnd();

	glPopMatrix();
}

int Pickup::GetBulletType() {
	return m_bullet_type;
}

int Pickup::GetBullets() {
	return m_quantity;
}

void Pickup::GetPosition(float &p_xpos, float &p_ypos) {
	p_xpos = m_xpos;
	p_ypos = m_ypos;
}

void Pickup::GetSize(float &p_width, float &p_height) {
	p_width = m_width;
	p_height = m_height;
}

float Pickup::GetScale() {
	return m_scale;
}

Color Pickup::GetColor() {
	return m_color;
}

int Pickup::GetDamage() {
	return m_bullet_type == 1 ? 2 : INT_MAX;
}

float SelfDestruct::time = 0;
float SelfDestruct::max_time = 2;