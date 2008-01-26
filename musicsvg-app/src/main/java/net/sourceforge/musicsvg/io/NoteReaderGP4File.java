/*
 * NoteReaderGP4File.java
 *
 * Created on 10 ao�t 2007, 22:49
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.io;

import net.sourceforge.musicsvg.io.impl.GP4ParserListenerImpl;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import net.sourceforge.musicsvg.model.Note;

/**
 *
 * @author Dav
 */
public class NoteReaderGP4File implements NoteReader {
    
    Iterator<Note> iterator = null;
    GP4Parser parser = null;
    GP4ParserListenerImpl listener = null;
    // GP4L
    /** Creates a new instance of NoteReaderGP4File */
    public NoteReaderGP4File() {
       
    }

    public GP4ParserListenerImpl getListener() {
        return listener;
    }

    public void setListener(GP4ParserListenerImpl listener) {
        this.listener = listener;
    }

    public GP4Parser getParser() {
        return parser;
    }

    public void setParser(GP4Parser parser) {
        this.parser = parser;
    }
    
    
    @Override
    public void openFile(final File openFile) {
        try {
            parser.openFile(openFile);
            // iterator = listener.getNotes().iterator();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }   
    @Override
    public Note readNote() throws IOException {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }
    
}
