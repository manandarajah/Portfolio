//------------------------------------------------------------------------
// GameTest.cpp
//------------------------------------------------------------------------
#include "stdafx.h"
//------------------------------------------------------------------------
#include <windows.h> 
#include <math.h>
#include <string>
//------------------------------------------------------------------------
#include "app\app.h"
//------------------------------------------------------------------------

//------------------------------------------------------------------------
// Eample data....
//------------------------------------------------------------------------
CSimpleSprite *testSprite;
CSimpleSprite *testSprite2;

//Pickup settings
Pickup *pickup = nullptr;
float pickup_time = 0, pickup_max_time = 10;
bool freeze_pickup_time = false;

//Generate ships setting
float generate_ships_time = 0, generate_ships_max_time = 20;
bool has_pickup = false;
Color standard_color{ 0.804f, 0.361f, 0.361f };

using namespace std;

//Manage enemy sprites
vector<CSimpleSprite> ships;

//Ship spawn point
struct SpawnPoint {
	float x, y;
};

SpawnPoint spawn_points[4];

enum
{
	ANIM_FORWARDS,
	ANIM_BACKWARDS,
	ANIM_LEFT,
	ANIM_RIGHT,
};

//My variables

//Bullet settings
GLuint display;

//My defined methods
void Shoot(CSimpleSprite &sprite);
void ConfigureBulletSettings(CSimpleSprite &sprite, int p_key, float p_input);
void AI_Behaviour(float dt, vector<CSimpleSprite> &ships);
bool CollidingShips(vector<CSimpleSprite> &ships, vector<Bullet> &bullets, vector<Bullet>::iterator &bullet);
void GenerateShips(vector<CSimpleSprite> &ships);

//Utilize display list to display bullets
void DisplayList();
//------------------------------------------------------------------------

//------------------------------------------------------------------------
// Called before first update. Do any initial setup here.
//------------------------------------------------------------------------
void Init()
{
	//------------------------------------------------------------------------
	// Example Sprite Code....
	
	//Instantiate ship spawn point
	spawn_points[0] = { 523, 17 };
	spawn_points[1] = { 523, 754 };
	spawn_points[2] = { -2, 380 };
	spawn_points[3] = { 1022, 380 };

	testSprite = App::CreateSprite(".\\TestData\\Test.bmp", 100, -1, 0.3f, 0, 8, 4);
	testSprite->SetPosition(500.0f, 400.0f);
	float speed = 1.0f / 15.0f;
	testSprite->CreateAnimation(ANIM_BACKWARDS, speed, { 0,1,2,3,4,5,6,7 });
	testSprite->CreateAnimation(ANIM_LEFT, speed, { 8,9,10,11,12,13,14,15 });
	testSprite->CreateAnimation(ANIM_RIGHT, speed, { 16,17,18,19,20,21,22,23 });
	testSprite->CreateAnimation(ANIM_FORWARDS, speed, { 24,25,26,27,28,29,30,31 });
	testSprite->SetScale(2.0f);
	ConfigureBulletSettings(*testSprite, 0, -1);

	for (int i = 0; i < 4; ++i) {
		float scale = 1;
		int enemy_type = 0;

		enemy_type = scale <= 2 ? 0 : 1;

		CSimpleSprite *sprite = App::CreateSprite(".\\TestData\\Ships.bmp", 30, enemy_type, 0.3f, 0, 2, 12);
		sprite->SetPosition(spawn_points[i].x, spawn_points[i].y);
		sprite->SetFrame(2);
		sprite->SetScale(scale);
		ships.push_back(*sprite);
	}

	/*testSprite2 = App::CreateSprite(".\\TestData\\Ships.bmp", 0, 0.3f, 0, 2, 12);
	testSprite2->SetPosition(200.0f, 400.0f);	
	testSprite2->SetFrame(2);
	testSprite2->SetScale(1.0f);*/
	//------------------------------------------------------------------------

	DisplayList();
}

