/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui.listener;

import java.util.List;
import net.sourceforge.musicsvg.model.Song;

/**
 *
 * @author Dav
 */
public interface PerformAddDirectoryListener {

    void publish(List<Song> songs);
}
