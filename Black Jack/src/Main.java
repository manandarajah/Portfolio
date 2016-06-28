

import java.applet.*;
import java.awt.*;
import java.awt.Font;
import java.awt.Graphics;

public class Main extends Applet {
    TextField White = new TextField();
    TextField Red = new TextField();
    TextField Green = new TextField();
    TextField Black = new TextField();
    Button play = new Button("Play");
    Button start = new Button("Start");
    Button hit = new Button("Hit Me");
    Button stay = new Button("Stay");
    Button again = new Button("Play Again");
    Button quit = new Button("Quit");
    Button one = new Button("1");
    Button eleven = new Button("11");
    Color green = new Color(3, 140, 0);
    Color lightgreen = new Color(133, 253, 130);
    Color red = new Color(174, 22, 0);
    Font size = new Font("", Font.CENTER_BASELINE, 40);
    Font buttonSize = new Font("", Font.BOLD + Font.ITALIC, 40);
    Font textSize = new Font("", Font.PLAIN, 30);
    Font numSize = new Font("", Font.ITALIC, 50);
    Font size21 = new Font("", Font.BOLD + Font.ITALIC, 50);
    Font betSize = new Font("", Font.PLAIN, 20);
    CardType[] card = new CardType[40];
    CardType[] used = new CardType[40];
    UsedCheck u = new UsedCheck();
    Card d = new Card();
    TwoCards d2 = new TwoCards();
    Suit s = new Suit();
    BetCheck b = new BetCheck();
    Hit h = new Hit();
    Eligibility e = new Eligibility();
    Prompts p = new Prompts();
    int cover = 0, score = 0, Dscore = 0, cardNum = 0, probS = 9, probN = 3;
    int cardVal, prevN, prevS, suit, W = 0, R = 0, G = 0, B = 0, betSum = 0;
    int Dcover = 0, DCardNum = 0, Dsuit, Csuit, DCsuit, bet, winnings = 0;
    int current = 3, sameVal = 0;
    boolean toIntro = false, toGame = false, c = false, choice = false, ch = false; 
    boolean playAgain = false, stop = false, same = false;
    boolean wE = false, rE = false, gE = false, bE = false, error = true;
    boolean newBetCheck = true, newBet = false;
    
    public void init() {
	this.setSize(1050, 568);
	setLayout(null);
	setBackground(green);
	
	add(White);
	White.setLocation(350, 300);
	White.setFont(betSize);
	White.setSize(80, 25);
	White.setBackground(lightgreen);
	
	add(Red);
	Red.setLocation(470, 300);
	Red.setFont(betSize);
	Red.setSize(80, 25);
	Red.setBackground(lightgreen);
	
	add(Green);
	Green.setLocation(590, 300);
	Green.setFont(betSize);
	Green.setSize(80, 25);
	Green.setBackground(lightgreen);
	
	add(Black);
	Black.setLocation(700, 300);
	Black.setFont(betSize);
	Black.setSize(80, 25);
	Black.setBackground(lightgreen);

	add(play);
	play.setLocation(400, 450);
	play.setSize(300, 70);
	play.setFont(buttonSize);
	play.setBackground(lightgreen);
	play.setForeground(red);
	
	if (toIntro == true) {
	    remove(play);
	    remove(White);
	    remove(Red);
	    remove(Green);
	    remove(Black);
	    
	    add(start);
	    start.setLocation(750, 500);
	    start.setSize(200, 50);
	    start.setBackground(lightgreen);
	    start.setForeground(red);
	    start.setFont(buttonSize);
	
	    if (toGame == true) {
		remove(start);
	    
		add(hit);
		hit.setLocation(550, 470);
		hit.setSize(200,50);
		hit.setBackground(lightgreen);
		hit.setForeground(red);
		hit.setFont(buttonSize);
	    
		add(stay);
		stay.setLocation(330, 470);
		stay.setSize(200, 50);
		stay.setBackground(lightgreen);
		stay.setForeground(red);
		stay.setFont(buttonSize);
	    }
	}
    }
    
