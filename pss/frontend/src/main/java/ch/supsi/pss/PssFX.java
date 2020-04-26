package ch.supsi.pss;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.*;

public class PssFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @java.lang.Override
    public void start(Stage stage) {
        stage.setTitle("Draw.io");
        StackPane drawPane = new StackPane();
        StackPane galleryPane = new StackPane();

        Button drawBtn = new Button();
        drawBtn.setText("Draw");
        drawBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Draw Scene");
            }
        });
        drawBtn.setVisible(true);

        Button galleryBtn = new Button();
        galleryBtn.setText("Gallery");
        galleryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Gallery Scene");
            }
        });
        galleryBtn.setVisible(true);

        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Drawing saved");
            }

        });

        Canvas canvas = new Canvas(250,250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        HBox sceneChanger = new HBox();
        sceneChanger.getChildren().addAll(drawBtn, galleryBtn);

        drawPane.getChildren().addAll(sceneChanger, canvas, saveBtn);

        galleryPane.getChildren().add(drawBtn);
        galleryPane.getChildren().add(galleryBtn);

        Scene drawScene = new Scene(drawPane);
        Scene galleryScene = new Scene(galleryPane, 300, 300, Color.BLUE);

        stage.setScene(drawScene);
        stage.show();
    }

}