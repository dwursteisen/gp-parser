/*
 * GP4Parser.java Created on 11 ao�t 2007, 10:25 See the enclosed file COPYING for license
 * information (LGPL). If you did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */
package net.sourceforge.musicsvg.io.gp;

import com.google.inject.Inject;
import net.sourceforge.musicsvg.io.Parser;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dav
 * @see <a href='http://dguitar.sourceforge.net/GP4format.html'>GP4 Specification</a>
 */
public class GP4Parser implements Parser {

    protected static final int GP4_NUMBER_OF_STRING = 7;

    public static final int VERSION_LENGTH = 30;

    private int numberOfTracks;

    private int numberOfMeasures;

    protected FileInputStream is;

    protected GP4ParserListener listener;

    private final ParserHelper helper = new ParserHelper();

    public void close() {
        if (listener != null) {
            listener.close();
        }
    }

    public void openFile(File file) throws IOException {

        try {
            this.is = new FileInputStream(file);
        }
        catch (FileNotFoundException ex) {
            throw new IOException("File " + file.getAbsolutePath() + "Not Found", ex);
        }

        listener.open(file);
        readFileHeaders(this.is);
        readLyrics(this.is);
        readOtherInformations(this.is);
        readMeasures(this.is);
        readTracks(this.is);
        readMeasuresTracksPair(this.is);
        listener.endOfParsing(file);

    }

    /**
     * @param is
     * @throws java.io.IOException
     * @see <a href='http://dguitar.sourceforge.net/GP4format.html#Information_About_the_Piece'>GP4
     *      specification</a>
     */
    private void readFileHeaders(InputStream is) throws IOException {

        String version = helper.readStringByte(is, VERSION_LENGTH);
        this.listener.readVersion(version);

        String title = helper.readStringIntegerPlusOne(is);
        this.listener.readTitle(title);

        String subtitle = helper.readStringIntegerPlusOne(is);
        this.listener.readSubTitle(subtitle);

        String interpret = helper.readStringIntegerPlusOne(is);
        this.listener.readArtist(interpret);

        String album = helper.readStringIntegerPlusOne(is);
        this.listener.readAlbum(album);

        String songAuthor = helper.readStringIntegerPlusOne(is);
        this.listener.readSongAuthor(songAuthor);

        String copyright = helper.readStringIntegerPlusOne(is);
        this.listener.readCopyright(copyright);

        String pieceAuthor = helper.readStringIntegerPlusOne(is);
        this.listener.readPieceAuthor(pieceAuthor);

        String instructions = helper.readStringIntegerPlusOne(is);
        this.listener.readInstruction(instructions);

        int numberOfNotice = helper.readInt(is);
        this.listener.readNumberOfNotices(numberOfNotice);

        for (int i = 0; i < numberOfNotice; i++) {
            String notice = helper.readStringIntegerPlusOne(is);
            this.listener.readNotice(notice);
        }

        boolean tripletFeel = helper.readBoolean(is);
        this.listener.readIsTripleFeel(tripletFeel);

    }

    /**
     * @param is
     * @throws java.io.IOException
     * @see <a href='http://dguitar.sourceforge.net/GP4format.html#Lyrics'>GP4 Specification</a>
     */
    private void readLyrics(InputStream is) throws IOException {

        // ----lyrics----
        int lyricTrack = helper.readInt(is);
        // Lyric lyric = new Lyric(readInt(),readStringInteger());
        helper.readInt(is);
        helper.readStringInteger(is);
        for (int i = 0; i < 4; i++) {
            int measureNumber = helper.readInt(is);
            String lines = helper.readStringInteger(is);
        }
    }

    /**
     * @param is
     * @throws java.io.IOException
     * @see <a href='http://dguitar.sourceforge.net/GP4format.html#Measures-Tracks_pairs'>GP4
     *      Specification</a>
     */
    private void readMeasuresTracksPair(InputStream is) throws IOException {
        for (int mesure = 0; mesure < numberOfMeasures; mesure++) {
            for (int track = 0; track < numberOfTracks; track++) {
                readMeasureComponents(is, track, mesure);
            }
        }
    }

