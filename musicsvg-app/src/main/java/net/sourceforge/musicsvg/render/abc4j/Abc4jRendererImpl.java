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
import net.sourceforge.musicsvg.model.NoteHeight;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.render.Renderer;

/**
 *
 * @author Dav
 */
public class Abc4jRendererImpl implements Renderer {

    private NoteDAO noteDAO;
    private Tune tune;
    private JScoreComponent jscore;

    @Inject
    public void injectJScoreComponent(JScoreComponent jscore) {
        this.jscore = jscore;
    }

    @Inject
    public void injectTune(Tune tune) {
        this.tune = tune;
    }

    @Inject
    public void injectNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
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
            NoteHeight height = note.getNoteHeight();
            switch(height) {
                case A:
                    music.add(new abc.notation.Note(abc.notation.Note.A));
                break;
                case B:
                    music.add(new abc.notation.Note(abc.notation.Note.B));
                break;
                case C:
                    music.add(new abc.notation.Note(abc.notation.Note.C));
                break;
                case D:
                    music.add(new abc.notation.Note(abc.notation.Note.D));
                break;
                case E:
                    music.add(new abc.notation.Note(abc.notation.Note.E));
                break;
                case F:
                    music.add(new abc.notation.Note(abc.notation.Note.F));
                break;
                case G:
                    music.add(new abc.notation.Note(abc.notation.Note.G));
                break;
            }
        }
        jscore.setTune(tune);
        jscore.repaint();
    }
}
