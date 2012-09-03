package com.github.gp.parser.model;

import com.github.gp.parser.model.beats.Beat;
import com.github.gp.parser.model.beats.BeatBuilder;
import com.github.gp.parser.model.header.Headers;
import com.github.gp.parser.model.header.HeadersBuilder;
import com.github.gp.parser.model.header.PieceInformation;
import com.github.gp.parser.model.header.PieceInformationBuilder;
import com.github.gp.parser.model.measures.Measure;
import com.github.gp.parser.model.measures.MeasureBuilder;
import com.github.gp.parser.model.measures.MeasureHeader;
import com.github.gp.parser.model.measures.MeasureHeaderBuilder;
import com.github.gp.parser.model.tracks.Track;
import com.github.gp.parser.model.tracks.TrackBuilder;
import com.github.gp.parser.model.tracks.TrackHeader;
import com.github.gp.parser.model.tracks.TrackHeaderBuilder;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

import java.io.File;
import java.util.*;

/**
 * User: Wursteisen David Date: 02/09/12 Time: 21:12
 */
public class ParserToModelListener implements GP4ParserListener {

    private final HeadersBuilder headersBuilder = new HeadersBuilder();

    private final PieceInformationBuilder pieceInformationBuilder =
            new PieceInformationBuilder();

    private final List<MeasureHeaderBuilder> measureHeaderBuilders =
            new ArrayList<MeasureHeaderBuilder>();

    private final List<TrackHeaderBuilder> trackHeaderBuilders =
            new ArrayList<TrackHeaderBuilder>();

    private final Map<MeasureTrackKey, MeasureBuilder> measureBuilderMap =
            new HashMap<MeasureTrackKey, MeasureBuilder>();

    private final Map<MeasureTrackKey, List<BeatBuilder>> beatBuilderMap =
            new HashMap<MeasureTrackKey, List<BeatBuilder>>();

    private Headers headers;

    private PieceInformation pieceInformation;

    private List<MeasureHeader> measureHeaders;

    private List<TrackHeader> trackHeaders;

    private List<Measure> measures;

    private List<Track> tracks;

    public Headers getHeaders() {
        return headers;
    }

    public PieceInformation getPieceInformation() {
        return pieceInformation;
    }

    public List<MeasureHeader> getMeasureHeaders() {
        return new ArrayList<MeasureHeader>(measureHeaders);
    }

    public List<TrackHeader> getTrackHeaders() {
        return new ArrayList<TrackHeader>(trackHeaders);
    }

    public List<Measure> getMeasures() {
        return new ArrayList<Measure>(measures);
    }

    public List<Track> getTracks() {
        return new ArrayList<Track>(tracks);
    }

    public void open(File file) {

    }

    public void close() {

    }

    public void readVersion(String version) {
        headersBuilder.withVersion(version);
    }

    public void readTitle(String title) {
        headersBuilder.withTitle(title);
    }

    public void readSubTitle(String subtitle) {
        headersBuilder.withSubtitle(subtitle);
    }

    public void readArtist(String artist) {
        headersBuilder.withInterpret(artist);
    }

    public void readAlbum(String album) {
        headersBuilder.withAlbum(album);
    }

    public void readSongAuthor(String songAuthor) {
        headersBuilder.withSongAuthor(songAuthor);
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
        pieceInformationBuilder.withTempo(tempo);
    }

    public void readKey(int key) {
        pieceInformationBuilder.withKey(key);
    }

    public void readOctave(int octave) {
        pieceInformationBuilder.withOctave(octave);
    }

    public void readMidiChannel(int port, int channel, int instrument, byte volume,
                                byte balance, byte chorus, byte reverb, byte phaser, byte tremolo) {

    }

    public void readNumberOfTracks(int numberOfTracks) {
        pieceInformationBuilder.withNumberOfTrack(numberOfTracks);
        for (int trackIndex = 0; trackIndex < numberOfTracks; trackIndex++) {
            trackHeaderBuilders.add(new TrackHeaderBuilder());
        }

    }

    public void readNumberOfMesures(int numberOfMesures) {
        pieceInformationBuilder.withNumberOfMeasure(numberOfMesures);
        for (int measureIndex = 0; measureIndex < numberOfMesures; measureIndex++) {
            measureHeaderBuilders.add(new MeasureHeaderBuilder());
        }

        // trackHeaders.size may not be the best to know the number of tracks
        for (int trackIndex = 0; trackIndex < trackHeaderBuilders.size(); trackIndex++) {
            for (int measureIndex = 0; measureIndex < numberOfMesures; measureIndex++) {
                MeasureBuilder builder =
                        new MeasureBuilder().withMeasureIndex(measureIndex).withTrackIndex(
                                trackIndex);
                measureBuilderMap.put(new MeasureTrackKey(trackIndex, measureIndex), builder);
            }
        }
    }

    public void readMesureMarker(int number, String name, int r, int g, int b) {

    }

    public void readMeasureTonality(int tonality) {

    }