    /**
     * @param is
     * @throws java.io.IOException
     * @see <a
     *      href='http://dguitar.sourceforge.net/GP4format.html#Other_information_about_the_piece'>GP4
     *      Specification</a>
     */
    private void readOtherInformations(InputStream is) throws IOException {

        int tempoValue = helper.readInt(is);
        this.listener.readTempoValue(tempoValue);

        int key = helper.readByte(is);
        this.listener.readKey(key);

        int octave = helper.readInt(is);
        this.listener.readOctave(octave);

        // 4 ports
        // 16 channels
        final int NUMBER_OF_PORTS = 4;
        final int NUMBER_OF_CHANNELS = 16;
        for (int port = 0; port < NUMBER_OF_PORTS; port++) {
            for (int channel = 0; channel < NUMBER_OF_CHANNELS; channel++) {
                int instrument = helper.readInt(is);
                byte volume = helper.readByte(is);
                byte balance = helper.readByte(is);
                byte chorus = helper.readByte(is);
                byte reverb = helper.readByte(is);
                byte phaser = helper.readByte(is);
                byte tremolo = helper.readByte(is);
                byte blank1 = helper.readByte(is); // Backward compatibility with version 3.0
                byte blank2 = helper.readByte(is); // Backward compatibility with version 3.0
                listener.readMidiChannel(port, channel, instrument, volume, balance, chorus,
                        reverb, phaser, tremolo);
            }
        }

        numberOfMeasures = helper.readInt(is);
        numberOfTracks = helper.readInt(is);

        this.listener.readNumberOfTracks(numberOfTracks);
        this.listener.readNumberOfMesures(numberOfMeasures);
    }

    private void readMeasures(InputStream is) throws IOException {

        if (numberOfMeasures <= 0) {
            return;
        }

        for (int measureIndex = 0; measureIndex < numberOfMeasures; measureIndex++) {
            readMeasureHeader(is, measureIndex);
        }

    }

    /**
     * @param is
     * @param measureIndex
     * @throws java.io.IOException
     * @see <a href='http://dguitar.sourceforge.net/GP4format.html#Measures'>GP4 Specification</a>
     */
    private void readMeasureHeader(InputStream is, int measureIndex) throws IOException {
        int header = helper.readUnsignedByte(is);

        boolean repeatStart = ((header & ParserHelper.BITMASK_3) != 0);
        boolean doubleBar = ((header & ParserHelper.BITMASK_8) != 0);

        int numerator = 0;
        if ((header & ParserHelper.BITMASK_1) != 0) {
            numerator = helper.readByte(is);
        }

        int denominator = 0;
        if ((header & ParserHelper.BITMASK_2) != 0) {
            denominator = helper.readByte(is);
        }

        int numberOfRepeats = 0;
        if ((header & ParserHelper.BITMASK_4) != 0) {
            numberOfRepeats = helper.readByte(is);
        }

        int numberOfAlternateEnding = 0;
        if ((header & ParserHelper.BITMASK_5) != 0) {
            numberOfAlternateEnding = helper.readByte(is);
        }

        if ((header & ParserHelper.BITMASK_6) != 0) {
            String name = helper.readStringIntegerPlusOne(is);

            int r = helper.readUnsignedByte(is);
            int g = helper.readUnsignedByte(is);
            int b = helper.readUnsignedByte(is);
            int white = helper.readUnsignedByte(is); // always 0x00

            this.listener.readMesureMarker(measureIndex, name, r, g, b);

        }

        if ((header & ParserHelper.BITMASK_7) != 0) {
            int tonality = helper.readByte(is);
            helper.readByte(is); // WTF ?
            this.listener.readMeasureTonality(tonality);
        }

        this.listener.readMeasureHeader(measureIndex, numerator, denominator, repeatStart,
                doubleBar, numberOfAlternateEnding, numberOfRepeats);
    }

