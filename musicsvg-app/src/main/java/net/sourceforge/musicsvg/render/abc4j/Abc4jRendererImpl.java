/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.render.abc4j;

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

    // TODO: why inject the tune ? 
    @Inject
    public void injectTune(Tune tune) {
        this.tune = tune;
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

    }

    @Override
    public void render() {
        Music music = tune.getMusic();
        List<Note> notes = noteDAO.findAll();
        for ( Note note : notes) {
            abc.notation.Note abcNote = translater.translater(note);
            music.add(abcNote);
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
