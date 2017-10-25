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
public class Tank {

    private Image img;
    private ImageView iv;
    private Rectangle2D r2d, bullet;
    private int x;
    private final int y = 300;
    
    public Tank(String url, int x) {
        try {
            this.img = new Image(Tank.class.getResourceAsStream(url));
            this.iv = new ImageView(img);
        }
        
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        this.x = x;
        this.r2d = new Rectangle2D(274, 222, 39, 28);
        this.iv.setViewport(this.r2d);
        this.bullet = new Rectangle2D(178, 440, 14, 7);
        this.iv.relocate(x, y);
    }
    
    public ImageView getImageView() {
        return this.iv;
    }
    
    public void moveLeft() {
        this.x--;
        this.reposition();
    }
    
    public void moveRight() {
        this.x++;
        this.reposition();
    }
    
    public void reposition() {
        this.iv.relocate(this.x, this.y);
    }
    
    public void shoot() {
        
    }
    
    public void hit() {
        
    }
}
