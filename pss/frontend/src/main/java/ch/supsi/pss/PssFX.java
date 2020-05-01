package ch.supsi.pss;

import ch.supsi.pss.drawFrame.DrawingFrame;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;



public class PssFX extends Application {

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

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

        // Saving Button
        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(actionEvent -> {
            System.out.println("Drawing saved");

            FileChooser savefile = new FileChooser();
            savefile.setTitle("Save File");

            File file = savefile.showSaveDialog(stage);
            if(file != null){
                try {
                    WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
                    drawFrame.snapshot(null, writableImage);

                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
                    ImageIO.write(renderedImage,"png",file);
                }catch (IOException e){
                    System.out.printf("Error");
                }
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