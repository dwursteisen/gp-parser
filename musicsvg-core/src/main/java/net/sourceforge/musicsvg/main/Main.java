/*
 * Main.java
 *
 * Created on 18 mai 2007, 20:08
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import java.util.List;
import java.util.Vector;
import net.sourceforge.musicsvg.gui.MainController;
import net.sourceforge.musicsvg.module.ABC4jModule;
import net.sourceforge.musicsvg.module.DAOModule;
import net.sourceforge.musicsvg.module.GUIModule;
import net.sourceforge.musicsvg.module.MusicSVGModule;
import net.sourceforge.musicsvg.module.ParsersModule;
import net.sourceforge.musicsvg.module.SVGModule;
import net.sourceforge.musicsvg.module.UtilsModule;

/**
 *
 * @author Dav
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Module> modules = new Vector<Module>();
        modules.add(new MusicSVGModule());
        modules.add(new DAOModule());
        modules.add(new GUIModule());
        modules.add(new ParsersModule());
        modules.add(new UtilsModule());
        //modules.add(new SVGModule());
        modules.add(new ABC4jModule());
        
        Injector injector = Guice.createInjector(modules);
        
        MainController main = injector.getInstance(MainController.class);
        main.startApplication();
    }
    
}