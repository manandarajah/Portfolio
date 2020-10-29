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
public class Baricade {

    private Image img;
    private ImageView iv;
    private Rectangle2D r2d;
    private int hit = 0;
    
    public Baricade(String url, int x, int y) {
        try {
            this.img = new Image(Baricade.class.getResourceAsStream(url));
            this.iv = new ImageView(this.img);
        }
        
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        this.iv.relocate(x, y);
        this.r2d = new Rectangle2D(310, 206, 55, 45);
        this.iv.setViewport(this.r2d);
    }
    
    public ImageView getImageView() {
        return this.iv;
    }
    
    public void hit() {
        this.hit++;
        
        if (this.hit == 1) {
            this.r2d = new Rectangle2D(473, 206, 55, 45);
        }
        
        else if (this.hit == 2) {
            this.r2d = new Rectangle2D(367, 206, 55, 45);
        }
        
        else if (this.hit == 3) {
            this.r2d = new Rectangle2D(419, 206, 55, 45);
        }
        
        else {
            this.r2d = null;
            this.iv = null;
        }
        this.iv.setViewport(this.r2d);
    }
}
