package Project;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndScreen {
    Pane pane;
    Button tryAgain, exit;
    Stage stage;
    Scene scene;
    public EndScreen(int score, Scene scene , Stage stage){
        this.scene = scene;
        this.stage = stage;
        pane = new Pane();
        pane.setId("backgroundImage");
        Text text = new Text("Score: " + String.valueOf(score));
        text.setFont(Font.font("Bradley Hand ITC", FontWeight.THIN, 50));
        text.setFill(Color.LIGHTGRAY);
        text.setLayoutX(200);
        text.setLayoutY(150);
        tryAgain = new Button("Try Again");
        exit = new Button("Quit");
        tryAgain.setLayoutX(500);
        tryAgain.setLayoutY(300);
        exit.setLayoutX(500);
        exit.setLayoutY(350);
        tryAgain.setFont(Font.font(20));
        exit.setFont(Font.font(20));
        exit.setOnAction(e->{quit(stage);});
        pane.getChildren().addAll(text, tryAgain, exit);
        scene = new Scene(pane, 600, 400);
        tryAgain.setOnAction(e->{game();});
        scene.getStylesheets().add("Project/format.css");
        stage.setScene(scene);
        stage.show();
        
    }
    public void game(){
        GameScreen game = new GameScreen(stage, scene);
    }
    public void quit(Stage stage){
        stage.close();
    }
}