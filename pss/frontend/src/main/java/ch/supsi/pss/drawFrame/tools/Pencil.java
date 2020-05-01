package ch.supsi.pss.drawFrame.tools;
import javafx.scene.canvas.Canvas;

public class Pencil extends Tool {
    public Pencil(){
        super("pencil");
    }

    @Override
    public void setOnMousePressed() {
        super.onMousePressed = (event) -> {
            if(event.getSource() instanceof Canvas) {
                Canvas src = (Canvas) event.getSource();
                src.getGraphicsContext2D().moveTo(event.getX(), event.getY());
                src.getGraphicsContext2D().beginPath();
                src.getGraphicsContext2D().stroke();
            }
        };
    }

    @Override
    public void setOnMouseReleased() {
        super.onMouseReleased = (event) -> {
            if(event.getSource() instanceof Canvas) {
                ((Canvas) event.getSource()).getGraphicsContext2D().closePath();
            }
        };
    }

    @Override
    public void setOnMouseDragged() {
        super.onMouseDragged = (event) -> {
            if(event.getSource() instanceof Canvas) {
                Canvas src = (Canvas) event.getSource();
                src.getGraphicsContext2D().lineTo(event.getX(),event.getY());
                src.getGraphicsContext2D().moveTo(event.getX(), event.getY());
                src.getGraphicsContext2D().stroke();
            }
        };
    }
}
