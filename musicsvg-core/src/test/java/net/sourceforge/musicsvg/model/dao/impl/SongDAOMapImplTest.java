/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class SongDAOMapImplTest {

    @Test
    public void testGetLastSong() {
        SongDAO dao = new SongDAOMapImpl();
        Song result;
        Song expResult;
        
        result = dao.getLastSong();
        Assert.assertNull(result);
        
        expResult = new Song();
        dao.saveOrUpdate(expResult);
        
        result = dao.getLastSong();
        Assert.assertSame(expResult, result);
        
        dao.saveOrUpdate(new Song());
        result = dao.getLastSong();
        Assert.assertNotNull(result);
        Assert.assertNotSame(result, expResult);
    }
}
