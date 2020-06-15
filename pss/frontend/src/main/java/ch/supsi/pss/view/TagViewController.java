package ch.supsi.pss.view;

import ch.supsi.pss.model.menubar.MenuBarController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

public class TagViewController {
    private static TagViewController instance;
    private TagView tagView;

    private TagViewController() {
    }

    public static TagViewController getInstance() {
        if (instance == null)
            instance = new TagViewController();

        return instance;
    }

    public void setupEvents() {
        tagView.getAddBtn().setOnAction(e -> {
            getTagView().submitTag();
        });

        tagView.getAddTags().setOnKeyReleased(e -> {
            if (e.getCode().equals(KeyCode.ENTER))
                tagView.submitTag();
        });

    }

    public void setOnBack(EventHandler<ActionEvent> handler){
        tagView.getDoneBtn().setOnAction(handler);
    }

    public void setTagView(TagView tagView) {
        this.tagView = tagView;
    }

    public TagView getTagView() {
        return tagView;
    }
}
