/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sourceforge.musicsvg.gui.listener.PerformAddDirectoryListener;
import net.sourceforge.musicsvg.model.Song;

/**
 *
 * @author Dav
 */
public class LibrarieStatusBar extends JPanel implements PerformAddDirectoryListener{
    private JLabel status;

    public LibrarieStatusBar() {
        status = new JLabel("...");
        add(status);
    }


    public void publish(List<Song> songs) {
        status.setText("Examen des "+songs.size()+"fichiers");
    }

}
