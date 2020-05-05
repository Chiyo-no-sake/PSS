package ch.supsi.pss;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.UUID;

public class Save {

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

    public static void save(Stage stage, BorderPane drawFrame){
        FileChooser savefile = new FileChooser();
        savefile.setInitialFileName(UUID.randomUUID().toString());

        File file = savefile.showSaveDialog(stage);
        try {
            WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
            drawFrame.snapshot(null, writableImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
            ImageIO.write(renderedImage,"png",file);

        }catch (Exception e){
            System.out.println("Error");
        }

        stage.sizeToScene();
    }
}
