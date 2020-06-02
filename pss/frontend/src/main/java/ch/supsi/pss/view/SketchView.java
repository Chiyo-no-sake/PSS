package ch.supsi.pss.view;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.model.menubar.MenuBarController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.Set;


public class SketchView extends View {

    public SketchView(Image image, Set<String> tags) {

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane scrollPane = new ScrollPane();
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(scrollPane.widthProperty());
        imageView.fitHeightProperty().bind(scrollPane.heightProperty());
        scrollPane.setContent(imageView);

        ObservableList<String> observableList = FXCollections.observableList(new ArrayList<>(tags));
        ListView<String> listView = new ListView<>(observableList);
        listView.setEditable(false);

        Button back = new Button(LanguageController.getInstance().getString("back"));
        back.setOnAction(e ->{
            ViewManager.getInstance().toView(GalleryViewController.getInstance().getGalleryView());
        });

        Insets insets = new Insets(10);

        BorderPane borderPane = new BorderPane();
        BorderPane.setAlignment(back, Pos.CENTER);

        borderPane.prefWidthProperty().bind(MenuBarController.getInstance().getStage().widthProperty());
        borderPane.prefHeightProperty().bind(MenuBarController.getInstance().getStage().heightProperty());

        borderPane.setCenter(scrollPane);

        borderPane.setRight(listView);

        borderPane.setBottom(back);

        borderPane.getChildren().forEach( node -> {
            BorderPane.setMargin(node,insets);
        });

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
