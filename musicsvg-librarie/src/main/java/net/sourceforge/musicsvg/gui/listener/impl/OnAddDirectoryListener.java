/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.gui.listener.impl;

import com.l2fprod.common.swing.JDirectoryChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import net.sourceforge.musicsvg.io.Parser;
import net.sourceforge.musicsvg.main.LibrarieController;
import org.apache.log4j.Logger;

/**
 *
 * @author Dav
 */
public class OnAddDirectoryListener implements ActionListener {
    private static final Logger LOG = Logger.getLogger(OnAddDirectoryListener.class);
    
    private Parser parser;
    private LibrarieController controller;

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setController(LibrarieController controller) {
        this.controller = controller;
    }

    

    public void actionPerformed(ActionEvent e) {
        LOG.debug("addDirectoryListener --> Action Performed ! ");
        controller.beginAddDirectory();
        JDirectoryChooser chooser = new JDirectoryChooser();

        chooser.showOpenDialog((Component) e.getSource());
        File files = chooser.getSelectedFile();
        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return name.endsWith("gp4");
            }
        };
        for (File file : files.listFiles(filter)) {
            LOG.debug("parsing du fichier "+file.getName());
            try {
                parser.openFile(file);
            } catch (IOException ex) {
                LOG.error("Erreur lors de la lecture du fichier "+file.getName(), ex);
            } finally {
                parser.close();
            }
        }
        controller.endAddDirectory();
    }
}
