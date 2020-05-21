package ch.supsi.pss.model.drawFrame;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


/**
 * not intended to use outside the package
 */
class ImageButton extends Button {
    private ImageView img;
    private boolean isSelected;

    private String selectedStyle = "-fx-border-color: #5561ff; -fx-border-width: 2";
    private String defaultStyle = "-fx-border-color: #bbb7bc; -fx-border-width: 2";

    ImageButton(ImageView img) {
        super("", img);
        this.img = img;
        img.setFitWidth(20);
        img.setFitHeight(20);
        setSelected(false);
    }

    ImageButton(ImageView img, double width, double height) {
        super("", img);
        img.setFitWidth(width);
        img.setFitHeight(height);
        setSelected(false);
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
        setSelected(false);
    }

    void setSelected(boolean selected){
        this.isSelected=selected;
        this.setStyle(isSelected ? selectedStyle : defaultStyle);
    }

}
