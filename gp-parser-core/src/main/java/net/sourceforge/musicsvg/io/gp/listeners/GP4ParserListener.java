/*
 * GP4ParserListener.java Created on 11 ao�t 2007, 10:25 See the enclosed file COPYING for license
 * information (LGPL). If you did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */
package net.sourceforge.musicsvg.io.gp.listeners;

import java.io.File;

/**
 * @author Dav
 */
public interface GP4ParserListener {

    void open(File file);

    void close();

    void readVersion(String version);

    void readTitle(String title);

    void readSubTitle(String subtitle);

    void readArtist(String artist);

    void readAlbum(String album);

    void readSongAuthor(String songAuthor);

    void readCopyright(String copyright);

    void readPieceAuthor(String pieceAuthor);

    void readInstruction(String instructions);

    void readNumberOfNotices(int nbNotes);

    void readNotice(String note);

    void readIsTripleFeel(boolean isTripleFeel);

    void readTempoValue(int tempo);

    void readKey(int key);

    void readOctave(int octave);

    void readMidiChannel(int port, int channel, int instrument, byte volume, byte balance,
        byte chorus, byte reverb, byte phaser, byte tremolo);

    void readNumberOfTracks(int numberOfTracks);

    void readNumberOfMesures(int numberOfMesures);

    void readMesureMarker(int number, String name, int r, int g, int b);

    void readMeasureTonality(int tonality);

    void readMeasureHeader(int number, int numerator, int denominator, boolean repeatStart,
        boolean doubleBar, int numberOfAlternateEnding, int numberOfRepetitions);

    void readTrackMidiParameter(int trackIndex, int port, int channelIndex, int effects,
        int numberOfFrets, int capo, int r, int g, int b);

    void readTrackParameter(int trackIndex, String name, int numberOfStrings,
        boolean isDrumsTrack, boolean is12StringedGuitarTrack, boolean isBanjoTrack);

    void readStringTunning(int number, int stringIndex, int tunning);

    void readBeat(int track, int mesure, int beat, int duration, boolean dottedNotes);

    void readEmptyBeat(int track, int mesure, int beat, boolean emptyBeat, boolean restBeat);

    void readTuplet(int track, int mesure, int beat, int tuplet);

    void readNumberOfBeats(int track, int mesure, int numberOfBeats);

    void readStringPlayed(int track, int mesure, int beat, int stringsPlayed);

    void readNote(int track, int mesure, int beat, int stringPlayer, int numberOfFret,
        int duration);

    void readNoteParameter(int track, int mesure, int beat, boolean accentuated,
        boolean ghostNote, boolean dotted);

    void endOfParsing(File file);
}
