/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sp;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Mugiesshan Anandarajah
 */
public class Test extends Application {
    
    private int alienspace = 100;
    private int alienrow = 20;
    private int alientype = 0;
    private ArrayList<UFO> ufo;
    private boolean right = true, left = false;
    private EventHandler<ActionEvent> event;
    private Timeline t;
    private int shooter = 0;
    
    public Test() throws Exception {
        this.ufo = new ArrayList();
        
        for (int i = 0; i < 55; i++) {
            ufo.add(new UFO("../spimg/SpaceInvaders.png", alienspace, alienrow, alientype));
            alienspace += 30;
            
            if (i == 10 || i == 32) {
                alienrow += 30;
                alientype++;
                alienspace = 100;
            }
            
            if (i == 21 || i == 43) {
                alienrow += 30;
                alienspace = 100;
            }
        }
    }

    @Override
    public void start(Stage stage)
    {
        Pane p = new Pane();
        p.setId("background");
        
        this.event = e->{
            run();
            animate();
        };
        this.t = new Timeline(new KeyFrame(Duration.millis(10), event));
        this.t.setCycleCount(Timeline.INDEFINITE);
        this.t.play();
        
        for (int i = 0; i < ufo.size(); i++) {
            p.getChildren().add(ufo.get(i).getImageView());
        }
        Scene scene = new Scene(p, 600, 400);
        scene.getStylesheets().add("sp/Style.css");
        stage.setScene(scene);
        stage.show();
    }
    
    public void animate() {
        for (int i = 0; i < ufo.size(); i++) {
            ufo.get(i).animate(ufo.get(i));
        }
    }
    
    public void run() {
        for (int i = 0; i < ufo.size(); i++) {
            this.shooter = (int) (0 + Math.random() * 1);
            
            if (this.shooter == 1) {
                ufo.get(i).shoot(ufo.get(i));
            }
            
            if (ufo.get(i).getX() >= 600 || ufo.get(i).getX() <= 0) {
                for (int j = 0; j < ufo.size(); j++) {
                    ufo.get(j).moveDown();
                }
                
                if (ufo.get(i).getX() >= 600) {
                    this.right = false;
                    this.left = true;
                }
                
                else if (ufo.get(i).getX() <= 0) {
                    this.right = true;
                    this.left = false;
                }
            }
            
            if (this.right) {
                ufo.get(i).moveRight();
            }
            
            else {
                ufo.get(i).moveLeft();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        launch(args);
    }
}
