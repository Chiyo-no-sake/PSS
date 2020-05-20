package ch.supsi.pss.drawFrame;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * modularized class, create an object child of Pane (BorderPane) composed by 3 main parts:
 *      - a scrollview to contain the canvas
 *      - a personal canvas with some custom methods
 *      - a personal toolbar with some custom buttons
 */
public class DrawingFrame extends BorderPane {

    private final ScrollPane canvasContainer;
    private final DrawToolbar toolbar;
    private final DrawCanvas canvas;

    /*
     * explicit size constructor, instantly add a new non portrait paper to the canvas
     * @param draw_width explicit width of the draw
     * @param draw_height explicit height of the draw
     */
    public DrawingFrame(double draw_width, double draw_height) {
        this.setPadding(new Insets(1, 1, 0, 1));

        canvas = new DrawCanvas(draw_width, draw_height);
        canvas.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;");

        Pane drawZone = new Pane();
        drawZone.getChildren().add(canvas);
        drawZone.getChildren().add(canvas.getUpperCanvas());

        canvasContainer = new ScrollPane(drawZone);
        canvasContainer.setPadding(new Insets(10, 10, 10, 10));

        toolbar = new DrawToolbar(canvas);
        toolbar.setPrefHeight(draw_height);

        canvas.setConnectedToolbar(toolbar);

        this.setCenter(canvasContainer);
        this.setRight(toolbar);
    }

    /**
     * create the frame with an empty canvas
     */
    public DrawingFrame() {
        this.setPadding(new Insets(1, 1, 0, 1));

        canvas = new DrawCanvas();
        canvas.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;");

        Pane drawZone = new Pane();
        drawZone.getChildren().add(canvas);
        drawZone.getChildren().add(canvas.getUpperCanvas());

        canvasContainer = new ScrollPane(drawZone);
        canvasContainer.setPadding(new Insets(10, 10, 10, 10));

        toolbar = new DrawToolbar(canvas);

        canvas.setConnectedToolbar(toolbar);

        this.setCenter(canvasContainer);
        this.setRight(toolbar);
    }

    public void bindSizeTo(Pane parent){
        this.prefWidthProperty().bind(parent.widthProperty());
        this.prefHeightProperty().bind(parent.heightProperty());
    }
}
