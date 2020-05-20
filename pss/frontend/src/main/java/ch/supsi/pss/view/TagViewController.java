package ch.supsi.pss.views;

import ch.supsi.pss.drawFrame.DrawCanvasController;
import ch.supsi.pss.menubar.MenuBarController;

public class TagViewController {
    private static TagViewController instance;
    private TagView tagView;

    private TagViewController(){};

    public static TagViewController getInstance(){
        if(instance == null)
            instance = new TagViewController();

        return instance;
    }

    public void setupEvents(){
        tagView.getAddBtn().setOnAction(e -> {
            String newTag = tagView.getAddTags().getText();
            DrawCanvasController.getInstance().getSketchController().addTag(newTag);
            tagView.updateContent();
        });

        tagView.getDoneBtn().setOnAction(e -> {
            ViewManager.getInstance().toView(DrawViewController.getInstance().getDrawView());
            MenuBarController.getInstance().getMenuBar().updateClickableMenus();
        });
    }

    public void setTagView(TagView tagView) {
        this.tagView = tagView;
    }

    public TagView getTagView() {
        return tagView;
    }
}
