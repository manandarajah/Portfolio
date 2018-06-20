/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pafx;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.paint.Color.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Mugiesshan
 */
public class Sprite extends Transition {

    private final Image img;
    private final ImageView iv;
    private Rectangle2D r2d;
    private Rectangle r;
    private boolean move = false, attack = false;
    private int x, y, count = 0, second = 0, col, row, hitop = 0;
    private final int width = 60, height = 180;
    
    public Sprite(String url, Duration d, int x, int y) {
        this.img = new Image(Sprite.class.getResourceAsStream(url));
        this.iv = new ImageView(img);
        this.iv.relocate(x, y);
        this.x = x;
        this.y = y;
        this.r2d = new Rectangle2D(64, 0, this.width, this.height);
        this.r = new Rectangle(1000,1000,WHITE);
        this.iv.setViewport(this.r2d);
        this.setCycleDuration(d);
        this.setInterpolator(Interpolator.LINEAR);
    }
    
    public Rectangle getRound() {
        return this.r;
    }
    
    public ImageView getImageView() {
        return this.iv;
    }
    
    public void setMove(boolean move) {
        this.move = move;
    }
    
    public void setAttack(boolean attack) {
        this.attack = attack;
    }
    
    public void moveUp() {
        this.y--;
        this.row = this.height*3;
        this.reposition(x, y);
    }
    
    public void moveDown() {
        this.y++;
        this.row = 0;
        this.reposition(x, y);
    }
    
    public void moveLeft() {
        this.x--;
        this.row = this.height;
        this.reposition(x, y);
    }
    
    public void moveRight() {
        this.x++;
        this.row = this.height*2;
        this.reposition(x, y);
    }
    
    public void reposition(int x, int y) {
        this.getImageView().relocate(x, y);
    }

    @Override
    protected void interpolate(double d)
    {   
        if (move) {
            if (this.count % 2 == 0 && this.count != 8 && this.count != 6) {
                this.col = this.width*3;
            }

            else if (this.count == 3) {
                this.col = this.width*4;
            }

            else if (this.count % 2 == 0) {
                this.col = this.width;
            }

            else if (this.count == 7) {
                this.col = 0;
            }

            else {
                this.col = this.width*2;
            }
        }
        
        else {
            this.col = this.width*2;
        }
        
        if (attack) {
            this.row = this.height*4;
            this.r2d = new Rectangle2D(0, this.row, this.width, this.height);
            this.iv.setViewport(r2d);
            
            if (this.hitop % 2 == 0) {
                this.col = this.width*2;
            }
            
            else {
                this.col = this.width;
            }
        }
        this.r2d = new Rectangle2D(this.col, this.row, this.width, this.height);
        this.second++;
        
        if (this.second == 7) {
            this.count++;
            this.hitop++;
            this.second = 0;
            
            if (this.count == 9) {
                this.count = 1;
            }
        }
        this.iv.setViewport(this.r2d);
    }
    
}
