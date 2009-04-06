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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.sourceforge.musicsvg.io.*;
import net.sourceforge.musicsvg.model.NoteAccident;
import net.sourceforge.musicsvg.model.NoteDuration;
import net.sourceforge.musicsvg.model.NoteHeight;
import net.sourceforge.musicsvg.model.NoteTablature;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import net.sourceforge.musicsvg.model.factory.NoteTablatureFactory;
import net.sourceforge.musicsvg.model.factory.SongFactory;
import net.sourceforge.musicsvg.model.factory.TrackFactory;

/**
 *
 * @author Dav
 */
public class GP4ParserListenerImpl implements GP4ParserListener {

    private SongDAO songDAO;
    private TrackFactory trackFactory;
    private SongFactory songFactory;
    private NoteDAO noteDAO;
    private NoteTablatureFactory noteFactory;
    private net.sourceforge.musicsvg.model.Song currentModelSong;
    private final static Map<Integer, NoteHeight> MATRIX_TUNNING = new HashMap<Integer, NoteHeight>();
    private final static Map<Integer, NoteAccident> MATRIX_ACCIDENT = new HashMap<Integer, NoteAccident>();
    private final static Map<Integer, NoteDuration> MATRIX_DURATION = new HashMap<Integer, NoteDuration>();
    

    static {
        MATRIX_TUNNING.put(0, NoteHeight.C);
        MATRIX_TUNNING.put(1, NoteHeight.C);
        MATRIX_TUNNING.put(2, NoteHeight.D);
        MATRIX_TUNNING.put(3, NoteHeight.D);
        MATRIX_TUNNING.put(4, NoteHeight.E);
        MATRIX_TUNNING.put(5, NoteHeight.F);
        MATRIX_TUNNING.put(6, NoteHeight.F);
        MATRIX_TUNNING.put(7, NoteHeight.G);
        MATRIX_TUNNING.put(8, NoteHeight.G);
        MATRIX_TUNNING.put(9, NoteHeight.A);
        MATRIX_TUNNING.put(10, NoteHeight.B);
        MATRIX_TUNNING.put(11, NoteHeight.B);

        MATRIX_ACCIDENT.put(0, NoteAccident.None);
        MATRIX_ACCIDENT.put(1, NoteAccident.Sharp);
        MATRIX_ACCIDENT.put(2, NoteAccident.None);
        MATRIX_ACCIDENT.put(3, NoteAccident.Sharp);
        MATRIX_ACCIDENT.put(4, NoteAccident.None);
        MATRIX_ACCIDENT.put(5, NoteAccident.None);
        MATRIX_ACCIDENT.put(6, NoteAccident.Sharp);
        MATRIX_ACCIDENT.put(7, NoteAccident.None);
        MATRIX_ACCIDENT.put(8, NoteAccident.Sharp);
        MATRIX_ACCIDENT.put(9, NoteAccident.None);
        MATRIX_ACCIDENT.put(10, NoteAccident.None);
        MATRIX_ACCIDENT.put(11, NoteAccident.Sharp);

        MATRIX_DURATION.put(-2, NoteDuration.wholeNote);
        MATRIX_DURATION.put(-1, NoteDuration.halfNote);
        MATRIX_DURATION.put(0, NoteDuration.quarterNote);
        MATRIX_DURATION.put(1, NoteDuration.eighthNote);
        MATRIX_DURATION.put(2, NoteDuration.sixteenthNote);
        MATRIX_DURATION.put(3, NoteDuration.thirtySecondNote);
        MATRIX_DURATION.put(4, NoteDuration.sixtyFourthNote);
    }

    @Inject
    public void injectNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Inject
    public void injectNoteFactory(NoteTablatureFactory noteFactory) {
        this.noteFactory = noteFactory;
    }

    @Inject
    public void injectTrackFractory(TrackFactory trackFactory) {
        this.trackFactory = trackFactory;
    }

    @Inject
    public void injectSongFactory(SongFactory songFactory) {
        this.songFactory = songFactory;
    }

    @Inject
    public void injectSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public void open() {
        
    }
    
    @Override
    public void readVersion(String version) {
    }

    @Override
    public void readTitle(String title) {
        currentModelSong = songFactory.createSong();
        songDAO.saveOrUpdate(currentModelSong);
        currentModelSong.setTitle(title);
    }

    @Override
    public void readSubTitle(String subtitle) {
        currentModelSong.setSubTitle(subtitle);
    }

    @Override
    public void readArtist(String interpret) {
        currentModelSong.setArtist(interpret);
    }

    @Override
    public void readAlbum(String album) {
        currentModelSong.setAlbum(album);
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

        NoteHeight tunning = null;
        NoteAccident accident = null;
        if (noteIndex != -1) {
            tunning = MATRIX_TUNNING.get(noteIndex);
            accident = MATRIX_ACCIDENT.get(noteIndex);
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
        currentSong.get(track).get(mesure).get(beat).setDuration(duration);
    }

    @Override
    public void readEmptyBeat(int track, int mesure, int beat, boolean emptyBeat, boolean restBeat) {
    }

    @Override
    public void readTuplet(int track, int mesure, int beat, int tuplet) {
    }

    @Override
    public void readNumberOfBeats(int track, int mesure, int numberOfBeats) {
        currentSong.get(track).get(mesure).createBets(numberOfBeats);
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
        if (stringHeight == null) { // dirty fix for a NPE. TODO: fix it corretly

            return;
        }

        // TODO: use a matrix instead of this ?
        // TODO: move it into a method ?
        int newOrdinal = stringHeight.getHeight() + fretNumber + stringAccident.getAccident();
        int nbNoteValue = NoteHeight.SCALE.length;
        int ord = newOrdinal % nbNoteValue;
        NoteHeight height = NoteHeight.SCALE[ord];

        int beatDuration = t.get(mesure).get(beat).getDuration();
        NoteDuration d = MATRIX_DURATION.get(beatDuration);

        // TODO: move this into the noteFactory ?
        note.setFret(fretNumber);
        note.setNoteHeight(height);
        note.setString(t.getTunning(stringPlayer));
        // note.setAccident(t.getAccident(stringPlayer));
        // FIXME: the note accident depends of the key signature
        // the string accident and the note player
        // so for testing, it's set to none...
        note.setAccident(NoteAccident.None);
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

        void createBets(int numberOfBeats) {
            beats = new Vector<Beat>(numberOfBeats);
            for (int i = 0; i < numberOfBeats; i++) {
                beats.add(new Beat());
            }
        }
    }

    private class Beat {

        int duration;

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }

    @Override
    public void close() {
        currentSong.tracks.clear();
    }
}
