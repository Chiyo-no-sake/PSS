package ch.supsi.pss;

import ch.supsi.pss.misc.PreferencesRepository;
import ch.supsi.pss.sketch.SketchCreator;
import ch.supsi.pss.model.menubar.PssMenuBar;
import ch.supsi.pss.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PssFX extends Application {

    private static final String title = "Personal Sketching System";

    static View galleryView, drawView, tagView;

    public static void main(String[] args) {
        launch(args);
    }

    @java.lang.Override
    public void start(Stage stage) {
        // get window width and height properties
        final double DEF_WIN_WIDTH = Double.parseDouble(
                PreferencesRepository.getAllProperties(true).getProperty("default_app_width"));

        final double DEF_WIN_HEIGHT = Double.parseDouble(
                PreferencesRepository.getAllProperties(true).getProperty("default_app_height"));


        //Setup properties file on host
        PreferencesRepository.copyPropertiesFile();

        stage.setTitle(title);

        // root of the window
        VBox globalVBox = new VBox();

        //  Menu bar
        PssMenuBar menuBar = new PssMenuBar(stage);

        // Root Scene creation
        Scene defaultScene = new Scene(globalVBox, DEF_WIN_WIDTH, DEF_WIN_HEIGHT);

        // Gallery window settings
        galleryView = new GalleryView();

        galleryView.prefWidthProperty().bind(stage.widthProperty());
        galleryView.prefHeightProperty().bind(stage.heightProperty());

        // Tag window settings
        tagView = new TagView();
        tagView.prefHeightProperty().bind(stage.heightProperty());
        tagView.prefWidthProperty().bind(stage.widthProperty());

        // Draw window settings
        drawView = new DrawView();
        drawView.prefHeightProperty().bind(stage.heightProperty());
        drawView.prefWidthProperty().bind(stage.widthProperty());



        //----------------- adding to menuBar to the scene --------------------------
        globalVBox.getChildren().addAll(menuBar);
        stage.setScene(defaultScene);
        stage.sizeToScene();
        stage.show();

        //---------------- view changer setup -----------------------------
        ViewManager.getInstance().setRoot(globalVBox);

        // display draw view
        ViewManager.getInstance().toView(drawView);

        // create new sketch
        SketchCreator.newSketch();
    }

}