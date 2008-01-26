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

import net.sourceforge.musicsvg.gui.mainframe.MainFrame;
import net.sourceforge.musicsvg.io.GP4Parser;
import net.sourceforge.musicsvg.midi.impl.MidiControllerImpl;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.render.Renderer;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;

/**
 *
 * @author Dav
 */
public class MainController {

    private FileChooser fileChooser;
    private Renderer renderer;
    private MidiControllerImpl midi = null;
    private MainFrame frame;
    private MusicSVGLogger log;
    private NoteDAO noteDAO;
    private GP4Parser parser;

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
    public void injectNoteDAO(NoteDAO dao) {
        this.noteDAO = dao;
    }

    @Inject
    public void injectSVGRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    @Inject
    public void injectFileChooser(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public boolean openFile(final String file) {
        return openFile(new File(file));
    }

    public boolean openFile(final File file) {
        boolean result = false;
        try {
            // String filepath = file.getName();

            parser.openFile(file);
            renderer.init();
            renderer.render();
            /*
            NoteReader reader = readerSelector(filepath);
            reader.openFile(file);
            score.clear();
            Note n = null;
            while((n = reader.readNote()) != null) {
            score.addNote(n);
            midi.addNote(n);
            }
            score.validate();
            score.saveToFile("score_output.svg");
            midi.saveToFile("midi_output.midi");
             */

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
        frame.setVisible(true);
    }

    public void playFile() {
        midi.play();
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

    void stopFile() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void pauseFile() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
