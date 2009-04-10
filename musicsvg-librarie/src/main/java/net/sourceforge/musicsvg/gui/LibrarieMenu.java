/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.gui;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.apache.log4j.Logger;

/**
 *
 * @author Dav
 */
public class LibrarieMenu extends JMenuBar {

    private static final Logger LOG = Logger.getLogger(LibrarieMenu.class);
    JMenu file;
    JMenuItem addDirectory;

    public LibrarieMenu() {
        super();
        addDirectory = new JMenuItem("Ajout d'un repertoire...");

        file = new JMenu("Fichier");
        file.add(addDirectory);

        add(file);

    }

    public void setAddDirectoryListeners(List<ActionListener> listeners) {
        for (ActionListener listener : listeners) {
            addDirectory.addActionListener(listener);
        }
    }
}
