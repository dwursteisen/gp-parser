/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.io;

import java.io.File;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class MidiParserTest {
    private MidiParser parser;
    private File rythmeFile;

    public static final String RYTHME_FILE = "/midi/rythme.mid";

    @BeforeMethod
    public void setUp() throws Exception {
        rythmeFile = new File(this.getClass().getResource(RYTHME_FILE).toURI());
        parser = new MidiParser();
    }
    
    @Test
    public void testOpenFile() throws Exception {
        parser.openFile(rythmeFile);
    }
}
