package ch.supsi.pss.model.drawFrame.tools;

import ch.supsi.pss.model.drawFrame.DrawCanvasController;
import javafx.scene.canvas.Canvas;

public class Pencil extends Tool {
    double prevX, prevY;

    public Pencil() {
        super("pencil");

        this.setOnMousePressed((event) -> {
            Canvas src = DrawCanvasController.getInstance().getDrawCanvas();
            src.getGraphicsContext2D().moveTo(event.getX(), event.getY());
            src.getGraphicsContext2D().stroke();
            prevX = event.getX();
            prevY = event.getY();
        });

        this.setOnMouseDragged(event -> {
            Canvas src = DrawCanvasController.getInstance().getDrawCanvas();
            src.getGraphicsContext2D().strokeLine(prevX, prevY, event.getX(), event.getY());
            prevX = event.getX();
            prevY = event.getY();
        });

        this.setOnMouseReleased(e -> {
        });
    }
}
