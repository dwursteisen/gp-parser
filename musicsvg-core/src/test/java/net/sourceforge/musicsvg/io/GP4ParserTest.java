/*
 * GP4ParserTest.java
 * JUnit based test
 *
 * Created on 11 ao�t 2007, 11:14
 */
package net.sourceforge.musicsvg.io;

import java.io.File;
import net.sourceforge.musicsvg.io.impl.GP4ParserListenerImpl;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.model.factory.impl.NoteTablatureFactoryImpl;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Dav
 */
public class GP4ParserTest {

    public static final String SIMPLE_FILE = "/gp4/test_gp4.gp4";
    public static final String SOLFEGE_FILE = "/gp4/solfege.gp4";
    public static final String BLUES_FILE = "/gp4/rythme_blues.gp4";

    @Test
    public void testReadFileHeaders() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readVersion("FICHIER GUITAR PRO v4.06");
        listener.readTitle("title");
        listener.readSubTitle("subtitle");
        listener.readArtist("artist");
        listener.readAlbum("album");
        listener.readSongAuthor("music");
        listener.readCopyright("copyright");
        // listener.readPieceAuthor(String pieceAuthor);
        listener.readInstruction("instructions");
        listener.readNumberOfNotices(1);
        listener.readNotice("notice");
        listener.readIsTripleFeel(false);
        // listener.readTempoValue(120);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testReadOtherInformations() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readTempoValue(120);
        /**
         *  Bb (bb)= -2
         *  F (b)  = -1
         *  C      = 0
         *  G (#)  = 1  
         */
        listener.readKey(0);
        listener.readOctave(0); // not used by guitar pro
        // listener.readChannel(channel, effectChannel, instrument, volume, balance, chorus, reverb, phaser, tremolo, solo, mute)
        listener.readMidiChannel(0, 0, 25, (byte) 7, (byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
        listener.readMidiChannel(0, 1, 25, (byte) 7, (byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
        listener.readNumberOfMesures(3);
        listener.readNumberOfTracks(1);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testReadMesureHeader() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readMesureMarker(0, "Section 1", 255, 0, 0);
        listener.readMeasureTonality(0);
        listener.readMeasureHeader(0, 4, 4, false, false, 0, 0);
        listener.readMeasureHeader(1, 0, 0, false, false, 0, 0);
        listener.readMeasureHeader(2, 3, 8, false, false, 0, 0);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testReadTrack() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readTrackParameter(0, "Track 1", 6, false, false, false);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }
    
    @Test
    public void testReadStringTunning() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readStringTunning(0, 0, 64);
        listener.readStringTunning(0, 1, 59);
        listener.readStringTunning(0, 2, 55);
        listener.readStringTunning(0, 3, 50);
        listener.readStringTunning(0, 4, 45);
        listener.readStringTunning(0, 5, 40);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testReadTrackMidiParameter() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readTrackMidiParameter(0, 1, 1, 2, 24, 0, 255, 0, 0);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testReadNumberOfBeats() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readNumberOfBeats(0, 0, 4);
        listener.readNumberOfBeats(0, 1, 1); // this beat can be empty
        listener.readNumberOfBeats(0, 2, 1);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testReadEmtpyBeat() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readEmptyBeat(0, 1, 0, true, false);
        listener.readEmptyBeat(0, 2, 0, true, false);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

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
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readStringPlayed(0, 0, 0, 0xC);
        listener.readStringPlayed(0, 0, 1, 0x4);
        listener.readStringPlayed(0, 0, 2, 0x4);
        listener.readStringPlayed(0, 0, 3, 0x4);
        listener.readStringPlayed(0, 1, 0, 0x0); // silence

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testReadNote() throws Exception {
        File file = new File(this.getClass().getResource(SIMPLE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        // control.checkOrder(true);
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);

        // First mesure ( 0  on each string)
        listener.readNote(0, 0, 0, 3, (byte) 5, 0);
        listener.readNote(0, 0, 0, 2, (byte) 3, 0);
        listener.readNote(0, 0, 1, 2, (byte) 5, 0);
        listener.readNote(0, 0, 2, 2, (byte) 7, 0);
        listener.readNote(0, 0, 3, 2, (byte) 8, 0);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testNbStringPlayed() {
        GP4Parser instance = new GP4Parser();

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
        GP4Parser instance = new GP4Parser();

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
        File file = new File(this.getClass().getResource(BLUES_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        // control.checkOrder(true);
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);
        listener.readNumberOfTracks(3);
        listener.readNumberOfMesures(20);
        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        EasyMock.verify(listener);

    }

    @Test
    public void testReadNote_solfege_file() throws Exception {
        File file = new File(this.getClass().getResource(SOLFEGE_FILE).toURI());
        GP4Parser instance = new GP4Parser();

        IMocksControl control = EasyMock.createNiceControl();
        // control.checkOrder(true);
        GP4ParserListener listener = control.createMock(GP4ParserListener.class);

        // First mesure ( 0  on each string)
        listener.readNote(0, 0, 0, 1, (byte) 0, 0);
        listener.readNote(0, 0, 1, 2, (byte) 0, 0);
        listener.readNote(0, 0, 2, 3, (byte) 0, 0);
        listener.readNote(0, 0, 3, 4, (byte) 0, 0);
        listener.readNote(0, 0, 4, 5, (byte) 0, 0);
        listener.readNote(0, 0, 5, 6, (byte) 0, 0);
        listener.readNote(0, 0, 6, 5, (byte) 0, 0);
        listener.readNote(0, 0, 7, 4, (byte) 0, 0);

        // second mesure (1, 2, 3, on each string)
        listener.readNote(0, 1, 0, 1, (byte) 1, 0);
        listener.readNote(0, 1, 1, 2, (byte) 2, 0);
        listener.readNote(0, 1, 2, 3, (byte) 3, 0);
        listener.readNote(0, 1, 3, 4, (byte) 4, 0);
        listener.readNote(0, 1, 4, 5, (byte) 5, 0);
        listener.readNote(0, 1, 5, 6, (byte) 6, 0);
        listener.readNote(0, 1, 6, 5, (byte) 7, 0);
        listener.readNote(0, 1, 7, 4, (byte) 8, 0);

        // third mesure (chord)
        listener.readNote(0, 2, 0, 2, (byte) 2, 0);
        listener.readNote(0, 2, 0, 1, (byte) 0, 0);

        listener.readNote(0, 2, 1, 3, (byte) 2, 0);
        listener.readNote(0, 2, 1, 2, (byte) 0, 0);

        listener.readNote(0, 2, 2, 4, (byte) 2, 0);
        listener.readNote(0, 2, 2, 3, (byte) 0, 0);

        listener.readNote(0, 2, 3, 5, (byte) 2, 0);
        listener.readNote(0, 2, 3, 4, (byte) 0, 0);

        listener.readNote(0, 2, 4, 6, (byte) 2, 0);
        listener.readNote(0, 2, 4, 5, (byte) 0, 0);

        listener.readNote(0, 2, 5, 3, (byte) 5, 0);
        listener.readNote(0, 2, 5, 2, (byte) 5, 0);
        listener.readNote(0, 2, 5, 1, (byte) 3, 0);

        listener.readNote(0, 2, 6, 4, (byte) 5, 0);
        listener.readNote(0, 2, 6, 3, (byte) 5, 0);
        listener.readNote(0, 2, 6, 2, (byte) 3, 0);

        listener.readNote(0, 2, 7, 5, (byte) 5, 0);
        listener.readNote(0, 2, 7, 4, (byte) 5, 0);
        listener.readNote(0, 2, 7, 3, (byte) 3, 0);

        EasyMock.replay(listener);

        instance.injectListener(listener);

        instance.openFile(file);

        control.verify();

    }

    @Test
    public void testFunctionnal() throws Exception {
        File file = new File(this.getClass().getResource(SOLFEGE_FILE).toURI());
        GP4Parser instance = new GP4Parser();
        GP4ParserListenerImpl listener = new GP4ParserListenerImpl();

        listener.injectNoteDAO(EasyMock.createNiceMock(NoteDAO.class));
        listener.injectNoteFactory(new NoteTablatureFactoryImpl());

        instance.injectListener(listener);
        instance.openFile(file);
    }
}
