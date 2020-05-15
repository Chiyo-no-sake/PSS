package ch.supsi.pss.helpers;

import ch.supsi.pss.LanguageController;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class Alerter {
    /**
     * provides a confirmation dialog 'secure' for important decisions, no is on the right and is selected by default.
     *
     * @return the user answer
     */
    public static boolean popConfirmDialog(String title, String header, String content) {
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

    public static void popInformationAlert(String title, String header, String content){
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle(title);
        al.setHeaderText(header);
        al.setContentText(content);
        al.setResizable(true);
        al.showAndWait();
    }

    public static void popNotImlementedAlert(){
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle(LanguageController.getIstance().getString("comingSoon"));
        al.setHeaderText(LanguageController.getIstance().getString("comingSoon"));
        al.setContentText(LanguageController.getIstance().getString("waitNewV"));
        al.setResizable(true);
        al.showAndWait();
    }
}
