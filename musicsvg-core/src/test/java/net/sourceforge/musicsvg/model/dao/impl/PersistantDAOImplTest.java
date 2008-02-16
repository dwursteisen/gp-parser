/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;


import net.sourceforge.musicsvg.model.Note;
import org.testng.Assert;
import org.testng.annotations.Test;




/**
 *
 * @author Dav
 */

public class PersistantDAOImplTest{
    
    @Test 
    public void testGetIdFrom() {
        
        PersistantDAOMapImpl<Note> dao = new PersistantDAOMapImpl<Note>();
        
        Note note = new Note();
        Integer expResult = 5;
        
        note.setId(expResult);
        
        dao.saveOrUpdate(note);
        Assert.assertEquals(note.getId(), expResult);
        
        note = new Note();
        dao.saveOrUpdate(note);
        Assert.assertEquals(note.getId().intValue(), 1);
        
        note = new Note();
        dao.saveOrUpdate(note);
        Assert.assertEquals(note.getId().intValue(), 2);
        
    }
    
    @Test
    public void testClear() {
        PersistantDAOMapImpl<Note> dao = new PersistantDAOMapImpl<Note>();
        Assert.assertEquals(0, dao.findAll().size());
        dao.saveOrUpdate(new Note());
        Assert.assertEquals(1, dao.findAll().size());
        dao.clear();
        Assert.assertEquals(0, dao.findAll().size());
        
    }
    
}
