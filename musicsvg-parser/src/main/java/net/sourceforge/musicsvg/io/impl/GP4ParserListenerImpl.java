/*
 * GP4ParserListenerImpl.java
 *
 * Created on 11 ao�t 2007, 11:04
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */
package net.sourceforge.musicsvg.io.impl;

import com.google.inject.Inject;
import java.util.List;
import java.util.Vector;
import net.sourceforge.musicsvg.io.*;
import net.sourceforge.musicsvg.model.NoteAccident;
import net.sourceforge.musicsvg.model.NoteDuration;
import net.sourceforge.musicsvg.model.NoteHeight;
import net.sourceforge.musicsvg.model.NoteTablature;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.model.factory.NoteTablatureFactory;

/**
 *
 * @author Dav
 */
public class GP4ParserListenerImpl implements GP4ParserListener {

    /** Creates a new instance of GP4ParserListenerImpl */
    public GP4ParserListenerImpl() {
    }
    private NoteDAO noteDAO;
    private NoteTablatureFactory noteFactory;

    @Inject
    public void injectNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Inject
    public void injectNoteFactory(NoteTablatureFactory noteFactory) {
        this.noteFactory = noteFactory;
    }

    @Override
    public void readVersion(String version) {
    }

    @Override
    public void readTitle(String title) {
    }

    @Override
    public void readSubTitle(String subtitle) {
    }

    @Override
    public void readArtist(String interpret) {
    }

    @Override
    public void readAlbum(String album) {
    }

    @Override
    public void readSongAuthor(String songAuthor) {
    }

    @Override
    public void readCopyright(String copyright) {
    }

    @Override
    public void readPieceAuthor(String pieceAuthor) {
    }

    @Override
    public void readInstruction(String instructions) {
    }

    @Override
    public void readNumberOfNotices(int nbNotes) {
    }

    @Override
    public void readNotice(String note) {
    }

    @Override
    public void readIsTripleFeel(boolean isTripleFeel) {
    }

    @Override
    public void readTempoValue(int tempo) {
    }

    @Override
    public void readKey(int key) {
    }

    @Override
    public void readOctave(int octave) {
    }

    @Override
    public void readNumberOfTracks(int numberOfTracks) {
        currentSong.createTracks(numberOfTracks);

    }

    @Override
    public void readNumberOfMesures(int numberOfMesures) {
        currentSong.createMesures(numberOfMesures);
    }

    @Override
    public void readMesureMarker(int track, String name, int r, int g, int b) {
    }

    @Override
    public void readMeasureHeader(int track, int numerator, int denominator, boolean repeatStart, boolean doubleBar, int numberOfAlternateEnding, int numberOfRepetitions) {
    }

    @Override
    public void readTrackMidiParameter(int track, int port, int channelIndex, int effects, int numberOfFrets, int capo, int r, int g, int b) {
    }

    @Override
    public void readStringTunning(int track, int string, int stringTunning) {
        // @see http://www.harmony-central.com/MIDI/Doc/table2.html
        int noteIndex = stringTunning % 12;
        final NoteHeight[] tunnings = {
            NoteHeight.C,
            NoteHeight.C,
            NoteHeight.D,
            NoteHeight.D,
            NoteHeight.E,
            NoteHeight.F,
            NoteHeight.F,
            NoteHeight.G,
            NoteHeight.G,
            NoteHeight.A,
            NoteHeight.B,
            NoteHeight.B
        };

        final NoteAccident[] accidents = {
            NoteAccident.None,
            NoteAccident.Sharp,
            NoteAccident.None,
            NoteAccident.Sharp,
            NoteAccident.None,
            NoteAccident.None,
            NoteAccident.Sharp,
            NoteAccident.None,
            NoteAccident.Sharp,
            NoteAccident.None,
            NoteAccident.None,
            NoteAccident.Sharp
        };

        NoteHeight tunning = null;
        NoteAccident accident = null;
        if (noteIndex != -1) {
            tunning = tunnings[noteIndex];
            accident = accidents[noteIndex];
        }
        currentSong.get(track).setTunning(string, tunning);
        currentSong.get(track).setAccident(string, accident);

    }