    public void readMeasureHeader(int measureIndex, int numerator, int denominator,
                                  boolean repeatStart, boolean doubleBar, int numberOfAlternateEnding,
                                  int numberOfRepetitions) {
        measureHeaderBuilders.get(measureIndex).withMeasureIndex(measureIndex).withRepeat(
                repeatStart).withDoubleBar(doubleBar).withNumberOfAlternateEnding(
                numberOfAlternateEnding).withNumberOfRepeats(numberOfRepetitions);
    }

    public void readTrackMidiParameter(int trackIndex, int port, int channelIndex, int effects,
                                       int numberOfFrets, int capo, int r, int g, int b) {
        trackHeaderBuilders.get(trackIndex).withCapodastrePosition(capo).withNumberOfFrets(
                numberOfFrets);
    }

    public void readTrackParameter(int trackIndex, String name, int numberOfStrings,
                                   boolean isDrumsTrack, boolean is12StringedGuitarTrack, boolean isBanjoTrack) {
        trackHeaderBuilders.get(trackIndex).withTrackIndex(trackIndex).withName(name)
                .withNumberOfString(numberOfStrings).withDrumTrack(isDrumsTrack)
                .withBanjoTrack(isBanjoTrack).withTwelveStringTrack(is12StringedGuitarTrack);

    }

    public void readStringTunning(int number, int stringIndex, int tunning) {

    }

    public void readBeat(int track, int mesure, int beat, int duration, boolean dottedNotes) {

    }

    public void readEmptyBeat(int track, int mesure, int beat, boolean emptyBeat,
                              boolean restBeat) {

    }

    public void readTuplet(int track, int mesure, int beat, int tuplet) {

    }

    public void readNumberOfBeats(int track, int mesure, int numberOfBeats) {
        MeasureTrackKey key = new MeasureTrackKey(track, mesure);
        measureBuilderMap.get(key).withNumberOfBeats(
                numberOfBeats);

        beatBuilderMap.put(key, new ArrayList<BeatBuilder>(numberOfBeats));
    }

    public void readStringPlayed(int track, int mesure, int beat, int stringsPlayed) {

    }

    public void readNote(int track, int mesure, int beat, int stringPlayer, int numberOfFret,
                         int duration) {

        BeatBuilder builder = new BeatBuilder();
        builder.withBeatIndex(beat).withDuration(duration).withFret(numberOfFret).withString(stringPlayer);
        beatBuilderMap.get(new MeasureTrackKey(track, mesure)).add(builder);
    }

    public void readNoteParameter(int track, int mesure, int beat, boolean accentuated,
                                  boolean ghostNote, boolean dotted) {

    }

    @Override
    public void endOfParsing(File file) {

        // TODO: refactoring. the code is not enough clear
        // (but it build object graph)
        headers = headersBuilder.createHeaders();
        pieceInformation = pieceInformationBuilder.createPieceInformation();
        measureHeaders = new ArrayList<MeasureHeader>(measureHeaderBuilders.size());
        for (MeasureHeaderBuilder builder : measureHeaderBuilders) {
            measureHeaders.add(builder.createMeasureHeader());
        }

        trackHeaders = new ArrayList<TrackHeader>(trackHeaderBuilders.size());
        for (TrackHeaderBuilder builder : trackHeaderBuilders) {
            trackHeaders.add(builder.createTrackHeader());
        }

        measures = new ArrayList<Measure>(measureBuilderMap.size());
        for (MeasureBuilder builder : measureBuilderMap.values()) {

            MeasureTrackKey key = new MeasureTrackKey(builder.getTrackIndex(), builder.getMeasureIndex());
            List<BeatBuilder> builders = beatBuilderMap.get(key);

            List<Beat> beats = new ArrayList<Beat>(builders.size());
            for (BeatBuilder beatBuilder : builders) {
                beats.add(beatBuilder.createBeat());
            }
            Collections.sort(beats);

            builder.withHeader(measureHeaders.get(builder.getMeasureIndex())); // should be ok...
            builder.withBeats(beats);
            measures.add(builder.createMeasure());
        }
        Collections.sort(measures);

        tracks = new ArrayList<Track>(trackHeaders.size());
        for (TrackHeader trackHeader : trackHeaders) {

            TrackBuilder trackBuilder = new TrackBuilder();
            List<Measure> measureOfTrack =
                    new ArrayList<Measure>(Collections2.filter(measures,
                            new MeasuresOnTrackPredicate(trackHeader.getTrackIndex())));

            trackBuilder.withHeader(trackHeader).withTrackIndex(trackHeader.getTrackIndex())
                    .withMeasures(measureOfTrack);

            tracks.add(trackBuilder.createTrack());

        }

    }

    private class MeasuresOnTrackPredicate implements Predicate<Measure> {
        private final int trackIndex;

        public MeasuresOnTrackPredicate(int trackIndex) {
            this.trackIndex = trackIndex;
        }

        @Override
        public boolean apply(Measure measure) {
            return measure.getTrackIndex() == trackIndex;
        }
    }


}
