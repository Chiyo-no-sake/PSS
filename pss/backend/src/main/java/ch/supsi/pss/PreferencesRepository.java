package ch.supsi.pss;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class PreferencesRepository{

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

