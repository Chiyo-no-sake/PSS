package ch.supsi.pss.model.drawFrame;

import ch.supsi.pss.model.drawFrame.tools.*;
import ch.supsi.pss.misc.Alerter;
import javafx.event.Event;


public class DrawToolbarController {

    DrawToolbar tb;
    static DrawToolbarController instance;

    private DrawToolbarController() {
    }

    public static DrawToolbarController getInstance() {
        if (instance == null) {
            instance = new DrawToolbarController();
        }

        return instance;
    }

    public void setToolBar(DrawToolbar tb) {
        this.tb = tb;
    }

    public DrawToolbar getToolBar() {
        return this.tb;
    }

    /**
     * setup all listeners in the given drawToolBar
     */
    void setupListeners() {
        // unimplemented buttons
        tb.getBtnToolsList().forEach(b -> {
            b.setOnMouseClicked(e -> {
                Alerter.popNotImlementedAlert();
            });
        });

        // Color picker listener
        tb.getColorPicker().setOnAction(e -> {
            tb.getConnectedCanvas().setColor(tb.getColorPicker().getValue());
            StrokeSliderController.getInstance().getStrokeSlider().setColor(tb.getSelectedColor());
            StrokeSliderController.getInstance().getStrokeSlider().updateSquareView();
        });

        // freeDraw button listener
        tb.getBtnToolsList().get(0).setOnMouseClicked(e -> {
            selectBtnAs(new Pencil(), e);
        });

        // line button listener
        tb.getBtnToolsList().get(1).setOnMouseClicked(e -> {
            selectBtnAs(new Line(), e);
        });

        // square button listener
        tb.getBtnToolsList().get(2).setOnMouseClicked(e -> {
            selectBtnAs(new Square(), e);
        });

        // oval button listener
        tb.getBtnToolsList().get(3).setOnMouseClicked(e -> {
            selectBtnAs(new Circle(), e);
        });

        // eraser button listener
        tb.getBtnToolsList().get(4).setOnMouseClicked(e -> {
            selectBtnAs(new Eraser(), e);
        });

        // stroke slider listener
        tb.getStrokeSlider().getSlider().valueProperty().addListener(e -> {
            tb.getConnectedCanvas().getGraphicsContext2D().setLineWidth(tb.getStrokeSlider().getValue());
        });
    }

    private void resetButtonStatus() {
        tb.getBtnToolsList().forEach(b -> {
            b.setSelected(false);
        });
    }

    private void selectBtnAs(Tool t, Event e) {
        resetButtonStatus();
        ((ImageButton) e.getSource()).setSelected(true);
        tb.getConnectedCanvas().setTool(t);
    }
}

