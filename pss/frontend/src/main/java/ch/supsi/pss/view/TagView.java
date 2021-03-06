package ch.supsi.pss.view;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.model.drawFrame.canvas.DrawCanvasController;
import ch.supsi.pss.model.menubar.MenuBarController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class TagView extends View {

    private final TextArea tags;
    private final TextField addTags;
    private final Button addBtn;
    private final Button doneBtn;

    public TagView() {
        this.setPadding(new Insets(10, 10, 10, 10));

        Label text = new Label(LanguageController.getInstance().getString("tag_title"));

        tags = new TextArea();
        tags.setPrefWidth(this.getWidth() - 50);
        tags.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        tags.setPrefHeight(500);
        tags.setEditable(false);

        addTags = new TextField();
        addTags.setPromptText(LanguageController.getInstance().getString("tag_caption"));

        addBtn = new Button();
        addBtn.setText(LanguageController.getInstance().getString("tag_add_btn"));

        doneBtn = new Button();
        doneBtn.setText(LanguageController.getInstance().getString("done_btn"));

        //Add nodes to children list
        getChildren().addAll(text, tags, addTags, addBtn, doneBtn);

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(10,10,10,10));

        TagViewController.getInstance().setTagView(this);
        TagViewController.getInstance().setupEvents();
    }

    public TextArea getTags() {
        return tags;
    }

    public TextField getAddTags() {
        return addTags;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public Button getDoneBtn() {
        return doneBtn;
    }

    public void updateContent() {
        if (DrawCanvasController.getInstance().getSketchController() == null)
            tags.setText("");
        else
            tags.setText(DrawCanvasController.getInstance().getSketchController().getTagsAsString());
    }

    public void submitTag(){
        String newTag = addTags.getText();
        if(!newTag.isEmpty())
            DrawCanvasController.getInstance().getSketchController().addTag(newTag);
        else
            addTags.setPromptText(LanguageController.getInstance().getString("tag_caption"));

        addTags.clear();
        updateContent();
    }

    @Override
    public void onShow() {
        MenuBarController.getInstance().getMenuBar().updateClickableMenus();
        TagViewController.getInstance().getTagView().updateContent();
    }

    @Override
    public void onHide() {

    }
}
