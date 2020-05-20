package ch.supsi.pss.views;

public class DrawViewController {
    private static DrawViewController instance;
    private DrawView drawView;

    private DrawViewController(){};

    public static DrawViewController getInstance(){
        if(instance == null)
            instance = new DrawViewController();

        return instance;
    }

    public void setDrawView(DrawView drawView) {
        this.drawView = drawView;
    }

    public DrawView getDrawView() {
        return drawView;
    }
}
