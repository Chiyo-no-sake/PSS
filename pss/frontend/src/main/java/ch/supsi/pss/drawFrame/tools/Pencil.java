package ch.supsi.pss.drawFrame.tools;

import ch.supsi.pss.drawFrame.DrawCanvasController;
import javafx.scene.canvas.Canvas;

public class Pencil extends Tool {
    public Pencil() {
        super("pencil");
    }

    @Override
    public void setOnMousePressed() {
        super.onMousePressed = (event) -> {
            Canvas src = DrawCanvasController.getInstance().getDrawCanvas();
            src.getGraphicsContext2D().moveTo(event.getX(), event.getY());
            src.getGraphicsContext2D().beginPath();
            src.getGraphicsContext2D().stroke();
        };
    }

    @Override
    public void setOnMouseReleased() {
        super.onMouseReleased = (event) -> {
            DrawCanvasController.getInstance().getDrawCanvas().getGraphicsContext2D().closePath();
        };
    }

    @Override
    public void setOnMouseDragged() {
        super.onMouseDragged = (event) -> {
            Canvas src = DrawCanvasController.getInstance().getDrawCanvas();
            src.getGraphicsContext2D().lineTo(event.getX(), event.getY());
            src.getGraphicsContext2D().moveTo(event.getX(), event.getY());
            src.getGraphicsContext2D().stroke();
        };
    }
}
