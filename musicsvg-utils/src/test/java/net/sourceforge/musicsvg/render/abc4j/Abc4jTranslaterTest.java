/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.render.abc4j;

import abc.notation.AccidentalType;
import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.NoteAccident;
import net.sourceforge.musicsvg.model.NoteHeight;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class Abc4jTranslaterTest {

    /**
     * Test of translater method, of class Abc4jTranslater.
     */
    @Test
    public void testTranslater() {
        System.out.println("translater");
        
        Abc4jTranslater instance = new Abc4jTranslater();
        
        Note note;
        abc.notation.Note expResult;
        abc.notation.Note result;
        
        note = new Note();
        note.setNoteHeight(NoteHeight.D);
        expResult = new abc.notation.Note(abc.notation.Note.D);
        result = instance.translater(note);
        Assert.assertEquals(expResult.getHeight(), result.getHeight());
        Assert.assertEquals(expResult.getAccidental(), result.getAccidental());
        
        note.setNoteHeight(NoteHeight.D);
        note.setAccident(NoteAccident.Sharp);
        
        expResult = new abc.notation.Note(abc.notation.Note.D);
        expResult.setAccidental(AccidentalType.SHARP);
        result = instance.translater(note);
        Assert.assertEquals(expResult.getHeight(), result.getHeight());
        Assert.assertEquals(expResult.getAccidental(), result.getAccidental());

    }
}
