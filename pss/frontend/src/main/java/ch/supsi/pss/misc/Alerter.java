package ch.supsi.pss.misc;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
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

        ((Stage)alert.getDialogPane()
                .getScene()
                .getWindow())
                .getIcons()
                .add(new Image(Alerter.class.getResourceAsStream("/icons/logo.png")));

        final Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    public static void popInformationAlert(String title, String header, String content){
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle(title);
        al.setHeaderText(header);
        al.setContentText(content);
        al.setResizable(true);

        ((Stage)al.getDialogPane()
                .getScene()
                .getWindow())
                .getIcons()
                .add(new Image(Alerter.class.getResourceAsStream("/icons/logo.png")));

        al.showAndWait();
    }

    // return the index of the choice made, if none, return negative number
    public static int popChoiceDialog(String title, String header, String text, List<String> choices){
        ChoiceDialog<String> d = new ChoiceDialog<>(choices.get(0), choices);
        d.setTitle(title);
        d.setHeaderText(header);
        d.setContentText(text);
        d.setResizable(true);

        ((Stage)d.getDialogPane()
                .getScene()
                .getWindow())
                .getIcons()
                .add(new Image(Alerter.class.getResourceAsStream("/icons/logo.png")));

        Optional<String> res = d.showAndWait();

        return res.map(choices::indexOf).orElse(-1);
    }

    public static void popNotImlementedAlert(){
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle(LanguageController.getInstance().getString("comingSoon"));
        al.setHeaderText(LanguageController.getInstance().getString("comingSoon"));
        al.setContentText(LanguageController.getInstance().getString("waitNewV"));
        al.setResizable(true);

        ((Stage)al.getDialogPane()
                .getScene()
                .getWindow())
                .getIcons()
                .add(new Image(Alerter.class.getResourceAsStream("/icons/logo.png")));

        al.showAndWait();
    }
}