//------------------------------------------------------------------------
// Update your simulation here. deltaTime is the elapsed time since the last update in ms.
// This will be called at no greater frequency than the value of APP_MAX_FRAME_RATE
//------------------------------------------------------------------------
void Update(float deltaTime)
{
	//------------------------------------------------------------------------
	// Example Sprite Code....
	if (testSprite != nullptr) {

		testSprite->Update(deltaTime);

		generate_ships_time += deltaTime / 1000;

		if (generate_ships_time > generate_ships_max_time) {
			generate_ships_time = 0;
			GenerateShips(ships);
		}

		if (!ships.empty())
			AI_Behaviour(deltaTime / 1000, ships);
		else
			GenerateShips(ships);

		if (App::GetController().GetLeftThumbStickX() > 0.5f)
		{
			testSprite->SetAnimation(ANIM_RIGHT);
			float x, y;
			testSprite->GetPosition(x, y);
			x += 1.0f;
			testSprite->SetPosition(x, y);

			//My code
			ConfigureBulletSettings(*testSprite, 1, App::GetController().GetLeftThumbStickX());
		}
		if (App::GetController().GetLeftThumbStickX() < -0.5f)
		{
			testSprite->SetAnimation(ANIM_LEFT);
			float x, y;
			testSprite->GetPosition(x, y);
			x -= 1.0f;
			testSprite->SetPosition(x, y);

			//My code
			ConfigureBulletSettings(*testSprite, 1, App::GetController().GetLeftThumbStickX());
		}
		if (App::GetController().GetLeftThumbStickY() > 0.5f)
		{
			testSprite->SetAnimation(ANIM_FORWARDS);
			float x, y;
			testSprite->GetPosition(x, y);
			y += 1.0f;
			testSprite->SetPosition(x, y);

			//My code
			ConfigureBulletSettings(*testSprite, 0, App::GetController().GetLeftThumbStickY());
		}
		if (App::GetController().GetLeftThumbStickY() < -0.5f)
		{
			testSprite->SetAnimation(ANIM_BACKWARDS);
			float x, y;
			testSprite->GetPosition(x, y);
			y -= 1.0f;
			testSprite->SetPosition(x, y);

			//My code
			ConfigureBulletSettings(*testSprite, 0, App::GetController().GetLeftThumbStickY());
		}
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_DPAD_UP, false))
		{
			testSprite->SetScale(testSprite->GetScale() + 0.1f);
		}
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_DPAD_DOWN, false))
		{
			testSprite->SetScale(testSprite->GetScale() - 0.1f);
		}
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_DPAD_LEFT, false))
		{
			testSprite->SetAngle(testSprite->GetAngle() + 0.1f);
		}
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_DPAD_RIGHT, false))
		{
			testSprite->SetAngle(testSprite->GetAngle() - 0.1f);
		}
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_A, true))
		{
			testSprite->SetAnimation(-1);
		}
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_B, true))
		{
			testSprite->SetVertex(0, testSprite->GetVertex(0) + 5.0f);
		}
		//------------------------------------------------------------------------
		// Sample Sound.
		//------------------------------------------------------------------------
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_B, true))
		{
			App::PlaySound(".\\TestData\\Test.wav");
		}

		//Make use of the X and Y buttons here

		//Shoot bullets
		if (App::GetController().CheckButton(XINPUT_GAMEPAD_X, true)) {
			int tmp = testSprite->GetBulletType();
			Color tmpC = testSprite->GetBulletColor();

			if (has_pickup) {
				testSprite->SetBulletType(0);
				testSprite->SetBulletColor(standard_color.r, standard_color.g, standard_color.b);
				ConfigureBulletSettings(*testSprite, testSprite->key, testSprite->GetInput());
			}

			Shoot(*testSprite);

			if (has_pickup) {
				testSprite->SetBulletType(tmp);
				testSprite->SetBulletColor(tmpC.r, tmpC.g, tmpC.b);
				ConfigureBulletSettings(*testSprite, testSprite->key, testSprite->GetInput());
			}
		}

		if (App::GetController().CheckButton(XINPUT_GAMEPAD_Y, true)) {
			if (has_pickup)
				Shoot(*testSprite);
		}

		if (pickup_time > pickup_max_time && pickup == nullptr) {
			freeze_pickup_time = true;
			pickup_time = 0;
			int bullet_type = rand() % 50 + 1;

			if (bullet_type % 10 == 0)
				bullet_type = 2;
			else
				bullet_type = 1;

			float x = APP_VIRTUAL_WIDTH / 2, y = APP_VIRTUAL_HEIGHT / 2;
			float width = 0.03f, height = 0.06f;

#if APP_USE_VIRTUAL_RES
			APP_NATIVE_TO_VIRTUAL_COORDS(width, height);
#endif

			if (bullet_type == 2) {
				Color color{ 0.255f, 0.412f, 0.882f };
				pickup = new Pickup(color, 3, bullet_type, x, y, width, height);
			}

			else {
				Color color{ 0.827f, 0.827f, 0.827f };
				pickup = new Pickup(color, 8, bullet_type, x, y, width, height);
			}
		}

		else if (!freeze_pickup_time)
			pickup_time += deltaTime / 1000;

		//Collect pickup if player collides
		if (pickup != nullptr) {
			if (testSprite->CollideWith(*pickup)) {
				testSprite->damage_val = pickup->GetDamage();
				testSprite->SetBulletColor(pickup->GetColor().r, pickup->GetColor().g, pickup->GetColor().b);
				testSprite->SetBulletType(pickup->GetBulletType());
				testSprite->bullet_count = pickup->GetBullets();
				testSprite->bullet_size = pickup->GetBullets();
				has_pickup = true;

				pickup = nullptr;
			}
		}

		if (testSprite->are_bullets_active) {
			vector<Bullet> *bullets = testSprite->GetBullets();

			for (vector<Bullet>::iterator it = bullets->begin(); it != bullets->end(); ++it) {
				it->Update();

				if (CollidingShips(ships, *bullets, it))
					break;
			}

			SelfDestruct::Delay(deltaTime / 500, *bullets);

			if (bullets->empty())
				testSprite->are_bullets_active = false;
		}

		for (vector<CSimpleSprite>::iterator it = ships.begin(); it != ships.end(); ++it) {
			if (it->are_bullets_active) {
				vector<Bullet> *bullets = it->GetBullets();

				for (vector<Bullet>::iterator it2 = bullets->begin(); it2 != bullets->end(); ++it2) {
					it2->Update();

					if (testSprite != nullptr) {
						if (testSprite->GetHealth() > 0) {

							if (testSprite->CollideWith(*it2)) {
								testSprite->TakeDamage(it2->GetDamage());
							}
						}

						else
							testSprite = nullptr;
					}
				}

				SelfDestruct::Delay(deltaTime / 500, *bullets);

				if (bullets->empty())
					it->are_bullets_active = false;
			}
		}
	}

	if (App::GetController().CheckButton(XINPUT_GAMEPAD_BACK, true)) {
		exit(0);
	}

	DisplayList();
}

