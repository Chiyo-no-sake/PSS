package ch.supsi.labingsw1.model;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import javafx.scene.paint.*;

public class StyledCell extends ListCell<Course> {
    private Color bgColor;

    public StyledCell(Color bgColor) {
        super();
        this.bgColor = bgColor;
    }

    @Override
    protected void updateItem(Course item, boolean empty) {
        super.updateItem(item, empty);
        this.setBackground(
                new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY))
        );

        this.setTextFill(Colors.WHITE.getVal());
    }

    public void setColor(Colors c){
        this.bgColor = c.getVal();
    }
}
