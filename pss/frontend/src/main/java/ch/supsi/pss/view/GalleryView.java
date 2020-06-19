package ch.supsi.pss.view;

import ch.supsi.pss.misc.LanguageController;
import ch.supsi.pss.misc.RepositoryController;
import ch.supsi.pss.model.menubar.MenuBarController;
import ch.supsi.pss.model.gallery.SketchPreview;
import ch.supsi.pss.sketch.SketchController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class GalleryView extends View {

    private final TextField searchBar;
    private final FlowPane gallery;

    private final static double PREVIEW_WIDTH = Double.parseDouble(
            RepositoryController.getInstance().getConf().getProperty("preview_sketch_width"));

    private final static double PREVIEW_HEIGHT = Double.parseDouble(
            RepositoryController.getInstance().getConf().getProperty("preview_sketch_height"));

    public GalleryView() {
        // Gallery Window Layout
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(10, 10, 10, 10));

        // Label for the title of gallery mode
        Label galleryTitle = new Label(LanguageController.getInstance().getString("gallery"));

        // Search Field
        searchBar = new TextField();
        searchBar.setPromptText(LanguageController.getInstance().getString("gallery_searchbar_caption"));
        searchBar.setFocusTraversable(false);
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
        Map<byte[], Set<String>> tmp = SketchController.getAllSketches();//SketchReader.getInstance().getSketches();

        Map<Image, Set<String>> sketches = new HashMap<>();
        tmp.keySet().forEach((bytes -> {
            try(InputStream in = new ByteArrayInputStream(bytes);) {
                sketches.put(new Image(in), tmp.get(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));


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
        ArrayList<SketchPreview> allSketches = takeAllSketches();
        if (getSearchBar().getText().equals("")) {
            // no filter entered
            if(allSketches.isEmpty()) {
                // empty gallery
                gallery.setAlignment(Pos.CENTER);
                gallery.getChildren().add(new Label(LanguageController.getInstance().getString("gallery_no_sketch")));
            }else {
                gallery.getChildren().addAll(allSketches);
            }
        } else {
            // filter results
            Set<String> tags = new HashSet<>(Arrays.asList(getSearchBar().getText().split(" ")));
            Set<SketchPreview> res = filterSketches(allSketches, tags);
            if (res.isEmpty()) {
                // no results from search
                gallery.setAlignment(Pos.CENTER);
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
        searchBar.clear();
        if(!SketchController.refresh()){
            gallery.getChildren().add(new Label(LanguageController.getInstance().getString("gallery_no_result")));
        }
        this.updateGalleryContent();
        MenuBarController.getInstance().getMenuBar().updateClickableMenus();
    }

    @Override
    public void onHide() {

    }
}
