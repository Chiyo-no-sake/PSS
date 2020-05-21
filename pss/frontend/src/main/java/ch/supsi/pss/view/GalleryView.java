package ch.supsi.pss.view;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.misc.PreferencesRepository;
import ch.supsi.pss.model.menubar.MenuBarController;
import ch.supsi.pss.sketch.SketchPreview;
import ch.supsi.pss.sketch.SketchReader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.util.*;


public class GalleryView extends View {
    // TODO: layout
    //  Searchbar listener to filter sketches on each digit
    //      clicking on an item will open another view with full sized image and bottom TextArea to show tags of the draw
    //      this view will be a SketchView
    //
    // TODO: display a semi-transparent centered text instead of the FlowPane either:
    //      - if there are no sketch to show ("gallery_no_sketch")
    //      - if there are no result from the searchbar ("gallery_no_result")

    private final TextField searchBar;
    private final FlowPane gallery;

    private final static double PREVIEW_WIDTH = Double.parseDouble(
            PreferencesRepository
                    .getAllProperties(true)
                    .getProperty("preview_sketch_width"));

    private final static double PREVIEW_HEIGHT = Double.parseDouble(
            PreferencesRepository
                    .getAllProperties(true)
                    .getProperty("preview_sketch_height"));

    public GalleryView() {
        // Gallery Window Layout
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(10, 10, 10, 10));

        // Label for the title of gallery mode
        Label galleryTitle = new Label(LanguageController.getInstance().getString("gallery"));

        // Search Field
        searchBar = new TextField();
        searchBar.setPromptText(PreferencesRepository.getAllProperties(true).getProperty("gallery_searchbar_caption"));

        // Gallery flowpane to display previews
        gallery = new FlowPane(Orientation.HORIZONTAL);
        gallery.setHgap(10);
        gallery.setVgap(10);

        // scrollpane to contain flowpane
        ScrollPane scroll = new ScrollPane(gallery);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(600);

        // binding flowPane width to scrollPane width
        gallery.prefWidthProperty().bind(scroll.widthProperty());

        this.getChildren().addAll(galleryTitle, searchBar, scroll);
        GalleryViewController.getInstance().setGalleryView(this);

        GalleryViewController.getInstance().setUpListeners();
    }

    private Set<SketchPreview> filterSketches(ArrayList<SketchPreview> sketches, Set<String> tags) {
        Set<SketchPreview> res = new TreeSet<>();

        sketches.forEach(sketch -> {
            tags.forEach(searchingTag -> {
                sketch.getTags().forEach(realTag -> {
                    if (realTag.contains(searchingTag))
                        res.add(sketch);
                });
            });
        });

        return res;
    }

    private ArrayList<SketchPreview> takeAllSketches() {
        Map<Image, Set<String>> sketches = SketchReader.getInstance().getSketches();

        ArrayList<SketchPreview> sketchPreviews = new ArrayList<>();

        sketches.keySet().forEach(img -> {
            sketchPreviews.add(new SketchPreview(img, sketches.get(img), PREVIEW_WIDTH, PREVIEW_HEIGHT));
        });

        return sketchPreviews;
    }

    public void clearGallery() {
        gallery.getChildren().clear();
    }

    public void updateGalleryContent() {
        clearGallery();
        gallery.setAlignment(Pos.TOP_LEFT);
        if (getSearchBar().getText().equals("")) {
            gallery.getChildren().addAll(takeAllSketches());
        } else {
            Set<String> tags = new HashSet<String>(Arrays.asList(getSearchBar().getText().split(" ")));
            Set<SketchPreview> res = filterSketches(takeAllSketches(), tags);
            if (res.isEmpty()) {
                gallery.setAlignment(Pos.CENTER);
                // TODO: dk why not showing text
                gallery.getChildren().add(new Label(LanguageController.getInstance().getString("gallery_no_result")));
            } else
                gallery.getChildren().addAll(res);
        }
    }

    public TextField getSearchBar() {
        return searchBar;
    }

    public FlowPane getGallery() {
        return gallery;
    }

    @Override
    public void onShow() {
        SketchReader.getInstance().refreshSketches();
        this.updateGalleryContent();
        MenuBarController.getInstance().getMenuBar().updateClickableMenus();
    }

    @Override
    public void onHide() {

    }
}
