/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.model.dao.impl;

import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.TablatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */

@ContextConfiguration(locations = {"/dao-context.xml", "/test-context.xml"})
@TransactionConfiguration(defaultRollback = true)
public class SongDAOHibernateImplTest extends AbstractTransactionalTestNGSpringContextTests{

    SongDAOHibernateImpl songDao;

    @Autowired
    public void setSongDao(SongDAOHibernateImpl songDao) {
        this.songDao = songDao;
    }
    @Test
    public void testFindByDigest() {
        Song s = new Song();
        s.setTablatureType(TablatureType.POWERTAB);
        final String digest = "blablablabla";
        s.setDigest(digest.getBytes());
        songDao.saveOrUpdate(s);
        Song song = songDao.findByDigest(digest.getBytes());
        Assert.assertEquals(song.getDigest(), s.getDigest());
        Assert.assertEquals(song.getTablatureType(), TablatureType.POWERTAB);
    }
}
