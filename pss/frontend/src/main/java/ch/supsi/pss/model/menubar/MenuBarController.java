package ch.supsi.pss.model.menubar;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.misc.PreferencesRepository;
import ch.supsi.pss.sketch.SketchController;
import ch.supsi.pss.model.drawFrame.DrawCanvasController;
import ch.supsi.pss.misc.Alerter;
import ch.supsi.pss.sketch.SketchCreator;
import ch.supsi.pss.view.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Locale;

public class MenuBarController {
    private PssMenuBar menuBar;

    private static MenuBarController instance;

    public static MenuBarController getInstance() {
        if (instance == null) {
            instance = new MenuBarController();
        }

        return instance;
    }

    public PssMenuBar getMenuBar() {
        return menuBar;
    }

    private MenuBarController() {
    }

    public void setupController(Stage controlledStage, PssMenuBar connectedMenuBar) {
        this.menuBar = connectedMenuBar;

        LanguageController languageController = LanguageController.getInstance();
        HashMap<String, Menu> menus = connectedMenuBar.getMenuMap();

        //not implemented linsteners
        menus.values().forEach(m -> m.getItems().forEach(menuItem -> menuItem.setOnAction(e -> Alerter.popNotImlementedAlert())));

        // 'View->gallery' listener, change to gallery scene
        menus.get("View").getItems().get(0).setOnAction(e -> {
            ViewManager.getInstance().toView(GalleryViewController.getInstance().getGalleryView());
        });

        // 'View->draw' listener, change to draw scene
        menus.get("View").getItems().get(1).setOnAction(e -> {
            ViewManager.getInstance().toView(DrawViewController.getInstance().getDrawView());
        });

        // 'Edit->Tag' listener
        menus.get("Edit").getItems().get(1).setOnAction(e -> {
           ViewManager.getInstance().toView(TagViewController.getInstance().getTagView());
        });

        // 'Edit->Clear' listener
        menus.get("Edit").getItems().get(0).setOnAction(e -> {
            if (Alerter.popConfirmDialog(languageController.getString("r_u_sure"), languageController.getString("erase"), languageController.getString("ok_with"))) {
                DrawCanvasController.getInstance().getDrawCanvas().clearContent();
            }
        });

        // 'Edit->Preferences' listener
        menus.get("Edit").getItems().get(2).setOnAction(e -> {
        });

        // 'Edit->Preferences->Language' listener
        menus.get("Preferences").getItems().get(0).setOnAction(e -> {
        });

        // 'Edit->Preferences->Folder' listener
        menus.get("Preferences").getItems().get(1).setOnAction(e -> {
            PreferencesRepository.updateDirectory(controlledStage);
        });

        // 'Edit->Preferences->Language->Italiano' listener
        menus.get("Language").getItems().get(0).setOnAction(e -> {
            if (Alerter.popConfirmDialog(languageController.getString("restart"), languageController.getString("restart_mes"), languageController.getString("r_u_sure"))) {
                PreferencesRepository.changeFiel("current_language", Locale.ITALIAN.getLanguage());
                controlledStage.close();
            }
        });

        // 'Edit->Preferences->Language->English' listener
        menus.get("Language").getItems().get(1).setOnAction(e -> {
            if (Alerter.popConfirmDialog(languageController.getString("restart"), languageController.getString("restart_mes"), languageController.getString("r_u_sure"))) {
                PreferencesRepository.changeFiel("current_language", Locale.ENGLISH.getLanguage());
                controlledStage.close();
            }
        });

        // 'Help->About' listener
        menus.get("Help").getItems().get(0).setOnAction(e -> {
            Alerter.popInformationAlert(
                    languageController.getString("about_tab"),
                    PreferencesRepository.getAllProperties(false).getProperty("application_title"),
                    PreferencesRepository.getAllProperties(false).getProperty("authors") + " - v" + PreferencesRepository.getAllProperties(false).getProperty("current_version"));
        });


        // 'File->new' listener
        menus.get("File").getItems().get(0).setOnAction(e -> {
            SketchCreator.newSketch();
        });


        // 'File->save' listener
        menus.get("File").getItems().get(1).setOnAction(e -> {
            PreferencesRepository.setDirectory(controlledStage);

            SketchController sketchController = DrawCanvasController.getInstance().getSketchController();

            if (sketchController.saveSketch()) {
                if (!sketchController.isAlreadySaved())
                    Alerter.popInformationAlert(null, null, languageController.getString("saved"));
                else
                    Alerter.popInformationAlert(null, null, languageController.getString("updated"));
            }else{
                System.out.println("Error saving the draw. See stack trace");
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle(LanguageController.getInstance().getString("error"));
                al.setHeaderText(LanguageController.getInstance().getString("error_saving_header"));
                al.setContentText(LanguageController.getInstance().getString("error_saving_text"));
                al.setResizable(true);
                al.showAndWait();

            }

            System.out.println("Drawing saved");
        });

    }
}
