/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.utils.impl;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class I18nImplTest {
    private I18nImpl i18n;
    
    MusicSVGLogger logger = new MusicSVGLoggerLog4jImpl();
    @BeforeMethod
    public void setUp() {
        i18n = new I18nImpl();
        i18n.injectMusicSVGLogger(logger);
    }
    
    @Test
    public void testGetCurrentLocale() {
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
        i18n.loadBundle("fakeResource");
    }
    
    @Test
    public void testLoadBundle() throws IOException {
        i18n.loadBundle("testI18n");
    }
    
    @Test
    public void testGetString() {
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
