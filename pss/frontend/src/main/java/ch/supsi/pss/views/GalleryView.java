package ch.supsi.pss.views;

import ch.supsi.pss.LanguageController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GalleryView extends View {

    public GalleryView() {
        // Label for the title of gallery mode
        Label galleryTitle = new Label(LanguageController.getInstance().getString("gallery"));

        // Search Field
        TextField search = new TextField();

        // Gallery Window Layout
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);

        this.getChildren().addAll(galleryTitle, search);
        GalleryViewController.getInstance().setGalleryView(this);
    }
}
