/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;

/**
 *
 * @author Dav
 */
public class SongDAOHibernateImpl  extends PersistantDAOHibernateImpl<Song> implements SongDAO{

    public Song getLastSong() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
