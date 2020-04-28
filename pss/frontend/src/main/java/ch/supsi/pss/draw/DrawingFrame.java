package ch.supsi.pss.draw;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class DrawingFrame extends BorderPane {

    private final MyToolBar toolbar;
    private final Canvas canvas;

    public DrawingFrame(double draw_width, double draw_height) {
        this.setWidth(draw_width);
        this.setHeight(draw_height);

        this.setPadding(new Insets(1,1,0,1));

        canvas = new Canvas();
        canvas.setHeight(draw_height);
        canvas.setWidth(draw_width);

        toolbar = new MyToolBar();
        toolbar.setPrefHeight(draw_height);

        this.setLeft(canvas);
        this.setRight(toolbar);
    }
}
