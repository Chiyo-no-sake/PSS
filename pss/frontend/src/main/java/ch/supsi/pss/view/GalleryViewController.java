package ch.supsi.pss.view;

/*
TODO:
    add a new event handler on each instance of SketchPreview that opens a new view ( aka SketchView that extends View )
    the new sketchview will:
        - extends borderPane
        - set center to a scrollview containing the imageView of the draw ( see drawFrame construcor for an example )
        - set right to a textarea, where there will be a list of tags ( see TagView for example )
        - have a back button
 */

public class GalleryViewController {
    private static GalleryViewController instance;
    private GalleryView galleryView;

    private GalleryViewController(){};

    public static GalleryViewController getInstance(){
        if(instance == null)
            instance = new GalleryViewController();

        return instance;
    }

    public void setGalleryView(GalleryView galleryView) {
        this.galleryView = galleryView;
    }

    public GalleryView getGalleryView() {
        return galleryView;
    }

    public void setUpListeners(){
        galleryView.getSearchBar().setOnKeyReleased( e -> galleryView.updateGalleryContent());
    }


}
