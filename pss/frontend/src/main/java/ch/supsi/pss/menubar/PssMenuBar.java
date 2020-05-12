package ch.supsi.pss.menubar;


import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.HashMap;

public class PssMenuBar extends MenuBar {
    private MenuBarController controller;
    private boolean bGalleryView;

    private HashMap<String, Menu> menus;

    public PssMenuBar(Scene controlledScene, Node galleryRoot, Node drawRoot, boolean bGalleryView) {
        Platform.runLater(() -> this.bGalleryView = bGalleryView);
        menus = new HashMap<>();

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(new MenuItem("New..."));
        fileMenu.getItems().add(new MenuItem("Save"));
        //fileMenu.getItems().add(new MenuItem("save as"));

        Menu editMenu = new Menu("Edit");
        editMenu.getItems().add(new MenuItem("Clear"));
        editMenu.getItems().add(new MenuItem("Tag"));
        editMenu.getItems().add(new MenuItem("Find..."));

        Menu viewMenu = new Menu("View");
        viewMenu.getItems().add(new MenuItem("Gallery Window"));
        viewMenu.getItems().add(new MenuItem("Draw Window"));


        Menu helpMenu = new Menu("help");
        helpMenu.getItems().add(new MenuItem("About..."));

        this.getMenus().add(fileMenu);
        this.getMenus().add(editMenu);
        this.getMenus().add(viewMenu);
        this.getMenus().add(helpMenu);

        menus.put("File", fileMenu);
        menus.put("Edit", editMenu);
        menus.put("View", viewMenu);
        menus.put("Help", helpMenu);

        if (bGalleryView) {
            menus.get("File").getItems().forEach(i -> i.setDisable(true));
            menus.get("Edit").getItems().get(0).setDisable(true);
            menus.get("Edit").getItems().get(1).setDisable(true);
            menus.get("Edit").getItems().get(2).setDisable(false);
            menus.get("View").getItems().get(0).setDisable(true);
            menus.get("View").getItems().get(1).setDisable(false);
        } else {
            menus.get("File").getItems().forEach(i -> i.setDisable(false));
            menus.get("Edit").getItems().get(0).setDisable(false);
            menus.get("Edit").getItems().get(1).setDisable(false);
            menus.get("Edit").getItems().get(2).setDisable(true);
            menus.get("View").getItems().get(0).setDisable(false);
            menus.get("View").getItems().get(1).setDisable(true);
        }

        controller = MenuBarController.getInstance();
        controller.setupController(controlledScene, galleryRoot, drawRoot, this);
    }

    HashMap<String, Menu> getMenuMap() {
        return menus;
    }
}
