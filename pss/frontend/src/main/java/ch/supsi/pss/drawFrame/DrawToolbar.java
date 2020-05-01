package ch.supsi.pss.drawFrame;

import ch.supsi.pss.drawFrame.tools.Pencil;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Optional;

/**
 * TODO: implement other tools
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
class DrawToolbar extends ToolBar {
    static final int BTN_SIZE = 40;

    static final String PENCIL_ICO = "/icons/pencil.png";
    static final String SQUARE_ICO = "/icons/square.png";
    static final String CIRCLE_ICO = "/icons/circle.png";
    static final String LINE_ICO = "/icons/line.png";
    static final String ERASER_ICO = "/icons/eraser.png";
    static final String CLEAR_ICO = "/icons/clear.png";
    static final String PORTAIT_OFF_ICO = "/icons/non_portrait.png";
    static final String PORTAIT_ON_ICO = "/icons/portrait.png";

    private final ArrayList<ImageButton> btnToolsList;
    private final ColorPicker colorPicker;
    private final ImageButton portraitButton;
    private final ImageButton clearButton;

    private final DrawCanvas connectedCanvas;

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
        
        // ----------------- setup listeners ------------------------

        // unimplemented buttons
        btnToolsList.forEach( b -> {
            b.setOnMouseClicked( e -> {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Feature Coming Soon");
                al.setHeaderText("Feature is coming soon");
                al.setContentText("Please wait for a new version");
                al.showAndWait();
            });
        });

        // Color picker listener
        colorPicker.setOnAction(e -> {
            connectedCanvas.setColor(colorPicker.getValue());
        });

        // PortraitMode button listener
        portraitButton.setOnMouseClicked(e -> {
            if (popConfirmDialog("Are you sure?", "This operation will erase your work", "Are you ok with this?")) {
                connectedCanvas.clearContent();
                connectedCanvas.changeMode(connectedCanvas.isPortrait());
                if (connectedCanvas.isPortrait()) {
                    portraitButton.changeImage(new ImageView(this.getClass().getResource(PORTAIT_ON_ICO).toExternalForm()));
                } else {
                    portraitButton.changeImage(new ImageView(this.getClass().getResource(PORTAIT_OFF_ICO).toExternalForm()));
                }
            }
        });

        // Clear button listener
        clearButton.setOnMouseClicked(e->{
            if (popConfirmDialog("Are you sure?", "This operation will erase your work", "Are you ok with this?")){
                connectedCanvas.clearContent();
            }
        });

        // freeDraw button listener
        btnToolsList.get(0).setOnMouseClicked(e -> {
            resetButtonStatus();
            ((ImageButton)e.getSource()).setSelected(true);
            connectedCanvas.setTool(new Pencil());
        });
    }

    boolean popConfirmDialog(String title, String header, String content) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);

        //Deactivate Defaultbehavior for yes-Button:
        Button yesButton = (Button) alert.getDialogPane().lookupButton( ButtonType.YES );
        yesButton.setDefaultButton( false );

        //Activate Defaultbehavior for no-Button:
        Button noButton = (Button) alert.getDialogPane().lookupButton( ButtonType.CANCEL );
        noButton.setDefaultButton( true );

        final Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    Color getSelectedColor(){
        return colorPicker.getValue();
    }

    private void resetButtonStatus(){
        btnToolsList.forEach( b -> {
            b.setSelected(false);
        });
    }
}
