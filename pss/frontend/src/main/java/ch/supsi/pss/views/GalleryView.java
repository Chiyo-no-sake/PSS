package ch.supsi.pss.views;

import ch.supsi.pss.misc.LanguageController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GalleryView extends View {

    // TODO: layout
    //  Searchbar to fliter tags after every digit (caption: "gallery_searchbar_caption"
    //  FlowPane that contains all sketches found in the directory
    //     - control for existance of both image and tags
    //     + ImageView with preserveAspectRatio true contained in the flow pane
    //      clicking on an item will open another view with full sized image and bottom TextArea to show tags of the draw
    //      this view will be a SketchView
    //
    // TODO: check for directory existance to avoid  bugs
    // TODO: display a semi-transparent centered text instead of the FlowPane either:
    //      - if there are no sketch to show ("gallery_no_sketch")
    //      - if there are no result from the searchbar ("gallery_no_result")

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
