package ch.supsi.pss.model.gallery;

import ch.supsi.pss.view.GalleryViewController;
import ch.supsi.pss.view.SketchView;
import ch.supsi.pss.view.SketchViewController;
import ch.supsi.pss.view.ViewManager;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import java.util.Set;

public class SketchPreview extends BorderPane implements Comparable<SketchPreview>{
    private final Set<String> tags;
    private final Image img;

    public SketchPreview(Image img, Set<String> tags, double width, double height) {
        this.img = img;
        ImageView imgView = new ImageView(img);
        imgView.setPreserveRatio(true);

        this.setStyle("-fx-border-color: darkgrey");

        if (img.getWidth() > img.getHeight()) {
            imgView.setFitWidth(width);
        } else {
            imgView.setFitHeight(height);
        }

        this.setCenter(imgView);
        this.tags = tags;

        this.setPrefWidth(width);
        this.setPrefHeight(height);
        BorderPane.setAlignment(imgView, Pos.CENTER);

        this.setOnMouseClicked( e -> {
            ViewManager.getInstance().toView(new SketchView(img, tags));
            SketchViewController.getInstance().setOnBack(ev ->{
                ViewManager.getInstance().toView(GalleryViewController.getInstance().getGalleryView());
            });
        });
    }

    public Set<String> getTags(){
        return this.tags;
    }

    @Override
    public int compareTo(SketchPreview o) {
        return this.equals(o) ? 0 : 1;
    }
}
