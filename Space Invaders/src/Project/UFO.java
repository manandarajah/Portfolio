package Project;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
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

public class UFO {

    Image img;
    ImageView iv;
    Rectangle2D r2d, bullet;
    double x, y;
    double dx = 1;
    double dy = 10;
    double speed = 1;
    int count = 0;
    double time;
    Timeline t;
    public UFO(int x, int y, Pane pane){
        this.x = x;
        this.y = y;
        PathTransition pt = new PathTransition();
            this.img = new Image(UFO.class.getResourceAsStream("../spimg/Alien.jpg"));
            this.iv = new ImageView(this.img);
            this.x = 75 *(x+1);
            this.y = y*15 + 10;
            iv.setLayoutX(this.x);
            iv.setLayoutY(this.y);
            EventHandler<ActionEvent> event = e->{moveUFO(pane);};
            t = new Timeline(new KeyFrame(Duration.millis(15) , event));
            t.setCycleCount(Timeline.INDEFINITE);
            t.play();
        pane.getChildren().addAll(iv);
    }
    public ImageView image(){
        return iv;
    }
    public void moveUFO(Pane pane) {
        iv.setLayoutX(iv.getLayoutX()+dx);
        if (iv.getLayoutX() < 25 || iv.getLayoutX() > 550) {
            dx *= -1;
            iv.setLayoutY(iv.getLayoutY()+dy);
        }  
    }
    public Bounds UFOBounds(){
        return iv.getBoundsInParent();
    }
    

    public void animate() {
        count++;
            if (count == 0){
                this.r2d = new Rectangle2D(36, 218, 25, 28);
            }
            else if (count == 1) {
            this.r2d = new Rectangle2D(72, 218, 25, 28);
        }
        
        else if (count == 2) {
            this.r2d = new Rectangle2D(178, 218, 25, 28);
        }
        iv.setViewport(r2d);
    }
}
