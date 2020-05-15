//-----------------------------------------------------------------------------
// SimpleSprite.h
// Privides a very simple DSOUND wrapper to load and play wav files.

//Code by Mugiesshan Anandarajah
//-----------------------------------------------------------------------------
#ifndef _SIMPLESPRITE_H_
#define _SIMPLESPRITE_H_

#include "../glut/include/GL/freeglut.h"
#include <map>
#include <vector>

struct Color {
	float r, g, b;
};

//Class to create simple bullet object used by both player and enemy
class Bullet {
public:
	Bullet(Color p_color, float p_xpos, float p_ypos, float p_offset, float p_scale, float p_width, float p_height, int p_key, float p_input);
	void Draw();
	void SetPosition(float p_xpos, float p_ypos);
	void SetScale(float p_scale);
	void SetSize(float p_width, float p_height);
	void SetColor(Color p_color);
	void GetPosition(float &p_xpos, float &p_ypos);
	void GetSize(float &p_width, float &p_height);
	float GetScale();

	//Determines if bullet his object
	bool objectHit = false;

	//Regularly updates bullet while it's active
	void Update();

	//Adjusts speed for smaller bullets
	void SetSpeed(float p_speed);

	//Countdown until bullet object is destroyed
	void DelaySelfDestruct(float dt, std::vector<Bullet> &bullets);

	//Alter damage settings
	void SetDamage(int p_damage);
	int GetDamage();

	//Helps determine which enemy the bullet should damage
	int GetEnemyType();

private:
	//Bullet variables
	float m_xpos, m_ypos, m_scale, m_width, m_height, m_offset, m_speed = 10, m_max_time = 2, m_time = 0, m_delimeter = 1000,
		m_incrementer = 1;
	int m_damage = 5;

	//Configures bullet spawn point direction based on player input
	std::map<int, float> m_direction_handler;

	//Bullet color
	Color m_color;
	//Find a way to make bullets shoot diagonal.
};

//Collectables for special bullets
class Pickup {
public:
	Pickup(Color p_color, int p_quantity, int p_bullet_type, float p_xpos, float p_ypos, float p_width, float p_height);
	int GetBulletType();
	int GetBullets();
	void Draw();
	void GetPosition(float &p_xpos, float &p_ypos);
	void GetSize(float &p_width, float &p_height);
	float GetScale();
	int GetDamage();
	Color GetColor();

private:
	Color m_color{ 0.804f, 0.361f, 0.361f };
	float m_xpos, m_ypos, m_width, m_height, m_scale = 2;
	int m_quantity, m_bullet_type;
};

//-----------------------------------------------------------------------------
// CSimpleSprite
//-----------------------------------------------------------------------------
class CSimpleSprite
{
public:
	//My public variables
	bool are_bullets_active = false, freeze_ai_behaviour = false, is_shocked = false;
	int key, damage_val = 5, bullet_count = 0, bullet_size = 0, choice = 0;
	float ai_max_patience = 3, ai_patience = 0;

    // fileName: File must be a 32 bit BMP format (A8 R8 G8 B8). The alpha channel can be used to mask out the sprite.
	// nColumns and nRows defines the number of frames in the sprite page. 
    CSimpleSprite(const char *fileName, int p_health, int p_enemy_type, float p_offset, int p_bullet_type, unsigned int nColumns = 1, unsigned int nRows = 1);
	// Update, call from your game update function.
    void Update(float dt);
	// Draw, call from your game draw function.

	//Override this method. Have the graphics ender code in a different method so we can make overriding it easy.
    void Draw();
	
	// Sets and Gets for the various attributes of the sprite.
	void SetPosition(float x, float y) { m_xpos = x; m_ypos = y; UpdateBulletPoint(); }
    void SetAngle(float a)  { m_angle = a; }
    void SetScale(float s) { m_scale = s; }
    void SetFrame(unsigned int f);
    
	void GetPosition(float &x, float &y) { x = m_xpos; y = m_ypos; }
    float GetWidth()  const { return m_width;  }
    float GetHeight() const { return m_height; }
    float GetAngle()  const { return m_angle;  }
    float GetScale()  const { return m_scale;  }    
	unsigned int GetFrame()  const { return m_frame; }

	// Set the sprite color. r,g,b for color and a= the alpha value (Fade).
	void SetColor(float r, float g, float b, float a = 1.0f) { m_red = r; m_green = g; m_blue = b; m_alpha = a; }

    // Create an animation. 
	// ID, Gives this animation a unique id.
	// speed, Set the movement speed for this sprite.
	// frames, provide a vector of sprite frame indices. Indices into your sprite page.
	void CreateAnimation( unsigned int id, float speed, const std::vector<int> &frames);
	// Set the current animation to play.
	// Note -1 will stop the animation.
	void SetAnimation(int id);

