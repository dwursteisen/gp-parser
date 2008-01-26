/*
 * GP4ParserListener.java
 *
 * Created on 11 ao�t 2007, 10:25
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */
package net.sourceforge.musicsvg.io;

/**
 *
 * @author Dav
 */
public interface GP4ParserListener {

    public void close();

    public void readVersion(String version);

    public void readTitle(String title);

    public void readSubTitle(String subtitle);

    public void readArtist(String artist);

    public void readAlbum(String album);

    public void readSongAuthor(String songAuthor);

    public void readCopyright(String copyright);

    public void readPieceAuthor(String pieceAuthor);

    public void readInstruction(String instructions);

    public void readNumberOfNotices(int nbNotes);

    public void readNotice(String note);

    public void readIsTripleFeel(boolean isTripleFeel);

    public void readTempoValue(int tempo);

    public void readKey(int key);

    public void readOctave(int octave);

    public void readMidiChannel(int port, int channel, int instrument, byte volume, byte balance, byte chorus, byte reverb, byte phaser, byte tremolo);

    public void readNumberOfTracks(int numberOfTracks);

    public void readNumberOfMesures(int numberOfMesures);

    public void readMesureMarker(int number, String name, int r, int g, int b);

    public void readMeasureTonality(int tonality);

    public void readMeasureHeader(int number, int numerator, int denominator, boolean repeatStart, boolean doubleBar, int numberOfAlternateEnding, int numberOfRepetitions);

    void readTrackMidiParameter(int trackIndex, int port, int channelIndex, int effects, int numberOfFrets, int capo, int r, int g, int b);

    void readTrackParameter(int trackIndex, String name, int numberOfStrings, boolean isDrumsTrack, boolean is12StringedGuitarTrack, boolean isBanjoTrack);

    void readStringTunning(int number, int stringIndex, int tunning);

    void readBeat(int track, int mesure, int beat, int duration, boolean dottedNotes);

    void readEmptyBeat(int track, int mesure, int beat, boolean emptyBeat, boolean restBeat);

    void readTuplet(int track, int mesure, int beat, int tuplet);

    void readNumberOfBeats(int track, int mesure, int numberOfBeats);

    void readStringPlayed(int track, int mesure, int beat, int stringsPlayed);

    public void readNote(int track, int mesure, int beat, int stringPlayer, int numberOfFret, int duration);

    public void readNoteParameter(int track, int mesure, int beat, boolean accentuated, boolean ghostNote, boolean dotted);
}
