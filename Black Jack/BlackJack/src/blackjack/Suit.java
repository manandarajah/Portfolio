package blackjack;

public class Suit {

    public int suit(int card) {
    
	int suit = 0;
    
	if (card > 30) {
	    suit = 4;
	}
	    
	else if (card > 20) {
	    suit = 3;
	}
	    
	else if (card > 10) {
	    suit = 2;
	}

	else {
	    suit = 1;
	}
	return suit;
    }
}