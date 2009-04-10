/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.model.dao.impl;

import java.util.List;
import net.sourceforge.musicsvg.model.Song;
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
public class PersistantDAOHibernateImplTest extends AbstractTransactionalTestNGSpringContextTests{

    SongDAOHibernateImpl songDao;

    @Autowired
    public void setSongDao(SongDAOHibernateImpl songDao) {
        this.songDao = songDao;
    }
    @Test
    public void testMethods() {
        Song s = new Song();
        songDao.saveOrUpdate(s);
        List<Song> list = songDao.findAll();
        Assert.assertEquals(1, list.size());
    }
}
