package ch.supsi.pss.view;

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
