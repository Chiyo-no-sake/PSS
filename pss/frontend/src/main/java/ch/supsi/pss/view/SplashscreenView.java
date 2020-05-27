package ch.supsi.pss.view;

import ch.supsi.pss.misc.PreferencesRepository;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class SplashscreenView extends View {

    public SplashscreenView() {
        ImageView img = new ImageView(new Image(this.getClass().getResource("/splash/logo.jpeg").toExternalForm()));
        img.setFitWidth(Double.parseDouble(PreferencesRepository.getAllProperties(true).getProperty("splash_width")));
        img.setFitHeight(Double.parseDouble(PreferencesRepository.getAllProperties(true).getProperty("splash_height")));
        img.setPreserveRatio(true);
        getChildren().add(img);
    }

    @Override
    public void onShow() {
    }

    public void performWithDelay(int delay, Runnable r){
        new Thread( () -> {
            try{
                Thread.sleep(delay);
                r.run();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onHide() {

    }
}
