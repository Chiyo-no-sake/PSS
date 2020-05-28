package ch.supsi.pss.model.drawFrame.toolbar.tools;

import ch.supsi.pss.model.drawFrame.canvas.DrawCanvas;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import javafx.scene.paint.Color;


public class Square extends Tool {
    double originX;
    double originY;

    public Square() {
        super("square");

        this.setOnMousePressed(event -> {
            originX = event.getX();
            originY = event.getY();
        });

        this.setOnMouseDragged(event -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();

            c.clearAllTemp();

            double oldX = originX;
            double oldY = originY;
            double nowX = event.getX();
            double nowY = event.getY();

            if(oldX > nowX){
                double tmp = oldX;
                oldX = nowX;
                nowX = tmp;
            }

            if(oldY > nowY){
                double tmp = oldY;
                oldY = nowY;
                nowY = tmp;
            }

            double width = nowX - oldX;
            double height = nowY - oldY;

            c.renderTempSquare(oldX, oldY, width, height, Color.GRAY);
        });

        this.setOnMouseReleased(event -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();

            c.clearAllTemp();

            double oldX = originX;
            double oldY = originY;
            double nowX = event.getX();
            double nowY = event.getY();

            if(oldX > nowX){
                double tmp = oldX;
                oldX = nowX;
                nowX = tmp;
            }

            if(oldY > nowY){
                double tmp = oldY;
                oldY = nowY;
                nowY = tmp;
            }

            double width = nowX - oldX;
            double height = nowY - oldY;

            c.getGraphicsContext2D().strokeRect(oldX, oldY, width, height);
        });
    }
}
