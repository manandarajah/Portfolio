#include "pch.h"
#include "Classes.h"
#include <stdlib.h>

const array<char, 13> Card::numbers{ 'A', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K' };
const array<char, 4> Card::suits{ 'C','S','H','D' };
Card Card::currentCard;

Card::Card() {

}

Card::Card(SDL_Renderer *renderer, int x, int y) {
	int n = rand() % 13, s = rand() % 4;
	num = Card::numbers.at(n);
	suit = Card::suits.at(s);
	this->renderer = renderer;
	image = IMG_LoadTexture(renderer, "Images/cards.png");
	rect = { 73 * n, 99 * s, 75, 97 };
	dest.x = x;
	dest.y = y;
	draw();
}

bool Card::compare(Card* card) {
	if ((this->getNum() == card->getNum()) || this->getSuit() == card->getSuit()) {
		return true;
	}

	else {
		return false;
	}
}

void Card::displayCardInField(Card* card) {
	Card::currentCard = *card;
	cout << "Card: " << card->getNum() << card->getSuit() << "\n\n";
}

char Card::getNum() {
	return num;
}

char Card::getSuit() {
	return suit;
}

void Card::setDestPos(int x, int y) {
	dest.x = x;
	dest.y = y;
}

SDL_Rect Card::getDest() {
	return this->dest;
}

void Card::draw() {
	SDL_RenderCopy(renderer, image, &rect, &dest);
	SDL_RenderPresent(renderer);
}

Player::Player() {

}

Player::Player(SDL_Renderer *renderer, SDL_Texture *play, SDL_Texture *avatar, int y, int avX, int cardY) {
	isTurn = false;
	suitCall = '\0';
	this->renderer = renderer;
	this->cardY = cardY;
	this->play = play;
	this->avatar = avatar;
	this->choice = IMG_LoadTexture(renderer, "Images/suit-selection.jpg");

	playSrc = { 0, 0, 100, 50 };
	playDest = { 250, y, 100, 50 };

	avSrc = { 0, 0, 100, 100 };
	avDest = { avX, 200, 100, 100 };

	for (int i = 0; i < hand.size(); ++i) {
		hand[i] = new Card(renderer, 65 * (i + 1), cardY);
	}
}

void Player::drawCard() {
	for (int i = 0; i < hand.size(); ++i) {
		if (hand[i] == nullptr) {
			hand[i] = new Card(renderer, 65 * (i + 1), cardY);
			break;
		}
	}
}

Card* Player::getCard(int index) {
	/*while (index < 0 || index > 7) {
		cout << "Invalid entry, try again: ";
		cin >> index;
	}*/
	return hand[index];
}

void Player::setIsTurn(bool isTurn) {
	this->isTurn = isTurn;
}

bool Player::getIsTurn() {
	return isTurn;
}

void Player::setSuitCall(char suitCall) {
	while (suitCall != 'H' && suitCall != 'D' && suitCall != 'C' && suitCall != 'S') {
		cout << "Invalid entry, try again: ";
		cin >> suitCall;
	}
	this->suitCall = suitCall;
}

char Player::getSuitCall() {
	return suitCall;
}

void Player::clearSuitCall() {
	this->suitCall = '\0';
}

int Player::cardCount() {
	int count = 0;

	for (Card* card : hand) {
		if (card != nullptr) {
			++count;
		}
	}
	return count;
}

void Player::showHand() {
	int count = 0;

	for (Card* card : hand) {
		++count;
		cout << count << ": ";

		if (card != nullptr) {
			cout << card->getNum() << "" << card->getSuit() << endl;
		}

		else {
			cout << endl;
		}
	}
}

void Player::removeCard(int index) {
	hand[index] = nullptr;
}

void Player::drawTwo() {
	drawCard();
	drawCard();
}

void Player::dropPairs(Card** cards, array<int, 8>& indexes, int count) {

	cout << "drop pair" << endl;
	for (int i = 0; i < count; ++i) {
		cards[i] = getCard(indexes[i]);
	}
}

void Player::crazy8(Player &opponent, bool& submit, bool& eight) {
	submit = false;

	/*while (SDL_PollEvent(&event)) {
		switch (event.type) {
		case SDL_MOUSEBUTTONUP:
			if (event.button.button == SDL_BUTTON_LEFT) {*/
				if (event.button.x >= opponent.avDest.x
					&& event.button.x <= opponent.avDest.x + 50
					&& event.button.y >= opponent.avDest.y
					&& event.button.y <= opponent.avDest.y + 50) {
					setSuitCall('S');
					submit = true;
					eight = false;
					opponent.setIsTurn(true);
					this->isTurn = false;
				}

				else if (event.button.x >= opponent.avDest.x
					&& event.button.x <= opponent.avDest.x + 50
					&& event.button.y >= opponent.avDest.y + 50
					&& event.button.y <= opponent.avDest.y + opponent.avDest.h) {
					setSuitCall('D');
					submit = true;
					eight = false;
					opponent.setIsTurn(true);
					this->isTurn = false;
				}

				else if (event.button.x >= opponent.avDest.x + 50
					&& event.button.x <= opponent.avDest.x + opponent.avDest.w
					&& event.button.y >= opponent.avDest.y
					&& event.button.y <= opponent.avDest.y + 50) {
					setSuitCall('H');
					submit = true;
					eight = false;
					opponent.setIsTurn(true);
					this->isTurn = false;
				}

				else if (event.button.x >= opponent.avDest.x + 50
					&& event.button.x <= opponent.avDest.x + opponent.avDest.w
					&& event.button.y >= opponent.avDest.y + 50
					&& event.button.y <= opponent.avDest.y + opponent.avDest.h) {
					setSuitCall('C');
					submit = true;
					eight = false;
					opponent.setIsTurn(true);
					this->isTurn = false;
				}
			/*}
		}
	}*/
}

void Player::checkInput(array<int, 8>& indexes, SDL_Rect deck, Player &opponent, bool& change, bool& submit, bool &eight) {
	while (SDL_PollEvent(&event)) {
		switch (event.type) {
		case SDL_MOUSEBUTTONUP:
			if (event.button.button == SDL_BUTTON_LEFT) {
				if (event.button.x >= deck.x
					&& event.button.x <= deck.x + deck.w
					&& event.button.y >= deck.y
					&& event.button.y <= deck.y + deck.h) {
					drawCard();
					change = true;
					opponent.setIsTurn(true);
					this->isTurn = false;
				}

				else if (event.button.x >= playDest.x
					&& event.button.x <= playDest.x + playDest.w
					&& event.button.y >= playDest.y
					&& event.button.y <= playDest.y + playDest.h) {
					Card* cards[8];
					dropPairs(cards, indexes, count);

					if (cards[0]->compare(&Card::currentCard) || cards[0]->getSuit() == opponent.getSuitCall()) {
						switch (cards[0]->getNum()) {
						case 'J':
							//reset graphics to show skip
							break;
						case '2':
							opponent.drawTwo();
							opponent.setIsTurn(true);
							this->isTurn = false;
							//set drawn card in hand
							break;
						case '8':
							eight = true;
							this->crazy8(opponent, submit, eight);
							//edit this method to include graphics
							break;
						default:
							opponent.setIsTurn(true);
							this->isTurn = false;
						}
						submit = true;
					}
				}

				else {
					for (int i = 0; i < hand.size(); ++i) {
						if (hand[i] != nullptr) {
							SDL_Rect cardDest = hand[i]->getDest();

							if (event.button.x >= cardDest.x
								&& event.button.x <= cardDest.x + cardDest.w
								&& event.button.y >= cardDest.y
								&& event.button.y <= cardDest.y + cardDest.h) {
								indexes[count] = i;
								++count;
								hand[i]->setDestPos(cardDest.x, cardDest.y - 20);
								break;
							}
						}
					}
				}

				if (eight) {
					this->crazy8(opponent, submit, eight);
				}

				for (int i = 0; i < indexes.size(); ++i) {
					if (indexes[i] != -1) {
						change = true;
						break;
					}
				}
			}
		}
	}
}

void Game::WinLose(Player &player) {
	player.cardCount() == 0 ? cout << "Player 1 wins" << endl : cout << "Player 2 wins" << endl;
}