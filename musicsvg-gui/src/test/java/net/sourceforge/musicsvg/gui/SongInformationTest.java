/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.gui;

import net.sourceforge.musicsvg.model.Song;
import org.fest.swing.annotation.GUITest;
import org.fest.swing.fixture.FrameFixture;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class SongInformationTest {

    @GUITest
    @Test
    public void testSongInformation() {
        Song song = new Song();
        song.setAlbum("album");
        song.setTitle("title");
        song.setArtist("artist");

        SongInformation frame = new SongInformation();
        frame.setSong(song);

        FrameFixture window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        
        window.label("albumValue").requireText(song.getAlbum());
        window.label("titleValue").requireText(song.getTitle());
        window.label("artistValue").requireText(song.getArtist());
        
        window.cleanUp();

    }
}
