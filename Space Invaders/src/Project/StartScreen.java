package Project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class StartScreen extends Application {
    Button submit;
    Button exit;
    @Override
    public void start(Stage stage){
        Pane pane = new Pane();
        pane.setId("backgroundImage");
        submit = new Button("Start");
        exit = new Button("Quit");
        pane.getChildren().addAll(submit, exit);
        submit.setLayoutX(500);
        submit.setLayoutY(300);
        exit.setLayoutX(500);
        exit.setLayoutY(350);
        submit.setFont(Font.font(20));
        exit.setFont(Font.font(20));
        exit.setOnAction(e->{quit(stage);});
        Scene scene = new Scene(pane, 600, 400);
        submit.setOnAction(e->{game(stage,scene);});
        scene.getStylesheets().add("Project/format.css");
        stage.setScene(scene);
        stage.show();
        
    }
    public void game(Stage stage, Scene scene){
        GameScreen game = new GameScreen(stage, scene);
    }
    public void quit(Stage stage){
        stage.close();
    }
    public static void main(String []args){
        launch(args);
    }
}
