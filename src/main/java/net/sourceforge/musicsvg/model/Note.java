/*
 * Note.java
 *
 * Created on 26 mai 2007, 10:49
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
public class Note implements PersistantObject {

    private NoteAccident accident = NoteAccident.None;
    private Integer id;
    private NoteHeight noteHeight;
    private NoteDuration noteDuration;
    private NoteVelocity noteVelocity;
    private int timePosition = 0;
    private boolean silence = false;

    @Override
    public final Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public NoteHeight getNoteHeight() {
        return noteHeight;
    }

    public void setNoteHeight(NoteHeight h) {
        this.noteHeight = h;
    }

    public NoteDuration getNoteDuration() {
        return this.noteDuration;
    }

    public void setNoteDuration(NoteDuration duration) {
        this.noteDuration = duration;
    }

    public NoteVelocity getNoteVelocity() {
        return this.noteVelocity;
    }

    public void setNoteVelocity(NoteVelocity v) {
        this.noteVelocity = v;
    }

    public int getTimePosition() {
        return timePosition;
    }

    public void setTimePosition(int timePosition) {
        this.timePosition = timePosition;
    }

    public boolean isSilence() {
        return silence;
    }

    public void setSilence(boolean silence) {
        this.silence = silence;
    }

    public void setAccident(NoteAccident accident) {
        this.accident = accident;
    }

    public NoteAccident getAccident() {
        return accident;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Note other = (Note) obj;
        if (this.accident != other.accident) {
            return false;
        }
        if (this.noteHeight != other.noteHeight) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.accident != null ? this.accident.hashCode() : 0);
        hash = 29 * hash + (this.noteHeight != null ? this.noteHeight.hashCode() : 0);
        return hash;
    }
}
