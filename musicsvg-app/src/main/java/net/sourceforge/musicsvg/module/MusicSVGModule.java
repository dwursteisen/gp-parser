/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import net.sourceforge.musicsvg.gui.Dispatcher;
import net.sourceforge.musicsvg.gui.MainController;

/**
 *
 * @author Dav
 */
public class MusicSVGModule implements Module {

    @Override
    public void configure(Binder binder) {
        
        // Main Controller
        MainController main = new MainController();

        binder.bind(MainController.class).toInstance(main);

        // Dispatcher
        Dispatcher dispatcher = new Dispatcher();
        binder.bind(Dispatcher.class).toInstance(dispatcher);
        // binder.bind(ActionListener.class).to(Dispatcher.class);

    }
}
