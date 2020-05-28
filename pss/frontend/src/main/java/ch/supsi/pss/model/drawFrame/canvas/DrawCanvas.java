package ch.supsi.pss.model.drawFrame.canvas;

import ch.supsi.pss.model.drawFrame.toolbar.tools.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DrawCanvas extends Canvas {
    private boolean containsPaper;
    private final boolean isPortrait;
    private Tool selectedTool;

    private final DrawCanvasController controller;

    // Upper canvas will catch all events, in canvasController, don't use event.src()
    // instead use DrawCanvasController.getDrawCanvas()
    private final Canvas upperCanvas;

    /**
     * create a canvas containing an empty paper with the following data
     * @param width of the new paper
     * @param height of the new paper
     */
    DrawCanvas(double width, double height) {
        super();
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.setHeight(height);
        this.setWidth(width);
        this.isPortrait = false;
        this.containsPaper = true;

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());

        this.selectedTool = null;

        this.controller = DrawCanvasController.getInstance();
        controller.setDrawCanvas(this);

        upperCanvas = new Canvas();
        upperCanvas.setWidth(width);
        upperCanvas.setHeight(height);
    }

    public boolean containsPaper(){
        return this.containsPaper;
    }

    /**
     * Constructor to create a canvas without any paper inside
     */
    public DrawCanvas() {
        super();
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.isPortrait = false;

        this.containsPaper = false;

        this.selectedTool = null;

        this.controller = DrawCanvasController.getInstance();
        controller.setDrawCanvas(this);

        upperCanvas = new Canvas();
    }

    public void createPaper(double width, double height){
        this.containsPaper = true;

        this.clearContent();

        this.setWidth(width);
        this.setHeight(height);

        this.upperCanvas.setWidth(width);
        this.upperCanvas.setHeight(height);

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public Canvas getUpperCanvas() {
        return upperCanvas;
    }

    public void setTool(Tool t) {
        if (this.selectedTool != null) {
            controller.resetMouseHandlers();
        }

        this.selectedTool = t;
        controller.setMouseHandlers(t.getOnMousePressed(), t.getOnMouseDragged(), t.getOnMouseReleased());
    }

    public void setColor(Paint c) {
        this.getGraphicsContext2D().setStroke(c);
    }

    boolean isPortrait() {
        return isPortrait;
    }

    public void clearContent() {
        Paint selectedColor = this.getGraphicsContext2D().getFill();
        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
        this.setColor(selectedColor);
    }

    public void renderTempOval(double x, double y, double width, double height, Paint color) {
        upperCanvas.toFront();
        this.upperCanvas.getGraphicsContext2D().setStroke(color);
        this.upperCanvas.getGraphicsContext2D().strokeOval(x, y, width, height);
    }

    public void renderTempLine(double x1, double y1, double x2, double y2, Paint color) {
        upperCanvas.toFront();
        this.upperCanvas.getGraphicsContext2D().setStroke(color);
        this.upperCanvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }

    public void renderTempSquare(double x, double y, double width, double height, Paint color) {
        upperCanvas.toFront();
        this.upperCanvas.getGraphicsContext2D().setStroke(color);
        this.upperCanvas.getGraphicsContext2D().strokeRect(x, y, width, height);
    }

    public void clearAllTemp() {
        this.upperCanvas.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
    }
}
