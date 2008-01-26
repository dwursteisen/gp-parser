/*
 * MidiController.java
 *
 * Created on 4 ao�t 2007, 19:51
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.midi.impl;

import net.sourceforge.musicsvg.midi.MidiController;
import net.sourceforge.musicsvg.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;
import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.dao.NoteDAO;

/**
 *
 * @author Dav
 */
public class MidiControllerImpl implements MidiController {
    
    NoteDAO noteDAO;
    Sequence sequence = null;
    Track currentTrack = null;
    MidiMessageFactory factory = new MidiMessageFactory();
    /**
     * Creates a new instance of MidiController
     */
    public MidiControllerImpl() {
        try {
            sequence = new Sequence(Sequence.SMPTE_25, 10);
            
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
        currentTrack = sequence.createTrack();
        
    }
    /**
     * Transform a Note to a MidiEvent
     */
    private int t = 15;
    public void addNote(final Note n) {
        MidiEvent event;
        try {
            t = factory.addNote(currentTrack, n, t);
            
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void play() {
        try {
            
            Sequencer sequencer = MidiSystem.getSequencer(false);
            Synthesizer synth = MidiSystem.getSynthesizer();
            
            Transmitter trans = sequencer.getTransmitter();
            Receiver r = synth.getReceiver();
            trans.setReceiver(r);
            
            synth.open();
            sequencer.open();
            sequencer.setSequence(sequence);
            
            sequencer.start();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void saveToFile(String filename) {
        try {
            MidiSystem.write(sequence, 1, new File(filename));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public NoteDAO getNoteDAO() {
        return noteDAO;
    }

    public void setNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    public void createSequence() {
        List<Note> notes = noteDAO.findAll();
    }
    
}