    /**
     * @param is
     * @param trackIndex
     * @throws java.io.IOException
     * @see <a href='http://dguitar.sourceforge.net/GP4format.html#Tracks'>GP4 Specification</a>
     */
    private void readTrack(InputStream is, int trackIndex) throws IOException {
        int header = helper.readUnsignedByte(is);

        boolean isDrumsTrack = ((header & ParserHelper.BITMASK_1) != 0);
        boolean is12StringedGuitarTrack = ((header & ParserHelper.BITMASK_2) != 0);
        boolean isBanjoTrack = ((header & ParserHelper.BITMASK_3) != 0);

        String name = helper.readStringByte(is, 40);

        int numberOfStrings = helper.readInt(is);
        this.listener.readTrackParameter(trackIndex, name, numberOfStrings, isDrumsTrack,
                is12StringedGuitarTrack, isBanjoTrack);

        for (int stringIndex = 0; stringIndex < GP4_NUMBER_OF_STRING; stringIndex++) {
            int tunning = helper.readInt(is);
            // WTF ??
            // if (numberOfStrings > stringIndex) {
            this.listener.readStringTunning(trackIndex, stringIndex, tunning);
            // }
        }

        int midiPort = helper.readInt(is);

        int midiChannel = helper.readInt(is);

        int midiChannelEffect = helper.readInt(is);

        int numberOfFrets = helper.readInt(is);

        int capo = helper.readInt(is);

        int r = helper.readUnsignedByte(is);
        int g = helper.readUnsignedByte(is);
        int b = helper.readUnsignedByte(is);
        int white = helper.readUnsignedByte(is);

        this.listener.readTrackMidiParameter(trackIndex, midiPort, midiChannel,
                midiChannelEffect, numberOfFrets, capo, r, g, b);
    }

    /*
     * private SongChannel parseChannel(List channels, int channelIndex,int effectChannel) {
     * SongChannel channel = (SongChannel) channels.get(channelIndex - 1); int instrument =
     * channel.getInstrument(); if (instrument == -1) { channel.setInstrument((short)0); }
     * if(!channel.isPercusionChannel()){ channel.setEffectChannel((short)(effectChannel - 1)); }
     * return channel; }
     */
    private void readMeasureComponents(InputStream is, int track, int mesure)
        throws IOException {
        int numberOfBeats = helper.readInt(is);
        this.listener.readNumberOfBeats(track, mesure, numberOfBeats);
        for (int beat = 0; beat < numberOfBeats; beat++) {
            readNotes(this.is, track, mesure, beat);
        }

    }

    /**
     * @param is
     * @param track
     * @param mesure
     * @param beat
     * @throws java.io.IOException
     * @see <a href='http://dguitar.sourceforge.net/GP4format.html#A_beat'>GP4 Specification</a>
     */
    private void readNotes(InputStream is, int track, int mesure, int beat) throws IOException {

        int header = helper.readUnsignedByte(is);
        boolean dottedNotes = ((header & ParserHelper.BITMASK_1) != 0);

        if ((header & ParserHelper.BITMASK_7) != 0) {
            int beatStatus = helper.readUnsignedByte(is);
            boolean emptyBeat = (beatStatus == 0x00);
            boolean restBeat = (beatStatus == ParserHelper.BITMASK_2);
            this.listener.readEmptyBeat(track, mesure, beat, emptyBeat, restBeat);
        }

        int beatDuration = helper.readByte(is);
        if ((header & ParserHelper.BITMASK_6) != 0) {
            int tuplet = helper.readInt(is);
            this.listener.readTuplet(track, mesure, beat, tuplet);
        }

        this.listener.readBeat(track, mesure, beat, beatDuration, dottedNotes);

        if ((header & ParserHelper.BITMASK_2) != 0) {
            readChordDiagram(this.is, track, mesure, beat);
        }

        if ((header & ParserHelper.BITMASK_3) != 0) {
            String text = helper.readStringIntegerPlusOne(is);
        }

        if ((header & ParserHelper.BITMASK_4) != 0) {
            readBeatEffects(this.is);
        }

        if ((header & ParserHelper.BITMASK_5) != 0) {
            readMixChange(this.is);
        }

        int stringsPlayed = helper.readUnsignedByte(is);

        this.listener.readStringPlayed(track, mesure, beat, stringsPlayed);
        int numberOfStringPlayed = nbPlayerString(stringsPlayed);
        for (int index = numberOfStringPlayed - 1; index >= 0; index--) {
            // It can be a better idea to give the absolute index of the string
            // ie: 1 = E ; A = 2 ; D = 3
            // than relative index
            // ie: 0 = first string, 1, second string in the chord
            int stringIndex = getStringIndexFromStringCode(stringsPlayed, index);
            parseNote(this.is, track, mesure, beat, stringIndex);
        }

    }

