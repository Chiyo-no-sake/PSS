package ch.supsi.pss.model.menubar;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import ch.supsi.pss.view.SketchView;
import ch.supsi.pss.view.DrawView;
import ch.supsi.pss.view.GalleryView;
import ch.supsi.pss.view.TagView;
import ch.supsi.pss.view.ViewManager;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.HashMap;


public class PssMenuBar extends MenuBar {

    private final HashMap<String, Menu> menus;

    public PssMenuBar(Stage controlledStage) {
        menus = new HashMap<>();

        LanguageController languageController = LanguageController.getInstance();

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(new MenuItem(languageController.getString("newTab")));
        fileMenu.getItems().add(new MenuItem(languageController.getString("save")));
        //fileMenu.getItems().add(new MenuItem("save as"));

        Menu editMenu = new Menu(languageController.getString("edit"));
        editMenu.getItems().add(new MenuItem(languageController.getString("clear")));
        editMenu.getItems().add(new MenuItem("Tag"));

        Menu languageMenu = new Menu(languageController.getString("langu"));
        languageMenu.getItems().add(new MenuItem("Italiano"));
        languageMenu.getItems().add(new MenuItem("English"));

        Menu preferencesMenu = new Menu(languageController.getString("prefs"));
        preferencesMenu.getItems().add(languageMenu);
        preferencesMenu.getItems().add(new MenuItem(languageController.getString("dir")));

        editMenu.getItems().add(preferencesMenu);

        Menu viewMenu = new Menu(languageController.getString("view"));
        viewMenu.getItems().add(new MenuItem(languageController.getString("gallery")));
        viewMenu.getItems().add(new MenuItem(languageController.getString("draw")));


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

        updateClickableMenus();

        if (languageController.getLocale().getLanguage().equals("en")) {
            menus.get("Language").getItems().get(1).setDisable(true);
            menus.get("Language").getItems().get(0).setDisable(false);
        } else if (languageController.getLocale().getLanguage().equals("it")) {
            menus.get("Language").getItems().get(0).setDisable(true);
            menus.get("Language").getItems().get(1).setDisable(false);
        }

        MenuBarController controller = MenuBarController.getInstance();
        controller.setupController(controlledStage, this);
    }

    public void updateClickableMenus() {
        if (ViewManager.getInstance().getCurrView() instanceof GalleryView) {
            setMenusForGallery();
        } else if (ViewManager.getInstance().getCurrView() instanceof DrawView){
            setMenusForDraw();
        } else if (ViewManager.getInstance().getCurrView() instanceof TagView){
            setMenusForTags();
        } else if (ViewManager.getInstance().getCurrView() instanceof SketchView){
            setMenusForTags();
        }
    }

    private void setMenusForDraw() {
        menus.get("File").getItems().forEach(i -> i.setDisable(false));
        menus.get("Edit").getItems().get(0).setDisable(false);
        menus.get("Edit").getItems().get(1).setDisable(false);
        menus.get("View").getItems().get(0).setDisable(false);
        menus.get("View").getItems().get(1).setDisable(true);

        if (!DrawCanvasController.getInstance().getDrawCanvas().containsPaper()) {
            // paper isn't present, so can't edit draw
            menus.get("File").getItems().get(1).setDisable(true);
            menus.get("Edit").getItems().get(0).setDisable(true);
            menus.get("Edit").getItems().get(1).setDisable(true);
        }
    }

    private void setMenusForGallery() {
        menus.get("File").getItems().forEach(i -> i.setDisable(true));
        menus.get("Edit").getItems().get(0).setDisable(true);
        menus.get("Edit").getItems().get(1).setDisable(true);
        menus.get("View").getItems().get(0).setDisable(true);
        menus.get("View").getItems().get(1).setDisable(false);
    }

    private void setMenusForSkechView() {
        menus.get("File").getItems().forEach(i -> i.setDisable(true));
        menus.get("Edit").getItems().get(0).setDisable(true);
        menus.get("Edit").getItems().get(1).setDisable(true);
        menus.get("View").getItems().get(0).setDisable(true);
        menus.get("View").getItems().get(1).setDisable(true);
    }

    private void setMenusForTags() {
        menus.get("Edit").getItems().get(0).setDisable(true);
        menus.get("Edit").getItems().get(1).setDisable(true);
        menus.get("Edit").getItems().get(2).setDisable(false);

        menus.get("File").getItems().get(0).setDisable(true);
        menus.get("File").getItems().get(1).setDisable(true);

        menus.get("View").getItems().get(0).setDisable(true);
        menus.get("View").getItems().get(1).setDisable(true);
    }

    HashMap<String, Menu> getMenuMap() {
        return menus;
    }
}
