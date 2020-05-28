package ch.supsi.pss.model.drawFrame;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.misc.PreferencesRepository;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class StrokeSlider extends VBox {
    private static final int STROKE_DEF_THICK = Integer.parseInt(
            PreferencesRepository.getAllProperties(true).getProperty("default_stroke_thick"));

    private static final int SLIDER_HEIGHT = Integer.parseInt(
            PreferencesRepository.getAllProperties(true).getProperty("toolbar_slider_height"));

    private static final int BTN_SIZE = Integer.parseInt(
            PreferencesRepository.getAllProperties(true).getProperty("toolbar_btn_size"));

    private final Slider strokeSlider;
    private final Canvas squareView;

    StrokeSlider() {
        this.setSpacing(8);

        Label label = new Label();
        label.setText(LanguageController.getInstance().getString("thickness") + ":");
        //label.setMaxSize(BTN_SIZE,BTN_SIZE);

        strokeSlider = new Slider();
        strokeSlider.setMin(1);
        strokeSlider.setMax(25);
        strokeSlider.setValue(STROKE_DEF_THICK);
        strokeSlider.setOrientation(Orientation.VERTICAL);
        strokeSlider.setPrefHeight(SLIDER_HEIGHT);
        strokeSlider.setShowTickMarks(true);
        strokeSlider.setShowTickLabels(true);

        squareView = new Canvas();
        squareView.setHeight(BTN_SIZE);
        squareView.setWidth(BTN_SIZE);

        StackPane canvasContainer = new StackPane(squareView);
        canvasContainer.setStyle("-fx-border-color: darkgrey; -fx-border-width: 2; -fx-border-style: solid");

        this.getChildren().add(label);
        this.getChildren().add(canvasContainer);
        this.getChildren().add(strokeSlider);

        StrokeSliderController.getInstance().setStrokeSlider(this);
        StrokeSliderController.getInstance().setupListeners();

        updateSquareView();
    }

    public void updateSquareView() {
        Canvas c = getSquareView();
        GraphicsContext gc = c.getGraphicsContext2D();

        gc.clearRect(0, 0, c.getWidth(), c.getHeight());

        double centerX = c.getWidth() / 2;
        double centerY = c.getHeight() / 2;

        gc.fillRect(centerX - getValue() / 2, centerY - getValue() / 2, getValue(), getValue());
    }

    public void setColor(Paint p) {
        squareView.getGraphicsContext2D().setFill(p);
        squareView.getGraphicsContext2D().setStroke(p);
    }

    public double getValue() {
        return strokeSlider.getValue();
    }

    public Slider getSlider() {
        return strokeSlider;
    }

    public Canvas getSquareView() {
        return squareView;
    }
}
