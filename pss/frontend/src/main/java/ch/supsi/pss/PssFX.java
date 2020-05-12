package ch.supsi.pss;

import ch.supsi.pss.drawFrame.DrawingFrame;
import ch.supsi.pss.menubar.PssMenuBar;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
=======
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
>>>>>>> origin/feature_menubar
import javafx.stage.Stage;

public class PssFX extends Application {

<<<<<<< HEAD
    static final int WIDTH = 1366;
    static final int HEIGHT = 768;
=======
    private static final int DEF_WIN_WIDTH = 800;
    private static final int DEF_WIN_HEIGHT = 600;

    private static final int DRAW_WIDTH = 1366;
    private static final int DRAW_HEIGHT = 768;
>>>>>>> origin/feature_menubar

    public static void main(String[] args) {
        launch(args);
    }

    @java.lang.Override
    public void start(Stage stage) {
        stage.setTitle("Draw.io");

        // ------------ Gallery window settings and elements creation  -----------
        VBox VerticalBoxGallery = new VBox();

        // Label for the title of gallery mode
        Label galleryTitle = new Label("Gallery Window");

<<<<<<< HEAD
        //CanvasPane instantiation

        DrawingFrame drawFrame = new DrawingFrame(WIDTH,HEIGHT);
        drawFrame.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;" +
                "-fx-background-color: #c0c0c0;");

        //Buttons
=======
        // Button-port to draw window
>>>>>>> origin/feature_menubar
        Button drawBtn = new Button();
        drawBtn.setText("Go to Draw Window");

        // Search Field
        TextField search = new TextField();

<<<<<<< HEAD
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
=======
        // Gallery Window Layout
        VerticalBoxGallery.setAlignment(Pos.TOP_CENTER);
        VerticalBoxGallery.prefWidthProperty().bind(stage.widthProperty());
        VerticalBoxGallery.prefHeightProperty().bind(stage.heightProperty());
        VerticalBoxGallery.setSpacing(20);

        // ----------------- Draw window settings and elements creation  ----------------
        VBox VerticalBoxDraw = new VBox();

        // Label for the draw mode
        Label drawTitle = new Label("Draw Window");

        //CanvasPane instantiation
        DrawingFrame drawFrame = new DrawingFrame(DRAW_WIDTH, DRAW_HEIGHT);
        drawFrame.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;" +
                "-fx-background-color: #c0c0c0;");
>>>>>>> origin/feature_menubar

        drawFrame.bindSizeTo(VerticalBoxDraw);

        // DrawPane will automatically resize basing on stage size
        VerticalBoxDraw.setAlignment(Pos.TOP_CENTER);
        VerticalBoxDraw.setSpacing(20);
        VerticalBoxDraw.prefHeightProperty().bind(stage.heightProperty());
        VerticalBoxDraw.prefWidthProperty().bind(stage.widthProperty());

        // ------------------- Scene creation ------------------------------------
        Scene defaultScene = new Scene(VerticalBoxDraw, DEF_WIN_WIDTH, DEF_WIN_HEIGHT);

        // -------------------- Menu bars ----------------------------------------
        PssMenuBar menuBar_draw = new PssMenuBar(defaultScene, VerticalBoxGallery, VerticalBoxDraw, false);
        PssMenuBar menuBar_gallery = new PssMenuBar(defaultScene, VerticalBoxGallery, VerticalBoxDraw, true);

        //----------------- adding elements to Gallery view -------------------
        //TODO gallery pane insertion, when gallery is ready
        VerticalBoxGallery.getChildren().addAll(menuBar_gallery,galleryTitle, search);


        //----------------- adding elements to draw view -------------------
        VerticalBoxDraw.getChildren().addAll(menuBar_draw, drawTitle, drawFrame);

        stage.setScene(defaultScene);
        stage.sizeToScene();
        stage.show();
    }

}