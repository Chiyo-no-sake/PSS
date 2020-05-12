package ch.supsi.pss.drawFrame.tools;

import ch.supsi.pss.drawFrame.DrawCanvasController;
import javafx.scene.canvas.Canvas;

public class Pencil extends Tool {
    double prevX, prevY;

    public Pencil() {
        super("pencil");
    }

    @Override
    public void setOnMousePressed() {
        super.onMousePressed = (event) -> {
            Canvas src = DrawCanvasController.getInstance().getDrawCanvas();
            src.getGraphicsContext2D().moveTo(event.getX(), event.getY());
            src.getGraphicsContext2D().stroke();
            prevX = event.getX();
            prevY = event.getY();
        };
    }

    @Override
    public void setOnMouseReleased() {
        super.onMouseReleased = (event) -> {
            //NOP
        };
    }

    @Override
    public void setOnMouseDragged() {
        super.onMouseDragged = (event) -> {
            Canvas src = DrawCanvasController.getInstance().getDrawCanvas();
            src.getGraphicsContext2D().strokeLine(prevX,prevY,event.getX(),event.getY());
            prevX = event.getX();
            prevY = event.getY();
        };
    }
}
