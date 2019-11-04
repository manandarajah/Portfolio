#pragma once
#include <array>
#include <string>
#include <iostream>
#include <SDL.h>
#include <SDL_image.h>

using namespace std;

//edit card class to include card image
class Card {
private:
	char num, suit;
	SDL_Renderer *renderer;
	SDL_Texture *image;
	SDL_Rect rect, dest = {73, 300, 75, 97};

public:
	static const array<char, 13> numbers;
	static const array<char, 4> suits;
	//Compares a card by number of suit. Returns true if number or suit matches, returns false if no match
	Card();
	Card(SDL_Renderer *renderer, int x, int y);
	static Card currentCard;
	bool compare(Card* card);
	//Get card information
	char getNum();
	char getSuit();
	//Displays the card on the pile
	static void displayCardInField(Card* card);
	void setDestPos(int x, int y);
	SDL_Rect getDest();
	void draw();
};

//edit Player class to include avatar, mouse inputs, and sounds
class Player {
private:
	array<Card*, 8> hand;
	bool isTurn;
	char suitCall;
	int cardY;
	SDL_Event event;

public:
	//Uses random function to generate card and returns the card
	SDL_Rect playSrc, playDest, avSrc, avDest;
	SDL_Texture *play, *avatar, *choice;
	SDL_Renderer *renderer;
	int count = 0;

	Player();
	Player(SDL_Renderer *renderer, SDL_Texture *play, SDL_Texture *avatar, int y, int avX, int cardY);
	Card* getCard(int index);
	void drawCard();
	void setIsTurn(bool isTurn);
	bool getIsTurn();
	void setSuitCall(char suitCall);
	char getSuitCall();
	void clearSuitCall();
	int cardCount();
	void showHand();
	void removeCard(int index);
	void crazy8(Player &opponent, bool& submit, bool& eight);
	void drawTwo();
	void skipTurn(Player &player);
	void dropPairs(Card** cards, array<int, 8>& indexes, int count);
	void checkInput(array<int, 8>& indexes, SDL_Rect deck, Player &opponent, bool& change, bool& submit, bool& eight);
};

//edit Game class to include SDL initialization, and create title screens, tables, cards, sounds, and text
class Game{
public:
	void WinLose(Player &player);
};

class Deck {
public:
	void shuffleDeck();
};