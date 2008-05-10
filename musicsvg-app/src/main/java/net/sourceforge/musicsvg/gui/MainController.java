/*
 * MainController.java
 *
 * Created on 3 ao�t 2007, 21:25
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */
package net.sourceforge.musicsvg.gui;

import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;

import java.util.Locale;
import net.sourceforge.musicsvg.gui.mainframe.MainFrame;
import net.sourceforge.musicsvg.io.GP4Parser;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import net.sourceforge.musicsvg.render.Renderer;
import net.sourceforge.musicsvg.utils.I18n;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;

/**
 *
 * @author Dav
 */
public class MainController {
    private SongInformation songInformationFrame;
    private I18n i18n;
    private Song currentSong;
    private NoteDAO noteDAO;

    private FileChooser fileChooser;
    private Renderer renderer;
    private MainFrame frame;
    private MusicSVGLogger log;
    private SongDAO songDAO;
    private GP4Parser parser;

    @Inject
    public void injectI18n(I18n i18n) {
        this.i18n = i18n;
    }
    
    @Inject
    public void injectSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }
    
    @Inject 
    public void injectNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }
    
    @Inject
    public void injectParser(GP4Parser parser) {
        this.parser = parser;
    }

    @Inject
    public void injectLog(MusicSVGLogger log) {
        this.log = log;
    }

    @Inject
    public void injectMainFrame(MainFrame frame) {
        this.frame = frame;
    }

    @Inject
    public void injectSongInformationFrame(SongInformation songInformationFrame) {
        this.songInformationFrame = songInformationFrame;
    }
    
    @Inject
    public void injectSVGRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    @Inject
    public void injectFileChooser(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public void closeFile() {
        songDAO.clear();
        noteDAO.clear();
    }
    
    public boolean openFile(final File file) {
        boolean result = false;
        try {
            closeFile();
            parser.openFile(file);
            
            renderer.init();
            renderer.render();
            
            currentSong = songDAO.getLastSong();
            frame.setTitle(currentSong.getTitle());
            result = true;
        } catch (IOException ex) {
            log.error(getClass(), "Unable to read the file " + file.getName(), ex);
            result = false;
        } finally {
            parser.close();
        }
        return result;
    }

    public void exportFile() {
        File file = fileChooser.saveFile(this.frame);
        if (file != null) {
            try {
                renderer.exportFile(file);
            } catch (IOException ex) {
               log.error(getClass(), "Unable to export the file " + file.getName(), ex);
            }
        }
    }

    public void startApplication() {
        log.info(getClass(), "Starting Application !");
        // HACK HACK HACK
        // TODO: remove hack
        i18n.setCurrentLocale(new Locale("fr")); 
        i18n.loadBundle("locales/Music");
        frame.setLabels();
        frame.setVisible(true);
    }

    public void prompOpenFile() {
        File f = fileChooser.prompt(this.frame);
        if ( f != null) {
            openFile(f);
        }
    }

    public void quitApplication() {
        log.info(getClass(), "Bye Bye");
        System.exit(0);
    }

    public MainFrame getFrame() {
        return frame;
    }

    public void displaySongInformation() {
        log.info(getClass(), "Display song Information");
        songInformationFrame.setSong(currentSong);
        songInformationFrame.setLabels();
        songInformationFrame.setVisible(true);
    }
    
    
}
