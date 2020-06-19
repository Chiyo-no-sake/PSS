package ch.supsi.pss.misc;

import ch.supsi.pss.interfaces.HasProperties;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigPropertiesFromUser implements HasProperties {
    private final Properties properties = new Properties();
    private static ConfigPropertiesFromUser instance = null;

    public static final String USER_HOME = System.getProperty("user.home");

    public static final String CONFIG_PROPERTIES =
            USER_HOME
            + File.separator + ".pss" + PomProperties.getInstance().getData().getProperty("version")
            + File.separator + "config.properties"  ;

    private ConfigPropertiesFromUser() {
        setProperties();
    }

    public static ConfigPropertiesFromUser getInstance(){
        if(instance == null)
            instance = new ConfigPropertiesFromUser();
        return instance;
    }

    @Override
    public boolean setProperties() {
        copyFromJarFile();
        try(InputStream is = new FileInputStream(CONFIG_PROPERTIES)){
            properties.load(is);
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return false;
    }

    private boolean copyFromJarFile() {
        File file = new File(CONFIG_PROPERTIES);

        if(file.exists())   return false;

        new File(file.getParent()).mkdir();

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.properties")){
            Files.copy(is, Paths.get(file.getAbsolutePath()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeLanguage(final String str){
        try (FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES)){
            properties.setProperty("current_language", str);
            properties.store(out,null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @Override
    public Properties getProperties() {
        return properties;
    }


}
