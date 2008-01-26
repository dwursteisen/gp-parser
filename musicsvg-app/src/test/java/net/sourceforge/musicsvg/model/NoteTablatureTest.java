/*
 * NoteTablatureTest.java
 *
 * Created on 25 oct. 2007, 01:08:32
 *
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
public class NoteTablatureTest  {

    @Test
    public void testGetNoteHeight() {
        NoteTablature note = new NoteTablature();
        note.setFret(0);
        note.setString(NoteHeight.E);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.E);

        note.setFret(3);
        note.setString(NoteHeight.E);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.G);

        note.setFret(0);
        note.setString(NoteHeight.A);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.A);

        note.setFret(5);
        note.setString(NoteHeight.A);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.D);

        note.setFret(12);
        note.setString(NoteHeight.A);
        Assert.assertEquals(note.getNoteHeight(), NoteHeight.A);
    }
}