void GenerateShips(vector<CSimpleSprite> &ships) {
	int n = rand() % 3 + 1;
	int prev_index = -1, same_counter = 0;

	for (int i = 0; i < n; ++i) {
		int ind = rand() % 4;
		float scale = 1;
		scale = rand() % 4 + 1;
		int enemy_type = 0;

		enemy_type = scale <= 2 ? 0 : 1;

		while (prev_index == ind && same_counter != 50) {
			ind = rand() % 4;
			++same_counter;
		}
		prev_index = ind;
		same_counter = 0;

		CSimpleSprite *sprite = App::CreateSprite(".\\TestData\\Ships.bmp", 30, enemy_type, 0.3f, 0, 2, 12);
		sprite->SetPosition(spawn_points[ind].x, spawn_points[ind].y);
		sprite->SetFrame(2);
		sprite->SetScale(scale);
		ships.push_back(*sprite);
	}
}

void ConfigureBulletSettings(CSimpleSprite &sprite, int p_key, float p_input) {
	float bullet_w, bullet_h;

	switch (sprite.GetBulletType()) {
	case 0:
		if (p_key == 1) {
			if (p_input > 0.5f) {
				bullet_w = 0.2f;
				bullet_h = 0.01f;
			}

			else if (p_input < -0.5f) {
				bullet_w = -0.2f;
				bullet_h = 0.01f;
			}
		}

		else if (p_key == 0) {
			if (p_input > 0.5f) {
				bullet_w = 0.01f;
				bullet_h = 0.2f;
			}

			else if (p_input < -0.5f) {
				bullet_w = 0.01f;
				bullet_h = -0.2f;
			}
		}

		sprite.damage_val = 5;
		sprite.SetBulletColor(0.804f, 0.361f, 0.361f);
		break;
	case 1:
		bullet_w = 0.03f;
		bullet_h = 0.03f;

		break;
	case 2:

		if (p_key == 1) {
			if (p_input > 0.5f) {
				bullet_w = 0.5f;
				bullet_h = 0.01f;
			}

			else if (p_input < -0.5f) {
				bullet_w = -0.5f;
				bullet_h = 0.01f;
			}
		}

		else if (p_key == 0) {
			if (p_input > 0.5f) {
				bullet_w = 0.01f;
				bullet_h = 0.5f;
			}

			else if (p_input < -0.5f) {
				bullet_w = 0.01f;
				bullet_h = -0.5f;
			}
		}

		break;
	}

#if APP_USE_VIRTUAL_RES
	APP_NATIVE_TO_VIRTUAL_COORDS(bullet_w, bullet_h);
#endif

	sprite.SetInput(p_input);
	sprite.SetBulletSize(bullet_w, bullet_h);
	sprite.key = p_key;
}

