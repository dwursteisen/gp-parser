/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import net.sourceforge.musicsvg.model.Song;
/**
 *
 * @author Dav
 */
public class SongInformation extends JFrame {
    private Song song;

    private JLabel album = new JLabel();
    private JLabel title = new JLabel();
    private JLabel artist = new JLabel();
    
    public void setSong(Song song) {
        this.song = song;
        album.setText(song.getAlbum());
        title.setText(song.getTitle());
        artist.setText(song.getArtist());
    }
    
    public SongInformation() {
        album.setName("albumValue");
        title.setName("titleValue");
        artist.setName("artistValue");
        
        add(album);
        add(title);
        add(artist);
    }
}
