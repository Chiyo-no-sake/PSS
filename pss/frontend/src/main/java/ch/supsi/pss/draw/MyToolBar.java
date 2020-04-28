package ch.supsi.pss.draw;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import java.util.ArrayList;


public class MyToolBar extends ToolBar {

    private final ArrayList<Button> btnList = new ArrayList<>();

    public MyToolBar() {
        btnList.add(new Button("Sq"));
        btnList.add(new Button("Li"));
        btnList.add(new Button("Fd"));
        btnList.add(new Button("Er"));
        btnList.add(new Button("XX"));

        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(10, 10, 10, 10));

        btnList.forEach(b -> {
            this.getItems().add(b);
            b.setPrefWidth(40);
            b.setPrefHeight(40);
        });
    }
}
