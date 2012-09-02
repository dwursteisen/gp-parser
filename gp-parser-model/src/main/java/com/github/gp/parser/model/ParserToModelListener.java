package com.github.gp.parser.model;

import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

import java.io.File;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 21:12
 */
public class ParserToModelListener implements GP4ParserListener {

    private final HeadersBuilder headers = new HeadersBuilder();

    private final PieceInformationBuilder pieceInformation = new PieceInformationBuilder();

    public Headers getHeaders() {
        return headers.createHeaders();
    }

    public PieceInformation getPieceInformation() {
        return pieceInformation.createPieceInformation();
    }

    public void open(File file) {

    }

    public void close() {

    }

    public void readVersion(String version) {
        headers.withVersion(version);
    }

    public void readTitle(String title) {
        headers.withTitle(title);
    }

    public void readSubTitle(String subtitle) {
        headers.withSubtitle(subtitle);
    }

    public void readArtist(String artist) {
        headers.withInterpret(artist);
    }

    public void readAlbum(String album) {
        headers.withAlbum(album);
    }

    public void readSongAuthor(String songAuthor) {
        headers.withSongAuthor(songAuthor);
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
        pieceInformation.withTempo(tempo);
    }

    public void readKey(int key) {
        pieceInformation.withKey(key);
    }

    public void readOctave(int octave) {
        pieceInformation.withOctave(octave);
    }

    public void readMidiChannel(int port, int channel, int instrument, byte volume, byte balance, byte chorus, byte reverb, byte phaser, byte tremolo) {

    }

    public void readNumberOfTracks(int numberOfTracks) {
        pieceInformation.withNumberOfTrack(numberOfTracks);
    }

    public void readNumberOfMesures(int numberOfMesures) {
        pieceInformation.withNumberOfMeasure(numberOfMesures);
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
