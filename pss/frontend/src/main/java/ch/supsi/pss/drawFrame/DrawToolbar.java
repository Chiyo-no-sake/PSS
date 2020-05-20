package ch.supsi.pss.drawFrame;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 *
 * TODO: adjust portrait icons to be bigger
 *
 */


/**
 * - not intended to use outside the package -
 *
 * intended to work with a connected DrawCanvas, this last need to be passed in the constructor
 *
 * provide a toolbar with some tools in it and some buttons mapped to em.
 * provide also a portrait mode button, a clear button and a color picker.
 */
public class DrawToolbar extends ToolBar {
    private static final int BTN_SIZE = 40;
    private static final int SLIDER_HEIGHT = 150;
    private static final int SPACER1_HEIGHT = 12;
    private static final int SPACER2_HEIGHT = 12;

    private static final int STROKE_DEF_THICK = 3;

    private static final String PENCIL_ICO = "/icons/pencil.png";
    private static final String SQUARE_ICO = "/icons/square.png";
    private static final String CIRCLE_ICO = "/icons/circle.png";
    private static final String LINE_ICO = "/icons/line.png";
    private static final String ERASER_ICO = "/icons/eraser.png";

    private final ArrayList<ImageButton> btnToolsList;
    private final ColorPicker colorPicker;

    private final DrawToolbarController controller;
    private final DrawCanvas connectedCanvas;

    private final Slider strokeSlider;

    /**
     * @param connectedCanvas the canvas connected to the Toolbar
     */
    DrawToolbar(DrawCanvas connectedCanvas) {
        this.btnToolsList = new ArrayList<>();
        this.colorPicker = new ColorPicker();

        this.connectedCanvas = connectedCanvas;

        // ----------------- create items and set properties --------------------------
        colorPicker.setPrefWidth(BTN_SIZE);
        colorPicker.setPrefHeight(BTN_SIZE);
        colorPicker.setValue(Color.BLACK);

        Region spacer1 = new Region();
        spacer1.setPrefHeight(SPACER1_HEIGHT);

        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(PENCIL_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(LINE_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(SQUARE_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(CIRCLE_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(ERASER_ICO).toExternalForm())));

        Region spacer2 = new Region();
        spacer2.setPrefHeight(SPACER2_HEIGHT);

        strokeSlider = new Slider();
        strokeSlider.setMin(1);
        strokeSlider.setMax(25);
        strokeSlider.setValue(STROKE_DEF_THICK);
        strokeSlider.setOrientation(Orientation.VERTICAL);
        strokeSlider.setPrefHeight(SLIDER_HEIGHT);
        strokeSlider.setShowTickMarks(true);
        strokeSlider.setShowTickLabels(true);
        connectedCanvas.getGraphicsContext2D().setLineWidth(strokeSlider.getValue());

        updateButtonStatus();

        // ------------------ set toolbox properties ------------------------------

        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(10, 10, 20, 10));


        // ------------------ Add items to the toolbox -------------------------
        this.getItems().add(colorPicker);
        this.getItems().add(spacer1);

        btnToolsList.forEach(b -> {
            this.getItems().add(b);
            //set the style for each tool-button
            b.setPrefWidth(BTN_SIZE);
            b.setPrefHeight(BTN_SIZE);
        });

        this.getItems().add(spacer2);
        this.getItems().add(strokeSlider);

        // ---------------- Create Controller -----------------------------------
        this.controller = DrawToolbarController.getInstance();
        this.controller.setToolBar(this);
        controller.setupListeners();
    }

    public void updateButtonStatus(){
        btnToolsList.forEach(b -> b.setDisable(!connectedCanvas.containsPaper()));
    }


    public Slider getStrokeSlider(){
        return strokeSlider;
    }

    Color getSelectedColor(){
        return colorPicker.getValue();
    }

    ArrayList<ImageButton> getBtnToolsList(){
        return this.btnToolsList;
    }

    ColorPicker getColorPicker() {
        return colorPicker;
    }

    DrawCanvas getConnectedCanvas() {
        return connectedCanvas;
    }

}
