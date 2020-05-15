package ch.supsi.pss;

import ch.supsi.pss.drawFrame.DrawingFrame;
import ch.supsi.pss.helpers.SketchCreator;
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

    private static final String title = "Personal Sketching System";

    public static void main(String[] args) {
        launch(args);
    }

    @java.lang.Override
    public void start(Stage stage) {
        final double DEF_WIN_WIDTH = Double.parseDouble(
                PreferencesRepository.getAllProperties(true).getProperty("default_app_width"));

        final double DEF_WIN_HEIGHT = Double.parseDouble(
                PreferencesRepository.getAllProperties(true).getProperty("default_app_height"));


        PreferencesRepository.copyPropertiesFile();
        LanguageController languageController = LanguageController.getInstance();
        stage.setTitle(title);

        VBox globalVBox = new VBox();

        // ------------ Gallery window settings and elements creation  -----------
        VBox verticalBoxGallery = new VBox();

        // Label for the title of gallery mode
        Label galleryTitle = new Label(languageController.getString("gallery"));

        // Search Field
        TextField search = new TextField();

        // Gallery Window Layout
        verticalBoxGallery.setAlignment(Pos.TOP_CENTER);
        verticalBoxGallery.prefWidthProperty().bind(stage.widthProperty());
        verticalBoxGallery.prefHeightProperty().bind(stage.heightProperty());
        verticalBoxGallery.setSpacing(20);

        // ----------------- Draw window settings and elements creation  ----------------
        VBox verticalBoxDraw = new VBox();

        // Label for the draw mode
        Label drawTitle = new Label(languageController.getString("draw"));

        //CanvasPane instantiation
        DrawingFrame drawFrame = new DrawingFrame();
        drawFrame.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;" +
                "-fx-background-color: #c0c0c0;");

        drawFrame.bindSizeTo(verticalBoxDraw);

        // DrawPane will automatically resize basing on stage size
        verticalBoxDraw.setAlignment(Pos.TOP_CENTER);
        verticalBoxDraw.setSpacing(20);
        verticalBoxDraw.prefHeightProperty().bind(stage.heightProperty());
        verticalBoxDraw.prefWidthProperty().bind(stage.widthProperty());

        // ------------------- Scene creation ------------------------------------
        Scene defaultScene = new Scene(globalVBox, DEF_WIN_WIDTH, DEF_WIN_HEIGHT);

        // -------------------- Menu bars ----------------------------------------
        PssMenuBar menuBar= new PssMenuBar(stage, globalVBox, verticalBoxGallery, verticalBoxDraw, false);

        //----------------- adding elements to Gallery view -------------------
        //TODO gallery pane insertion, when gallery is ready
        verticalBoxGallery.getChildren().addAll(galleryTitle, search);


        //----------------- adding elements to draw view -------------------
        verticalBoxDraw.getChildren().addAll(drawTitle, drawFrame);

        //----------------- adding to global vbox --------------------------
        globalVBox.getChildren().addAll(menuBar, verticalBoxDraw);
        stage.setScene(defaultScene);
        stage.sizeToScene();
        stage.show();

        SketchCreator.newSketch();
    }

}