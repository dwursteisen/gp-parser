/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.main;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.musicsvg.gui.listener.FilterListener;
import net.sourceforge.musicsvg.gui.listener.PerformAddDirectoryListener;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Dav
 */
public class LibrarieController implements FilterListener {

    private static final Logger LOG = Logger.getLogger(LibrarieController.class);
    
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

    // TODO: renomer la méthode
    public void endAddDirectory() {
        List<Song> songs = songDao.findAll();
        firePublishSong(songs);
    }

    public void filter(String filterText) {
    LOG.debug("Filtre les morceaux sur la chaine : "+filterText);
        List<Song> songs = songDao.findAll();
        List<Song> filtered = new ArrayList<Song>();

        for(Song s: songs) {
            final boolean gotTitle = StringUtils.containsIgnoreCase(s.getTitle(), filterText);
            final boolean gotAuthor = StringUtils.containsIgnoreCase(s.getArtist(), filterText);
            final boolean gotFilename = StringUtils.containsIgnoreCase(s.getFile().getName(), filterText);
            if(gotTitle ||gotAuthor || gotFilename) {
                filtered.add(s);
            }
        }
        firePublishSong(filtered);
    }

    private void firePublishSong(List<Song> songs) {
        if (addLibrarieListeners != null) {
            for (PerformAddDirectoryListener listener : addLibrarieListeners) {
                listener.publish(songs);
            }
        }
    }

}
