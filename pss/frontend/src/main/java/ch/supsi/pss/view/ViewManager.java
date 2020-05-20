package ch.supsi.pss.view;

import javafx.scene.layout.Pane;

public class ViewManager {
    private static ViewManager instance;

    private View currView;

    private Pane root;

    private ViewManager() {
    }

    public static ViewManager getInstance() {
        if (instance == null)
            instance = new ViewManager();

        return instance;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Pane getRoot() {
        return root;
    }

    public void toView(View v){
        resetScene();
        this.root.getChildren().add(v);
        this.currView = v;
    }

    private void resetScene() {
        root.getChildren().remove(currView);
        currView = null;
    }

    public View getCurrView(){
        return this.currView;
    }
}
