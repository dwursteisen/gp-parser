/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class NoteTest {
    @Test
    public void testSetAccident() {
        
        Note note = new Note();
        testNote(note);
        
        note = new NoteTablature();
        testNote(note);
        
    }

    private void testNote(Note note) {

        note.setNoteHeight(NoteHeight.C);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.C);

        note.setAccident(NoteAccident.Flat);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.C);

        note.setAccident(NoteAccident.Sharp);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.C);

        note.setAccident(NoteAccident.Flat);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.C);

        note.setAccident(NoteAccident.Sharp);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.C);
    }
    
    @Test
    public void testEquals() {
        Note n1 = new Note();
        n1.setNoteHeight(NoteHeight.A);
        n1.setAccident(NoteAccident.Flat);
        
        Note n2 = new Note();
        n2.setNoteHeight(NoteHeight.A);
        n2.setAccident(NoteAccident.Flat);
        
        Assert.assertTrue(n1.equals(n2));
        Assert.assertTrue(n2.equals(n1));
        
        n1.setNoteHeight(NoteHeight.A);
        n1.setAccident(NoteAccident.Flat);
        n2.setNoteHeight(NoteHeight.G);
        n2.setAccident(NoteAccident.Sharp);
        
        /*
        Assert.assertTrue(n1.equals(n2));
        Assert.assertTrue(n2.equals(n1));
        */
    }
    
}
