package Project;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Tank {

    private Image img, img2;
    private ImageView iv, iv2;
    private Rectangle2D r2d, bullet;
    private boolean fire = false;
    private Timeline t;
    int incre = 1;
    public Tank(Pane pane) {
            img = new Image(Tank.class.getResourceAsStream("../spimg/Tank.jpg"));
            iv = new ImageView(img);
            
            iv.setLayoutX(250);
            iv.setLayoutY(350);
            pane.getChildren().addAll(iv);
    }
    public int getIncre(){
        return incre;
    }
    public void movement(Pane pane, KeyEvent e){
        
        switch (e.getCode()) {
        case LEFT:
            
            if (iv.getLayoutX()> 25) {
                iv.setLayoutX(iv.getLayoutX() - 20);
            }
            break;
        case RIGHT:
            if (iv.getTranslateX() < 550) {
                iv.setLayoutX(iv.getLayoutX() + 20);
            }
            break;
        case SPACE:
            shoot(pane);
            break;
        }
    }

    public void shoot(Pane pane) {
        
        if (fire == false)
        {
            fire = true;
        Timeline time = new Timeline();
        img2 = new Image(Tank.class.getResourceAsStream("../spimg/Bullet.jpg"));
        iv2 = new ImageView(img2);
        double y = iv.getLayoutY();
        iv2.relocate(iv.getLayoutX(), y);
        pane.getChildren().add(iv2);
        EventHandler<ActionEvent> event = e->{bullet(iv2, pane);};
        t = new Timeline(new KeyFrame(Duration.millis(4) , event));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        }
    }
    public Bounds getBulletBounds(){
        return iv2.getLayoutBounds();
    }
    
    public boolean getFire(){
        return fire;
    }
    public void removeBullet(Pane pane){
        pane.getChildren().remove(iv2);
    }
    public boolean Hit(Pane pane, UFO ufo){
        if (fire == true){
                if (iv2.getBoundsInParent().intersects(ufo.UFOBounds())){
                    pane.getChildren().remove(iv2);
                    ufo.iv.setImage(null);
                    iv2.setImage(null);
                    fire = false;
                    ufo.t.stop();
                    t.stop();
                    pane.getChildren().remove(ufo.iv);
                    incre++;
                    return true;
                    
                }
            }
        return false;
    }
    public boolean HitTank(Pane pane, UFO ufo){
                if (iv.getBoundsInParent().intersects(ufo.UFOBounds())){
                    ufo.t.stop();
                    return true;
                    
            }
        return false;
    }
    public void bullet(ImageView iv2, Pane pane){
            iv2.setLayoutY(iv2.getLayoutY()-1);
            if(iv2.getLayoutY() < -50){
            incre = 1;
            pane.getChildren().remove(iv2);
            fire = false;
            t.stop();
            }
    }
}
