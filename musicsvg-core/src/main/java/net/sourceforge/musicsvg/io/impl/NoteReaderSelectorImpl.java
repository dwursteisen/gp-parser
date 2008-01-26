/*
 * NoteReaderSelectorImpl.java
 *
 * Created on 22 oct. 2007, 00:58:47
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io.impl;

import com.google.inject.Inject;
import net.sourceforge.musicsvg.io.NoteReader;
import net.sourceforge.musicsvg.io.NoteReaderGP4File;
import net.sourceforge.musicsvg.io.NoteReaderSelector;
import net.sourceforge.musicsvg.io.NoteReaderTextFile;

/**
 *
 * @author Dav
 */
public class NoteReaderSelectorImpl implements NoteReaderSelector {

    @Inject
    private NoteReaderGP4File noteReaderGP4File;

    @Inject
    private NoteReaderTextFile noteReaderTextFile;

    @Override
    public NoteReader getNoteReader(final String filename) {
        NoteReader reader = null;
        if (filename.endsWith(".txt")) {
            reader = getNoteReaderTextFile();
        } else if (filename.endsWith(".gp4")) {
            reader = getNoteReaderGP4File();
        }
        return reader;
    }

    public NoteReaderGP4File getNoteReaderGP4File() {
        return noteReaderGP4File;
    }

    public void setNoteReaderGP4File(NoteReaderGP4File noteReaderGP4File) {
        this.noteReaderGP4File = noteReaderGP4File;
    }

    public NoteReaderTextFile getNoteReaderTextFile() {
        return noteReaderTextFile;
    }

    public void setNoteReaderTextFile(NoteReaderTextFile noteReaderTextFile) {
        this.noteReaderTextFile = noteReaderTextFile;
    }
}