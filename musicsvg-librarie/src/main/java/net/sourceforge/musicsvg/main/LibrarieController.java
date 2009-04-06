/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.main;

import java.util.List;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;

/**
 *
 * @author Dav
 */
public class LibrarieController {

    SongDAO songDao;

    public void setSongDao(SongDAO songDao) {
        this.songDao = songDao;
    }

    public void beginAddDirectory() {
        
    }

    public void endAddDirectory() {
        List<Song> songs = songDao.findAll();
        for(Song s : songs) {
            System.err.println(s.getTitle());
            System.err.println(s.getArtist());
        }
    }

}
