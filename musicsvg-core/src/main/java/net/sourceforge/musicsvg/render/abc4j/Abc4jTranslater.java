/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.render.abc4j;

import abc.notation.AccidentalType;
import abc.notation.Note;
import net.sourceforge.musicsvg.model.NoteHeight;
import net.sourceforge.musicsvg.render.NoteTranslater;

/**
 *
 * @author Dav
 */
public class Abc4jTranslater implements NoteTranslater<Note> {

    @Override
    public Note translater(net.sourceforge.musicsvg.model.Note note) {
        byte height = 0;
        NoteHeight noteHeigth = note.getNoteHeight();
        switch (noteHeigth) {
            case A:
                height = abc.notation.Note.A;
                break;
            case B:
                height = abc.notation.Note.B;
                break;
            case C:
                height = abc.notation.Note.C;
                break;
            case D:
                height = abc.notation.Note.D;
                break;
            case E:
                height = abc.notation.Note.E;
                break;
            case F:
                height = abc.notation.Note.F;
                break;
            case G:
                height = abc.notation.Note.G;
                break;
            }

        Note abcNote = new Note(height);
        byte accident = AccidentalType.NONE;
        switch (note.getAccident()) {
            case Flat:
                accident = AccidentalType.FLAT;
                break;
            case Sharp:
                accident = AccidentalType.SHARP;
                break;
            case None:
                accident = AccidentalType.NONE;
                break;
        }
        abcNote.setAccidental(accident);
        return abcNote;
    }
}