    protected int nbPlayerString(int stringsPlayer) {
        int res = 0;

        for (int i = 0; i < GP4_NUMBER_OF_STRING; i++) {
            if ((stringsPlayer & (1 << i)) != 0) {
                res++;
            }
        }
        return res;
    }

    protected int getStringIndexFromStringCode(int stringCode, int index) {
        int tmpIndex = index;
        for (int i = 0; i < GP4_NUMBER_OF_STRING; i++) {
            if ((stringCode & (1 << i)) != 0) {
                if (tmpIndex == 0) {
                    return i;
                }
                else {
                    tmpIndex--;
                }
            }
        }
        return -1;
    }

    void parseNote(InputStream is, int track, int mesure, int beat, int stringPlayer)
        throws IOException {
        int header = helper.readUnsignedByte(is);

        boolean accentuated = ((header & ParserHelper.BITMASK_7) != 0);
        boolean ghostNote = ((header & ParserHelper.BITMASK_3) != 0);
        boolean dotted = ((header & ParserHelper.BITMASK_2) != 0);

        boolean tiedNote = false;
        boolean deadNote = false;
        if ((header & ParserHelper.BITMASK_6) != 0) {
            int noteType = helper.readUnsignedByte(is);
            tiedNote = (noteType == ParserHelper.BITMASK_2);
            // effect.setDeadNote((noteType == 0x03));
        }

        /**
         * -2: Whole Note; -1: Half note; 0: Quarter note; 1: Eighth note; 2: Sixteenth note; 3:
         * Thirty-second note;
         */
        byte duration = 0;
        if ((header & ParserHelper.BITMASK_1) != 0) {
            duration = helper.readByte(is);
            byte tuplet = helper.readByte(is);
        }

        // int velocity = VelocityValues.DEFAULT;
        if ((header & ParserHelper.BITMASK_5) != 0) {
            helper.readByte(is);
            // velocity = (VelocityValues.MIN_VELOCITY + (VelocityValues.VELOCITY_INCREMENT *
            // readByte())) - VelocityValues.VELOCITY_INCREMENT;
        }

        int fretIndex = 0;
        if ((header & ParserHelper.BITMASK_6) != 0) {
            fretIndex = helper.readUnsignedByte(is);
        }

        if ((header & ParserHelper.BITMASK_8) != 0) {
            byte fingeringLeftHand = helper.readByte(is);
            byte fingeringRightHand = helper.readByte(is);
        }

        if ((header & ParserHelper.BITMASK_4) != 0) {
            readNoteEffects(this.is);
        }

        if (fretIndex >= 0 || tiedNote) {
            if (tiedNote) {
                // value = getTiedNoteValue(string.getNumber(), currMeasureNotes,
                // currTrackMeasures);
            }

            // return new Note(value, start, (Duration) currDuration.clone(), velocity,
            // string.getNumber(),tiedNote,effect);
        }
        this.listener.readNote(track, mesure, beat, stringPlayer, fretIndex, duration);
        this.listener.readNoteParameter(track, mesure, beat, accentuated, ghostNote, dotted);
        // return null;
    }

