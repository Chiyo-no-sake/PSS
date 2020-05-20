package ch.supsi.pss.drawFrame.tools;

import ch.supsi.pss.drawFrame.DrawCanvas;
import ch.supsi.pss.drawFrame.DrawCanvasController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Line extends Tool {
    double originX, originY;

    public Line() {
        super("line");
    }

    @Override
    void setOnMousePressed() {
        super.onMousePressed = e -> {
            originX = e.getX();
            originY = e.getY();
        };
    }

    @Override
    void setOnMouseDragged() {
        super.onMouseDragged = e -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();

            c.clearAllTemp();
            c.renderTempLine(originX, originY, e.getX(), e.getY(), Color.GRAY);
        };
    }

    @Override
    void setOnMouseReleased() {
        super.onMouseReleased = e -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();
            GraphicsContext gc = c.getGraphicsContext2D();

            c.clearAllTemp();
            gc.strokeLine(originX, originY, e.getX(), e.getY());
        };
    }
}
