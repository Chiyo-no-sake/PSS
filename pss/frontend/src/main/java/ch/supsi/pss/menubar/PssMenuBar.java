package ch.supsi.pss.menubar;

import ch.supsi.pss.PreferencesRepository;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Properties;


public class PssMenuBar extends MenuBar {
    private MenuBarController controller;
    private boolean bGalleryView;

    private HashMap<String, Menu> menus;

    public PssMenuBar(Stage controlledStage, Node galleryRoot, Node drawRoot, boolean bGalleryView) {
        Platform.runLater(() -> this.bGalleryView = bGalleryView);
        menus = new HashMap<>();

        Properties props = PreferencesRepository.getAllProperties(true);

        String editString = null;
        String viewString = null;
        String helpString = null;
        String newString = null;
        String saveString = null;
        String findString = null;
        String clearString = null;
        String langString = null;
        String prefsString = null;
        String dirString = null;
        String galleryString = null;
        String drawString = null;
        String aboutString = null;


        String language = null;
        if (PreferencesRepository.getDefaultLanguage().equals("en"))
            language = "Eng";
        else
            language = "Ita";


        editString = props.getProperty("edit" + language);
        viewString = props.getProperty("view" + language);
        helpString = props.getProperty("help" + language);

        newString = props.getProperty("new" + language);
        saveString = props.getProperty("save" + language);
        findString = props.getProperty("find" + language);
        clearString = props.getProperty("clear" + language);
        langString = props.getProperty("lang" + language);
        prefsString = props.getProperty("prefs" + language);
        dirString = props.getProperty("dir" + language);
        galleryString = props.getProperty("gallery" + language);
        drawString = props.getProperty("draw" + language);
        aboutString = props.getProperty("about" + language);



        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(new MenuItem(newString));
        fileMenu.getItems().add(new MenuItem(saveString));
        //fileMenu.getItems().add(new MenuItem("save as"));

        Menu editMenu = new Menu(editString);
        editMenu.getItems().add(new MenuItem(clearString));
        editMenu.getItems().add(new MenuItem("Tag"));
        editMenu.getItems().add(new MenuItem(findString));

        Menu languageMenu = new Menu(langString);
        languageMenu.getItems().add(new MenuItem("Italiano"));
        languageMenu.getItems().add(new MenuItem("English"));

        Menu preferencesMenu = new Menu(prefsString);
        preferencesMenu.getItems().add(languageMenu);
        preferencesMenu.getItems().add(new MenuItem(dirString));

        editMenu.getItems().add(preferencesMenu);

        Menu viewMenu = new Menu(viewString);
        viewMenu.getItems().add(new MenuItem(galleryString));
        viewMenu.getItems().add(new MenuItem(drawString));


        Menu helpMenu = new Menu(helpString);
        helpMenu.getItems().add(new MenuItem(aboutString));

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

        controller = MenuBarController.getInstance();
        controller.setupController(controlledStage, galleryRoot, drawRoot, this);
    }

    HashMap<String, Menu> getMenuMap() {
        return menus;
    }
}
