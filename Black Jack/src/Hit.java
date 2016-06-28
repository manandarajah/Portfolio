public class Hit {

    public int hit(int card) {
	int score = 0;
    
	if (card > 30) {
	    score += card - 30;
	}
	
	else if (card > 20) {
	    score += card - 20;
	}
	
	else if (card > 10) {
	    score += card - 10;
	}
	
	else {
	    score += card;
	}
	return score;
    }
    
    public int Dhit(int card) {
    
	int score = 0;

	if (card > 30) {
		
	    if ((card - 1) % 10 == 0) {
		score = (int) (Math.random() * 1);
		
		if (score == 0) {
		    score = 1;
		}
		
		else {
		    score = 11;
		}
	    }
		
	    else {
		score = (card - 30);
	    }
	}
	    
	else if (card > 20) {
		
	    if ((card - 1) % 10 == 0) {
		score = (int) (Math.random() * 1);
		
		if (score == 0) {
		    score = 1;
		}
		
		else {
		    score = 11;
		}
	    }
		
	    else {
		score = (card - 20);
	    }
	}
	    
	else if (card > 10) {
		
	    if ((card - 1) % 10 == 0) {
		score = (int) (Math.random() * 1);
		
		if (score == 0) {
		    score = 1;
		}
		
		else {
		    score = 11;
		}
	    }
		
	    else {
		score = (card - 10);
	    }
	}

	else {
		
	    if ((card - 1) % 10 == 0) {
		score = (int) (Math.random() * 1);
		
		if (score == 0) {
		    score = 1;
		}
		
		else {
		    score = 11;
		}
	    }
		
	    else {
		score = card;
	    }
	}
	return score;
    }
}
