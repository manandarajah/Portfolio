import java.awt.*;
import java.awt.Graphics;

public class Card {

    Color blue = new Color(11, 128, 166);
    int[] xTri = {584, 600, 616};
    int[] CxTri = {465, 481, 496};
    int[] yTri = new int[3];

    public Graphics drawCard(Graphics g, int number, int suit) {    
    
	g.setColor(Color.white);
	g.fillRect(550, 295, 100, 150);
	g.setColor(Color.black);
	g.drawRect(550, 295, 100, 150);
	g.setColor(Color.red);
	g.setFont(new Font("", Font.PLAIN, 20)); 
	
	if (suit == 1) {
	    yTri[0] = 372;
	    yTri[1] = 392;
	    yTri[2] = 372;
	    g.fillPolygon(xTri, yTri, 3);
	    yTri[1] = 352;
	    g.fillPolygon(xTri, yTri, 3);
	    
	    if (number == 1) {
		g.drawString("A", 552, 315);
	    }
	    
	    else {
		g.drawString(""+number, 552, 315);
	    }
	}   
	
	if (suit == 2) {
	    yTri[0] = 362;
	    yTri[1] = 382;
	    yTri[2] = 362;
	    g.fillOval(584, 350, 20, 20);
	    g.fillOval(596, 350, 20, 20);
	    g.fillPolygon(xTri, yTri, 3);
	    
	    if (number == 1) {
		g.drawString("A", 552, 315);
	    }
	    
	    else {
		g.drawString(""+number, 552, 315);
	    }
	}
	
	g.setColor(Color.black);
	if (suit == 3) {
	    yTri[0] = 372;
	    yTri[1] = 342;
	    yTri[2] = 372;
	    g.fillPolygon(xTri, yTri, 3);
	    g.fillOval(585, 365, 20, 20);
	    g.fillOval(596, 365, 20, 20);
	    g.fillRect(598, 383, 5, 10);
	    
	    if (number == 1) {
		g.drawString("A", 552, 315);
	    }
	    
	    else {
		g.drawString(""+number, 552, 315);
	    }
	}
	
	if (suit == 4) {
	    g.fillOval(585, 365, 20, 20);
	    g.fillOval(596, 365, 20, 20);
	    g.fillOval(590, 353, 20, 20);
	    g.fillRect(598, 383, 5, 10);
	    
	    if (number == 1) {
		g.drawString("A", 552, 315);
	    }
	    
	    else {
		g.drawString(""+number, 552, 315);
	    }
	}
    
	return g;
    }
    
    public Graphics drawCover(Graphics g, int number, int suit, boolean cover) {
    
	g.setColor(blue);
	
	//if (cover == true) {
	    g.setColor(Color.white);
	//}
	g.fillRect(430, 295, 100, 150);
	g.setColor(Color.black);
	g.drawRect(430, 295, 100, 150);
	
	//if (cover == true) {
	    g.setColor(Color.red);
	    
	    if (suit == 1) {
		yTri[0] = 372;
		yTri[1] = 392;
		yTri[2] = 372;
		g.fillPolygon(CxTri, yTri, 3);
		yTri[1] = 352;
		g.fillPolygon(CxTri, yTri, 3);
		
		if (number == 1) {
		    g.drawString("A", 432, 315);
		}
		
		else {
		    g.drawString(""+number, 432, 315);
		}
	    } 
	
	    if (suit == 2) {
		yTri[0] = 362;
		yTri[1] = 382;
		yTri[2] = 362;
		g.fillOval(465, 350, 20, 20);
		g.fillOval(477, 350, 20, 20);
		g.fillPolygon(CxTri, yTri, 3);
		
		if (number == 1) {
		    g.drawString("A", 432, 315);
		}
		
		else {
		    g.drawString(""+number, 432, 315);
		}
	    }

	    g.setColor(Color.black);
	    if (suit == 3) {
		yTri[0] = 372;
		yTri[1] = 342;
		yTri[2] = 372;
		g.fillOval(465, 365, 20, 20);
		g.fillOval(477, 365, 20, 20);
		g.fillPolygon(CxTri, yTri, 3);
		g.fillRect(478, 383, 5, 10);
		
		if (number == 1) {
		    g.drawString("A", 432, 315);
		}
		
		else {
		    g.drawString(""+number, 432, 315);
		}
	    }
	    
	    if (suit == 4) {
		g.fillOval(465, 365, 20, 20);
		g.fillOval(477, 365, 20, 20);
		g.fillOval(471, 353, 20, 20);
		g.fillRect(478, 383, 5, 10);
		
		if (number == 1) {
		    g.drawString("A", 432, 315);
		}
		
		else {
		    g.drawString(""+number, 432, 315);
		}
	    }
	//}
	
	return g;
    }
    
