package ch.supsi.pss.view;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.model.drawFrame.DrawingFrame;
import ch.supsi.pss.model.menubar.MenuBarController;
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

    @Override
    public void onShow() {
        MenuBarController.getInstance().getMenuBar().updateClickableMenus();
    }

    @Override
    public void onHide() {

    }
}
