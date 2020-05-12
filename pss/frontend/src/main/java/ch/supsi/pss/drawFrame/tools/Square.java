package ch.supsi.pss.drawFrame.tools;

import javafx.scene.canvas.Canvas;

public class Square extends Tool{
    Square(){
        super("square");
    }

    @Override
    void setOnMousePressed() {
        super.onMousePressed = event -> {
            if(event.getSource() instanceof Canvas){
                Canvas c = (Canvas)event.getSource();
                c.getGraphicsContext2D().setFill(null);
                //TODO
            }
        };
    }

    @Override
    void setOnMouseReleased() {

    }

    @Override
    void setOnMouseDragged() {

    }
}
