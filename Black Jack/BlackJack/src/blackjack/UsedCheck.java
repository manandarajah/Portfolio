package blackjack;

public class UsedCheck {

    public boolean same(int position, CardType[] used, int array) {
    
	boolean a = false;
	
	for (int i = 0; i <= array; i++) {
	
	    if (used[position].number == used[i].number && i != position) {
		a = true;
		break;
	    }
	}
	return a;
    }
    
    public int change(int position, CardType[] card, CardType[] used, int array) {
    
	boolean a = false;

	for (int i = 0; i <= array; i++) {
	
	    if (card[position].number == used[i].number) {
		a = true;
		break;
	    }
	}
	
	while (a == true) {
	    a = false;
	    position = (int) (Math.random() * 39);
	    
	    for (int i = 0; i <= array; i++) {
	
		if (card[position].number == used[i].number) {
		    a = true;
		    break;
		}
	    }
	}
	return position;
    }
}