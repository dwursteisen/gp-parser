/*
 * NoteReaderGP4FileTest.java
 * JUnit based test
 *
 * Created on 10 ao�t 2007, 23:20
 */

package net.sourceforge.musicsvg.io;

import java.net.URISyntaxException;
import junit.framework.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import net.sourceforge.musicsvg.model.Note;

/**
 *
 * @author Dav
 */
public class NoteReaderGP4FileTest extends TestCase {
    
    public NoteReaderGP4FileTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public void testStub() {
        
    }
    /**
     * Test of openFile method, of class reader.NoteReaderGP4File.
     */
    /*
    public void testOpenFile() throws URISyntaxException {
        System.out.println("openFile");
        
        File openFile = new File(this.getClass().getResource("/resources/test_gp4.gp4").toURI());
        NoteReaderGP4File instance = new NoteReaderGP4File();
        
        instance.openFile(openFile);
        

    }
*/
    /**
     * Test of readNote method, of class reader.NoteReaderGP4File.
     */
    /*
    public void testReadNote() throws Exception {
        System.out.println("readNote");
        
        NoteReaderGP4File instance = new NoteReaderGP4File();
        
        Note expResult = null;
        Note result = instance.readNote();
        assertEquals(expResult, result);
        

    }
    */
}
