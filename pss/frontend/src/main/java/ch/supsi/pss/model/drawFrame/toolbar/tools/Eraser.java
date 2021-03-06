package ch.supsi.pss.model.drawFrame.toolbar.tools;

import ch.supsi.pss.model.drawFrame.canvas.DrawCanvas;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Eraser extends Tool {
    double width, height;

    public Eraser(){
        super("eraser");
        width = 20;
        height = 20;

        this.setOnMousePressed(event -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();
            c.renderTempSquare(event.getX()-width/2, event.getY()-height/2, width, height, Color.BLACK);
            eraserTick(c, event);
        });

        this.setOnMouseDragged(event -> {
            DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();
            c.clearAllTemp();
            c.renderTempSquare(event.getX()-width/2, event.getY()-height/2, width, height, Color.BLACK);
            eraserTick(c, event);
        });

        this.setOnMouseReleased(event -> {
            if(event.getSource() instanceof Canvas){
                DrawCanvas c = DrawCanvasController.getInstance().getDrawCanvas();
                c.clearAllTemp();
                eraserTick(c, event);
            }
        });
    }

    private void eraserTick(DrawCanvas c, MouseEvent event){
        c.getGraphicsContext2D().setFill(Color.WHITE);
        Paint old = c.getGraphicsContext2D().getStroke();

        c.getGraphicsContext2D().setStroke(null);
        c.getGraphicsContext2D().fillRect(event.getX()-width/2, event.getY()-height/2, width, height);
        c.getGraphicsContext2D().setStroke(old);
    }
}
