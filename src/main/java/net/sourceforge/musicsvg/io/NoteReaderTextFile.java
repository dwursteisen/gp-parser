/*
 * NoteReaderTextFile.java
 *
 * Created on 26 mai 2007, 10:54
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import net.sourceforge.musicsvg.model.*;

/**
 *
 * @author Dav
 */
public class NoteReaderTextFile implements NoteReader {
    
    private BufferedReader reader = null;
    public NoteReaderTextFile() {     }
    
    @Override
    public Note readNote() throws IOException {
        String line;
        
        line = this.reader.readLine();
        // end of file
        if (line == null) {
            this.reader.close();
            return null;
        }
        String fields[] = line.split(" ");
        int height = Integer.valueOf(fields[0]);
        int duration = Integer.valueOf(fields[1]);
        Note n = new Note();
        
        NoteHeight[] translationHeigh = {
            NoteHeight.A,
            NoteHeight.B,
            NoteHeight.C,
            NoteHeight.D,
            NoteHeight.E,
            NoteHeight.F,
            NoteHeight.G
        };
        n.setNoteHeight(translationHeigh[height]);
        
        NoteDuration[] translationDuration = {
            NoteDuration.wholeNote,
            NoteDuration.thirtySecondNote,
            NoteDuration.sixtyFourthNote,
            NoteDuration.sixteenthNote,
            NoteDuration.halfNote,
            NoteDuration.eighthNote
        };
        
        n.setNoteDuration(translationDuration[duration-1]);
        return n;
    }
    
    @Override
    public void openFile(final File openFile) {
        FileReader in;
        try {
            in = new FileReader(openFile);
            reader = new BufferedReader(in);
            String line = null;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
