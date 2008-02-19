/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.utils.impl;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import net.sourceforge.musicsvg.utils.I18n;

/**
 *
 * @author Dav
 */
public class I18nImpl implements I18n {

    private ResourceBundle myResources;
    private Locale currentLocale = Locale.getDefault();
    private String resourceName = null;
    
    @Override
    public String getString(String key) {
        if (myResources.containsKey(key)) {
            return myResources.getString(key);
        }
        return "???"+key+"???";
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
        this.resourceName = resourceName;
        myResources = ResourceBundle.getBundle(resourceName, currentLocale);
    }
}
