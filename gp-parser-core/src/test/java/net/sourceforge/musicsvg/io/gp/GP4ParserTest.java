/*
 * GP4ParserTest.java
 * JUnit based test
 *
 * Created on 11 ao�t 2007, 11:14
 */
package net.sourceforge.musicsvg.io.gp;

import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Dav
 */
public class GP4ParserTest {

    private GP4Parser instance;
    public static final String SIMPLE_FILE = "/gp4/test_gp4.gp4";
    public static final String SOLFEGE_FILE = "/gp4/solfege.gp4";
    public static final String BLUES_FILE = "/gp4/rythme_blues.gp4";
    public static final String RYTHME_FILE = "/gp4/rythme.gp4";

    private File simpleFile;
    private File solfegeFile;
    private File bluesFile;
    private File rythmeFile;

    @Mock
    private GP4ParserListener listener;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this); //This could be pulled up into a shared base class

        simpleFile = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        bluesFile = new File(this.getClass().getResource(BLUES_FILE).toURI());
        solfegeFile = new File(this.getClass().getResource(SOLFEGE_FILE).toURI());
        rythmeFile = new File(this.getClass().getResource(RYTHME_FILE).toURI());
        instance = new GP4Parser();
        instance.setListener(listener);
    }

    @AfterMethod
    public void tearDown() {
        instance.close();
    }

    @Test
    public void testReadFileHeaders() throws Exception {
        instance.openFile(simpleFile);

        // verify
        Mockito.verify(listener).readVersion("FICHIER GUITAR PRO v4.06");
        Mockito.verify(listener).readTitle("title");
        Mockito.verify(listener).readSubTitle("subtitle");
        Mockito.verify(listener).readArtist("artist");
        Mockito.verify(listener).readAlbum("album");
        Mockito.verify(listener).readSongAuthor("music");
        Mockito.verify(listener).readCopyright("copyright");
        // listener.readPieceAuthor(String pieceAuthor);
        Mockito.verify(listener).readInstruction("instructions");
        Mockito.verify(listener).readNumberOfNotices(1);
        Mockito.verify(listener).readNotice("notice");
        Mockito.verify(listener).readIsTripleFeel(false);
        // listener.readTempoValue(120);

    }

    @Test
    public void testReadOtherInformations() throws Exception {

        instance.openFile(simpleFile);

        // verify
        Mockito.verify(listener).readTempoValue(120);
        /**
         *  Bb (bb)= -2
         *  F (b)  = -1
         *  C      = 0
         *  G (#)  = 1
         */
        Mockito.verify(listener).readKey(0);
        Mockito.verify(listener).readOctave(0); // not used by guitar pro
        // listener.readChannel(channel, effectChannel, instrument, volume, balance, chorus, reverb, phaser, tremolo, solo, mute)

        Mockito.verify(listener).readMidiChannel(0, 0, 25, (byte) 7, (byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
        Mockito.verify(listener).readMidiChannel(0, 1, 25, (byte) 7, (byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
        Mockito.verify(listener).readNumberOfMesures(3);
        Mockito.verify(listener).readNumberOfTracks(1);

    }

    @Test
    public void testReadMesureHeader() throws Exception {


        instance.openFile(simpleFile);

        Mockito.verify(listener).readMesureMarker(0, "Section 1", 255, 0, 0);
        Mockito.verify(listener).readMeasureTonality(0);
        Mockito.verify(listener).readMeasureHeader(0, 4, 4, false, false, 0, 0);
        Mockito.verify(listener).readMeasureHeader(1, 0, 0, false, false, 0, 0);
        Mockito.verify(listener).readMeasureHeader(2, 3, 8, false, false, 0, 0);

    }

    @Test
    public void testReadTrack() throws Exception {


        instance.openFile(simpleFile);

        Mockito.verify(listener).readTrackParameter(0, "Track 1", 6, false, false, false);

    }

    @Test
    public void testReadStringTunning() throws Exception {

        instance.openFile(simpleFile);
        Mockito.verify(listener).readStringTunning(0, 0, 64);
        Mockito.verify(listener).readStringTunning(0, 1, 59);
        Mockito.verify(listener).readStringTunning(0, 2, 55);
        Mockito.verify(listener).readStringTunning(0, 3, 50);
        Mockito.verify(listener).readStringTunning(0, 4, 45);
        Mockito.verify(listener).readStringTunning(0, 5, 40);

    }

    @Test
    public void testReadTrackMidiParameter() throws Exception {
        instance.openFile(simpleFile);
        Mockito.verify(listener).readTrackMidiParameter(0, 1, 1, 2, 24, 0, 255, 0, 0);
    }

    @Test
    public void testReadNumberOfBeats() throws Exception {
        instance.openFile(simpleFile);
        Mockito.verify(listener).readNumberOfBeats(0, 0, 4);
        Mockito.verify(listener).readNumberOfBeats(0, 1, 1); // this beat can be empty

        Mockito.verify(listener).readNumberOfBeats(0, 2, 1);

    }

    @Test
    public void testReadEmtpyBeat() throws Exception {

        instance.openFile(simpleFile);

        Mockito.verify(listener).readEmptyBeat(0, 1, 0, true, false);
        Mockito.verify(listener).readEmptyBeat(0, 2, 0, true, false);

    }

    @Test
    public void testReadStringsPlayed() throws Exception {
        /**
         *  stringsPlayer : 
         *  field of beat (on the base of 7 strings !)
         *    0000000
         *    eBGDAE     (the first 0 is the bit for the 7th string)
         * if 2 strings is played in the same time, each byte of each strings
         * are set to 1
         *    0001100 for example
         */
        instance.openFile(simpleFile);
        Mockito.verify(listener).readStringPlayed(0, 0, 0, 0xC);
        Mockito.verify(listener).readStringPlayed(0, 0, 1, 0x4);
        Mockito.verify(listener).readStringPlayed(0, 0, 2, 0x4);
        Mockito.verify(listener).readStringPlayed(0, 0, 3, 0x4);
        Mockito.verify(listener).readStringPlayed(0, 1, 0, 0x0); // silence

    }

    @Test
    public void testReadNote() throws Exception {
        instance.openFile(simpleFile);
        // First mesure ( 0  on each string)
        Mockito.verify(listener).readNote(0, 0, 0, 3, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 0, 0, 2, (byte) 3, 0);
        Mockito.verify(listener).readNote(0, 0, 1, 2, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 0, 2, 2, (byte) 7, 0);
        Mockito.verify(listener).readNote(0, 0, 3, 2, (byte) 8, 0);
    }

    @Test
    public void testReadBeat() throws Exception {
        instance.openFile(rythmeFile);
        // First mesure ( 0  on each string)
        Mockito.verify(listener).readBeat(0, 0, 0, -1, false);
        Mockito.verify(listener).readBeat(0, 0, 1, 0, false);
        Mockito.verify(listener).readBeat(0, 0, 2, 1, false);
        Mockito.verify(listener).readBeat(0, 0, 3, 2, false);
        Mockito.verify(listener).readBeat(0, 0, 4, 2, false);
    }


    @Test
    public void testNbStringPlayed() {

        int stringCode = 2;
        int result;
        int expResult = 1;

        result = instance.nbPlayerString(stringCode);
        Assert.assertEquals(result, expResult);

        stringCode = 4;
        result = instance.nbPlayerString(stringCode);
        Assert.assertEquals(result, expResult);

        stringCode = 8;
        result = instance.nbPlayerString(stringCode);
        Assert.assertEquals(result, expResult);

        stringCode = 32;
        result = instance.nbPlayerString(stringCode);
        Assert.assertEquals(result, expResult);

        stringCode = 64;
        result = instance.nbPlayerString(stringCode);
        Assert.assertEquals(result, expResult);

        stringCode = 0xC;
        expResult = 2;
        result = instance.nbPlayerString(stringCode);
        Assert.assertEquals(result, expResult);
    }

    @Test
    public void testGetStringIndexFromStringCode() {
        int stringCode = 0;
        int index = 0;
        int stringIndex = 0;

        stringCode = 2;
        stringIndex = instance.getStringIndexFromStringCode(stringCode, index);
        Assert.assertEquals(stringIndex, 1);

        stringCode = 4;
        stringIndex = instance.getStringIndexFromStringCode(stringCode, index);
        Assert.assertEquals(stringIndex, 2);

        stringCode = 8;
        stringIndex = instance.getStringIndexFromStringCode(stringCode, index);
        Assert.assertEquals(stringIndex, 3);

        stringCode = 64;
        stringIndex = instance.getStringIndexFromStringCode(stringCode, index);
        Assert.assertEquals(stringIndex, 6);

        index = 0;
        stringCode = 0xC;
        stringIndex = instance.getStringIndexFromStringCode(stringCode, index);
        Assert.assertEquals(stringIndex, 2);

        stringCode = 0xC;
        index = 1;
        stringIndex = instance.getStringIndexFromStringCode(stringCode, index);
        Assert.assertEquals(stringIndex, 3);

        // invalid index
        stringIndex = instance.getStringIndexFromStringCode(2, 1);
        Assert.assertEquals(stringIndex, -1);

    }

    @Test
    public void testReadNote_blues_file() throws Exception {
        instance.openFile(bluesFile);

        listener.readNumberOfTracks(3);
        listener.readNumberOfMesures(20);

    }

    @Test
    public void testReadNote_solfege_file() throws Exception {
        instance.openFile(solfegeFile);
        // First mesure ( 0  on each string)
        Mockito.verify(listener).readNote(0, 0, 0, 1, (byte) 0, 0);
        Mockito.verify(listener).readNote(0, 0, 1, 2, (byte) 0, 0);
        Mockito.verify(listener).readNote(0, 0, 2, 3, (byte) 0, 0);
        Mockito.verify(listener).readNote(0, 0, 3, 4, (byte) 0, 0);
        Mockito.verify(listener).readNote(0, 0, 4, 5, (byte) 0, 0);
        Mockito.verify(listener).readNote(0, 0, 5, 6, (byte) 0, 0);
        Mockito.verify(listener).readNote(0, 0, 6, 5, (byte) 0, 0);
        Mockito.verify(listener).readNote(0, 0, 7, 4, (byte) 0, 0);

        // second mesure (1, 2, 3, on each string)
        Mockito.verify(listener).readNote(0, 1, 0, 1, (byte) 1, 0);
        Mockito.verify(listener).readNote(0, 1, 1, 2, (byte) 2, 0);
        Mockito.verify(listener).readNote(0, 1, 2, 3, (byte) 3, 0);
        Mockito.verify(listener).readNote(0, 1, 3, 4, (byte) 4, 0);
        Mockito.verify(listener).readNote(0, 1, 4, 5, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 1, 5, 6, (byte) 6, 0);
        Mockito.verify(listener).readNote(0, 1, 6, 5, (byte) 7, 0);
        Mockito.verify(listener).readNote(0, 1, 7, 4, (byte) 8, 0);

        // third mesure (chord)
        Mockito.verify(listener).readNote(0, 2, 0, 2, (byte) 2, 0);
        Mockito.verify(listener).readNote(0, 2, 0, 1, (byte) 0, 0);

        Mockito.verify(listener).readNote(0, 2, 1, 3, (byte) 2, 0);
        Mockito.verify(listener).readNote(0, 2, 1, 2, (byte) 0, 0);

        Mockito.verify(listener).readNote(0, 2, 2, 4, (byte) 2, 0);
        Mockito.verify(listener).readNote(0, 2, 2, 3, (byte) 0, 0);

        Mockito.verify(listener).readNote(0, 2, 3, 5, (byte) 2, 0);
        Mockito.verify(listener).readNote(0, 2, 3, 4, (byte) 0, 0);

        Mockito.verify(listener).readNote(0, 2, 4, 6, (byte) 2, 0);
        Mockito.verify(listener).readNote(0, 2, 4, 5, (byte) 0, 0);

        Mockito.verify(listener).readNote(0, 2, 5, 3, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 2, 5, 2, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 2, 5, 1, (byte) 3, 0);

        Mockito.verify(listener).readNote(0, 2, 6, 4, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 2, 6, 3, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 2, 6, 2, (byte) 3, 0);

        Mockito.verify(listener).readNote(0, 2, 7, 5, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 2, 7, 4, (byte) 5, 0);
        Mockito.verify(listener).readNote(0, 2, 7, 3, (byte) 3, 0);


    }

}
