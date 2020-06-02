package ch.supsi.pss.misc;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public abstract class PreferencesRepository{

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String CONFIG_PROPERTIES =
            USER_HOME
                    + File.separator + ".pss"
                    + File.separator + "config.properties"  ;


    private static String drawsPath, metadataPath;
    private static Properties properties = null;

    private static void setProperties(final boolean flag){
        properties = getAllProperties(flag);
    }

    public static String getLanguage(){
        return properties.getProperty("current_language");
    }

    public static void setPaths(){
        drawsPath =  properties.getProperty("path") + File.separator + "draws";
        metadataPath = properties.getProperty("path") + File.separator + "metadata";
    }


    public static Properties getAllProperties(final boolean fromProjectDir){
        Properties properties = new Properties();

        try(InputStream inputStream =
                    fromProjectDir ?
                    PreferencesRepository.class.getClassLoader().getResourceAsStream("config.properties"):
                    new FileInputStream(CONFIG_PROPERTIES))
        {
            properties.load(inputStream);
            return properties;

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public static void copyPropertiesFile() {
        Path path = Paths.get(CONFIG_PROPERTIES);
        File directory = new File(path.getParent().toString());
        directory.mkdir();

        File file = new File(CONFIG_PROPERTIES);

        if(file.exists()){
            setProperties(false);
            setPaths();
            return;
        }

        setProperties(true);
        setPaths();
        try{
            if (file.createNewFile()){
                FileOutputStream out = new FileOutputStream(file);
                properties.store(out, null);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeField(final String field, final String str){
        try (FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES)){
            properties.setProperty(field, str);
            properties.store(out,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateDirectory(final Stage stage){

        if(properties == null || !properties.containsKey("path")){
            setDirectory(stage);
            return;
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        Path path = Paths.get(properties.getProperty("path"));

        directoryChooser.setInitialDirectory(new File(path.getParent().toString()));
        File directory = directoryChooser.showDialog(stage);

        try (FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES)){
            properties.setProperty("path", directory.getAbsolutePath());
            properties.store(out,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPaths();

        new File( drawsPath).mkdir();
        new File( metadataPath ).mkdir();
    }


    public static void setDirectory(final Stage stage) {

        setProperties(false);

        if(!properties.containsKey("path") || !(new File(properties.getProperty("path")).exists())){
            DirectoryChooser directoryChooser = new DirectoryChooser();

            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File directory = directoryChooser.showDialog(stage);

            try(FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES);){
                properties.setProperty("path", directory.getAbsolutePath());
                properties.store(out, null);
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }

        setPaths();

        new File( drawsPath).mkdir();
        new File( metadataPath ).mkdir();
    }

    public static String getDrawsPath() {
        return drawsPath;
    }

    public static String getMetadataPath() {
        return metadataPath;
    }
}

