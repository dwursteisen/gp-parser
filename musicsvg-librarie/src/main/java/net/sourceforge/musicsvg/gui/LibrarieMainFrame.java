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

    public JComponent getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JComponent mainPanel) {
        this.mainPanel = mainPanel;
        this.add(mainPanel);
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
        setJMenuBar(menu);
    }

    
}
