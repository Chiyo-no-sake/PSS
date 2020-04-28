package ch.supsi.pss.draw;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.*;


public class ToolBar {
    private ObservableList<Button> btnList;

    ToolBar() {
        this.btnList = new FXCollections.observableArrayList();

        this.btnList.add(new Button("FreeDraw"));
        this.btnList.add(new Button("Square"));
        this.btnList.add(new Button("Circle"));
    }

    void addToParent(Pane parentPane){
        parentPane.getChildren().addAll(btnList);
    }
}
