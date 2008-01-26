/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import javax.swing.JFileChooser;
import net.sourceforge.musicsvg.gui.FileChooser;
import net.sourceforge.musicsvg.gui.mainframe.MainFrame;

/**
 *
 * @author david
 */
public class GUIModule implements Module {

    @Override
    public void configure(Binder binder) {
        // GUI
        MainFrame frame = new MainFrame();

        FileChooser fileChooser = new FileChooser();
        JFileChooser jfileChooser = new JFileChooser();
        binder.bind(JFileChooser.class).toInstance(jfileChooser);
        binder.bind(FileChooser.class).toInstance(fileChooser);

        binder.bind(MainFrame.class).toInstance(frame);
    }

}
