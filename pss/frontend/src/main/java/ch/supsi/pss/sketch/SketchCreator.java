package ch.supsi.pss.sketch;

import ch.supsi.pss.misc.*;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import ch.supsi.pss.model.drawFrame.toolbar.DrawToolbarController;
import ch.supsi.pss.model.menubar.MenuBarController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class SketchCreator {
    public static void newSketch(){
        // prompt multi-choice dialog
        LinkedList<String> choices = new LinkedList<>();
        choices.add(LanguageController.getInstance().getString("non_portrait_text"));
        choices.add(LanguageController.getInstance().getString("portrait_text"));

        int dialogResult = Alerter.popChoiceDialog(LanguageController.getInstance().getString("new_draw_header"),
                LanguageController.getInstance().getString("new_draw_header"),
                LanguageController.getInstance().getString("mode_select_text"),
                choices);

        // dialog has a choice, creating sketch
        if(dialogResult>=0) {
            boolean bPortrait = dialogResult == 1;

            // get current weekDay for first tag
            ArrayList<String> tags = new ArrayList<>();
            tags.add(LanguageController.
                    getInstance().
                    getString(WeekDays.
                            toString(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))));

            if (DrawCanvasController.getInstance().getDrawCanvas().containsPaper())
                if (!Alerter.popConfirmDialog(LanguageController.getInstance().getString("r_u_sure"),
                        LanguageController.getInstance().getString("erase"),
                        LanguageController.getInstance().getString("ok_with"))) {
                    return;
                }

            double width = Double.parseDouble(bPortrait ? RepositoryController.getInstance().getConf().getProperty("portrait_draw_width")
                    : RepositoryController.getInstance().getConf().getProperty("horizontal_draw_width"));

            double height = Double.parseDouble(bPortrait ? RepositoryController.getInstance().getConf().getProperty("portrait_draw_height")
                    : RepositoryController.getInstance().getConf().getProperty("horizontal_draw_height"));

            DrawCanvasController.getInstance().getDrawCanvas().createPaper(width, height);

            // create a new sketch controller aka new UUID and empty tag list
            DrawCanvasController.getInstance().setSketchController(new SketchController(null, tags));

        }else{
            // Closed dialog without a choice
            Alerter.popInformationAlert(LanguageController.getInstance().getString("new_draw_header"),
                    LanguageController.getInstance().getString("not_new_draw_header"),
                    LanguageController.getInstance().getString("not_new_draw_text"));
        }

        DrawToolbarController.getInstance().getToolBar().updateButtonStatus();
        MenuBarController.getInstance().getMenuBar().updateClickableMenus();
    }
}
