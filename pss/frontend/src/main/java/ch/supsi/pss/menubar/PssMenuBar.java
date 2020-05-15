package ch.supsi.pss.menubar;

import ch.supsi.pss.LanguageController;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.util.HashMap;


public class PssMenuBar extends MenuBar {
    private MenuBarController controller;
    private boolean bGalleryView;

    private HashMap<String, Menu> menus;

    public PssMenuBar(Stage controlledStage, Node galleryRoot, Node drawRoot, boolean bGalleryView) {
        Platform.runLater(() -> this.bGalleryView = bGalleryView);
        menus = new HashMap<>();

        LanguageController languageController = LanguageController.getIstance();

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(new MenuItem(languageController.getString("newTab")));
        fileMenu.getItems().add(new MenuItem(languageController.getString("save")));
        //fileMenu.getItems().add(new MenuItem("save as"));

        Menu editMenu = new Menu(languageController.getString("edit"));
        editMenu.getItems().add(new MenuItem(languageController.getString("clear")));
        editMenu.getItems().add(new MenuItem("Tag"));
        editMenu.getItems().add(new MenuItem(languageController.getString("find")));

        Menu languageMenu = new Menu(languageController.getString("langu"));
        languageMenu.getItems().add(new MenuItem("Italiano"));
        languageMenu.getItems().add(new MenuItem("English"));

        Menu preferencesMenu = new Menu(languageController.getString("prefs"));
        preferencesMenu.getItems().add(languageMenu);
        preferencesMenu.getItems().add(new MenuItem(languageController.getString("dir")));

        editMenu.getItems().add(preferencesMenu);

        Menu viewMenu = new Menu(languageController.getString("view"));
        viewMenu.getItems().add(new MenuItem(languageController.getString("gallery")));
        viewMenu.getItems().add(new MenuItem(languageController.getString("toDraw")));


        Menu helpMenu = new Menu(languageController.getString("help"));
        helpMenu.getItems().add(new MenuItem(languageController.getString("about")));

        this.getMenus().add(fileMenu);
        this.getMenus().add(editMenu);
        this.getMenus().add(viewMenu);
        this.getMenus().add(helpMenu);

        menus.put("File", fileMenu);
        menus.put("Edit", editMenu);
        menus.put("View", viewMenu);
        menus.put("Help", helpMenu);
        menus.put("Preferences", preferencesMenu);
        menus.put("Language", languageMenu);

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

        if(languageController.getLocale().getLanguage().equals("en"))
            menus.get("Language").getItems().get(1).setDisable(true);
        else if(languageController.getLocale().getLanguage().equals("it"))
            menus.get("Language").getItems().get(0).setDisable(true);

        controller = MenuBarController.getInstance();
        controller.setupController(controlledStage, galleryRoot, drawRoot, this);
    }

    HashMap<String, Menu> getMenuMap() {
        return menus;
    }
}
