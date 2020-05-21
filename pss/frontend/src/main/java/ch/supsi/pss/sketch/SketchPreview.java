package ch.supsi.pss.sketch;

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
    }

    public Set<String> getTags(){
        return this.tags;
    }

    @Override
    public int compareTo(SketchPreview o) {
        return this.equals(o) ? 0 : 1;
    }
}
