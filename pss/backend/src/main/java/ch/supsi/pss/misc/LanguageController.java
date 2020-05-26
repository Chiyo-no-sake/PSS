package ch.supsi.pss.misc;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {
    private static LanguageController istance;

    private final Locale locale;
    private final ResourceBundle resourceBundle;

    private LanguageController(){
        locale = new Locale(PreferencesRepository.getLanguage());
        resourceBundle = ResourceBundle.getBundle("myProps", locale);
    }

    public static LanguageController getInstance() {
        if(istance == null)
            istance = new LanguageController();

        return istance;
    }

    public String getString(final String element){
        return resourceBundle.getString(element);
    }


    public Locale getLocale() {
        return locale;
    }
}
