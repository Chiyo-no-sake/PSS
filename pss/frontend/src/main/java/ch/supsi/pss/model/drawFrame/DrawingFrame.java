package ch.supsi.pss.model.drawFrame;

import ch.supsi.pss.model.drawFrame.canvas.DrawCanvas;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import ch.supsi.pss.model.drawFrame.toolbar.DrawToolbar;
import ch.supsi.pss.model.drawFrame.toolbar.DrawToolbarController;
import ch.supsi.pss.model.drawFrame.toolbar.StrokeSliderController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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

    /*  GENERIFY:
        Here we could generify these 3 objects with 3 interfaces that make you implement the methods that we are
        using inside this class. Then we would create a new class that handle real objects passed to this
     */
    private final DrawToolbar toolbar;
    private final DrawCanvas canvas;

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

        final ScrollPane canvasContainer = new ScrollPane(drawZone);
        canvasContainer.setPadding(new Insets(10, 10, 10, 10));

        toolbar = new DrawToolbar(canvas);
        toolbar.setOrientation(Orientation.VERTICAL);
        toolbar.prefWidthProperty().setValue(30);

        setupToolbarEvents();

        this.setCenter(canvasContainer);
        this.setLeft(toolbar);
    }

    public void bindSizeTo(Pane parent){
        this.prefWidthProperty().bind(parent.widthProperty());
        this.prefHeightProperty().bind(parent.heightProperty());
    }

    private void setupToolbarEvents(){
        DrawToolbarController.getInstance().setOnColorChange(e->{
            canvas.setColor(toolbar.getColorPicker().getValue());
        });

        DrawToolbarController.getInstance().setOnSliderChange(e -> {
            canvas.getGraphicsContext2D().setLineWidth(toolbar.getStrokeSlider().getValue());
        });
    }
}
