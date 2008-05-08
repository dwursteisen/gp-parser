/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.render.abc4j;

import abc.notation.AccidentalType;
import abc.notation.Note;
import java.util.HashMap;
import java.util.Map;
import net.sourceforge.musicsvg.model.NoteAccident;
import net.sourceforge.musicsvg.model.NoteDuration;
import net.sourceforge.musicsvg.model.NoteHeight;
import net.sourceforge.musicsvg.render.NoteTranslater;

/**
 *
 * @author Dav
 */
public class Abc4jTranslater implements NoteTranslater<Note> {

    private final static Map<NoteDuration, Short> matrixDuration = new HashMap<NoteDuration, Short>();
    private final static Map<NoteHeight, Byte> matrixHeight = new HashMap<NoteHeight, Byte>();
    private final static Map<NoteAccident, Byte> matrixAccident = new HashMap<NoteAccident, Byte>();
    
    static {
        matrixDuration.put(NoteDuration.wholeNote, abc.notation.Note.WHOLE);
        matrixDuration.put(NoteDuration.halfNote, abc.notation.Note.HALF);
        matrixDuration.put(NoteDuration.eighthNote, abc.notation.Note.EIGHTH);
        matrixDuration.put(NoteDuration.thirtySecondNote, abc.notation.Note.THIRTY_SECOND);
        matrixDuration.put(NoteDuration.sixtyFourthNote, abc.notation.Note.SIXTY_FOURTH);
        matrixDuration.put(NoteDuration.sixteenthNote, abc.notation.Note.SIXTEENTH);
        matrixDuration.put(NoteDuration.quarterNote, abc.notation.Note.QUARTER);
        
        matrixHeight.put(NoteHeight.A, abc.notation.Note.A);
        matrixHeight.put(NoteHeight.B, abc.notation.Note.B);
        matrixHeight.put(NoteHeight.C, abc.notation.Note.C);
        matrixHeight.put(NoteHeight.D, abc.notation.Note.D);
        matrixHeight.put(NoteHeight.E, abc.notation.Note.E);
        matrixHeight.put(NoteHeight.F, abc.notation.Note.F);
        matrixHeight.put(NoteHeight.G, abc.notation.Note.G);
        
        matrixAccident.put(NoteAccident.Flat, AccidentalType.FLAT);
        matrixAccident.put(NoteAccident.Sharp, AccidentalType.SHARP);
        matrixAccident.put(NoteAccident.None, AccidentalType.NONE);
        
    }

    
    @Override
    public Note translater(net.sourceforge.musicsvg.model.Note note) {
        byte height = matrixHeight.get(note.getNoteHeight());
        byte accident = matrixAccident.get(note.getAccident());
        short duration = matrixDuration.get(note.getNoteDuration());
        
        Note abcNote = new Note(height);
        abcNote.setAccidental(accident);
        abcNote.setDuration(duration);
        return abcNote;
    }
}
