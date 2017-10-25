/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package partner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Mugiesshan Anandarajah
 */
public class Test extends Application {

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane pane = new Pane();
        Tank tank = new Tank(pane);
        Scene scene = new Scene(pane, 600, 400);
        stage.setScene(scene);
        scene.setOnKeyPressed(e->tank.movement(e, pane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
