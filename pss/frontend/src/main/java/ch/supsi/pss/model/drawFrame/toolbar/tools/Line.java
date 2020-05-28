package ch.supsi.pss.model.drawFrame.toolbar.tools;

import ch.supsi.pss.model.drawFrame.canvas.DrawCanvas;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Line extends Tool {
    double originX, originY;

    public Line() {
        super("line");

        this.setOnMousePressed(e -> {
            originX = e.getX();
            originY = e.getY();
        });

        this.setOnMouseDragged(e -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();

            c.clearAllTemp();
            c.renderTempLine(originX, originY, e.getX(), e.getY(), Color.GRAY);
        });

        this.setOnMouseReleased(e -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();
            GraphicsContext gc = c.getGraphicsContext2D();

            c.clearAllTemp();
            gc.strokeLine(originX, originY, e.getX(), e.getY());
        });
    }
}
