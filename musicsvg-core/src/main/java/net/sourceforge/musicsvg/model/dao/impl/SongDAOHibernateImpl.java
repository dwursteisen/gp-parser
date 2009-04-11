/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dav
 */
public class SongDAOHibernateImpl extends PersistantDAOHibernateImpl<Song> implements SongDAO{

    public Song getLastSong() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Song findByDigest(byte[] digest) {
        Criteria criteria = getSession().createCriteria(Song.class);
        criteria.add(Restrictions.eq("digest", digest));
        return (Song) criteria.uniqueResult();
    }

}
