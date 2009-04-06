/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Dav
 */
public class LibrarieMenu extends JMenuBar {
    JMenu file;
    JMenuItem addDirectory;
    public LibrarieMenu() {

        addDirectory = new JMenuItem("Ajout d'un repertoire...");

        file = new JMenu("Fichier");
        file.add(addDirectory);

        add(file);

    }


    public void setAddDirectoryListener(ActionListener listener) {
        addDirectory.addActionListener(listener);
    }


}
