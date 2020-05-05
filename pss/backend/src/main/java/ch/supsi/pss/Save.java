package ch.supsi.pss;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

import javafx.stage.Stage;

import javax.imageio.ImageIO;

import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Properties;
import java.util.UUID;

public class Save {

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

    private static String directoryPath;
    private static String drawsDirectory, metadataDirectory;

    public static void setDirectory(final Stage stage) {

        try{
            FileInputStream in = new FileInputStream("pss/backend/config.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            String initialPath = props.getProperty("path");

            File file = new File(initialPath);

            if(!file.exists()){
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File directory = directoryChooser.showDialog(stage);

                FileOutputStream out = new FileOutputStream("pss/backend/config.properties");
                props.setProperty("path",directory.getAbsolutePath());
                props.store(out, null);
                out.close();

                new File(  props.getProperty("path") + "/draws").mkdir();
                new File(  props.getProperty("path") + "/metadata").mkdir();

            }
            directoryPath = props.getProperty("path");
            drawsDirectory = directoryPath + "/draws";
            metadataDirectory = directoryPath + "/metadata";

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void save(final Stage stage, final BorderPane drawFrame){

        String name = UUID.randomUUID().toString();

        File file = new File(drawsDirectory + "/" + name);
        try {
            WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
            drawFrame.snapshot(null, writableImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
            ImageIO.write(renderedImage,"png",file);

        }catch (Exception e){
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sketch salvato correttamente");
        alert.show();

        stage.sizeToScene();
    }
}
