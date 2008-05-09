/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.utils.impl;

import com.google.inject.Inject;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import net.sourceforge.musicsvg.utils.I18n;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;

/**
 *
 * @author Dav
 */
public class I18nImpl implements I18n {
    private MusicSVGLogger logger;

    private ResourceBundle myResources;
    private Locale currentLocale = Locale.getDefault();
    private String resourceName = null;
    
    @Inject
    public void injectMusicSVGLogger(MusicSVGLogger logger) {
        this.logger = logger;
    }
    
    @Override
    public String getString(String key) {
        String translation = "???"+key+"???";
        // JAVA 6 stuff
        //if (myResources.containsKey(key)) {
        //    return myResources.getString(key);
        // }
        try {
            translation = myResources.getString(key);
        } catch (MissingResourceException ex) {
            logger.warm(getClass(), "Error during getString of "+key, ex);
        }
        return translation;
    }

    @Override
    public Object getObject(String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    @Override
    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        if ( resourceName != null) {
            loadBundle(resourceName);
        }
    }

    @Override
    public void loadBundle(String resourceName) throws MissingResourceException {
        logger.info(getClass(), "loading resources : "+resourceName);
        this.resourceName = resourceName;
        myResources = ResourceBundle.getBundle(resourceName, currentLocale);
    }
}
