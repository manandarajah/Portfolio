import java.awt.*;
import java.awt.Graphics;

public class Prompts {

    public Graphics bet(Graphics g) {
	
	g.setFont(new Font("", Font.PLAIN, 30));
	g.drawString("Place your bet", 450, 270);
	g.drawString("W", 300, 325);
	g.drawString("R", 440, 325);
	g.drawString("G", 560, 325);
	g.drawString("B", 675, 325);
	
	g.setFont(new Font("", Font.PLAIN, 20));
	g.drawString("= $1", 350, 350);
	g.drawString("= $5", 470, 350);
	g.drawString("= $25", 590, 350);
	g.drawString("= $100", 700, 350);
	
	return g;
    }
    
    public Graphics instructions(Graphics g) {
    
	g.setFont(new Font("", Font.ITALIC + Font.BOLD, 40));
	g.drawString("Rules of Game", 390, 50);
	g.drawString("____________", 390, 50);
	
	g.setFont(new Font("", Font.PLAIN, 30));
	g.drawString("      The game is simple, in order to win, try and get", 0, 100);
	g.drawString("exactly 21. If not, then get", 675, 100);
	g.drawString("  closer to 21 (Don't go over 21). If you want", 0, 135);
	g.drawString("more cards, click " + "\"Hit Me\". if not,", 580, 135);
	g.drawString("  then click " + "\"Stay\" to stay at your", 0, 170);
	g.drawString("current score. Now, when the game ends,", 430, 170);
	g.drawString("  depending on your score, you'll win or lose a", 0, 205);
	g.drawString("specific amount for every bet", 606, 205);
	g.drawString("  made. Below are some things to consider:", 0, 240);
	
	g.setFont(new Font("", Font.PLAIN, 25));
	g.drawString("\u00B7 Win - you'll keep your bet and get", 10, 320);
	g.drawString("the same amount from the dealer.", 410, 320);
	g.drawString("\u00B7 Lose - your bet is taken by the dealer.", 10, 370);
	g.drawString("\u00B7 Blackjack - you'll recieve 1.5 times", 10, 420);
	g.drawString("the bet with the original amount.", 420, 420);
	g.drawString("\u00B7 Push (or draw) - ", 10, 470);
	g.drawString("you'll get your money back, neither winning or losing.", 210, 470);
       
	return g;
    }
    
    public Graphics probability(Graphics g, int probS, int probN) {
	
	g.setFont(new Font("", Font.PLAIN, 30));
	g.drawString("Probability of same suit: 1/" + probS, 25, 50);
	g.drawString("Probability of same #: 1/" + probN, 25, 80);
	
	return g;
    }
    
    public Graphics score(Graphics g, int score, int Dscore) {
    
	g.setFont(new Font("", Font.ITALIC, 30));
	g.drawString("score: " + score, 470, 50);
	g.drawString("Dealer score: " + Dscore, 820, 50);
	
	return g;
    }
    
    public Graphics scoreCheck(Graphics g, int score, int Dscore, boolean stop) {
	
	g.setColor(Color.yellow);
	g.setFont(new Font("", Font.ITALIC, 30));
	
	
	if (score == 21) {
	    g.setFont(new Font("", Font.BOLD + Font.ITALIC, 50));
	    g.drawString("BLACKJACK!!", 360, 285);
	}
	
	else if (Dscore == 21) {
	    g.drawString("Dealer got Blackjack", 395, 275);
	}
	
	else if (score > 21) {
	    g.drawString("Your score is over 21, YOU LOST!!", 308, 275);
	}
	
	else if (Dscore > 21) {
	    g.drawString("Dealer went over 21, YOU WIN!!", 315, 275);
	}
	
	else if (stop == true) {
	    g.drawString("You stayed at the score of " + score, 350, 275);
	}
	
	return g;
    }
    
    public Graphics drawLiner(Graphics g, boolean stop) {
    
	g.setColor(Color.black);
	
	if (stop == true) {
	    g.drawRect(550, 470, 250, 50);
	}
	
	else {
	    g.drawRect(550, 470, 200, 50);
	}
	g.drawRect(330, 470, 200, 50);
	
	return g;
    }
    
    public Graphics newBet(Graphics g) {
	g.setColor(Color.yellow);
	g.setFont(new Font("", Font.PLAIN, 30));
	
	g.drawString("W", 840, 225);
	g.drawString("R", 840, 275);
	g.drawString("G", 840, 325);
	g.drawString("B", 840, 375);
	
	g.setFont(new Font("", Font.PLAIN, 20));
	g.drawString("= $1", 880, 245);
	g.drawString("= $5", 880, 295);
	g.drawString("= $25", 880, 345);
	g.drawString("= $100", 880, 395);
	
	return g;
    }
}
