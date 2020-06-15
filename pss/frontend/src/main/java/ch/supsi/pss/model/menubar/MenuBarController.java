package ch.supsi.pss.model.menubar;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.misc.PreferencesRepository;
import ch.supsi.pss.sketch.SketchController;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import ch.supsi.pss.misc.Alerter;
import ch.supsi.pss.sketch.SketchCreator;
import ch.supsi.pss.view.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;

public class MenuBarController {
    private PssMenuBar menuBar;
    private Stage stage;

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
        this.stage = controlledStage;

        LanguageController languageController = LanguageController.getInstance();
        HashMap<String, Menu> menus = connectedMenuBar.getMenuMap();

        //not implemented linsteners
        menus.values().forEach(m -> m.getItems().forEach(menuItem -> menuItem.setOnAction(e -> Alerter.popNotImlementedAlert())));

        // 'View->gallery' listener, change to gallery scene
        menus.get("View").getItems().get(0).setOnAction(e -> {
            ViewManager.getInstance().toView(GalleryViewController.getInstance().getGalleryView());
        });
        menus.get("View").getItems().get(0).setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        // 'View->draw' listener, change to draw scene
        menus.get("View").getItems().get(1).setOnAction(e -> {
            ViewManager.getInstance().toView(DrawViewController.getInstance().getDrawView());
        });
        menus.get("View").getItems().get(1).setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));

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
        menus.get("Edit").getItems().get(0).setAccelerator(new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_DOWN));

        // 'Edit->Preferences' listener
        menus.get("Edit").getItems().get(2).setOnAction(e -> {
        });

        // 'Edit->Preferences->Language' listener
        menus.get("Preferences").getItems().get(0).setOnAction(e -> {
        });

        // 'Edit->Preferences->Folder' listener
        menus.get("Preferences").getItems().get(1).setOnAction(e -> {
            if(PreferencesRepository.propertiesExistsOrNotContainsKey())
                setDirectory();
            else{
                Path path = Paths.get(PreferencesRepository.getProperties().getProperty("path"));
                File tmp = directoryChooser(path.getParent().toString());
                PreferencesRepository.updateDirectory(tmp);
            }
        });

        // 'Edit->Preferences->Language->Italiano' listener
        menus.get("Language").getItems().get(0).setOnAction(e -> {
            if (Alerter.popConfirmDialog(languageController.getString("restart"), languageController.getString("restart_mes"), languageController.getString("r_u_sure"))) {
                PreferencesRepository.changeField("current_language", Locale.ITALIAN.getLanguage());
                controlledStage.close();
            }
        });

        // 'Edit->Preferences->Language->English' listener
        menus.get("Language").getItems().get(1).setOnAction(e -> {
            if (Alerter.popConfirmDialog(languageController.getString("restart"), languageController.getString("restart_mes"), languageController.getString("r_u_sure"))) {
                PreferencesRepository.changeField("current_language", Locale.ENGLISH.getLanguage());
                controlledStage.close();
            }
        });

        // 'Help->About' listener
        menus.get("Help").getItems().get(0).setOnAction(e -> {
            Alerter.popInformationAlert(
                    languageController.getString("about_tab"),
                    PreferencesRepository.getAllProperties(false).getProperty("application_title")+ " - v" + PreferencesRepository.getAllProperties(true).getProperty("current_version"),
                    PreferencesRepository.getAllProperties(false).getProperty("authors") );
        });


        // 'File->new' listener
        menus.get("File").getItems().get(0).setOnAction(e -> {
            SketchCreator.newSketch();
        });
        menus.get("File").getItems().get(0).setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));


        // 'File->save' listener
        menus.get("File").getItems().get(1).setOnAction(e -> {

            setDirectory();
            SketchController sketchController = DrawCanvasController.getInstance().getSketchController();

            boolean already_saved = SketchController.isAlreadySaved();
            if (sketchController.saveSketch()) {
                if (!already_saved)
                    Alerter.popInformationAlert(languageController.getString("saved_title"), null, languageController.getString("saved"));
                else
                    Alerter.popInformationAlert(languageController.getString("updated_title"), null, languageController.getString("updated"));
                SketchController.setAlreadySaved(true);
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
        menus.get("File").getItems().get(1).setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        menus.get("File").getItems().get(2).setOnAction( e ->{
            if(!SketchController.isAlreadySaved())
                if (!Alerter.popConfirmDialog(LanguageController.getInstance().getString("r_u_sure"),
                        LanguageController.getInstance().getString("erase"),
                        LanguageController.getInstance().getString("ok_with"))) {
                    return;
                }
            controlledStage.close();
        });
    }

    public Stage getStage(){
        return stage;
    }

    private void setDirectory(){
        PreferencesRepository.setProperties(false);
        if(PreferencesRepository.containsPathOrFolderNotExists())
            PreferencesRepository.setDirectory(directoryChooser(PreferencesRepository.getUserHome()));
        PreferencesRepository.setOrCreateFolders();
    }

    public File directoryChooser(final String path ){
        DirectoryChooser dir = new DirectoryChooser();
        if(path != null)
            dir.setInitialDirectory(new File(path));

        return dir.showDialog(stage);
    }
}
