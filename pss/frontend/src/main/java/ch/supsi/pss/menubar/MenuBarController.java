package ch.supsi.pss.menubar;

import ch.supsi.pss.PreferencesRepository;
import ch.supsi.pss.SketchController;
import ch.supsi.pss.drawFrame.DrawCanvasController;
import ch.supsi.pss.helpers.Alerter;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;

import java.util.HashMap;

class MenuBarController {
    private PssMenuBar connectedMenuBar;
    private Scene controlledScene;
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

    public void setupController(Scene controlledScene, Node galleryRoot, Node drawRoot, PssMenuBar connectedMenuBar) {
        this.connectedMenuBar = connectedMenuBar;
        this.controlledScene = controlledScene;
        this.drawRoot = drawRoot;
        this.galleryRoot = galleryRoot;

        HashMap<String, Menu> menus = connectedMenuBar.getMenuMap();

        //not implemented linsteners
        menus.values().forEach( m -> m.getItems().forEach( menuItem -> menuItem.setOnAction( e -> Alerter.popNotImlementedAlert())));

        // 'View->gallery' listener
        menus.get("View").getItems().get(0).setOnAction(e -> {
            controlledScene.setRoot((Parent) galleryRoot);
        });

        // 'View->draw' listener
        menus.get("View").getItems().get(1).setOnAction(e -> {
            controlledScene.setRoot((Parent) drawRoot);
        });

        // 'Edit->Clear' listener
        menus.get("Edit").getItems().get(0).setOnAction(e -> {
            if (Alerter.popConfirmDialog("Are you sure?", "This operation will erase your work", "Are you ok with this?")) {
                DrawCanvasController.getInstance().getDrawCanvas().clearContent();
            }
        });

        // 'File->save' listener
        menus.get("File").getItems().get(1).setOnAction( e -> {
            System.out.println("Drawing saved");
            //TODO make classes public in sketchcontroller and create a package (maybe 'saves'?)
            /*
            SketchController sketchController = new SketchController();
            PreferencesRepository.setRepository(stage);

            sketchController.newSketch(drawFrame);

            if(sketchController.getSketchService().saveSketch()){
                Alerter.popInformationAlert(null,null,"Sketch correctly saved.");
            }
            */
        });

    }
}
