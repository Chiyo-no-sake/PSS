package ch.supsi.pss.drawFrame.draw;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class DrawCanvas extends Canvas {
    private boolean isPortrait;

    DrawCanvas(double width, double height) {
        super();
        this.setHeight(height);
        this.setWidth(width);
        this.isPortrait=false;

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void changeMode(boolean isPortrait) {
        double tmp;

        this.isPortrait=!isPortrait;

        this.getGraphicsContext2D().clearRect(0,0,this.getWidth(),this.getHeight());

        tmp = this.getWidth();
        this.setWidth(this.getHeight());
        this.setHeight(tmp);

        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public boolean isPortrait() {
        return isPortrait;
    }
}
