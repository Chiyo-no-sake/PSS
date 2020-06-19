package ch.supsi.pss.interfaces;

import java.util.Properties;

public interface HasProperties {
    public boolean setProperties();
    public String getProperty(String key);
    public Properties getProperties();
}
