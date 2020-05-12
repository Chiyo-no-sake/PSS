package ch.supsi.pss.drawFrame;

import ch.supsi.pss.drawFrame.tools.Eraser;
import ch.supsi.pss.drawFrame.tools.Pencil;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.Optional;


public class DrawToolbarController {

    DrawToolbar tb;

    DrawToolbarController(DrawToolbar tb) {
        this.tb = tb;
    }

    /**
     * setup all listeners in the given drawToolBar
     */
    void setupListeners() {
        // unimplemented buttons
        tb.getBtnToolsList().forEach(b -> {
            b.setOnMouseClicked(e -> {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Feature Coming Soon");
                al.setHeaderText("Feature is coming soon");
                al.setContentText("Please wait for a new version");
                al.setResizable(true);
                al.showAndWait();
            });
        });

        // Color picker listener
        tb.getColorPicker().setOnAction(e -> {
            tb.getConnectedCanvas().setColor(tb.getColorPicker().getValue());
        });

        // PortraitMode button listener
        tb.getPortraitButton().setOnMouseClicked(e -> {
            if (popConfirmDialog("Are you sure?", "This operation will erase your work", "Are you ok with this?")) {
                tb.getConnectedCanvas().clearContent();
                tb.getConnectedCanvas().changeMode(tb.getConnectedCanvas().isPortrait());
                if (tb.getConnectedCanvas().isPortrait()) {
                    tb.getPortraitButton().changeImage(new ImageView(this.getClass().getResource(DrawToolbar.getPortaitOnIco()).toExternalForm()));
                } else {
                    tb.getPortraitButton().changeImage(new ImageView(this.getClass().getResource(DrawToolbar.getPortaitOffIco()).toExternalForm()));
                }
            }
        });

        // Clear button listener
        tb.getClearButton().setOnMouseClicked(e -> {
            if (popConfirmDialog("Are you sure?", "This operation will erase your work", "Are you ok with this?")) {
                tb.getConnectedCanvas().clearContent();
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

    /**
     * provides a confirmation dialog 'secure' for important decisions, no is on the right and is selected by default.
     *
     * @return the user answer
     */
    private boolean popConfirmDialog(String title, String header, String content) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);

        //Deactivate Default behavior for yes-Button:
        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
        yesButton.setDefaultButton(false);

        //Activate Default behavior for no-Button:
        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        noButton.setDefaultButton(true);

        alert.setResizable(true);

        final Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    private void resetButtonStatus() {
        tb.getBtnToolsList().forEach(b -> {
            b.setSelected(false);
        });
    }
}
