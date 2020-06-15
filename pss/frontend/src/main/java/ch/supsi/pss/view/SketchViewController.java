package ch.supsi.pss.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SketchViewController {
    private static SketchViewController instance;
    private SketchView sketchView;

    private SketchViewController(){};

    public static SketchViewController getInstance(){
        if(instance == null)
            instance = new SketchViewController();

        return instance;
    }

    public void setSketchView(SketchView sketchView) {
        this.sketchView = sketchView;
    }

    public SketchView getSketchView() {
        return sketchView;
    }

    public void setOnBack(EventHandler<ActionEvent> handler){
        sketchView.getBackBtn().setOnAction(handler);
    }

    public void SetupListeners(){
    }
}
