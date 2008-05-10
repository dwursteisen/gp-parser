/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.render.abc4j;

import abc.notation.KeySignature;
import abc.notation.Tune;
import abc.notation.Tune.Music;
import abc.ui.swing.JScoreComponent;
import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.render.NoteTranslater;
import net.sourceforge.musicsvg.render.Renderer;

/**
 *
 * @author Dav
 */
public class Abc4jRendererImpl implements Renderer {

    private NoteDAO noteDAO;
    private Tune tune;
    private JScoreComponent jscore;
    private NoteTranslater<abc.notation.Note> translater;
    
    @Inject
    public void injectJScoreComponent(JScoreComponent jscore) {
        this.jscore = jscore;
    }

    @Inject
    public void injectNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Inject
    public void injectNoteTranslater(NoteTranslater noteTranslater) {
        this.translater = noteTranslater;
    }
    
    @Override
    public void exportFile(File file) throws IOException {
        jscore.writeScoreTo(file);
    }

    @Override
    public void init() {
        clear();
    }

    @Override
    public void render() {
        // TODO: tmp fix, for testing
        KeySignature key = new KeySignature(abc.notation.Note.C, KeySignature.MAJOR);

        Music music = tune.getMusic();
        music.addElement(key);
        
        List<Note> notes = noteDAO.findAll();
        for (Note note : notes) {
            abc.notation.Note abcNote = translater.translater(note);
            music.addElement(abcNote);
        }
        jscore.setTune(tune);
        jscore.repaint();
    }

    public void clear() {
        tune = new Tune();
        jscore.setTune(tune);
        jscore.repaint();
    }
}
