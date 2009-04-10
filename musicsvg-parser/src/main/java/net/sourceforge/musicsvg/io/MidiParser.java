/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.io;

import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import org.jfugue.Pattern;
import org.jfugue.Player;

/**
 *
 * @author Dav
 */
public class MidiParser implements Parser {

    JFugueParser jfugueParser;

    @Inject
    public void injectJFugueParser(JFugueParser jfugueParser) {
        this.jfugueParser = jfugueParser;
    }
    
    public void openFile(File file) throws IOException {
        Player player = new Player();

        try {
            Pattern pattern = player.loadMidi(file);
//            jfugueParser.readMusicString(pattern.getMusicString());
        } catch (InvalidMidiDataException ex) {
            throw new IOException("Error during the opening of " + file.getName(), ex);
        }
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
