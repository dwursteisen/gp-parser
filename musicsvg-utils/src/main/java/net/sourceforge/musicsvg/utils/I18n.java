/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.utils;

import java.util.Locale;
import java.util.MissingResourceException;

/**
 *
 * @author Dav
 */
public interface I18n {

    void loadBundle(String resourceName) throws MissingResourceException;

    String getString(String key);

    Object getObject(String key);

    Locale getCurrentLocale();

    void setCurrentLocale(Locale currentLocale);
}
