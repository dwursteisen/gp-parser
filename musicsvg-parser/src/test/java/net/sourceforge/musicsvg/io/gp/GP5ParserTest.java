/*
 * GP4ParserTest.java
 * JUnit based test
 *
 * Created on 11 ao�t 2007, 11:14
 */
package net.sourceforge.musicsvg.io.gp;

import net.sourceforge.musicsvg.io.*;
import java.io.File;
import net.sourceforge.musicsvg.io.gp.listeners.GP4InfoParserListenerImpl;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.impl.SongDAOMapImpl;
import net.sourceforge.musicsvg.model.factory.impl.SongFactoryImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class GP5ParserTest {

    private GP5Parser instance;
    public static final String GP5_FILE = "/gp5/sample.gp5";


    private File gp5File;

    @BeforeMethod
    public void setUp() throws Exception {
        gp5File = new File(this.getClass().getResource(GP5_FILE).toURI());
        instance = new GP5Parser();
    }

    @AfterMethod
    public void tearDown() {
        instance.close();
    }

    @Test
    public void testFunctionnal() throws Exception {
        GP4InfoParserListenerImpl listener = new GP4InfoParserListenerImpl();
        final SongDAOMapImpl songDAOMapImpl = new SongDAOMapImpl();
        listener.setSongDAO(songDAOMapImpl);
        listener.setHash(new Hasher());
        listener.setSongFactory(new SongFactoryImpl());
        

        instance.setListener(listener);
        instance.openFile(gp5File);
        instance.close();
        
        Song s = songDAOMapImpl.getLastSong();
        Assert.assertEquals("title - GP5", s.getTitle());
        
    }


}
