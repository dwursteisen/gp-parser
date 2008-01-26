/*
 * MidiMessageFactoryTest.java
 * JUnit based test
 *
 * Created on 7 ao�t 2007, 22:05
 */

package net.sourceforge.musicsvg.midi;

import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.NoteHeight;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class MidiMessageFactoryTest  {
    
    /**
     * Test of getMidiCode method, of class midi.MidiMessageFactory.
     */
    @Test
    public void testGetMidiCode() {
        System.out.println("getMidiCode");
        MidiMessageFactory instance = new MidiMessageFactory();
        
        Note n = new Note();
        int result;
        int expResult;
        
        /*
        n = NoteHeight.GDiese;
        expResult = 68;
        result = instance.getMidiCode(n);
        Assert.assertEquals(expResult, result);
        */
        n.setNoteHeight(NoteHeight.A);
        expResult = 69;
        result = instance.getMidiCode(n);
        Assert.assertEquals(expResult, result);
        /*
        n = Note.NoteHeight.Bb;
        expResult = 70;
        result = instance.getMidiCode(n);
        assertEquals(expResult, result);
        */
        
        n.setNoteHeight(NoteHeight.B);
        expResult = 71;
        result = instance.getMidiCode(n);
        Assert.assertEquals(expResult, result);
        
        n.setNoteHeight(NoteHeight.C);
        expResult = 60;
        result = instance.getMidiCode(n);
        Assert.assertEquals(expResult, result);
        
        
    }
    
}
