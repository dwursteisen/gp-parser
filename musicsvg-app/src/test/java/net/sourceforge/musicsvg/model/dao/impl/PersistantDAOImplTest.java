/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;


import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.easymock.EasyMock;
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
    
}
