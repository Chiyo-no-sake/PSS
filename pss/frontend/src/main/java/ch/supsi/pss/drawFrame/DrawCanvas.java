package ch.supsi.pss.drawFrame;

import ch.supsi.pss.drawFrame.tools.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * - not intended to use outside the package -
 * <p>
 * Provide a canvas intended to work with the class 'DrawToolbar'
 * those two items needs infact to be connected with the method: setConnectedToolbar.
 */
public class DrawCanvas extends Canvas {
    private boolean isPortrait;
    private DrawToolbar connectedToolbar;
    private Tool selectedTool;

    private DrawCanvasController controller;

    private Canvas upperCanvas;

    DrawCanvas(double width, double height) {
        super();
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.setHeight(height);
        this.setWidth(width);
        this.isPortrait = false;

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());

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

    // TODO: not working rect overlay
    public void renderTempSquare(double x1, double x2, double width, double height, Paint color){
        upperCanvas.toFront();
        this.upperCanvas.getGraphicsContext2D().setStroke(color);
        this.upperCanvas.getGraphicsContext2D().strokeRect(x1,x2,width,height);
    }

    public void clearTempSquare(){
        this.upperCanvas.getGraphicsContext2D().clearRect(0,0,this.getWidth(), this.getHeight());
    }
}
