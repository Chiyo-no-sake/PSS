package ch.supsi.pss.drawFrame.tools;

import ch.supsi.pss.drawFrame.DrawCanvas;
import ch.supsi.pss.drawFrame.DrawCanvasController;
import javafx.scene.paint.Color;

public class Circle extends Tool {
    double originX;
    double originY;

    public Circle(){
        super("circle");
    }

    @Override
    void setOnMousePressed() {
        super.onMousePressed = event -> {
            originX = event.getX();
            originY = event.getY();
        };
    }

    @Override
    void setOnMouseDragged() {
        super.onMouseDragged = event -> {
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
        };
    }

    @Override
    void setOnMouseReleased() {
        super.onMouseReleased = event -> {
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
        };
    }
}
