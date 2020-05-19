package ch.supsi.pss.menubar;

import ch.supsi.pss.LanguageController;
import ch.supsi.pss.PreferencesRepository;
import ch.supsi.pss.SketchController;
import ch.supsi.pss.drawFrame.DrawCanvasController;
import ch.supsi.pss.helpers.Alerter;
import ch.supsi.pss.helpers.SketchCreator;
import ch.supsi.pss.views.*;
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
            menuBar.updateClickableMenus();
        });

        // 'View->draw' listener, change to draw scene
        menus.get("View").getItems().get(1).setOnAction(e -> {
            ViewManager.getInstance().toView(DrawViewController.getInstance().getDrawView());
            menuBar.updateClickableMenus();
        });

        // 'Edit->Tag' listener
        menus.get("Edit").getItems().get(1).setOnAction(e -> {
           ViewManager.getInstance().toView(TagViewController.getInstance().getTagView());
           menuBar.updateClickableMenus();
           TagViewController.getInstance().getTagView().updateContent();
        });

        // 'Edit->Clear' listener
        menus.get("Edit").getItems().get(0).setOnAction(e -> {
            if (Alerter.popConfirmDialog(languageController.getString("r_u_sure"), languageController.getString("erase"), languageController.getString("ok_with"))) {
                DrawCanvasController.getInstance().getDrawCanvas().clearContent();
            }
        });

        // 'Edit->Preferences' listener
        menus.get("Edit").getItems().get(3).setOnAction(e -> {
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
            }

            System.out.println("Drawing saved");
        });

    }
}
