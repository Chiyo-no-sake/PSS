package ch.supsi.pss;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

public class PssFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @java.lang.Override
    public void start(Stage stage) {
        stage.setTitle("Draw.io");
        GridPane drawPane = new GridPane();
        GridPane galleryPane = new GridPane();

        Scene drawScene = new Scene(drawPane, 800, 600, Color.BLACK);
        Scene galleryScene = new Scene(galleryPane, 800, 600, Color.BLUE);

        //Labels
        Label drawTitle = new Label("Draw Window");
        Label galleryTitle = new Label("Gallery Window");

        //Buttons
        Button drawBtn = new Button();
        drawBtn.setText("Go to Draw Window");
        drawBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(drawScene);
            }
        });

        Button galleryBtn = new Button();
        galleryBtn.setText("Go to Gallery Window");
        galleryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(galleryScene);
                stage.sizeToScene();
            }
        });

        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Drawing saved");
                stage.sizeToScene();
            }

        });

        //Canvas
        Pane canvasPane = new Pane();
        Canvas canvas = new Canvas();
        canvas.setHeight(500);
        canvas.setWidth(canvasPane.getWidth());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvasPane.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;" +
                "-fx-background-color: white;");
        canvasPane.getChildren().add(canvas);

        //Search Field
        TextField search = new TextField();

        //Draw Window Layout
        VBox vbox = new VBox();
        vbox.prefWidthProperty().bind(stage.widthProperty());
        vbox.prefHeightProperty().bind(stage.heightProperty());
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 20, 10, 20));
        vbox.getChildren().addAll(drawTitle, galleryBtn, canvasPane, saveBtn);
        vbox.setAlignment(Pos.CENTER);

        drawPane.getChildren().add(vbox);

        //Gallery Window Layout
        VBox vbox2 = new VBox();
        vbox2.prefWidthProperty().bind(stage.widthProperty());
        vbox2.prefHeightProperty().bind(stage.heightProperty());
        vbox2.setSpacing(10);
        vbox2.setPadding(new Insets(0, 20, 10, 20));
        HBox images = new HBox();
        vbox2.getChildren().addAll(galleryTitle, drawBtn, search, images);
        vbox2.setAlignment(Pos.CENTER);
        galleryPane.getChildren().add(vbox2);

        stage.setScene(drawScene);
        stage.sizeToScene();
        stage.show();
    }

}