void Shoot(CSimpleSprite &sprite) {
	sprite.are_bullets_active = true;

	if (sprite.GetBulletType() != 0)
		--sprite.bullet_count;

	float x, y, bullet_w, bullet_h;
	sprite.GetBulletPoint(x, y);
	sprite.GetBulletSize(bullet_w, bullet_h);

	Bullet bullet(sprite.GetBulletColor(), x, y, 0.3f, sprite.GetScale(), bullet_w, bullet_h, sprite.key, sprite.GetInput());
	bullet.SetDamage(sprite.damage_val);

	if (bullet.GetScale() < 1)
		bullet.SetSpeed(2);

	//testSprite->AddBullet(bullet);
	sprite.GetBullets()->push_back(bullet);

	if (sprite.bullet_count == 0 && sprite.bullet_size > 0) {
		sprite.SetBulletType(0);
		sprite.bullet_size = 0;
		freeze_pickup_time = false;
		has_pickup = false;
	}
}

void AI_Behaviour(float dt, vector<CSimpleSprite> &ships) {
	for (vector<CSimpleSprite>::iterator it = ships.begin(); it != ships.end(); ++it) {
		if (!it->is_shocked) {
			float x, y;
			it->GetPosition(x, y);
			float player_x, player_y;
			testSprite->GetPosition(player_x, player_y);

			float distance = sqrtf(pow(player_x - x, 2) + pow(player_y - y, 2)), bullet_w, bullet_h;
			float dot = (x * player_x) + (y * player_y);

			if (it->ai_patience > it->ai_max_patience) {
				it->ai_patience = 0;
				it->freeze_ai_behaviour = false;
				it->choice = (int)rand() % 10 + 1;
			}
			else
				it->ai_patience += dt;

			if (it->choice % 2 == 0) {
				if (distance > 200) {
					if (x < player_x) {
						x += 0.5f;
						ConfigureBulletSettings(*it, 1, 1);
					}
					else if (x > player_x) {
						x -= 0.5f;
						ConfigureBulletSettings(*it, 1, -1);
					}

					if (y < player_y) {
						y += 0.5f;
						ConfigureBulletSettings(*it, 0, 1);
					}
					else if (y > player_y) {
						y -= 0.5f;
						ConfigureBulletSettings(*it, 0, -1);
					}

					it->SetPosition(x, y);
					it->Update(dt);
				}
			}

			else if (!it->freeze_ai_behaviour) {
				Shoot(*it);
				it->freeze_ai_behaviour = true;
			}
		}

		else
			it->ApplyShock(dt);
	}
}

