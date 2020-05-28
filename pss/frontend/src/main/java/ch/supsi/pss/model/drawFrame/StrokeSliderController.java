package ch.supsi.pss.model.drawFrame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class StrokeSliderController {
    private StrokeSlider ss;
    static StrokeSliderController instance;

    private StrokeSliderController() {
    }

    public static StrokeSliderController getInstance() {
        if (instance == null) {
            instance = new StrokeSliderController();
        }

        return instance;
    }

    public void setStrokeSlider(StrokeSlider tb) {
        this.ss = tb;
    }

    public StrokeSlider getStrokeSlider() {
        return this.ss;
    }

    public void setupListeners() {
        ss.getSlider().valueProperty().addListener(e -> {
            ss.updateSquareView();
        });
    }

}
