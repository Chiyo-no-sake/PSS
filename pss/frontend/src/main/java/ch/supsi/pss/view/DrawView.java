package ch.supsi.pss.views;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.drawFrame.DrawingFrame;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class DrawView extends View {

    public DrawView(){
        // Label for the draw mode
        Label drawTitle = new Label(LanguageController.getInstance().getString("draw"));

        //CanvasPane instantiation
        DrawingFrame drawFrame = new DrawingFrame();
        drawFrame.setStyle("-fx-border-style: solid;" +
                "-fx-border-color: black;" +
                "-fx-background-color: #c0c0c0;");

        drawFrame.bindSizeTo(this);

        this.getChildren().addAll(drawTitle, drawFrame);

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);

        DrawViewController.getInstance().setDrawView(this);
    }
}
