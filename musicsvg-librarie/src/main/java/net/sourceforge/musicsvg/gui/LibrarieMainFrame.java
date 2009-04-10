/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Dav
 */
public class LibrarieMainFrame extends JFrame {

    JMenuBar menu;
    JComponent mainPanel;

    public LibrarieMainFrame(JMenuBar menu, JComponent panel) {
        this.menu = menu;
        mainPanel = panel;
        setJMenuBar(menu);
        add(mainPanel);
    }

    
}
