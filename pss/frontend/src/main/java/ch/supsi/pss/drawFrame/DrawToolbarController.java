package ch.supsi.pss.drawFrame;

import ch.supsi.pss.drawFrame.tools.Eraser;
import ch.supsi.pss.drawFrame.tools.Pencil;
import ch.supsi.pss.helpers.Alerter;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.Optional;


public class DrawToolbarController {

    DrawToolbar tb;
    static DrawToolbarController instance;

    private DrawToolbarController() { }

    public static DrawToolbarController getInstance() {
        if (instance == null) {
            instance = new DrawToolbarController();
        }

        return instance;
    }

    public void setToolBar(DrawToolbar tb){
        this.tb = tb;
    }

    public DrawToolbar getToolBar(){
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
        });

        // PortraitMode button listener
        tb.getPortraitButton().setOnMouseClicked(e -> {
            if (Alerter.popConfirmDialog("Are you sure?", "This operation will erase your work", "Are you ok with this?")) {
                tb.getConnectedCanvas().clearContent();
                tb.getConnectedCanvas().changeMode(tb.getConnectedCanvas().isPortrait());
                if (tb.getConnectedCanvas().isPortrait()) {
                    tb.getPortraitButton().changeImage(new ImageView(this.getClass().getResource(DrawToolbar.getPortaitOnIco()).toExternalForm()));
                } else {
                    tb.getPortraitButton().changeImage(new ImageView(this.getClass().getResource(DrawToolbar.getPortaitOffIco()).toExternalForm()));
                }
            }
        });
        // freeDraw button listener
        tb.getBtnToolsList().get(0).setOnMouseClicked(e -> {
            resetButtonStatus();
            ((ImageButton) e.getSource()).setSelected(true);
            tb.getConnectedCanvas().setTool(new Pencil());
        });

        // eraser button listener
        tb.getBtnToolsList().get(4).setOnMouseClicked(e -> {
            resetButtonStatus();
            ((ImageButton) e.getSource()).setSelected(true);
            tb.getConnectedCanvas().setTool(new Eraser());
        });
    }

    private void resetButtonStatus() {
        tb.getBtnToolsList().forEach(b -> {
            b.setSelected(false);
        });
    }
}

