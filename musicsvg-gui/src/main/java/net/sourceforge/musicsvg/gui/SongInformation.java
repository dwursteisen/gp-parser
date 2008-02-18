/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sourceforge.musicsvg.model.Song;
/**
 *
 * @author Dav
 */
public class SongInformation extends JFrame {
    private static final int DEFAULT_HEIGHT = 350;
    private static final int DEFAULT_WIDTH = 300;
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
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        
        
        album.setName("albumValue");
        title.setName("titleValue");
        artist.setName("artistValue");
        
        JPanel parent = new JPanel(new GridLayout(0,2));
        
        JLabel label;
        
        label= new JLabel();
        label.setText("Album: ");
        parent.add(label);
        parent.add(album);
        
        label = new JLabel();
        label.setText("Title: ");
        parent.add(label);
        parent.add(title);
        
        label = new JLabel();
        label.setText("Artist: ");
        parent.add(label);
        parent.add(artist);
        
        parent.add(new JButton("Close"));
        add(parent);
    }
}
