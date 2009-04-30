/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.io.gp.listeners;

import com.google.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;
import net.sourceforge.musicsvg.io.Hasher;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import net.sourceforge.musicsvg.model.factory.SongFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author Dav
 */
public class GP4InfoParserListenerImpl implements GP4ParserListener {

    private static final Logger LOG = Logger.getLogger(GP4InfoParserListenerImpl.class);
    private SongDAO songDAO;
    private SongFactory songFactory;
    private Song currentSong = null;
    private Hasher hash;

    @Inject
    public void setSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    @Inject
    public void setSongFactory(SongFactory factory) {
        songFactory = factory;
    }

    public void setHash(Hasher hash) {
        this.hash = hash;
    }

    public void open(File file) {
        currentSong = songFactory.createSong();
        currentSong.setFile(file);
        try {
            currentSong.setDigest(null);
            if(file != null) {
                currentSong.setDigest(hash.hash(file));
            }
        } catch (FileNotFoundException ex) {
            // TODO: manager correctement l'exception
           LOG.error("Erreur lors du hash du fichier", ex);
           currentSong.setDigest(null);
        } catch (IOException ex) {
           LOG.error("Erreur lors du hash du fichier", ex);
           currentSong.setDigest(null);
        }
    }

    public void close() {
        if(songDAO.findByDigest(currentSong.getDigest()) == null) {
            songDAO.saveOrUpdate(currentSong);
        }
    }

    public void readVersion(String version) {
    }

    public void readTitle(String title) {
        currentSong.setTitle(title);
    }

    public void readSubTitle(String subtitle) {
    }

    public void readArtist(String artist) {
        currentSong.setArtist(artist);
    }

    public void readAlbum(String album) {
        currentSong.setAlbum(album);
    }

    public void readSongAuthor(String songAuthor) {
        currentSong.setArtist(songAuthor);
    }

    public void readCopyright(String copyright) {
    }

    public void readPieceAuthor(String pieceAuthor) {
    }

    public void readInstruction(String instructions) {
    }

    public void readNumberOfNotices(int nbNotes) {
    }

    public void readNotice(String note) {
    }

    public void readIsTripleFeel(boolean isTripleFeel) {
    }

    public void readTempoValue(int tempo) {
    }

    public void readKey(int key) {
    }

    public void readOctave(int octave) {
    }

    public void readMidiChannel(int port, int channel, int instrument, byte volume, byte balance, byte chorus, byte reverb, byte phaser, byte tremolo) {
    }

    public void readNumberOfTracks(int numberOfTracks) {
    }

    public void readNumberOfMesures(int numberOfMesures) {
    }

    public void readMesureMarker(int number, String name, int r, int g, int b) {
    }

    public void readMeasureTonality(int tonality) {
    }

    public void readMeasureHeader(int number, int numerator, int denominator, boolean repeatStart, boolean doubleBar, int numberOfAlternateEnding, int numberOfRepetitions) {
    }

    public void readTrackMidiParameter(int trackIndex, int port, int channelIndex, int effects, int numberOfFrets, int capo, int r, int g, int b) {
    }

    public void readTrackParameter(int trackIndex, String name, int numberOfStrings, boolean isDrumsTrack, boolean is12StringedGuitarTrack, boolean isBanjoTrack) {
    }

    public void readStringTunning(int number, int stringIndex, int tunning) {
    }

    public void readBeat(int track, int mesure, int beat, int duration, boolean dottedNotes) {
    }

    public void readEmptyBeat(int track, int mesure, int beat, boolean emptyBeat, boolean restBeat) {
    }

    public void readTuplet(int track, int mesure, int beat, int tuplet) {
    }

    public void readNumberOfBeats(int track, int mesure, int numberOfBeats) {
    }

    public void readStringPlayed(int track, int mesure, int beat, int stringsPlayed) {
    }

    public void readNote(int track, int mesure, int beat, int stringPlayer, int numberOfFret, int duration) {
    }

    public void readNoteParameter(int track, int mesure, int beat, boolean accentuated, boolean ghostNote, boolean dotted) {
    }
}
