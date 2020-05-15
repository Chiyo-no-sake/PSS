package ch.supsi.pss.menubar;

import ch.supsi.pss.LanguageController;
import ch.supsi.pss.PreferencesRepository;
import ch.supsi.pss.SketchController;
import ch.supsi.pss.drawFrame.DrawCanvasController;
import ch.supsi.pss.helpers.Alerter;
import ch.supsi.pss.helpers.SketchCreator;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class MenuBarController {
    private PssMenuBar menuBar;
    private VBox globalVBox;
    private Stage controlledScene;
    private Node galleryRoot, drawRoot;
    private AtomicReference<SketchController> sketchController;

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

    public AtomicReference<SketchController> getSketchController() {
        return sketchController;
    }

    private MenuBarController() {
    }

    public void setupController(Stage controlledStage, VBox globalVBox, Node galleryRoot, Node drawRoot, PssMenuBar connectedMenuBar) {
        this.menuBar = connectedMenuBar;
        this.controlledScene = controlledStage;
        this.drawRoot = drawRoot;
        this.galleryRoot = galleryRoot;
        this.globalVBox = globalVBox;

        LanguageController languageController = LanguageController.getInstance();
        HashMap<String, Menu> menus = connectedMenuBar.getMenuMap();

        sketchController = new AtomicReference<>(new SketchController());
        sketchController.set(new SketchController());

        //not implemented linsteners
        menus.values().forEach( m -> m.getItems().forEach( menuItem -> menuItem.setOnAction( e -> Alerter.popNotImlementedAlert())));

        // 'View->gallery' listener, change to gallery scene
        menus.get("View").getItems().get(0).setOnAction(e -> {
            globalVBox.getChildren().remove(drawRoot);
            globalVBox.getChildren().add(galleryRoot);
            menuBar.setBGalleryView(true);
            menuBar.updateClickableMenus();
        });

        // 'View->draw' listener, change to draw scene
        menus.get("View").getItems().get(1).setOnAction(e -> {
            globalVBox.getChildren().remove(galleryRoot);
            globalVBox.getChildren().add(drawRoot);
            menuBar.setBGalleryView(false);
            menuBar.updateClickableMenus();
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
        menus.get("File").getItems().get(0).setOnAction( e -> {
            SketchCreator.newSketch();
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
