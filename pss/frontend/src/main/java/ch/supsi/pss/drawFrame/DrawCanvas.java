package ch.supsi.pss.drawFrame;

import ch.supsi.pss.drawFrame.tools.Tool;
import ch.supsi.pss.drawFrame.DrawToolbar;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

class DrawCanvas extends Canvas {
    private boolean isPortrait;
    private DrawToolbar connectedToolbar;
    private Tool selectedTool;

    DrawCanvas(double width, double height) {
        super();
        this.setHeight(height);
        this.setWidth(width);
        this.isPortrait=false;

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());

        this.selectedTool = null;
    }

    void changeMode(boolean isPortrait) {
        double tmp;

        this.isPortrait=!isPortrait;

        this.getGraphicsContext2D().clearRect(0,0,this.getWidth(),this.getHeight());

        tmp = this.getWidth();
        this.setWidth(this.getHeight());
        this.setHeight(tmp);

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    void setTool(Tool t){
        if(this.selectedTool!=null) {
            this.removeEventHandler(MouseEvent.MOUSE_PRESSED, this.selectedTool.getOnMousePressed());
            this.removeEventHandler(MouseEvent.MOUSE_DRAGGED, this.selectedTool.getOnMouseDragged());
            this.removeEventHandler(MouseEvent.MOUSE_RELEASED, this.selectedTool.getOnMouseReleased());
        }

        this.selectedTool = t;
        this.addEventHandler(MouseEvent.MOUSE_PRESSED,  t.getOnMousePressed());
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED,  t.getOnMouseDragged());
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, t.getOnMouseReleased());
    }

    void setConnectedToolbar(DrawToolbar connectedToolbar) {
        this.connectedToolbar = connectedToolbar;
    }

    void setColor(Color c){
        this.getGraphicsContext2D().setStroke(c);
    }

    boolean isPortrait() {
        return isPortrait;
    }

    void clearContent() {
        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
        this.setColor(connectedToolbar.getSelectedColor());
    }
}
