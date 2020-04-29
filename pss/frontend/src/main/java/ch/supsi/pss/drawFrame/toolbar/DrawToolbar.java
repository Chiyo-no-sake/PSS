package ch.supsi.pss.drawFrame.toolbar;

import ch.supsi.pss.drawFrame.draw.DrawCanvas;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * TODO: create Tool abstract class. every button will correspond to a shape,
 *       every Tool will have a few methos: onmousedown, onmousedragged, onmouserelease.
 *       the shape will be extended by each tool, and each tool will immplement his own methods.
 *       those methods will all be used by the DrawCanvas class by calling the
 *              'selectedTool.<eventhandlename>'
 *       where selectedTool is a Tool.
 *
 * TODO: adjust portrait icons to be bigger
 * 
 */



public class DrawToolbar extends ToolBar {
    static final int BTN_SIZE = 40;

    static final String PENCIL_ICO = "/icons/pencil.png";
    static final String SQUARE_ICO = "/icons/square.png";
    static final String CIRCLE_ICO = "/icons/circle.png";
    static final String LINE_ICO = "/icons/line.png";
    static final String ERASER_ICO = "/icons/eraser.png";
    static final String CLEAR_ICO = "/icons/clear.png";
    static final String PORTAIT_OFF_ICO = "/icons/non_portrait.png";
    static final String PORTAIT_ON_ICO = "/icons/portrait.png";

    private final ArrayList<Button> btnToolsList;
    private final ColorPicker colorPicker;
    private final ImageButton portraitButton;
    private final ImageButton clearButton;

    private final DrawCanvas connectedCanvas;

    /**
     * @param connectedCanvas the canvas connected to the Toolbar
     */
    public DrawToolbar(DrawCanvas connectedCanvas) {
        this.btnToolsList = new ArrayList<>();
        this.colorPicker = new ColorPicker();

        this.connectedCanvas = connectedCanvas;
        
        // ----------------- create items and set properties --------------------------
        colorPicker.setPrefWidth(BTN_SIZE);
        colorPicker.setPrefHeight(BTN_SIZE);
        colorPicker.setValue(Color.BLACK);

        portraitButton = new ImageButton(new ImageView(this.getClass().getResource(PORTAIT_OFF_ICO).toExternalForm()));
        portraitButton.setPrefHeight(BTN_SIZE);
        portraitButton.setPrefWidth(BTN_SIZE);

        Region spacer = new Region();
        spacer.setPrefHeight(50);

        Region spacer2 = new Region();
        spacer2.setPrefHeight(25);

        Region spacer3 = new Region();
        spacer3.setPrefHeight(25);

        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(PENCIL_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(LINE_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(SQUARE_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(CIRCLE_ICO).toExternalForm())));
        btnToolsList.add(new ImageButton(new ImageView(this.getClass().getResource(ERASER_ICO).toExternalForm())));

        clearButton = new ImageButton(new ImageView(this.getClass().getResource(CLEAR_ICO).toExternalForm()));
        clearButton.setPrefHeight(BTN_SIZE);
        clearButton.setPrefWidth(BTN_SIZE);
        // ------------------ set toolbox properties ------------------------------
        
        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(10, 10, 20, 10));


        // ------------------ Add items to the toolbox -------------------------
        this.getItems().add(portraitButton);
        this.getItems().add(spacer);
        this.getItems().add(colorPicker);
        this.getItems().add(spacer2);

        btnToolsList.forEach(b -> {
            this.getItems().add(b);
            //set the style for each button
            b.setPrefWidth(BTN_SIZE);
            b.setPrefHeight(BTN_SIZE);
        });

        this.getItems().add(spacer3);
        this.getItems().add(clearButton);
        
        //setup btn listeners
        portraitButton.setOnMouseClicked(e -> {
            connectedCanvas.changeMode(connectedCanvas.isPortrait());

            if(connectedCanvas.isPortrait()){
                portraitButton.changeImage(new ImageView(this.getClass().getResource(PORTAIT_ON_ICO).toExternalForm()));
            }else{
                portraitButton.changeImage(new ImageView(this.getClass().getResource(PORTAIT_OFF_ICO).toExternalForm()));
            }
        });
    }
}
