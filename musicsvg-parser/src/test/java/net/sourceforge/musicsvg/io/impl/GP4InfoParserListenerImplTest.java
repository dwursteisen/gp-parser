/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io.impl;

import net.sourceforge.musicsvg.io.gp.listeners.GP4InfoParserListenerImpl;
import net.sourceforge.musicsvg.io.Hasher;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import net.sourceforge.musicsvg.model.dao.impl.SongDAOMapImpl;
import net.sourceforge.musicsvg.model.factory.impl.SongFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class GP4InfoParserListenerImplTest {

    private GP4InfoParserListenerImpl parser;
    private SongDAO dao;

    @BeforeMethod
    public void setUp() {
        parser = new GP4InfoParserListenerImpl();
        dao = new SongDAOMapImpl();
        parser.setSongDAO(dao);
        parser.setSongFactory(new SongFactoryImpl());
        parser.setHash(new Hasher());

    }
    
    @Test
    public void testReadArtist() {
        String artist = "artist";
        String album = "album";
        String title = "title";

        parser.open(null);
        parser.readAlbum(album);
        parser.readArtist(artist);
        parser.readTitle(title);
        parser.close();
        
        Song song = dao.getLastSong();
        Assert.assertEquals(album, song.getAlbum());
        Assert.assertEquals(artist, song.getArtist());
        Assert.assertEquals(title, song.getTitle());

    }

}
