/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.io.impl;

import net.sourceforge.musicsvg.model.NoteDuration;
import net.sourceforge.musicsvg.model.NoteHeight;
import net.sourceforge.musicsvg.model.NoteTablature;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import net.sourceforge.musicsvg.model.factory.NoteTablatureFactory;
import net.sourceforge.musicsvg.model.factory.SongFactory;
import org.easymock.classextension.EasyMock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class GP4ParserListenerImplTest {
    private GP4ParserListenerImpl parser;

    @BeforeMethod
    public void setUp() {
        parser = new GP4ParserListenerImpl();
    }
    @Test
    public void testReadNote() {
        NoteTablature noteEString = new NoteTablature();
        NoteTablature noteAString = new NoteTablature();

        NoteTablatureFactory factory = EasyMock.createMock(NoteTablatureFactory.class);
        EasyMock.expect(factory.createNoteTablature()).andReturn(noteAString);
        EasyMock.expect(factory.createNoteTablature()).andReturn(noteEString);
        EasyMock.replay(factory);

        NoteDAO dao = EasyMock.createNiceMock(NoteDAO.class);
        dao.saveOrUpdate(noteEString);
        EasyMock.replay(dao);

        parser.injectNoteFactory(factory);
        parser.injectNoteDAO(dao);

        int ENote = 64;
        int ANote = 57;
        parser.readNumberOfTracks(1);
        parser.readNumberOfMesures(2);
        parser.readTrackParameter(0, "Test Track", 6, false, false, false);
        parser.readStringTunning(0, 0, ANote);
        parser.readStringTunning(0, 1, ENote);
        parser.readNote(0, 0, 0, 0, 9, -2);
        parser.readNote(0, 0, 0, 1, 5, 0);
        EasyMock.verify(dao);
        EasyMock.verify(factory);

        Assert.assertEquals(9, noteAString.getFret());
        Assert.assertEquals(NoteHeight.A, noteAString.getString());
        Assert.assertEquals(NoteDuration.wholeNote, noteAString.getNoteDuration());

        Assert.assertEquals(5, noteEString.getFret());
        Assert.assertEquals(NoteHeight.E, noteEString.getString());
        Assert.assertEquals(NoteDuration.quarterNote, noteEString.getNoteDuration());

    }

    @Test
    public void testReadSongInformation() {
        Song s = new Song();
        SongFactory songFactory = EasyMock.createMock(SongFactory.class);
        songFactory.createSong();
        EasyMock.expectLastCall().andStubReturn(s);
        EasyMock.replay(songFactory);

        parser.injectSongFactory(songFactory);
        parser.injectSongDAO(EasyMock.createMock(SongDAO.class));

        parser.readTitle("title");
        parser.readSubTitle("subtitle");
        parser.readArtist("interpret");
        parser.readAlbum("album");
        parser.readSongAuthor("songAuthor");
        
        Assert.assertEquals(s.getTitle(), "title");
        Assert.assertEquals(s.getSubTitle(), "subtitle");
        Assert.assertEquals(s.getArtist(), "interpret");
        Assert.assertEquals(s.getAlbum(), "album");
        // Assert.assertEquals(s.getSongAuthor(), "interpret");
        
    }
}
