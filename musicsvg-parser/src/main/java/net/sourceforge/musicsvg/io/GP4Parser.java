/*
 * GP4Parser.java
 *
 * Created on 11 ao�t 2007, 10:25
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */
package net.sourceforge.musicsvg.io;

import com.google.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Dav
 * 
 * @see http://dguitar.sourceforge.net/GP4format.html
 */
public class GP4Parser {
    private static final int BITMASK_1 = 0x01;
    private static final int BITMASK_2 = 0x02;
    private static final int BITMASK_3 = 0x04;
    private static final int BITMASK_4 = 0x08;
    private static final int BITMASK_5 = 0x10;
    private static final int BITMASK_6 = 0x20;
    private static final int BITMASK_7 = 0x40;
    private static final int BITMASK_8 = 0x80;
    private static final int GP4_NUMBER_OF_STRING = 7;

    GP4ParserListener listener = null;
    InputStream is = null;
    private int numberOfTracks;
    private int numberOfMeasures;

    public void close() {
        if(listener != null) {
            listener.close();
        }
    }

    @Inject
    public void injectListener(GP4ParserListener listener) {
        this.listener = listener;
    }

    public void openFile(File file) throws IOException {
        try {
            this.is = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        readFileHeaders();
        readLyrics();
        readOtherInformations();
        readMeasures();
        readTracks();
        readMeasuresTracksPair();

    }

    /**
     * @see http://dguitar.sourceforge.net/GP4format.html#Information_About_the_Piece
     * @throws java.io.IOException
     */
    private void readFileHeaders() throws IOException {

        String version = readStringByte(30);
        this.listener.readVersion(version);

        String title = readStringIntegerPlusOne();
        this.listener.readTitle(title);

        String subtitle = readStringIntegerPlusOne();
        this.listener.readSubTitle(subtitle);

        String interpret = readStringIntegerPlusOne();
        this.listener.readArtist(interpret);

        String album = readStringIntegerPlusOne();
        this.listener.readAlbum(album);

        String songAuthor = readStringIntegerPlusOne();
        this.listener.readSongAuthor(songAuthor);

        String copyright = readStringIntegerPlusOne();
        this.listener.readCopyright(copyright);

        String pieceAuthor = readStringIntegerPlusOne();
        this.listener.readPieceAuthor(pieceAuthor);

        String instructions = readStringIntegerPlusOne();
        this.listener.readInstruction(instructions);

        int numberOfNotice = readInt();
        this.listener.readNumberOfNotices(numberOfNotice);

        for (int i = 0; i < numberOfNotice; i++) {
            String notice = readStringIntegerPlusOne();
            this.listener.readNotice(notice);
        }

        boolean tripletFeel = readBoolean();
        this.listener.readIsTripleFeel(tripletFeel);

    }

    /**
     * @see http://dguitar.sourceforge.net/GP4format.html#Lyrics
     * @throws java.io.IOException
     */
    private void readLyrics() throws IOException {

        //----lyrics----
        int lyricTrack = readInt();
        //Lyric lyric = new Lyric(readInt(),readStringInteger());
        readInt();
        readStringInteger();
        for (int i = 0; i < 4; i++) {
            int measureNumber = readInt();
            String lines = readStringInteger();
        }
    }

    /**
     * @see http://dguitar.sourceforge.net/GP4format.html#Measures-Tracks_pairs
     * @throws java.io.IOException
     */
    private void readMeasuresTracksPair() throws IOException {
    for (int mesure = 0; mesure < numberOfMeasures; mesure++) {
            for (int track = 0; track < numberOfTracks; track++) {
                readMeasureComponents(track, mesure);
            }
        }
    }

    /**
     * @see http://dguitar.sourceforge.net/GP4format.html#Other_information_about_the_piece
     * @throws java.io.IOException
     */
    private void readOtherInformations() throws IOException {

        int tempoValue = readInt();
        this.listener.readTempoValue(tempoValue);

        int key = readByte();
        this.listener.readKey(key);

        int octave = readInt();
        this.listener.readOctave(octave);

        // 4 ports
        // 16 channels
        final int NUMBER_OF_PORTS = 4;
        final int NUMBER_OF_CHANNELS = 16;
        for (int port = 0; port < NUMBER_OF_PORTS; port++) {
            for (int channel = 0; channel < NUMBER_OF_CHANNELS; channel++) {
                int instrument = readInt();
                byte volume = readByte();
                byte balance = readByte();
                byte chorus = readByte();
                byte reverb = readByte();
                byte phaser = readByte();
                byte tremolo = readByte();
                byte blank1 = readByte(); // Backward compatibility with version 3.0
                byte blank2 = readByte(); // Backward compatibility with version 3.0
                listener.readMidiChannel(port, channel, instrument, volume, balance, chorus, reverb, phaser, tremolo);
            }
        }

        numberOfMeasures = readInt();
        this.listener.readNumberOfMesures(numberOfMeasures);

        numberOfTracks = readInt();
        this.listener.readNumberOfTracks(numberOfTracks);
    }

    private void readMeasures() throws IOException {

        if (numberOfMeasures <= 0) {
            return;
        }

        for (int measureIndex = 0; measureIndex < numberOfMeasures; measureIndex++) {
            readMeasureHeader(measureIndex);
        }

    }

    /**
     * @see http://dguitar.sourceforge.net/GP4format.html#Measures
     * @param number
     * @throws java.io.IOException
     */
    private void readMeasureHeader(int measureIndex) throws IOException {
        int header = readUnsignedByte();

        boolean repeatStart = ((header & BITMASK_3) != 0);
        boolean doubleBar = ((header & BITMASK_8) != 0);
        
        int numerator = 0;
        if ((header & BITMASK_1) != 0) {
            numerator = readByte();
        }

        int denominator = 0;
        if ((header & BITMASK_2) != 0) {
            denominator = readByte();
        }


        int numberOfRepeats = 0;
        if ((header & BITMASK_4) != 0) {
            numberOfRepeats = readByte();
        }

        int numberOfAlternateEnding = 0;
        if ((header & BITMASK_5) != 0) {
            numberOfAlternateEnding = readByte();
        }

        
        if ((header & BITMASK_6) != 0) {
            String name = readStringIntegerPlusOne();

            int r = readUnsignedByte();
            int g = readUnsignedByte();
            int b = readUnsignedByte();
            int white = readUnsignedByte(); // always 0x00

            this.listener.readMesureMarker(measureIndex, name, r, g, b);

        }

        if ((header & BITMASK_7) != 0) {
            int tonality = readByte();
            readByte(); // WTF ?
            this.listener.readMeasureTonality(tonality);
        }
        
        this.listener.readMeasureHeader(measureIndex, numerator, denominator, repeatStart, doubleBar, numberOfAlternateEnding, numberOfRepeats);
    }

    /**
     * @see http://dguitar.sourceforge.net/GP4format.html#Tracks
     * @param trackIndex
     * @throws java.io.IOException
     */
    private void readTrack(int trackIndex) throws IOException {
        int header = readUnsignedByte();

        boolean isDrumsTrack = ((header & BITMASK_1) != 0);
        boolean is12StringedGuitarTrack = ((header & BITMASK_2) != 0);
        boolean isBanjoTrack = ((header & BITMASK_3) != 0);

        String name = readStringByte(40);

        int numberOfStrings = readInt();
        this.listener.readTrackParameter(trackIndex, name, numberOfStrings, isDrumsTrack, is12StringedGuitarTrack, isBanjoTrack);

        for (int stringIndex = 0; stringIndex < GP4_NUMBER_OF_STRING; stringIndex++) {
            int tunning = readInt();
            // WTF ?? 
            //if (numberOfStrings > stringIndex) {
                this.listener.readStringTunning(trackIndex, stringIndex, tunning);
            //}
        }

        int midiPort = readInt();

        int midiChannel = readInt();

        int midiChannelEffect = readInt();

        int numberOfFrets = readInt();

        int capo = readInt();

        int r = readUnsignedByte();
        int g = readUnsignedByte();
        int b = readUnsignedByte();
        int white = readUnsignedByte();

        this.listener.readTrackMidiParameter(trackIndex, midiPort, midiChannel, midiChannelEffect, numberOfFrets, capo, r, g, b);
    }

    /*
    private SongChannel parseChannel(List channels, int channelIndex,int effectChannel) {
        SongChannel channel = (SongChannel) channels.get(channelIndex - 1);
     
        int instrument = channel.getInstrument();
        if (instrument == -1) {
            channel.setInstrument((short)0);
        }
        if(!channel.isPercusionChannel()){
            channel.setEffectChannel((short)(effectChannel - 1));
        }
     
        return channel;
    }
     */
    private void readMeasureComponents(int track, int mesure) throws IOException {
        int numberOfBeats = readInt();
        this.listener.readNumberOfBeats(track, mesure, numberOfBeats);
        for (int beat = 0; beat < numberOfBeats; beat++) {
            readNotes(track, mesure, beat);
        }

    }

    /**
     * @see http://dguitar.sourceforge.net/GP4format.html#A_beat
     * @param track
     * @param mesure
     * @param beat
     * @throws java.io.IOException
     */
    private void readNotes(int track, int mesure, int beat) throws IOException {
    
        int header = readUnsignedByte();
        boolean dottedNotes = ((header & BITMASK_1) != 0);

        if ((header & BITMASK_7) != 0) {
            int beatStatus = readUnsignedByte();
            boolean emptyBeat = (beatStatus == 0x00);
            boolean restBeat = (beatStatus == BITMASK_2);
            this.listener.readEmptyBeat(track, mesure, beat, emptyBeat, restBeat);
        }

        int beatDuration = readByte();
        if ((header & BITMASK_6) != 0) {
            int tuplet = readInt();
            this.listener.readTuplet(track, mesure, beat, tuplet);
        }
        
        this.listener.readBeat(track, mesure, beat, beatDuration, dottedNotes);

        if ((header & BITMASK_2) != 0) {
            readChordDiagram(track, mesure, beat);
        }

        if ((header & BITMASK_3) != 0) {
            String text = readStringIntegerPlusOne();
        }

        if ((header & BITMASK_4) != 0) {
            readBeatEffects();
        }

        if ((header & BITMASK_5) != 0) {
            readMixChange();
        }

        int stringsPlayed = readUnsignedByte();

        this.listener.readStringPlayed(track, mesure, beat, stringsPlayed);
        int numberOfStringPlayed = nbPlayerString(stringsPlayed);
        for (int index = numberOfStringPlayed - 1; index >= 0; index--) {
            // It can be a better idea to give the absolute index of the string
            // ie: 1 = E ; A = 2 ; D = 3
            // than relative index
            // ie: 0 = first string, 1, second string in the chord
            int stringIndex = getStringIndexFromStringCode(stringsPlayed, index);
            parseNote(track, mesure, beat, stringIndex);
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
        for ( int i = 0; i < GP4_NUMBER_OF_STRING ; i++) {
            if ( (stringCode & (1 << i)) != 0) {
                if (tmpIndex == 0) {
                    return i;
                } else {
                    tmpIndex--;
                }
            }
        }
        return -1;
    }

    private void parseNote(int track, int mesure, int beat, int stringPlayer) throws IOException {
        int header = readUnsignedByte();
        
        boolean accentuated = ((header & BITMASK_7) != 0);
        boolean ghostNote = ((header & BITMASK_3) != 0);
        boolean dotted = ((header & BITMASK_2) != 0);


        boolean tiedNote = false;
        boolean deadNote = false;
        if ((header & BITMASK_6) != 0) {
            int noteType = readUnsignedByte();
            tiedNote = (noteType == BITMASK_2);
        //    effect.setDeadNote((noteType == 0x03));
        }

        /**
        * -2: Whole Note;
 	* -1:	Half note;
 	*  0:	Quarter note;
 	*  1:	Eighth note;
 	*  2:	Sixteenth note;
 	*  3:	Thirty-second note;
        */
        byte duration = 0;
        if ((header & BITMASK_1) != 0) {
            duration = readByte();
            byte tuplet = readByte();
        }
        
        // int velocity = VelocityValues.DEFAULT;
        if ((header & BITMASK_5) != 0) {
            readByte();
        //velocity = (VelocityValues.MIN_VELOCITY + (VelocityValues.VELOCITY_INCREMENT * readByte())) - VelocityValues.VELOCITY_INCREMENT;
        }

        int fretIndex = 0;
        if ((header & BITMASK_6) != 0) {
            fretIndex = readUnsignedByte();
        }

        if ((header & BITMASK_8) != 0) {
            byte fingeringLeftHand = readByte();
            byte fingeringRightHand = readByte();
        }

        if ((header & BITMASK_4) != 0) {
            readNoteEffects();
        }

        if (fretIndex >= 0 || tiedNote) {
            if (tiedNote) {
            // value = getTiedNoteValue(string.getNumber(), currMeasureNotes, currTrackMeasures);
            }

        // return new Note(value, start, (Duration) currDuration.clone(), velocity, string.getNumber(),tiedNote,effect);
        }
        this.listener.readNote(track, mesure, beat, stringPlayer, fretIndex, duration);
        this.listener.readNoteParameter(track, mesure, beat, accentuated, ghostNote, dotted);
    // return null;
    }

    private void readNoteEffects() throws IOException {
        int header1;
        int header2;
        int b;

        header1 = readUnsignedByte();
        header2 = readUnsignedByte();

        if ((header1 & BITMASK_1) != 0) {
            readBend();
        }

        if ((header1 & BITMASK_5) != 0) {
            readGraceNote();
        }

        if ((header2 & BITMASK_3) != 0) {
            int duration = readUnsignedByte();
        }

        if ((header2 & BITMASK_4) != 0) {
            // noteEffect.setSlide(true);
            readByte();
        }

        if ((header2 & BITMASK_5) != 0) {
            b = readByte();
        }

        //trill
        if ((header2 & BITMASK_6) != 0) {
            byte fret = readByte();
            byte period = readByte();
        }

        if ((header1 & BITMASK_4) != 0) {
        }

    //hammer
        // noteEffect.setHammer(((header1 & 0x02) != 0));

    //vibrato
        // noteEffect.setVibrato(((header2 & 0x40) != 0) || noteEffect.isVibrato());

    //palm-mute
        // noteEffect.setPalmMute(((header2 & 0x02) != 0));

    //staccato
        // noteEffect.setStaccato(((header2 & 0x01) != 0));

    }

    private void readGraceNote() throws IOException {
        int fret = readUnsignedByte();
        int velocity = readUnsignedByte();
        int transition = readUnsignedByte();
        int duration = readUnsignedByte();

        //dead
        boolean dead = (fret == 255);

        //fret
        fret = ((!dead) ? fret : 0);

    //velocity
        // velocity = (VelocityValues.MIN_VELOCITY + (VelocityValues.VELOCITY_INCREMENT * velocity)) - VelocityValues.VELOCITY_INCREMENT;

    //transition
        /*
        if(transition == 0){
                transition = GraceEffect.TRANSITION_NONE;
        }
        else if(transition == 1){
                transition = GraceEffect.TRANSITION_SLIDE;
        }
        else if(transition == 2){
                transition = GraceEffect.TRANSITION_BEND;
        }
        else if(transition == 3){
                transition = GraceEffect.TRANSITION_HAMMER;
        }
         */
    // effect.setGrace(new GraceEffect(fret,duration,velocity,transition,false,dead));
    }

    private void readBend() throws IOException {
        byte type = readByte();
        int value = readInt();

        int numPoints = readInt();
        for (int i = 0; i < numPoints; i++) {
            int bendPosition = readInt();
            int bendValue = readInt();
            byte bendVibrato = readByte();

        }

    }

    private void readMixChange() throws IOException {
        int pos[] = new int[8];
        int i;
        int n;
        int aux;
        n = 0;
        for (i = 0; i < GP4_NUMBER_OF_STRING; i++) {
            aux = readByte();
            if ((i != 0) && (aux != -1)) {
                pos[n] = i;
                n++;
            }
        }
        aux = readInt();
        if (aux != -1) {
            // tempo.setValue(aux);
            pos[n] = i;
            n++;
        }

        for (i = 0; i < n; i++) {
            aux = readByte();
        }
        int applyToAllTracks = readUnsignedByte();
    }

    private void readTracks() throws IOException {

        for (int trackIndex = 0; trackIndex < numberOfTracks; trackIndex++) {
            readTrack(trackIndex);
        }
    }

    private void readTremoloBar() throws IOException {
        byte type = readByte();
        int value = readInt();

        // TremoloBarEffect tremoloBar = new TremoloBarEffect();
        int numPoints = readInt();
        for (int i = 0; i < numPoints; i++) {
            int bendPosition = readInt();
            int bendValue = readInt();
            byte bendVibrato = readByte();

        // tremoloBar.addPoint((int)(bendPosition * TremoloBarEffect.MAX_POSITION_LENGTH / GP_BEND_POSITION),(bendValue * TremoloBarEffect.SEMITONE_LENGTH / GP_BEND_SEMITONE));
        }

    }

    private void readBeatEffects() throws IOException {
        int header[] = {0, 0};

        header[0] = readUnsignedByte();
        header[1] = readUnsignedByte();

        // noteEffect.setFadeIn(((header[0] & 0x10) != 0));
        //vnoteEffect.setVibrato(((header[0]  & 0x02) != 0));

        if ((header[0] & BITMASK_6) != 0) {
            int effect = readUnsignedByte();
        // noteEffect.setTapping(effect == 1);
            // noteEffect.setSlapping(effect == 2);
            // noteEffect.setPopping(effect == 3);
        }

        if ((header[1] & BITMASK_3) != 0) {
            readTremoloBar();
        }

        if ((header[0] & BITMASK_7) != 0) {
            //Upstroke - Downstroke
            readByte();
            readByte();
        }

        if ((header[1] & BITMASK_1) != 0) {
        //Rasgueado
        }

        if ((header[1] & BITMASK_2) != 0) {
            //Pickstroke
            readByte();
        }


    }

    private void readChordDiagram(int track, int measure, int beat) throws IOException {
        int header = readUnsignedByte();

        if ((header & BITMASK_1) == 0) {
            // old chord diagram (deprecated)
            String name = readStringIntegerPlusOne();
            int base = readInt();

            if (base != 0) {
                int fret0 = readInt();
                int fret1 = readInt();
                int fret2 = readInt();
                int fret3 = readInt();
                int fret4 = readInt();
                int fret5 = readInt();
            //    this.listener.readChordDiagram(track, mesure, beat, name, base, fret1, fret2, fret3, fret4, fret5);
            }
        } else {

            boolean sharp = readBoolean();

            this.is.skip(3);

            readByte(); // readRoot();

            readUnsignedByte(); // chord type

            int nineElevenThirteen = readUnsignedByte();

            int bass = readInt();

            readInt(); // readTonalityType(4);

            int addedNote = readUnsignedByte();

            String name = readStringByte(20);

            this.is.skip(2);

            readUnsignedByte(); //readTonalityType(1);

            readUnsignedByte(); // readTonalityType(1);

            readUnsignedByte(); // readTonalityType(1);

            int baseFret = readInt();

            int fret1 = readInt();
            int fret2 = readInt();
            int fret3 = readInt();
            int fret4 = readInt();
            int fret5 = readInt();
            int fret6 = readInt();
            int fret7 = readInt();

            int numBarres = readUnsignedByte();

            for (int i = 1; i <= 5; i++) {
                int fretOfBarre = readUnsignedByte();
            }
            for (int i = 1; i <= 5; i++) {
                int barreStart = readUnsignedByte();
            }
            for (int i = 1; i <= 5; i++) {
                int barreEnd = readUnsignedByte();
            }

            this.is.skip(8);

            for (int i = 1; i <= GP4_NUMBER_OF_STRING; i++) {
                int fingering = readByte();
            }

            boolean chordFingeringDisplayed = readBoolean();
        }
    }

    private String readChordName() throws IOException {
        byte[] nameB;
        char[] nameC;
        int i;
        int max;

        nameB = new byte[21];
        nameC = new char[20];
        this.is.read(nameB, 0, 21);
        max = 20;
        if (nameB[0] < max) {
            max = nameB[0];
        }

        for (i = 1; i <= max; i++) {
            nameC[i - 1] = (char) nameB[i];
        }
        return (String.valueOf(nameC, 0, max));
    }

    private byte readByte() throws IOException {
        return (byte) this.is.read();
    }

    private int readInt() throws IOException {
        int integer = 0;
        byte[] b = {0, 0, 0, 0};

        this.is.read(b);
        integer = ((b[3] & 0xff) << 24) | ((b[BITMASK_2] & 0xff) << BITMASK_5) | ((b[BITMASK_1] & 0xff) << BITMASK_4) | (b[0] & 0xff);

        return integer;
    }

    private boolean readBoolean() throws IOException {
        return (this.is.read() == BITMASK_1);
    }

    private String readStringByte(int expectedLength) throws IOException {
        byte[] bytes;
        int realLength = readUnsignedByte();

        if (expectedLength != 0) {
            bytes = new byte[expectedLength];
        } else {
            bytes = new byte[realLength];
        }
        this.is.read(bytes);

        realLength = (realLength >= 0) ? realLength : expectedLength;
        return new String(bytes, 0, realLength);
    }

    private String readStringInteger() throws IOException {
        byte[] b;
        String str;
        int length = readInt();

        b = new byte[length];
        this.is.read(b);

        str = new String(b);
        return str;
    }

    private String readStringIntegerPlusOne() throws IOException {
        byte[] b;
        String str;
        int lengthPlusOne = readInt();
        int length = lengthPlusOne - 1;

        if (length != this.is.read()) {
            throw new IOException();
        }

        b = new byte[length];
        this.is.read(b);

        str = new String(b);
        return str;
    }

    private int readUnsignedByte() throws IOException {
        return this.is.read();
    }

}
