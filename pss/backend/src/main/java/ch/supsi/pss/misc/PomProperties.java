package ch.supsi.pss.misc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PomProperties {

    static PomProperties instance = null;

    private PomProperties(){}

    public static PomProperties getInstance(){
        if(instance == null)
            instance = new PomProperties();
        return instance;
    }

    public Properties getData(){
        Properties props = new Properties();
        try(InputStream is = PomProperties.class.getClassLoader().getResourceAsStream("project.properties")){
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
