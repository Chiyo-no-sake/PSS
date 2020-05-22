package ch.supsi.pss.sketch;

import ch.supsi.pss.model.menubar.MenuBarController;
import ch.supsi.pss.view.GalleryViewController;
import ch.supsi.pss.view.View;
import ch.supsi.pss.view.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.Set;


public class SketchView extends View {

    public SketchView(Image image, Set<String> tags) {

        BorderPane borderPane = new BorderPane();

        ScrollPane scrollPane = new ScrollPane();
        ImageView imageView = new ImageView(image);

        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(scrollPane.widthProperty());
        imageView.fitHeightProperty().bind(scrollPane.heightProperty());

        scrollPane.setContent(imageView);


        ObservableList<String> observableList = FXCollections.observableList(new ArrayList<>(tags));
        ListView<String> listView = new ListView<>(observableList);

        AnchorPane anchorPane = new AnchorPane();
        Button back = new Button("Back");

        back.setOnAction(e ->{
            ViewManager.getInstance().toView(GalleryViewController.getInstance().getGalleryView());
        });

        AnchorPane.setRightAnchor(back,10.0);

        anchorPane.getChildren().add(back);



        borderPane.setCenter(scrollPane);
        borderPane.setRight(listView);
        borderPane.setBottom(anchorPane);
        borderPane.setPadding(new Insets(10,10,10,10));

        this.setSpacing(20);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().add(borderPane);
    }

    @Override
    public void onShow() {
        MenuBarController.getInstance().getMenuBar().updateClickableMenus();
    }

    @Override
    public void onHide() {

    }
}