    private void readNoteEffects(InputStream is) throws IOException {
        int header1;
        int header2;
        int b;

        header1 = helper.readUnsignedByte(is);
        header2 = helper.readUnsignedByte(is);

        if ((header1 & ParserHelper.BITMASK_1) != 0) {
            readBend(this.is);
        }

        if ((header1 & ParserHelper.BITMASK_5) != 0) {
            readGraceNote(this.is);
        }

        if ((header2 & ParserHelper.BITMASK_3) != 0) {
            int duration = helper.readUnsignedByte(is);
        }

        if ((header2 & ParserHelper.BITMASK_4) != 0) {
            // noteEffect.setSlide(true);
            helper.readByte(is);
        }

        if ((header2 & ParserHelper.BITMASK_5) != 0) {
            b = helper.readByte(is);
        }

        // trill
        if ((header2 & ParserHelper.BITMASK_6) != 0) {
            byte fret = helper.readByte(is);
            byte period = helper.readByte(is);
        }

        if ((header1 & ParserHelper.BITMASK_4) != 0) {
        }

        // hammer
        // noteEffect.setHammer(((header1 & 0x02) != 0));

        // vibrato
        // noteEffect.setVibrato(((header2 & 0x40) != 0) || noteEffect.isVibrato());

        // palm-mute
        // noteEffect.setPalmMute(((header2 & 0x02) != 0));

        // staccato
        // noteEffect.setStaccato(((header2 & 0x01) != 0));

    }

    private void readGraceNote(InputStream is) throws IOException {
        int fret = helper.readUnsignedByte(is);
        int velocity = helper.readUnsignedByte(is);
        int transition = helper.readUnsignedByte(is);
        int duration = helper.readUnsignedByte(is);

        // dead
        boolean dead = (fret == 255);

        // fret
        fret = ((!dead) ? fret : 0);

        // velocity
        // velocity = (VelocityValues.MIN_VELOCITY + (VelocityValues.VELOCITY_INCREMENT *
        // velocity)) - VelocityValues.VELOCITY_INCREMENT;

        // transition
        /*
         * if(transition == 0){ transition = GraceEffect.TRANSITION_NONE; } else if(transition ==
         * 1){ transition = GraceEffect.TRANSITION_SLIDE; } else if(transition == 2){ transition =
         * GraceEffect.TRANSITION_BEND; } else if(transition == 3){ transition =
         * GraceEffect.TRANSITION_HAMMER; }
         */
        // effect.setGrace(new GraceEffect(fret,duration,velocity,transition,false,dead));
    }

    private void readBend(InputStream is) throws IOException {
        byte type = helper.readByte(is);
        int value = helper.readInt(is);

        int numPoints = helper.readInt(is);
        for (int i = 0; i < numPoints; i++) {
            int bendPosition = helper.readInt(is);
            int bendValue = helper.readInt(is);
            byte bendVibrato = helper.readByte(is);

        }

    }

    private void readMixChange(InputStream is) throws IOException {
        int pos[] = new int[8];
        int i;
        int n;
        int aux;
        n = 0;
        for (i = 0; i < GP4_NUMBER_OF_STRING; i++) {
            aux = helper.readByte(is);
            if ((i != 0) && (aux != -1)) {
                pos[n] = i;
                n++;
            }
        }
        aux = helper.readInt(is);
        if (aux != -1) {
            // tempo.setValue(aux);
            pos[n] = i;
            n++;
        }

        for (i = 0; i < n; i++) {
            aux = helper.readByte(is);
        }
        int applyToAllTracks = helper.readUnsignedByte(is);
    }

    private void readTracks(InputStream is) throws IOException {

        for (int trackIndex = 0; trackIndex < numberOfTracks; trackIndex++) {
            readTrack(is, trackIndex);
        }
    }

    private void readTremoloBar(InputStream is) throws IOException {
        byte type = helper.readByte(is);
        int value = helper.readInt(is);

        // TremoloBarEffect tremoloBar = new TremoloBarEffect();
        int numPoints = helper.readInt(is);
        for (int i = 0; i < numPoints; i++) {
            int bendPosition = helper.readInt(is);
            int bendValue = helper.readInt(is);
            byte bendVibrato = helper.readByte(is);

            // tremoloBar.addPoint((int)(bendPosition * TremoloBarEffect.MAX_POSITION_LENGTH /
            // GP_BEND_POSITION),(bendValue * TremoloBarEffect.SEMITONE_LENGTH /
            // GP_BEND_SEMITONE));
        }

    }

