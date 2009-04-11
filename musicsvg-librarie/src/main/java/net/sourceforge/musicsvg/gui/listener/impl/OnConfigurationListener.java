/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui.listener.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Dav
 */
public class OnConfigurationListener implements ActionListener{

    JFrame frame;

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }


    public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
    }

}
