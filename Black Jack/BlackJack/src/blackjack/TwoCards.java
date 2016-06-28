package blackjack;

public class TwoCards {

    public int card(int card, int cover) {
	int cardVal = 0;
	int score = 0;
    
	if (cover > 30) {
	    cardVal = cover - 30;
	}
	    
	else if (cover > 20) {
	    cardVal = cover - 20;
	}
	   
	else if (cover > 10) {
	    cardVal = cover - 10;
	}

	else {
	    cardVal = cover;
	}
	    
	if (card > 30) {
	    score = (card - 30) + cardVal;
	}
	    
	else if (card > 20) {
	    score = (card - 20) + cardVal;
	}
	    
	else if (card > 10) {
	    score = (card - 10) + cardVal;
	}

	else {
	    score = card + cardVal;
	}
	return score;
    }
}