bool CollidingShips(vector<CSimpleSprite> &ships, vector<Bullet> &bullets, vector<Bullet>::iterator &bullet) {

	for (vector<CSimpleSprite>::iterator it = ships.begin(); it != ships.end(); ++it) {
		if (it->CollideWith(*bullet) && it->GetEnemyType() == bullet->GetEnemyType()) {
			it->TakeDamage(bullet->GetDamage());

			if (testSprite->GetBulletType() != 2 && !bullets.empty())
				bullets.erase(bullet);

			if (testSprite->GetBulletType() == 1)
				it->is_shocked = true;

			if (it->GetHealth() <= 0) {
				ships.erase(it);
			}
			return true;
		}
	}

	return false;
}

void DisplayList() {
	display = glGenLists(1);
	glNewList(display, GL_COMPILE);

	if (pickup != nullptr)
		pickup->Draw();

	if (testSprite != nullptr) {
		if (testSprite->are_bullets_active) {
			vector<Bullet> *bullets = testSprite->GetBullets();

			for (vector<Bullet>::iterator it = bullets->begin(); it != bullets->end(); ++it)
				it->Draw();
		}

		for (vector<CSimpleSprite>::iterator it = ships.begin(); it != ships.end(); ++it) {
			if (it->are_bullets_active) {
				vector<Bullet> *bullets = it->GetBullets();

				for (vector<Bullet>::iterator it2 = bullets->begin(); it2 != bullets->end(); ++it2)
					it2->Draw();
			}
		}
	}

	glEndList();
}

//------------------------------------------------------------------------
// Add your display calls here (DrawLine,Print, DrawSprite.) 
// See App.h 
//------------------------------------------------------------------------
void Render()
{	
	//------------------------------------------------------------------------
	// Example Line Drawing.
	//------------------------------------------------------------------------
	static float a = 0.0f;
	float r = 1.0f;
	float g = 1.0f;
	float b = 1.0f;
	a += 0.1f;
	for (int i = 0; i < 20; i++)
	{

		float sx = 200 + sinf(a + i * 0.1f)*60.0f;
		float sy = 200 + cosf(a + i * 0.1f)*60.0f;
		float ex = 700 - sinf(a + i * 0.1f)*60.0f;
		float ey = 700 - cosf(a + i * 0.1f)*60.0f;
		g = (float)i / 20.0f;
		b = (float)i / 20.0f;
		App::DrawLine(sx, sy, ex, ey,r,g,b);
	}

	//------------------------------------------------------------------------
	// Example Sprite Code....
	string message = "Press 'back' to exit the game";

	if (has_pickup && testSprite != nullptr)
		message = "Pickup: " + to_string(testSprite->bullet_count);

	if (testSprite != nullptr)
		testSprite->Draw();
	else
		message = "The player is dead, you can press 'back' to exit the game";

	for (vector<CSimpleSprite>::iterator it = ships.begin(); it != ships.end(); ++it) {
		it->Draw();
	}

	/*if (testSprite2 != nullptr)
		testSprite2->Draw();*/
	//------------------------------------------------------------------------

	//Draw bullets via Display List
	glCallList(display);

	//------------------------------------------------------------------------
	// Example Text.
	//------------------------------------------------------------------------

	std::string text = "Sample Text: " + message;
	App::Print(100, 100, text.c_str());
}
//------------------------------------------------------------------------
// Add your shutdown code here. Called when the APP_QUIT_testSprite.key is pressed.
// Just before the app exits.
//------------------------------------------------------------------------
void Shutdown()
{	
	//------------------------------------------------------------------------
	// Example Sprite Code....
	delete testSprite;
	delete pickup;

	/*if (testSprite != nullptr)
		delete testSprite2;*/
	//------------------------------------------------------------------------
}