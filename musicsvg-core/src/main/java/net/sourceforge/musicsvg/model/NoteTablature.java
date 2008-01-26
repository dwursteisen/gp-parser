/*
 * NoteTablature.java
 *
 * Created on 27 ao�t 2007, 23:05
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */
package net.sourceforge.musicsvg.model;

/**
 *
 * @author Dav
 */
public class NoteTablature extends Note {

    NoteHeight string = null;
    int fret = 0;

    /** Creates a new instance of NoteTablature */
    public NoteTablature() {
    }

    public void setString(NoteHeight heigth) {
        this.string = heigth;
    }

    public NoteHeight getString() {
        return this.string;
    }

    public void setFret(int fret) {
        this.fret = fret;
    }

    public int getFret() {
        return this.fret;
    }

    @Override
    public NoteHeight getNoteHeight() {
        // return fret + string
        NoteHeight noteHeight = super.getNoteHeight();
        if (noteHeight != null) {
            return noteHeight;
        }
        int newOrdinal = this.string.getHeight() + fret + this.getAccident().getAccident();
        int nbNoteValue = NoteHeight.SCALE.length;
        int ord = newOrdinal % nbNoteValue;
        return NoteHeight.SCALE[ord];
    }
}
