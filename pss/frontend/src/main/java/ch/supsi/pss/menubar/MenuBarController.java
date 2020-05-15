package ch.supsi.pss.menubar;

import ch.supsi.pss.LanguageController;
import ch.supsi.pss.PreferencesRepository;
import ch.supsi.pss.SketchController;
import ch.supsi.pss.drawFrame.DrawCanvasController;
import ch.supsi.pss.helpers.Alerter;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

class MenuBarController {
    private PssMenuBar connectedMenuBar;
    private Stage controlledScene;
    private Node galleryRoot, drawRoot;

    private static MenuBarController instance;

    public static MenuBarController getInstance() {
        if (instance == null) {
            instance = new MenuBarController();
        }

        return instance;
    }

    private MenuBarController() {
    }

    public void setupController(Stage controlledStage, Node galleryRoot, Node drawRoot, PssMenuBar connectedMenuBar) {
        this.connectedMenuBar = connectedMenuBar;
        this.controlledScene = controlledStage;
        this.drawRoot = drawRoot;
        this.galleryRoot = galleryRoot;


        LanguageController languageController = LanguageController.getIstance();
        HashMap<String, Menu> menus = connectedMenuBar.getMenuMap();

        AtomicReference<SketchController> sketchController = new AtomicReference<>();
        sketchController.set(new SketchController());

        //not implemented linsteners
        menus.values().forEach( m -> m.getItems().forEach( menuItem -> menuItem.setOnAction( e -> Alerter.popNotImlementedAlert())));

        // 'View->gallery' listener
        menus.get("View").getItems().get(0).setOnAction(e -> {
            controlledStage.getScene().setRoot((Parent) galleryRoot);
        });

        // 'View->draw' listener
        menus.get("View").getItems().get(1).setOnAction(e -> {
            controlledStage.getScene().setRoot((Parent) drawRoot);
        });
        
        // 'Edit->Clear' listener
        menus.get("Edit").getItems().get(0).setOnAction(e -> {
            if (Alerter.popConfirmDialog(languageController.getString("r_u_sure"), languageController.getString("erase"), languageController.getString("ok_with"))) {
                DrawCanvasController.getInstance().getDrawCanvas().clearContent();
            }
        });

        // 'Edit->Preferences' listener
        menus.get("Edit").getItems().get(3).setOnAction(e -> {});

        // 'Edit->Preferences->Language' listener
        menus.get("Preferences").getItems().get(0).setOnAction(e -> {});

        // 'Edit->Preferences->Folder' listener
        menus.get("Preferences").getItems().get(1).setOnAction(e -> {
            PreferencesRepository.updateDirectory(controlledStage);
        });

        // 'Edit->Preferences->Language->Italiano' listener
        menus.get("Language").getItems().get(0).setOnAction(e -> {
            if(Alerter.popConfirmDialog(languageController.getString("restart"),languageController.getString("restart_mes"), languageController.getString("r_u_sure"))) {
                PreferencesRepository.changeFiel("current_language", Locale.ITALIAN.getLanguage());
                controlledStage.close();
            }
        });

        // 'Edit->Preferences->Language->English' listener
        menus.get("Language").getItems().get(1).setOnAction(e -> {
            if(Alerter.popConfirmDialog(languageController.getString("restart"),languageController.getString("restart_mes"), languageController.getString("r_u_sure"))) {
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
        // TODO, select mode: portrait or not
        menus.get("File").getItems().get(0).setOnAction( e -> {
            sketchController.set(new SketchController());
            if (Alerter.popConfirmDialog(languageController.getString("r_u_sure"), languageController.getString("erase"), languageController.getString("ok_with")))
                DrawCanvasController.getInstance().getDrawCanvas().clearContent();
        });


        // 'File->save' listener
        menus.get("File").getItems().get(1).setOnAction( e -> {
            PreferencesRepository.setDirectory(controlledStage);

            if(sketchController.get().getSketch() == null){
                sketchController.get().setSketch(DrawCanvasController.getInstance().getDrawCanvas());
                if(sketchController.get().saveSketch())
                    Alerter.popInformationAlert(null, null, languageController.getString("saved"));
            }
            else if(sketchController.get().getSketch() != null){
                sketchController.get().setSketch(DrawCanvasController.getInstance().getDrawCanvas());
                if(sketchController.get().saveSketch())
                    Alerter.popInformationAlert(null, null, languageController.getString("updated"));
            }
            System.out.println("Drawing saved");
        });

    }
}
