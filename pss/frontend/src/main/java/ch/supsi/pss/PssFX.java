package ch.supsi.pss;

import ch.supsi.pss.drawFrame.DrawingFrame;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PssFX extends Application {

    static final int WIDTH = 1366;
    static final int HEIGHT = 768;

    public static void main(String[] args) {
        launch(args);
    }

    @java.lang.Override
    public void start(Stage stage) {
        stage.setTitle("Draw.io");
        GridPane drawPane = new GridPane();
        GridPane galleryPane = new GridPane();

        Scene drawScene = new Scene(drawPane, Color.BLACK);
        Scene galleryScene = new Scene(galleryPane, Color.BLUE);

        //Labels
        Label drawTitle = new Label("Draw Window");
        Label galleryTitle = new Label("Gallery Window");

        //CanvasPane instantiation

        DrawingFrame drawFrame = new DrawingFrame(WIDTH,HEIGHT);
        drawFrame.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;" +
                "-fx-background-color: #c0c0c0;");

        //Buttons
        Button drawBtn = new Button();
        drawBtn.setText("Go to Draw Window");
        drawBtn.setOnAction(actionEvent -> stage.setScene(drawScene));

        Button galleryBtn = new Button();
        galleryBtn.setText("Go to Gallery Window");
        galleryBtn.setOnAction(actionEvent -> {
            stage.setScene(galleryScene);
            stage.sizeToScene();
        });

        SketchController sketchController = new SketchController();
        // Saving Button

        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(actionEvent -> {
            System.out.println("Drawing saved");
            PreferencesRepository.setRepository(stage);

            sketchController.newSketch(drawFrame);

            if(sketchController.getSketchService().saveSketch()){
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle(null);
                al.setHeaderText(null);
                al.setContentText("Sketch salvato corretamente.");
                al.setResizable(true);
                al.showAndWait();
            }

            stage.sizeToScene();
        });

        //Search Field
        TextField search = new TextField();

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

        //Draw Window Layout
        VBox vbox = new VBox();
        vbox.prefWidthProperty().bind(stage.widthProperty());
        vbox.prefHeightProperty().bind(stage.heightProperty());
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 20, 10, 20));
        vbox.getChildren().addAll(drawTitle, galleryBtn, drawFrame, saveBtn);
        vbox.setAlignment(Pos.CENTER);
        drawPane.getChildren().add(vbox);


        stage.setScene(drawScene);
        stage.sizeToScene();
        stage.show();
    }

}