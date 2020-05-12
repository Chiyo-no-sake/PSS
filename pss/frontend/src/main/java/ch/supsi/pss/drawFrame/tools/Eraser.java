package ch.supsi.pss.drawFrame.tools;

import ch.supsi.pss.drawFrame.DrawCanvas;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Eraser extends Tool {
    float width, height;

    public Eraser(){
        super("eraser");
        width = 20;
        height = 20;
    }

    @Override
    void setOnMousePressed() {
        super.onMousePressed = event -> {
            if(event.getSource() instanceof DrawCanvas){
                DrawCanvas c = (DrawCanvas)event.getSource();
                c.renderTempSquare(event.getX()-width/2, event.getY()-height/2, width, height, Color.BLACK);
                eraserTick(c, event);
            }
        };
    }

    @Override
    void setOnMouseReleased() {
        super.onMouseReleased = event -> {
            if(event.getSource() instanceof DrawCanvas){
                DrawCanvas c = (DrawCanvas)event.getSource();
                c.clearTempSquare();
                eraserTick(c, event);
            }
        };
    }

    @Override
    void setOnMouseDragged() {
        super.onMouseDragged = event -> {
            if(event.getSource() instanceof DrawCanvas){
                DrawCanvas c = (DrawCanvas)event.getSource();
                c.clearTempSquare();
                c.renderTempSquare(event.getX()-width/2, event.getY()-height/2, width, height, Color.BLACK);
                eraserTick(c, event);
            }
        };
    }

    private void eraserTick(DrawCanvas c, MouseEvent event){
        c.getGraphicsContext2D().setFill(Color.WHITE);
        Paint old = c.getGraphicsContext2D().getStroke();

        c.getGraphicsContext2D().setStroke(null);
        c.getGraphicsContext2D().fillRect(event.getX()-width/2, event.getY()-height/2, width, height);
        c.getGraphicsContext2D().setStroke(old);
    }
}
