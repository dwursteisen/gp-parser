/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao;

import net.sourceforge.musicsvg.model.Song;

/**
 *
 * @author Dav
 */
public interface SongDAO extends PersistantDAO<Song>{
    Song getLastSong();
    Song findByDigest(byte[] digest);
}
