/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Dav
 */
public class LibrarieMainFrame extends JFrame {

    JMenuBar menu;
    JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.add(mainPanel);
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
        this.add(menu);
    }

    
}
