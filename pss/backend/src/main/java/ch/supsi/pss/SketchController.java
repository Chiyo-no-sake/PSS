package ch.supsi.pss;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URI;
import java.util.*;


public class SketchController {

    private SketchService sketchService;

    public void newSketch(final BorderPane sketch){
        sketchService = new SketchService(sketch);
    }

    public void updateSketch(final BorderPane sketch){

    }

    public SketchService getSketchService() {
        return sketchService;
    }
}

class SketchService{

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 768;

    private boolean saved = false;

    public boolean isSaved() {
        return saved;
    }

    private final Set<String> tags = new TreeSet<>();
    private BorderPane sketch;

    public SketchService(final BorderPane sketch) {
        this.sketch = sketch;
    }

    public boolean saveSketch(){
        String uuid = UUID.randomUUID().toString();
        if(saveDraw(uuid) && saveMetadata(uuid))
            return true;

        return false;
    }

    private boolean saveDraw(final String uuid){
        File file = new File(PreferencesRepository.getDrawsPath() + "/" + uuid );
        try {
            WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
            sketch.snapshot(null, writableImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
            ImageIO.write(renderedImage,"png",file);

            return true ;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveMetadata(final String uuid){
        File file = new File(PreferencesRepository.getMetadataPath() + "/" + uuid );
        try {
            // TODO: 12/05/20 Stampare su file lista tags

            return true ;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTag(String tag){
        return tags.add(tag);
    }

    public Set<String> getTags() {
        return tags;
    }
}

class PreferencesRepository{

    private static String drawsPath, metadataPath;

    private static Properties getProps(){
        try{
            FileInputStream in = new FileInputStream("pss/backend/config.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();

            return props;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getPath(){
        Properties properties = getProps();
        String path = null;

        if (properties != null) {
            path = properties.getProperty("path");
            drawsPath = path + "/draws";
            metadataPath = path + "/metadata";
        }

        return path;
    }

    public static URI getStorageUri(){
        try{
            String path = getPath();
            if(path == null)
                throw new Exception("Path has not been setted!");

            return new URI(path);

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static boolean setRepository(final Stage stage) {
        if(getPath() == null || !(new File(getPath()).exists())){
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File directory = directoryChooser.showDialog(stage);

            Properties properties = getProps();

            try{
                if(properties == null)
                    throw new IOException("Properties Error");

                FileOutputStream out = new FileOutputStream("pss/backend/config.properties");
                properties.setProperty("path",directory.getAbsolutePath());
                properties.store(out, null);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            drawsPath = getPath() + "/draws";
            metadataPath = getPath() + "/metadata";

            new File(  drawsPath).mkdir();
            new File( metadataPath ).mkdir();

            return true;
        }
        return false;
    }

    public static String getDrawsPath() {
        return drawsPath;
    }

    public static String getMetadataPath() {
        return metadataPath;
    }
}