    @Override
    public void readTrackParameter(int track, String name, int numberOfStrings, boolean isDrumsTrack, boolean is12StringedGuitarTrack, boolean isBanjoTrack) {
        currentSong.get(track).setNumberOfString(numberOfStrings);
    }

    @Override
    public void readBeat(int track, int mesure, int beat, int duration, boolean dottedNotes) {
    }

    @Override
    public void readEmptyBeat(int track, int mesure, int beat, boolean emptyBeat, boolean restBeat) {
    }

    @Override
    public void readTuplet(int track, int mesure, int beat, int tuplet) {
    }

    @Override
    public void readNumberOfBeats(int track, int mesure, int numberOfBeats) {
    }

    @Override
    public void readStringPlayed(int track, int mesure, int beat, int stringsPlayed) {
    }

    @Override
    public void readNote(int track, int mesure, int beat, int stringPlayer, int fretNumber, int duration) {
        NoteTablature note = noteFactory.createNoteTablature();

        Track t = currentSong.get(track);
        NoteHeight stringHeight = t.getTunning(stringPlayer);
        NoteAccident stringAccident = t.getAccident(stringPlayer);
        if ( stringHeight == null) { // dirty fix for a NPE. TODO: fix it corretly
            return;
        }
        
        int newOrdinal = stringHeight.getHeight() + fretNumber + stringAccident.getAccident();
        int nbNoteValue = NoteHeight.SCALE.length;
        int ord = newOrdinal % nbNoteValue;
        NoteHeight height = NoteHeight.SCALE[ord];

        note.setFret(fretNumber);
        note.setNoteHeight(height);
        note.setString(t.getTunning(stringPlayer));
        note.setAccident(t.getAccident(stringPlayer));
        NoteDuration d = NoteDuration.values()[duration + 2];
        note.setNoteDuration(d);
        this.noteDAO.saveOrUpdate(note);
    }

    @Override
    public void readMidiChannel(int port, int channel, int instrument, byte volume, byte balance, byte chorus, byte reverb, byte phaser, byte tremolo) {

    }

    @Override
    public void readMeasureTonality(int tonality) {

    }

    @Override
    public void readNoteParameter(int track, int mesure, int beat, boolean accentuated, boolean ghostNote, boolean dotted) {

    }
    private Song currentSong = new Song();

    private class Song {

        private List<Track> tracks = new Vector<Track>();

        public void createTracks(int numberOfTracks) {
            for (int i = 0; i < numberOfTracks; i++) {
                tracks.add(new Track());
            }
        }

        public Track get(int trackIndex) {
            return tracks.get(trackIndex);
        }

        private void createMesures(int numberOfMesures) {
            for (Track t : tracks) {
                t.createMesures(numberOfMesures);
            }
        }
    }

    private class Track {

        private NoteAccident[] accidents;
        private List<Measure> measures = new Vector<Measure>();
        private NoteHeight[] tunnings;

        public Measure get(int measureIndex) {
            return measures.get(measureIndex);
        }

        public void setNumberOfString(int numberOfString) {
            // because GP Format is stupid (a GP file contains a least 7 strings with 1 hidden)
            // I need to add this 'hidden' string
            tunnings = new NoteHeight[numberOfString + 1];
            accidents = new NoteAccident[numberOfString + 1];
        }

        public NoteHeight getTunning(int stringIndex) {
            return tunnings[stringIndex];
        }

        public void setTunning(int stringIndex, NoteHeight tunning) {
            tunnings[stringIndex] = tunning;
        }

        public NoteAccident getAccident(int stringIndex) {
            return accidents[stringIndex];
        }

        public void setAccident(int stringIndex, NoteAccident accident) {
            accidents[stringIndex] = accident;
        }

        private void createMesures(int numberOfMesures) {
            for (int i = 0; i < numberOfMesures; i++) {
                measures.add(new Measure());
            }
        }
    }

    private class Measure {

        private List<Beat> beats;

        public Beat get(int beatIndex) {
            return beats.get(beatIndex);
        }
    }

    private class Beat {
    }

    @Override
    public void close() {
        currentSong.tracks.clear();
    }
}
