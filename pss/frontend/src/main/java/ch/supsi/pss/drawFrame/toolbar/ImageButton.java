package ch.supsi.pss.drawFrame.toolbar;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ImageButton extends Button {
    ImageView img;

    ImageButton(ImageView img) {
        super("", img);
        this.img = img;
        img.setFitWidth(20);
        img.setFitHeight(20);
    }

    ImageButton(ImageView img, double width, double height) {
        super("", img);
        img.setFitWidth(width);
        img.setFitHeight(height);
    }

    void changeImage(ImageView img) {
        super.setGraphic(img);
        this.img = img;
        this.img.setFitWidth(20);
        this.img.setFitHeight(20);
    }

    void setImageSize(double width, double height){
        img.setFitWidth(width);
        img.setFitHeight(height);
    }

}
