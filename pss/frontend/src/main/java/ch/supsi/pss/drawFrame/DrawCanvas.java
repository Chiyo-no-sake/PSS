package ch.supsi.pss.drawFrame;

import ch.supsi.pss.drawFrame.tools.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * - not intended to use outside the package -
 *
 * Provide a canvas intended to work with the class 'DrawToolbar'
 * those two items needs infact to be connected with the method: setConnectedToolbar.
 */
public class DrawCanvas extends Canvas {
    private boolean containsPaper;
    private boolean isPortrait;
    private DrawToolbar connectedToolbar;
    private Tool selectedTool;

    private DrawCanvasController controller;

    // Upper canvas will catch all events, in canvasController, don't use event.src()
    // instad use DrawCanvasController.getDrawCanvas()
    private Canvas upperCanvas;

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
    DrawCanvas() {
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

    void changeMode(boolean isPortrait) {
        double tmp;

        this.isPortrait = !isPortrait;

        this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());

        tmp = this.getWidth();
        this.setWidth(this.getHeight());
        this.setHeight(tmp);
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void createPaper(double width, double height){
        this.containsPaper = true;
        this.setWidth(width);
        this.setHeight(height);
        this.upperCanvas.setWidth(width);
        this.upperCanvas.setHeight(height);

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    Canvas getUpperCanvas() {
        return upperCanvas;
    }

    void setTool(Tool t) {
        if (this.selectedTool != null) {
            controller.resetMouseHandlers();
        }

        this.selectedTool = t;
        controller.setMouseHandlers(t.getOnMousePressed(), t.getOnMouseDragged(), t.getOnMouseReleased());
    }

    void setConnectedToolbar(DrawToolbar connectedToolbar) {
        this.connectedToolbar = connectedToolbar;
    }

    void setColor(Color c) {
        this.getGraphicsContext2D().setStroke(c);
    }

    boolean isPortrait() {
        return isPortrait;
    }

    public void clearContent() {
        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
        this.setColor(connectedToolbar.getSelectedColor());
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