    private void readBeatEffects(InputStream is) throws IOException {
        int header[] = { 0, 0 };

        header[0] = helper.readUnsignedByte(is);
        header[1] = helper.readUnsignedByte(is);

        // noteEffect.setFadeIn(((header[0] & 0x10) != 0));
        // vnoteEffect.setVibrato(((header[0] & 0x02) != 0));

        if ((header[0] & ParserHelper.BITMASK_6) != 0) {
            int effect = helper.readUnsignedByte(is);
            // noteEffect.setTapping(effect == 1);
            // noteEffect.setSlapping(effect == 2);
            // noteEffect.setPopping(effect == 3);
        }

        if ((header[1] & ParserHelper.BITMASK_3) != 0) {
            readTremoloBar(is);
        }

        if ((header[0] & ParserHelper.BITMASK_7) != 0) {
            // Upstroke - Downstroke
            helper.readByte(is);
            helper.readByte(is);
        }

        if ((header[1] & ParserHelper.BITMASK_1) != 0) {
            // Rasgueado
        }

        if ((header[1] & ParserHelper.BITMASK_2) != 0) {
            // Pickstroke
            helper.readByte(is);
        }

    }

    private void readChordDiagram(InputStream is, int track, int measure, int beat)
        throws IOException {
        int header = helper.readUnsignedByte(is);

        if ((header & ParserHelper.BITMASK_1) == 0) {
            // old chord diagram (deprecated)
            String name = helper.readStringIntegerPlusOne(is);
            int base = helper.readInt(is);

            if (base != 0) {
                int fret0 = helper.readInt(is);
                int fret1 = helper.readInt(is);
                int fret2 = helper.readInt(is);
                int fret3 = helper.readInt(is);
                int fret4 = helper.readInt(is);
                int fret5 = helper.readInt(is);
                // this.listener.readChordDiagram(track, mesure, beat, name, base, fret1, fret2,
                // fret3, fret4, fret5);
            }
        }
        else {

            boolean sharp = helper.readBoolean(is);

            is.skip(3);

            helper.readByte(is); // readRoot();

            helper.readUnsignedByte(is); // chord type

            int nineElevenThirteen = helper.readUnsignedByte(is);

            int bass = helper.readInt(is);

            helper.readInt(is); // readTonalityType(4);

            int addedNote = helper.readUnsignedByte(is);

            String name = helper.readStringByte(is, 20);

            is.skip(2);

            helper.readUnsignedByte(is); // readTonalityType(1);

            helper.readUnsignedByte(is); // readTonalityType(1);

            helper.readUnsignedByte(is); // readTonalityType(1);

            int baseFret = helper.readInt(is);

            int fret1 = helper.readInt(is);
            int fret2 = helper.readInt(is);
            int fret3 = helper.readInt(is);
            int fret4 = helper.readInt(is);
            int fret5 = helper.readInt(is);
            int fret6 = helper.readInt(is);
            int fret7 = helper.readInt(is);

            int numBarres = helper.readUnsignedByte(is);

            for (int i = 1; i <= 5; i++) {
                int fretOfBarre = helper.readUnsignedByte(is);
            }
            for (int i = 1; i <= 5; i++) {
                int barreStart = helper.readUnsignedByte(is);
            }
            for (int i = 1; i <= 5; i++) {
                int barreEnd = helper.readUnsignedByte(is);
            }

            is.skip(8);

            for (int i = 1; i <= GP4_NUMBER_OF_STRING; i++) {
                int fingering = helper.readByte(is);
            }

            boolean chordFingeringDisplayed = helper.readBoolean(is);
        }
    }

    private String readChordName(InputStream is) throws IOException {
        byte[] nameB;
        char[] nameC;
        int i;
        int max;

        nameB = new byte[21];
        nameC = new char[20];
        is.read(nameB, 0, 21);
        max = 20;
        if (nameB[0] < max) {
            max = nameB[0];
        }

        for (i = 1; i <= max; i++) {
            nameC[i - 1] = (char) nameB[i];
        }
        return (String.valueOf(nameC, 0, max));
    }

    @Inject
    public void setListener(GP4ParserListener listener) {
        this.listener = listener;
    }
}
