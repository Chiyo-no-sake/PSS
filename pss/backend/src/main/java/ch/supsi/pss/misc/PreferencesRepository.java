package ch.supsi.pss.misc;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public abstract class PreferencesRepository{

    private static final String USER_HOME = System.getProperty("user.home");
    private static String drawsPath = null, metadataPath = null;
    private static Properties properties = new Properties();

    private static final String CONFIG_PROPERTIES =
            USER_HOME
                    + File.separator + ".pss" + PomProperties.getInstance().getData().getProperty("version")
                    + File.separator + "config.properties"  ;

    public static String getUserHome() {
        return USER_HOME;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(final boolean flag){
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

    public static void setOrCreateFolders(){
        setPaths();

        new File( drawsPath).mkdir();
        new File( metadataPath ).mkdir();
    }

    public static boolean propertiesExistsOrNotContainsKey(){
        return (properties == null || !properties.containsKey("path"));
    }


    public static void updateDirectory(final File directory){

        try (FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES)){
            properties.setProperty("path", directory.getAbsolutePath());
            properties.store(out,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOrCreateFolders();
    }

    public static boolean containsPathOrFolderNotExists(){
        return (!properties.containsKey("path") || !(new File(properties.getProperty("path")).exists()));
    }

    public static void setDirectory(final File directory) {
        try(FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES);){
            properties.setProperty("path", directory.getAbsolutePath());
            properties.store(out, null);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public static String getDrawsPath() {
        return drawsPath;
    }

    public static String getMetadataPath() {
        return metadataPath;
    }
}