    public void paint(Graphics g) {
	g.drawRect(0, 0, 1049, 567);
	g.drawRect(400, 450, 300, 70);
	g.setFont(size);
	g.setColor(Color.yellow);
	g.drawString("Welcome to Blackjack", 350, 200);
	g.setFont(textSize);
	p.bet(g);
	
	if (betSum > 100 || betSum < 5) {
	    g.setColor(Color.red);
	}
	
	else {
	    g.setColor(Color.yellow);
	}
	g.drawString("Bet: $" + betSum, 505, 390);
	
	if (wE == true) {
	    White.setForeground(Color.red);
	}
	
	else {
	    White.setForeground(Color.black);
	}
	White.setText(White.getText());
	
	if (rE == true) {
	    Red.setForeground(Color.red);
	}
	
	else {
	    Red.setForeground(Color.black);
	}
	Red.setText(Red.getText());
	
	if (gE == true) {
	    Green.setForeground(Color.red);
	}
	
	else {
	    Green.setForeground(Color.black);
	}
	Green.setText(Green.getText());
	
	if (bE == true) {
	    Black.setForeground(Color.red);
	}
	
	else {
	    Black.setForeground(Color.black);
	}
	Black.setText(Black.getText());
	
	if (playAgain == true) {
	    g.setColor(green);
	    g.drawRect(440, 470, 250, 50);
	    p.scoreCheck(g, score, Dscore, stop);
	}
	
	if (toIntro == true) {
	    g.setColor(green);
	    g.setFont(size);
	    g.drawRect(400, 450, 300, 70);
	    g.drawString("Welcome to Blackjack", 350, 200);
	    p.bet(g);
	    g.drawString("Bet: $" + betSum, 505, 390);
	    g.setColor(Color.yellow);
	    p.instructions(g);
	    g.setColor(Color.black);  
	    g.drawRect(750, 500, 200, 50);
    
	    if (toGame == true) {
		g.setColor(green);
		p.instructions(g);
		g.drawRect(750, 500, 200, 50);
		g.setColor(Color.yellow);
		p.probability(g, probS, probN);
		g.drawString("Current bet: $" + bet, 25, 370);
		g.setFont(new Font("", Font.PLAIN, 25));
		g.drawString("Total winnings: $" + winnings, 25, 400);
		p.score(g, score, Dscore);
		d.drawCard(g, h.hit(card[cardNum].number), suit);
		d.dealerCard(g, h.Dhit(card[DCardNum].number), Dsuit);
		d.drawCover(g, h.hit(card[cover].number), Csuit, c);
		d.dealerCover(g, h.Dhit(card[Dcover].number), DCsuit, c);
		p.drawLiner(g, stop);
	    }
	}
	
	if (score >= 21 || Dscore >= 21 || stop == true) {
	    p.scoreCheck(g, score, Dscore, stop);
	    g.setColor(green);
	    p.newBet(g);
	    
	    if (betSum < 5 || betSum > 100) {
		g.setColor(Color.red);
	    }
	    
	    else {
		g.setColor(Color.yellow);
	    }
	    g.drawString("New Bet: $" + betSum, 885, 425); 
	}
    }
    
    public void ace() {
    
	remove(hit);
	remove(stay);
		    
	add(one);
	one.setLocation(330, 470);
	one.setSize(200, 50);
	one.setBackground(lightgreen);
	one.setForeground(red);
	one.setFont(buttonSize);
		    
	add(eleven);
	eleven.setLocation(550, 470);
	eleven.setSize(200, 50);
	eleven.setBackground(lightgreen);
	eleven.setForeground(red);
	eleven.setFont(buttonSize);
	
	if (choice == true) {
	    remove(one);
	    remove(eleven);
	    init();
	}
    }
    
    public void GameOver() {
	remove(hit);
	remove(stay);
	
	add(again);
	again.setLocation(550, 470);
	again.setSize(250, 50);
	again.setBackground(lightgreen);
	again.setForeground(red);
	again.setFont(buttonSize);
	
	add(quit);
	quit.setLocation(330, 470);
	quit.setSize(200, 50);
	quit.setBackground(lightgreen);
	quit.setForeground(red);
	quit.setFont(buttonSize);
	
	add(White);
	White.setLocation(880, 200);
	White.setSize(100, 25);
	    
	add(Red);
	Red.setLocation(880, 250);
	Red.setSize(100, 25);
	    
	add(Green);
	Green.setLocation(880, 300);
	Green.setSize(100, 25);
	    
	add(Black);
	Black.setLocation(880, 350);
	Black.setSize(100, 25);
	
	if (newBetCheck == true) {
	    White.setText("");
	    Red.setText("");
	    Green.setText("");
	    Black.setText("");
	}
    }   
    
