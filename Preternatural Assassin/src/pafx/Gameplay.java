/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Mugiesshan
 */
public class Gameplay extends Application {

    private Pane p;
    private Scene scene;
    private EventHandler<ActionEvent> event;
    private Timeline t;
    private Sprite chester;
    private static boolean up = false, down = false, left = false, right = false, move = false, attack = false;
    int op = 0;
    
    public Gameplay() {
        p = new Pane();
        chester = new Sprite("/papicsfx/ChesterSprite.png", Duration.millis(1000), 0, 0);
        chester.setCycleCount(Sprite.INDEFINITE);
        chester.play();
    }
    
    @Override
    public void start(Stage stage) throws Exception
    {
        //this code is for source 8 or higher
        event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                run();
            }
        };
        //this code is for sources lower than version 8
        //event = e->{run();};
        t = new Timeline(new KeyFrame(Duration.millis(10), event));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        p.getChildren().addAll(chester.getRound(), chester.getImageView());
        scene = new Scene(p, 600, 400);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case UP:
                        Gameplay.up = true;
                        move = true;
                        break;
                    case DOWN:
                        Gameplay.down = true;
                        move = true;
                        break;
                    case LEFT:
                        Gameplay.left = true;
                        move = true;
                        break;
                    case RIGHT:
                        Gameplay.right = true;
                        move = true;
                        break;
                    case SPACE:
                        Gameplay.attack = true;
                        break;
                }
            }
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case UP:
                        Gameplay.up = false;
                        move = false;
                        break;
                    case DOWN:
                        Gameplay.down = false;
                        move = false;
                        break;
                    case LEFT:
                        Gameplay.left = false;
                        move = false;
                        break;
                    case RIGHT:
                        Gameplay.right = false;
                        move = false;
                        break;
                    case SPACE:
                        Gameplay.attack = false;
                        break;
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Gameplay g = new Gameplay();
        launch(args);
    }
    
    public void run() {
        if (Gameplay.down) {
            chester.moveDown();
        }
        
        if (Gameplay.up) {
            chester.moveUp();
        }
        
        if (Gameplay.right) {
            chester.moveRight();
        }
        
        if (Gameplay.left) {
            chester.moveLeft();
        }
        chester.setMove(move);
        chester.setAttack(attack);
    }
    
}
