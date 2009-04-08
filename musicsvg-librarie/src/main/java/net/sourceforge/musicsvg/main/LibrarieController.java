/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.main;

import java.util.List;
import net.sourceforge.musicsvg.gui.listener.PerformAddDirectoryListener;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;

/**
 *
 * @author Dav
 */
public class LibrarieController {

    SongDAO songDao;
    List<PerformAddDirectoryListener> addLibrarieListeners;

    public void setAddLibrarieListeners(List<PerformAddDirectoryListener> addLibrarieListeners) {
        this.addLibrarieListeners = addLibrarieListeners;
    }

    
    public void setSongDao(SongDAO songDao) {
        this.songDao = songDao;
    }

    public void beginAddDirectory() {
        
    }

    public void endAddDirectory() {
        List<Song> songs = songDao.findAll();
        if(addLibrarieListeners != null) {
            for(PerformAddDirectoryListener listener : addLibrarieListeners) {
                listener.publish(songs);
            }
        }
    }

}
