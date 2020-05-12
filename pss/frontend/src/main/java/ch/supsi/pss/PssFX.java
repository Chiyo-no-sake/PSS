package ch.supsi.pss;

import ch.supsi.pss.drawFrame.DrawingFrame;
import ch.supsi.pss.menubar.PssMenuBar;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PssFX extends Application {

    private static final int DEF_WIN_WIDTH = 800;
    private static final int DEF_WIN_HEIGHT = 600;

    private static final int DRAW_WIDTH = 1366;
    private static final int DRAW_HEIGHT = 768;

    public static void main(String[] args) {
        launch(args);
    }

    @java.lang.Override
    public void start(Stage stage) {
        PreferencesRepository.copyPropertiesFile();
        stage.setTitle("Draw.io");

        // ------------ Gallery window settings and elements creation  -----------
        VBox VerticalBoxGallery = new VBox();

        // Label for the title of gallery mode
        Label galleryTitle = new Label("Gallery Window");

        // Button-port to draw window
        Button drawBtn = new Button();
        drawBtn.setText("Go to Draw Window");

        // Search Field
        TextField search = new TextField();

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

        drawFrame.bindSizeTo(VerticalBoxDraw);

        // DrawPane will automatically resize basing on stage size
        VerticalBoxDraw.setAlignment(Pos.TOP_CENTER);
        VerticalBoxDraw.setSpacing(20);
        VerticalBoxDraw.prefHeightProperty().bind(stage.heightProperty());
        VerticalBoxDraw.prefWidthProperty().bind(stage.widthProperty());

        // ------------------- Scene creation ------------------------------------
        Scene defaultScene = new Scene(VerticalBoxDraw, DEF_WIN_WIDTH, DEF_WIN_HEIGHT);

        // -------------------- Menu bars ----------------------------------------
        PssMenuBar menuBar_draw = new PssMenuBar(stage, VerticalBoxGallery, VerticalBoxDraw, false);
        PssMenuBar menuBar_gallery = new PssMenuBar(stage, VerticalBoxGallery, VerticalBoxDraw, true);

        //----------------- adding elements to Gallery view -------------------
        //TODO gallery pane insertion, when gallery is ready
        VerticalBoxGallery.getChildren().addAll(menuBar_gallery, galleryTitle, search);


        //----------------- adding elements to draw view -------------------
        VerticalBoxDraw.getChildren().addAll(menuBar_draw, drawTitle, drawFrame);

        stage.setScene(defaultScene);
        stage.sizeToScene();
        stage.show();
    }

}