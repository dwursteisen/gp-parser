/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sourceforge.musicsvg.gui.listener.FilterListener;
import net.sourceforge.musicsvg.gui.listener.LaunchSongListener;
import net.sourceforge.musicsvg.gui.listener.PerformAddDirectoryListener;
import net.sourceforge.musicsvg.gui.listener.SaveConfigurationListener;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.UserConfiguration;

/**
 *
 * @author Dav
 */
public class LibrarieStatusBar extends JPanel implements FilterListener, PerformAddDirectoryListener, SaveConfigurationListener, LaunchSongListener {
    private JLabel status;

    public LibrarieStatusBar() {
        status = new JLabel("...");
        add(status);
    }


    public void publish(List<Song> songs) {
        status.setText("Examen des "+songs.size()+"fichiers");
    }

    public void save(UserConfiguration configuration) {
        status.setText("Enregistrement de la configuration utilisateur");
    }

    public void launch(Song song) {
        status.setText("Lanchement de "+song.getFile().getName());
    }

    public void filter(String filterText) {
        status.setText("Filtre sur la chaine "+filterText);
    }

}
