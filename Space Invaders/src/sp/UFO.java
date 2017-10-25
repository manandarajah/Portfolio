/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sp;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Mugiesshan Anandarajah
 */
public class UFO {

    private Image img;
    private ImageView iv;
    private Rectangle2D r2d, bullet;
    private int x, y, atype, op = 1, bx, by;
    
    public UFO(String url, int x, int y, int atype) throws Exception {
        try {
            this.img = new Image(UFO.class.getResourceAsStream(url));
            this.iv = new ImageView(this.img);
        }
        
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        this.iv.relocate(x, y);
        this.x = x;
        this.y = y;
        this.atype = atype;
        this.bullet = new Rectangle2D(410, 273, 11, 18);
        
        if (this.atype == 0) {
            this.r2d = new Rectangle2D(36, 218, 25, 28);
        }
        
        else if (this.atype == 1) {
            this.r2d = new Rectangle2D(72, 218, 25, 28);
        }
        
        else if (this.atype == 2) {
            this.r2d = new Rectangle2D(178, 218, 25, 28);
        }
        
        else {
            Exception e = new Exception("ERROR: Alien type num must be in a range of values 0-2");
            throw e;
        }
        
        if (this.r2d != null) {
            this.iv.setViewport(this.r2d);
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public ImageView getImageView() {
        return this.iv;
    }
    
    public void moveLeft() {
        this.x -= 3;
        this.reposition();
    }
    
    public void moveRight() {
        this.x += 3;
        this.reposition();
    }
    
    public void moveDown() {
        this.y += 3;
        this.reposition();
    }
    
    public void reposition() {
        this.iv.relocate(x, y);
    }
    
    public void animate(UFO ufo) {
        int op2 = 0;
        
        if (ufo.atype == 0 && this.op == 1) {
            op2 = 3;
            this.op = 0;
        }
        
        else if (ufo.atype == 0 && this.op == 0) {
            op2 = 36;
            this.op = 1;
        }
        
        else if (ufo.atype == 1 && this.op == 1) {
            op2 = 105;
            this.op = 0;
        }
        
        else if (ufo.atype == 1 && this.op == 0) {
            op2 = 72;
            this.op = 1;
        }
        
        else if (ufo.atype == 2 && this.op == 1) {
            op2 = 146;
            this.op = 0;
        }
        
        else if (ufo.atype == 2 && this.op == 0) {
            op2 = 178;
            this.op = 1;
        }
        this.r2d = new Rectangle2D(op2, 218, 25, 28);
        this.iv.setViewport(r2d);
    }
    
    public void shoot(UFO ufo) {
        this.bx = ufo.x;
        this.by = ufo.y;
        this.iv.setViewport(bullet);
        
        while (this.by != 400) {
            this.by++;
            this.iv.relocate(bx, by);
        }
    }
    
    public void hit() {
        
    }
}
