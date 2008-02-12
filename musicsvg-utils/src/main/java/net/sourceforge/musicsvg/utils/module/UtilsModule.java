/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.utils.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import java.util.ResourceBundle;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import net.sourceforge.musicsvg.utils.impl.MusicSVGLoggerLog4jImpl;

/**
 *
 * @author david
 */
public class UtilsModule implements Module {

    @Override
    public void configure(Binder binder) {
        // Logger
        binder.bind(MusicSVGLogger.class).to(MusicSVGLoggerLog4jImpl.class).in(Scopes.SINGLETON);

        // Bundle
        ResourceBundle resource = ResourceBundle.getBundle("locales/Music");
        binder.bind(ResourceBundle.class).toInstance(resource);
    }

}