    public boolean action(Event click, Object o) {
    
	wE = e.White(White.getText());
	rE = e.Red(Red.getText());
	gE = e.Green(Green.getText());
	bE = e.Black(Black.getText());
	    
	if (wE == false) {
	    W = Integer.parseInt(White.getText());
	}
	
	else {
	    W = 0;
	}
	    
	if (rE == false) {
	    R = Integer.parseInt(Red.getText()) * 5;
	}
	
	else {
	    R = 0;
	}
	    
	if (gE == false) {
	    G = Integer.parseInt(Green.getText()) * 25;
	}
	
	else {
	    G = 0;
	}
	    
	if (bE == false) {
	    B = Integer.parseInt(Black.getText()) * 100;
	}
	
	else {
	    B = 0;
	}
	betSum = 0;
	betSum = W + R + G + B;
	
	if (wE == false
	    && rE == false
	    && gE == false
	    && bE == false) {
	    error = false;
	}
    
	if (click.target == play) {
	
	    if (betSum > 100 || betSum < 5 || error == true) {
	    }
	    
	    else {
		toIntro = true;
		bet = betSum;
		init();
	    }
	}
	
	if (click.target == start) {
	
	    for (int i = 0; i < card.length; i++) {
		card[i] = new CardType(i + 1);
	    }
	    cover = (int) (Math.random() * 39);
	    cardNum = (int) (Math.random() * 39);
	    Dcover = (int) (Math.random() * 39);
	    DCardNum = (int) (Math.random() * 39);
	    
	    used[0] = new CardType(card[cardNum].number);
	    used[1] = new CardType(card[cover].number);
	    used[2] = new CardType(card[DCardNum].number);
	    used[3] = new CardType(card[Dcover].number);
	    
	    for (int i = 0; i <= current; i++) {
		same = u.same(i, used, current);
		
		if (same == true) {
		    sameVal = i;
		    break;
		}
	    }
	    
	    while (same == true) {
		used[sameVal] = new CardType((int) (1+Math.random() * 40));
		same = u.same(sameVal, used, current);
	    }
	    
	    score = d2.card(card[cardNum].number, card[cover].number);
	    Dscore = d2.card(card[DCardNum].number, card[Dcover].number);
	    suit = s.suit(card[cardNum].number);
	    Csuit = s.suit(card[cover].number);
	    Dsuit = s.suit(card[DCardNum].number);
	    DCsuit = s.suit(card[Dcover].number);
	    
	    prevN = card[cardNum].number;
	    prevS = suit;
	    toGame = true;
	    init();
	}
	
	if (click.target == hit) {
	    c = true;
	    same = false;
	    cardNum = (int) (Math.random() * 39);
	    cardNum = u.change(cardNum, card, used, current);
	    current++;
	    used[current] = new CardType(card[cardNum].number);
	    
	    if (cardNum % 10 == 0 && Dscore < 21) {
		ace();
	    }
	    
	    else {
		score += h.hit(card[cardNum].number);
		ch = true;
	    }
	    suit = s.suit(card[cardNum].number);
	    
	    if (prevN == card[cardNum].number) {
		probN--;
	    }
	    
	    else {
		prevN = card[cardNum].number;
		probN = 3;
	    }
	    
	    if (prevS == suit) {
		probS--;
	    }
	    
	    else {
		prevS = suit;
		probS = 9;
	    }
	}
	
	if (click.target == stay) {
	    stop = true;
	}
	
	if (click.target == one) {
	    score++;
	    choice = true;
	    ch = true;
	    ace();
	}
	
	if (click.target == eleven) {
	    score += 11;
	    choice = true;
	    ch = true;
	    ace();
	}
	
	if (click.target == again) {
	    if (betSum < 5 || betSum > 100 || error == true) {
	    }
	    
	    else {
		playAgain = true;
		c = false;
		stop = false;
		newBetCheck = true;
		newBet = false;
		bet = betSum;
		cover = (int) (Math.random() * 39);
		cardNum = (int) (Math.random() * 39);
		Dcover = (int) (Math.random() * 39);
		DCardNum = (int) (Math.random() * 39);
	    
		score = d2.card(card[cardNum].number, card[cover].number);
		Dscore = d2.card(card[DCardNum].number, card[Dcover].number);
		Csuit = s.suit(card[cover].number);
	    
		prevN = card[cardNum].number;
		prevS = suit;
		probS = 9;
		probN = 3;
		remove(again);
		remove(quit);
		init();
	    }
	}
	
	if (click.target == quit) {
	    remove(again);
	    remove(quit);
	    remove(White);
	    remove(Red);
	    remove(Green);
	    remove(Black);
	    score = 0;
	    Dscore = 0;
	    playAgain = false;
	    c = false;
	    stop = false;
	    repaint();
	    newBet = false;
	    newBetCheck = true;
	    toIntro = false;
	    toGame = false;
	    init();
	}
	
	if (Dscore >= 17) {
	    ch = false;
	}
	
	if (ch == true) {
	    DCardNum = (int) (Math.random() * 39);
	    DCardNum = u.change(DCardNum, card, used, current);
	    current++;
	    used[current] = new CardType(card[DCardNum].number);
	    Dscore += h.Dhit(card[DCardNum].number);
	    Dsuit = s.suit(card[DCardNum].number);
	    ch = false;
	}
	
	if (score >= 21 || Dscore >= 21 || stop == true) {
	    GameOver();
	    stop = true;
	    newBetCheck = false;
	    
	    if (newBet == false) {
		winnings = b.bets(score, Dscore, betSum, winnings);
	    }
	}
	repaint();
	
	if (stop == true && newBet == false) {
	    betSum = 0;
	    newBet = true;
	}
	return true;
    }
}