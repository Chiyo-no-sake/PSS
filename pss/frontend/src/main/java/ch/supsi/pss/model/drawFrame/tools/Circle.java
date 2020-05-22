package ch.supsi.pss.model.drawFrame.tools;

import ch.supsi.pss.model.drawFrame.DrawCanvas;
import ch.supsi.pss.model.drawFrame.DrawCanvasController;
import javafx.scene.paint.Color;

public class Circle extends Tool {
    double originX;
    double originY;

    public Circle(){
        super("circle");

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

            c.renderTempOval(oldX, oldY, width, height, Color.GRAY);
        });

        this.setOnMouseReleased(event -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();

            c.clearAllTemp();

            double oldX = originX;
            double oldY = originY;
            double nowX = event.getX();
            double nowY = event.getY();

            if (oldX > nowX) {
                double tmp = oldX;
                oldX = nowX;
                nowX = tmp;
            }

            if (oldY > nowY) {
                double tmp = oldY;
                oldY = nowY;
                nowY = tmp;
            }

            double width = nowX - oldX;
            double height = nowY - oldY;

            c.getGraphicsContext2D().strokeOval(oldX, oldY, width, height);
        });
    }
}
