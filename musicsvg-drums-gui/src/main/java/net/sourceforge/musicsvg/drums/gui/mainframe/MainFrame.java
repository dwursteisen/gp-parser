/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.drums.gui.mainframe;

import javax.swing.JFrame;
import net.sourceforge.musicsvg.drums.gui.DrumPadComponent;

/**
 *
 * @author Dav
 */
public class MainFrame extends JFrame {
    DrumPadComponent pad = new DrumPadComponent();

    public MainFrame() {
        add(pad);
    }
    
}
