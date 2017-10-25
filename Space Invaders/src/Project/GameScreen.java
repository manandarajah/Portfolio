package Project;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScreen {
    Pane pane;
    int score = 0;
    double speed = 1;
    Tank tank[] = new Tank[1];
    int ufoAmount = 30;
    ArrayList<UFO> ufo = new ArrayList();
    Timeline t = new Timeline();
    Timeline ti = new Timeline();
    boolean speedInc;
    Text label;
    private Stage stage;
    private Scene scene;
    public GameScreen(Stage stage, Scene scene){
        this.scene = scene;
        this.stage = stage;
        pane = new Pane(); 
        label = new Text("0");
        pane.setStyle("-fx-background-color: BLACK");
        tank[0] = new Tank(pane);
        
        for (int i = 0; i < ufoAmount; i++)
        {
            
            int x = i%5;
            int y = i%7;
            ufo.add(new UFO(x, y, pane));
        }
        label.setLayoutX(550);
        label.setLayoutY(10);
        label.setFill(Color.LIGHTGRAY);
        pane.getChildren().addAll(label);
        scene = new Scene(pane, 600, 400);
        scene.setOnKeyPressed(e -> {tankMove(e);});
        //scene.setOnKeyPressed(e -> {tankShot(e);});
        EventHandler<ActionEvent> event = e->{hit();};
        t = new Timeline(new KeyFrame(Duration.millis(1) , event));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        stage.setScene(scene);
        stage.show();
    }
    public void tankMove(KeyEvent e){
        tank[0].movement(pane, e);
    }
    public void hit(){
        for (int i = 0; i < ufo.size(); i++){
        if(ufo.size()==0 || tank[0].HitTank(pane, ufo.get(i))){
                    for (int m = 0; m < ufo.size(); m++){
                    ufo.remove(m);
                    EndScreen end = new EndScreen(score, scene, stage);
                    }
        }
        }
        for (int i = 0; i < ufo.size(); i++){
            speedInc = tank[0].Hit(pane, ufo.get(i));
            if (speedInc == true){
                score+=100*tank[0].getIncre();
                label.setText(String.valueOf(score));
                speed*=1.05;
                ufo.remove(i);
                for (int l = 0; l < ufo.size(); l++){
                ufo.get(l).t.setRate(speed);
                }
                }
            }
    } 
}
