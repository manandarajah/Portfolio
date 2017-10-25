package partner;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Tank {

    private Image img;
    private ImageView iv;
    private Rectangle2D r2d, bullet;
    
    public Tank(Pane pane) {
            img = new Image(Tank.class.getResourceAsStream("../spimg/SpaceInvaders.png"));
            iv = new ImageView(img);
            r2d = new Rectangle2D(274, 222, 39, 28);
            iv.setViewport(this.r2d);
            iv.setTranslateX(250);
            iv.setTranslateY(350);
            //iv.setOnKeyPressed(e -> {movement(e, pane);});
            pane.getChildren().addAll(iv);
    }
    public void movement(KeyEvent e, Pane pane){
        switch (e.getCode()) {
        case LEFT:
            if (iv.getTranslateX() > 25) {
                iv.setTranslateX(iv.getTranslateX() - 20);
            }
            break;
        case RIGHT:
            if (iv.getTranslateX() < 550) {
                iv.setTranslateX(iv.getTranslateX() + 20);
            }
            break;
        case SPACE:
            this.shoot(pane);
        }
    }
     
    public void shoot(Pane pane) {
        ImageView iv2 = new ImageView(img);
        this.bullet = new Rectangle2D(403, 350, 13, 25);
        double y = iv.getTranslateY();
        pane.getChildren().add(iv2);
        
        while (y > 5) {
            y--;
            iv2.relocate(iv.getTranslateX(), y);
            iv2.setViewport(bullet);
        }
    }
    
    public void hit() {
        
    }
}
