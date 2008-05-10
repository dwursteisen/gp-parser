/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.musicsvg.model.Note;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class NoteDAOMapImplTest {

    @Test
    public void testFindById() {
        Note expResult = new Note();
        final Integer id = new Integer(5);
        expResult.setId(id);
        
        Map<Integer, Note> map = new HashMap<Integer, Note>();
        map.put(expResult.getId(), expResult);
        
        NoteDAOMapImpl dao = new NoteDAOMapImpl();
        
        dao.setPersistantMap(map);
        
        Note result = dao.findById(id);
        Assert.assertEquals(result, expResult);
        
    }
    
    @Test
    public void testSaveOrUpdate() {
        NoteDAOMapImpl dao = new NoteDAOMapImpl();
        Map<Integer, Note> map = new HashMap<Integer, Note>();
        dao.setPersistantMap(map);
        
        Note expResult = new Note();
        final Integer id = new Integer(5);
        expResult.setId(id);
        
        dao.saveOrUpdate(expResult);
        
        Note result = dao.findById(id);
        Assert.assertEquals(result, expResult);
        
        Note newNote = new Note();
        dao.saveOrUpdate(newNote);
        Assert.assertEquals(newNote.getId().longValue(), 1L);
        
    }
    
    @Test
    public void testFindAll() {
        Map<Integer, Note> map = new HashMap<Integer, Note>();
        
        NoteDAOMapImpl dao = new NoteDAOMapImpl();
        
        Note note;
        note = new Note();
        note.setId(1);
        map.put(note.getId(), note);
        
        note = new Note();
        note.setId(2);
        map.put(note.getId(), note);
        
        dao.setPersistantMap(map);
        
        List<Note> result = dao.findAll();
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(1, result.get(0).getId().intValue());
        Assert.assertEquals(2, result.get(1).getId().intValue());
    }

}
