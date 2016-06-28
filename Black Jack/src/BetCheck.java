public class BetCheck {

    public int bets(int score, int Dscore, int bet, int winnings) {
    
	if (score == 21) {
	    winnings += (int) (bet + (bet * 1.5));
	}
	
	else if (Dscore == 21) {
	    winnings -= (int) (bet + (bet * 1.5));
	}
	
	else if (score > 21) {
	    winnings -= bet;
	}
	
	else if (Dscore > 21) {
	    winnings += bet;
	}
	
	else if (score < Dscore) {
	    winnings -= bet;
	}
	
	else if (score > Dscore) {
	    winnings += bet;
	}
	return winnings;
    }
}
