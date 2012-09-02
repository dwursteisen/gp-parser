package com.github.gp.parser.model;

import com.github.gp.parser.model.header.Headers;
import com.github.gp.parser.model.header.HeadersBuilder;
import com.github.gp.parser.model.header.PieceInformation;
import com.github.gp.parser.model.header.PieceInformationBuilder;
import com.github.gp.parser.model.measures.Measure;
import com.github.gp.parser.model.measures.MeasureBuilder;
import com.github.gp.parser.model.measures.MeasureHeader;
import com.github.gp.parser.model.measures.MeasureHeaderBuilder;
import com.github.gp.parser.model.tracks.TrackHeader;
import com.github.gp.parser.model.tracks.TrackHeaderBuilder;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

import java.io.File;
import java.util.*;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 21:12
 */
public class ParserToModelListener implements GP4ParserListener {

    private final HeadersBuilder headers = new HeadersBuilder();

    private final PieceInformationBuilder pieceInformation = new PieceInformationBuilder();

    private final List<MeasureHeaderBuilder> measureHeader = new ArrayList<MeasureHeaderBuilder>();

    private final List<TrackHeaderBuilder> trackHeaders = new ArrayList<TrackHeaderBuilder>();

    private final Map<MeasureTrackKey, MeasureBuilder> measures = new HashMap<MeasureTrackKey, MeasureBuilder>();

    public Headers getHeaders() {
        return headers.createHeaders();
    }

    public PieceInformation getPieceInformation() {
        return pieceInformation.createPieceInformation();
    }

    public List<MeasureHeader> getMeasureHeaders() {
        List<MeasureHeader> measureHeaders = new ArrayList<MeasureHeader>(measureHeader.size());
        for (MeasureHeaderBuilder builder : measureHeader) {
            measureHeaders.add(builder.createMeasureHeader());
        }
        return measureHeaders;
    }

    public List<TrackHeader> getTrackHeaders() {
        List<TrackHeader> trackHeaderList = new ArrayList<TrackHeader>(trackHeaders.size());
        for (TrackHeaderBuilder builder : trackHeaders) {
            trackHeaderList.add(builder.createTrackHeader());
        }
        return trackHeaderList;
    }

    public List<Measure> getMeasures() {
        List<Measure> measureList = new ArrayList<Measure>(measures.size());
        for (MeasureBuilder builder : measures.values()) {
            measureList.add(builder.createMeasure());
        }
        Collections.sort(measureList);
        return measureList;
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
        for (int trackIndex = 0; trackIndex < numberOfTracks; trackIndex++) {
            trackHeaders.add(new TrackHeaderBuilder());
        }


    }

    public void readNumberOfMesures(int numberOfMesures) {
        pieceInformation.withNumberOfMeasure(numberOfMesures);
        for (int measureIndex = 0; measureIndex < numberOfMesures; measureIndex++) {
            measureHeader.add(new MeasureHeaderBuilder());
        }

        // trackHeaders.size may not be the best to know the number of tracks
        for (int trackIndex = 0; trackIndex < trackHeaders.size(); trackIndex++) {
            for (int measureIndex = 0; measureIndex < numberOfMesures; measureIndex++) {
                MeasureBuilder builder = new MeasureBuilder()
                        .withMeasureIndex(measureIndex)
                        .withTrackIndex(trackIndex);
                measures.put(new MeasureTrackKey(trackIndex, measureIndex), builder);
            }
        }
    }

    public void readMesureMarker(int number, String name, int r, int g, int b) {

    }

    public void readMeasureTonality(int tonality) {

    }

    public void readMeasureHeader(int number, int numerator, int denominator, boolean repeatStart, boolean doubleBar, int numberOfAlternateEnding, int numberOfRepetitions) {
        measureHeader.get(number)
                .withRepeat(repeatStart)
                .withDoubleBar(doubleBar)
                .withNumberOfAlternateEnding(numberOfAlternateEnding)
                .withNumberOfRepeats(numberOfRepetitions);
    }

    public void readTrackMidiParameter(int trackIndex, int port, int channelIndex, int effects, int numberOfFrets, int capo, int r, int g, int b) {
        trackHeaders.get(trackIndex).withCapodastrePosition(capo).withNumberOfFrets(numberOfFrets);
    }

    public void readTrackParameter(int trackIndex, String name, int numberOfStrings, boolean isDrumsTrack, boolean is12StringedGuitarTrack, boolean isBanjoTrack) {
        trackHeaders.get(trackIndex).withName(name)
                .withNumberOfString(numberOfStrings)
                .withDrumTrack(isDrumsTrack)
                .withBanjoTrack(isBanjoTrack)
                .withTwelveStringTrack(is12StringedGuitarTrack);


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
        measures.get(new MeasureTrackKey(track, mesure)).withNumberOfBeats(numberOfBeats);
    }

    public void readStringPlayed(int track, int mesure, int beat, int stringsPlayed) {

    }

    public void readNote(int track, int mesure, int beat, int stringPlayer, int numberOfFret, int duration) {

    }

    public void readNoteParameter(int track, int mesure, int beat, boolean accentuated, boolean ghostNote, boolean dotted) {

    }
}
