package ch.supsi.pss.helpers;

import ch.supsi.pss.LanguageController;
import ch.supsi.pss.PreferencesRepository;
import ch.supsi.pss.SketchController;
import ch.supsi.pss.drawFrame.DrawCanvasController;
import ch.supsi.pss.drawFrame.DrawToolbarController;
import ch.supsi.pss.helpers.Alerter;
import ch.supsi.pss.menubar.MenuBarController;

import java.util.LinkedList;

public class SketchCreator {
    public static void newSketch(){
        LinkedList<String> choices = new LinkedList<>();
        choices.add(LanguageController.getInstance().getString("non_portrait_text"));
        choices.add(LanguageController.getInstance().getString("portrait_text"));
        boolean bPortrait = Alerter.popChoiceDialog(LanguageController.getInstance().getString("new_draw_header"),
                LanguageController.getInstance().getString("new_draw_header"),
                LanguageController.getInstance().getString("mode_select_text"),
                choices) == 1;

        MenuBarController.getInstance().getSketchController().set(new SketchController());

        if(DrawCanvasController.getInstance().getDrawCanvas().containsPaper())
            if (!Alerter.popConfirmDialog(LanguageController.getInstance().getString("r_u_sure"),
                    LanguageController.getInstance().getString("erase"),
                    LanguageController.getInstance().getString("ok_with"))) {
                return;
            }

        double width = Double.parseDouble(bPortrait ? PreferencesRepository.getAllProperties(true).getProperty("portrait_draw_width")
                : PreferencesRepository.getAllProperties(true).getProperty("horizontal_draw_width"));

        double height = Double.parseDouble(bPortrait ? PreferencesRepository.getAllProperties(true).getProperty("portrait_draw_height")
                : PreferencesRepository.getAllProperties(true).getProperty("horizontal_draw_height"));

        DrawCanvasController.getInstance().getDrawCanvas().createPaper(width, height);
        DrawToolbarController.getInstance().getToolBar().updateButtonStatus();
        MenuBarController.getInstance().getMenuBar().updateClickableMenus();
    }
}
