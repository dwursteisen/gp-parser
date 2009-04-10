/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.model.dao.impl;

import java.util.List;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;

/**
 *
 * @author Dav
 */
public class SongDAOMapImpl extends PersistantDAOMapImpl<Song> implements SongDAO {

    @Override
    public Song getLastSong() {
        List<Song> all = findAll();
        if (all.size() > 0) {
            return all.get(all.size()-1);
        }
        return null;
    }

    public Song findByDigest(byte[] digest) {
        return null;
    }
}
