package ch.supsi.pss.drawFrame;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

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

    public DrawingFrame(double draw_width, double draw_height) {
        this.setWidth(draw_width);
        this.setHeight(draw_height);

        this.setPadding(new Insets(1, 1, 0, 1));

        canvas = new DrawCanvas(draw_width, draw_height);
        canvas.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;");

        canvasContainer = new ScrollPane(canvas);
        canvasContainer.setPadding(new Insets(10, 10, 10, 10));
        canvasContainer.setMinHeight(draw_height);
        canvasContainer.setMinWidth(draw_width);


        toolbar = new DrawToolbar(canvas);
        toolbar.setPrefHeight(draw_height);

        canvas.setConnectedToolbar(toolbar);

        this.setCenter(canvasContainer);
        this.setRight(toolbar);
    }
}
