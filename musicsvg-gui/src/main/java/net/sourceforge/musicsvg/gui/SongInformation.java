/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import com.google.inject.Inject;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.utils.I18n;
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
    
    private JLabel albumLabel = new JLabel();
    private JLabel titleLabel = new JLabel();
    private JLabel artistLabel = new JLabel();
    
    private JButton closeButton = new JButton();
    private I18n i18n;
    
    public void setSong(Song song) {
        this.song = song;
        album.setText(song.getAlbum());
        title.setText(song.getTitle());
        artist.setText(song.getArtist());
    }
    
    @Inject
    public void injectI18n(I18n i18n) {
        this.i18n = i18n;
    }
    
    public SongInformation() {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        
        album.setName("albumValue");
        title.setName("titleValue");
        artist.setName("artistValue");
        
        JPanel parent = new JPanel(new GridLayout(0,2));
        parent.add(albumLabel);
        parent.add(album);
        
        parent.add(titleLabel);
        parent.add(title);
        
        parent.add(artistLabel);
        parent.add(artist);
        
        parent.add(closeButton);
        add(parent);
    }
    
    public void setLabels() {
        albumLabel.setText(i18n.getString("songInformation.albumLabel"));
        titleLabel.setText(i18n.getString("songInformation.titleLabel"));
        artistLabel.setText(i18n.getString("songInformation.artistLabel"));
        closeButton.setText(i18n.getString("songInformation.closeButton"));
    }
}