	//------------------------------------------------------------
	// Advanced:
	//------------------------------------------------------------
	// Directly set the local coordinates of the sprite.
	// Use only if you want non uniform sprites. Skewing or perspective can be achieved using this.
	void SetVertex(unsigned int i, float v) { if(i < 8) m_points[i] = v; }
	float GetVertex(unsigned int i) { if (i >= 8) i = 0; return m_points[i]; }
	
	//My  methods

	//Updates bullet point based on sprite's current position
	void UpdateBulletPoint();

	//Return bullet point data
	void GetBulletPoint(float &p_beam_xpos, float &p_beam_ypos);


	void AddBullet(Bullet p_bullet);

	//Creates pointer to reference for bullets vector to efficiently manage bullets
	std::vector<Bullet> *GetBullets();

	//Set bullet size to help create bullet objects
	void SetBulletSize(float p_bullet_w, float p_bullet_h);

	//Get current bullet size
	void GetBulletSize(float &p_bullet_w, float &p_bullet_h);

	//Gets current health
	int GetHealth();

	//Applies damage to sprite
	void TakeDamage(int p_damage);
	void ApplyShock(float dt);

	//Set player's previous input to help configure bullet direction
	void SetInput(float p_input);

	//Get player's previous input
	float GetInput();

	//Collision checks
	bool CollideWith(Bullet &bullet);
	bool CollideWith(Pickup &pickup);
	//Pickup ability variable

	//Altering bullet type
	void SetBulletType(int p_bullet_type);
	int GetBulletType();
	void SetBulletColor(float r, float g, float b);
	Color GetBulletColor();

	//Get type of enemy
	int GetEnemyType();

private:
    void CalculateUVs();
    GLuint m_texture;
    float m_xpos = 0.0F;
    float m_ypos = 0.0F;
    float m_width = 0.0F;
    float m_height = 0.0F;
    unsigned int   m_texWidth = 0;
    unsigned int   m_texHeight = 0;
    float m_angle = 0.0F;
    float m_scale = 1.0F;
    float m_points[8];    
    float m_uvcoords[8];
    unsigned int   m_frame;
    unsigned int   m_nColumns;
    unsigned int   m_nRows;
	float m_red = 1.0f;
	float m_green = 1.0f;
	float m_blue = 1.0f;
	float m_alpha = 1.0f;
    int     m_currentAnim = -1;
    float   m_animTime = 0.0F;

	//My variables
	float m_bullet_point_x, m_bullet_point_y, m_offset, m_bullet_w, m_bullet_h, shock_time = 0, shock_max_time = 5, shock_interval_time = 1;
	int m_bullet_type = 0, m_health = 10;
	Color m_bullet_color{ 0.804f, 0.361f, 0.361f };

	//Bullet manager
	std::vector<Bullet> m_bullets;

	//My variables for player
	float m_input = 1;// To use this variable properly, reference the GetLeftThumbStickY method

	//My variables for enemy
	int m_enemy_type;

    struct sAnimation
    {
        unsigned int m_id;
        float m_speed;
        std::vector<int> m_frames;
    };
    std::vector<sAnimation> m_animations;

    // Texture management.
    struct sTextureDef
    {
        unsigned int m_width;
        unsigned int m_height;
        GLuint m_textureID;
    };
    bool LoadTexture(const char*);
    static std::map<const char *, sTextureDef > m_textures;
    
	/*
		Include in CSimpleSprite
		- Beam point
		- Bullet class

		Player Sprite : Include
		- Beam point
		- Laser type(laser, shockwave)
		- Pickup ability variable that you can either activate or self - activate(Mirror, shockwave bomb, insta - kill)
		- Add size indicator as this will determine which enemies can and can't attack you

		Enemy Sprite : Include
		- Enemy Type : (value types are in - air or grounded).Enemy type cannot attack player when the player is at a certain size

		Create shape class so bullets and pickup can inherit this method
		- width and height
		- bullet type
		- DelaySelfDestrustMethod
		- equal operator for collision detection
		- Damage value

		Create a bullet class : Inherit from shape class

		Create a pickup management class : Inherit from shape class
		*/
};

//A call with a static delay timer to timely destroy bullet objects
class SelfDestruct{
public:
	static void Delay(float dt, std::vector<Bullet> &bullets) {
		time += dt;

		if (time > max_time) {
			bullets.erase(bullets.begin());
			time = 0;
		}
	}

private:
	static float time, max_time;
};

#endif