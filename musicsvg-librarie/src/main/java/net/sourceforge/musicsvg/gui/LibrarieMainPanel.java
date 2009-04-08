/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Dav
 */
public class LibrarieMainPanel extends JPanel {

    JComponent leftPanel;
    JComponent rightPanel;

    public void setLeftPanel(JComponent leftPanel) {
        this.leftPanel = leftPanel;
        add(leftPanel);
    }

    public void setRightPanel(JComponent rightPanel) {
        this.rightPanel = rightPanel;
        add(rightPanel);
    }

    
    
}