    public Graphics dealerCard(Graphics g, int number, int suit) {
    
	g.setColor(Color.white);
	g.fillRect(430, 90, 100, 150);
	g.setColor(Color.black);
	g.drawRect(430, 90, 100, 150);
	
	g.setColor(Color.red);
	if (suit == 1) {
	    yTri[0] = 153;
	    yTri[1] = 133;
	    yTri[2] = 153;
	    g.fillPolygon(CxTri, yTri, 3);
	    yTri[1] = 173;
	    g.fillPolygon(CxTri, yTri, 3);
	    
	    if (number == 1) {
		g.drawString("A", 432, 110);
	    }
	    
	    else {
		g.drawString(""+number, 432, 110);
	    }
	}
	
	if (suit == 2) {
	    yTri[0] = 153;
	    yTri[1] = 173;
	    yTri[2] = 153;
	    g.fillOval(465, 141, 20, 20);
	    g.fillOval(477, 141, 20, 20);
	    g.fillPolygon(CxTri, yTri, 3);
	    
	    if (number == 1) {
		g.drawString("A", 432, 110);
	    }
	    
	    else {
		g.drawString(""+number, 432, 110);
	    }
	}
	g.setColor(Color.black);
	
	if (suit == 3) {
	    yTri[0] = 163;
	    yTri[1] = 133;
	    yTri[2] = 163;
	    g.fillOval(465, 156, 20, 20);
	    g.fillOval(477, 156, 20, 20);
	    g.fillPolygon(CxTri, yTri, 3);
	    g.fillRect(478, 174, 5, 10);
	    
	    if (number == 1) {
		g.drawString("A", 432, 110);
	    }
	    
	    else {
		g.drawString(""+number, 432, 110);
	    }
	}
	
	if (suit == 4) {
	    g.fillOval(465, 156, 20, 20);
	    g.fillOval(477, 156, 20, 20);
	    g.fillOval(471, 143, 20, 20);
	    g.fillRect(478, 174, 5, 10);
	    
	    if (number == 1) {
		g.drawString("A", 432, 110);
	    }
	    
	    else {
		g.drawString(""+number, 432, 110);
	    }
	}
	
	return g;
    }
    
    public Graphics dealerCover(Graphics g, int number, int suit, boolean cover) {
    
	g.setColor(blue);
	
	if (cover == true) {
	    g.setColor(Color.white);
	}
	g.fillRect(550, 90, 100, 150);
	g.setColor(Color.black);
	g.drawRect(550, 90, 100, 150);
	
	if (cover == true) {
	    g.setColor(Color.red);
	
	    if (suit == 1) {
		yTri[0] = 153;
		yTri[1] = 133;
		yTri[2] = 153;
		g.fillPolygon(xTri, yTri, 3);
		yTri[1] = 173;
		g.fillPolygon(xTri, yTri, 3);
	    
		if (number == 1) {
		    g.drawString("A", 552, 110);
		}
	    
		else {
		    g.drawString(""+number, 552, 110);
		}
	    }
	
	    if (suit == 2) {
		yTri[0] = 153;
		yTri[1] = 173;
		yTri[2] = 153;
		g.fillOval(585, 141, 20, 20);
		g.fillOval(596, 141, 20, 20);
		g.fillPolygon(xTri, yTri, 3);
	    
		if (number == 1) {
		    g.drawString("A", 552, 110);
		}
	    
		else {
		    g.drawString(""+number, 552, 110);
		}
	    }
	    g.setColor(Color.black);
	
	    if (suit == 3) {
		yTri[0] = 163;
		yTri[1] = 133;
		yTri[2] = 163;
		g.fillOval(585, 156, 20, 20);
		g.fillOval(596, 156, 20, 20);
		g.fillPolygon(xTri, yTri, 3);
		g.fillRect(598, 174, 5, 10);
	    
		if (number == 1) {
		    g.drawString("A", 552, 110);
		}
	    
		else {
		    g.drawString(""+number, 552, 110);
		}
	    }
	
	    if (suit == 4) {
		g.fillOval(585, 156, 20, 20);
		g.fillOval(596, 156, 20, 20);
		g.fillOval(590, 143, 20, 20);
		g.fillRect(598, 174, 5, 10);
	    
		if (number == 1) {
		    g.drawString("A", 552, 110);
		}
	    
		else {
		    g.drawString(""+number, 552, 110);
		}
	    }
	}
	
	return g;
    }
}
