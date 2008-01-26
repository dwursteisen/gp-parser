/*
 * MidiMessageFactory.java
 *
 * Created on 7 ao�t 2007, 21:20
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.NoteDuration;

/**
 *
 * @author Dav
 */
public class MidiMessageFactory {
    
    
    /** Creates a new instance of MidiMessageFactory */
    public MidiMessageFactory() {
    }
    
    public int addNote(final Track track, final Note n, final int t) throws InvalidMidiDataException {
        
        int midiNote = getMidiCode(n);
        int midiVelocity = 75;
        int duration = getMidiDuration(n.getNoteDuration());
        ShortMessage msg = new ShortMessage();
        msg.setMessage(ShortMessage.NOTE_ON, midiNote, midiVelocity);
        MidiEvent event = new MidiEvent(msg, t);
        track.add(event);
        
        msg = new ShortMessage();
        msg.setMessage(ShortMessage.NOTE_OFF, midiNote, midiVelocity);
        event = new MidiEvent(msg, t + duration);
        track.add(event);
        
        return t + duration;
    }
    
    public int getMidiCode(Note n) {
        int[] translation = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71 };
        return translation[n.getNoteHeight().getHeight() + n.getAccident().getAccident()];
        
    }
    
    public int getMidiDuration(NoteDuration d) {
        int [] translation = { 600,500,400,300,200,100 };
        return translation[d.ordinal()];
    }
}
