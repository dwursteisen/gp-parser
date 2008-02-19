/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.utils.impl;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class I18nImplTest {
    
    @Test
    public void testGetCurrentLocale() {
        I18nImpl i18n = new I18nImpl();
        
        Locale currentLocale = i18n.getCurrentLocale();
        Assert.assertNotNull(currentLocale);
        
        Locale newLocale = new Locale("fr");
        Assert.assertFalse(newLocale.equals(currentLocale));
        
        i18n.setCurrentLocale(newLocale);
        currentLocale = i18n.getCurrentLocale();
        Assert.assertSame(currentLocale, newLocale);
    }
    
    @Test(expectedExceptions=MissingResourceException.class)
    public void testLoadBundle_IOException() throws MissingResourceException {
        I18nImpl i18n = new I18nImpl();
        i18n.loadBundle("fakeResource");
    }
    
    @Test
    public void testLoadBundle() throws IOException {
        I18nImpl i18n = new I18nImpl();
        i18n.loadBundle("testI18n");
    }
    
    @Test
    public void testGetString() {
        I18nImpl i18n = new I18nImpl();
        Locale en = new Locale("en");
        i18n.setCurrentLocale(en);
        i18n.loadBundle("testI18n");
        
        String result;
        result = i18n.getString("fakeKey");
        Assert.assertEquals(result, "???fakeKey???");
        
        result = i18n.getString("message.test");
        Assert.assertEquals(result, "default language");
        
        Locale fr = new Locale("fr");
        i18n.setCurrentLocale(fr);
        result = i18n.getString("message.test");
        Assert.assertEquals(result, "message par défaut");
        
        
    }
}
