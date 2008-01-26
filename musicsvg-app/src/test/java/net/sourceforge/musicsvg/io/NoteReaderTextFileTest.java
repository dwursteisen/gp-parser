/*
 * NoteReaderTextFileTest.java
 * JUnit based test
 *
 * Created on 26 mai 2007, 20:14
 */

package net.sourceforge.musicsvg.io;

import java.net.URISyntaxException;
import junit.framework.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.musicsvg.model.Note;

/**
 *
 * @author Dav
 */
public class NoteReaderTextFileTest extends TestCase {
    
    public NoteReaderTextFileTest(String testName) {
        super(testName);
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    /*
    public void testReadNote() throws Exception{
        File file = null;
        
        file = new File(this.getClass().getResource("/resources/inputfile.txt").toURI());
        
        NoteReaderTextFile reader = new NoteReaderTextFile();
        reader.openFile(file);
        
        boolean loop = false;
        for ( int i = 0; i < 5 ; i++) {
            Note n = reader.readNote();
            
            assertNotNull(n);
            loop = true;
        }
        assertTrue(loop);
        
        //assertNull(reader.readNote());
    }
     */
    public void testStub() {
        
    }
}
