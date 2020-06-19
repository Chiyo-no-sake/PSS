package ch.supsi.pss.misc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RepositoryController  {
    public static RepositoryController instance = null;
    private ConfigPropertiesFromUser conf = null;

    private String directoryPath = null;
    private String drawsPath = null;
    private String metaPath = null;

    private RepositoryController() {
        conf = ConfigPropertiesFromUser.getInstance();
        if(conf.getProperties().containsKey("path")){
            directoryPath = conf.getProperty("path");
            drawsPath = conf.getProperty("path") + File.separator + "draws";
            metaPath = conf.getProperty("path") + File.separator + "metadata";
        }
    }

    public static RepositoryController getInstance(){
        if(instance == null)
            instance = new RepositoryController();
        return instance;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public String getDrawsPath() {
        return drawsPath;
    }

    public String getMetaPath() {
        return metaPath;
    }

    public ConfigPropertiesFromUser getConf() {
        return conf;
    }


    public void setDirectory(final File directory) {
        try(FileOutputStream out = new FileOutputStream(ConfigPropertiesFromUser.CONFIG_PROPERTIES);){
            conf.getProperties().setProperty("path", directory.getAbsolutePath());
            conf.getProperties().store(out, null);

            directoryPath = directory.getAbsolutePath();
            drawsPath = directoryPath + File.separator + "draws";
            metaPath = directoryPath + File.separator + "metadata";

            new File(drawsPath).mkdir();
            new File(metaPath ).mkdir();

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
