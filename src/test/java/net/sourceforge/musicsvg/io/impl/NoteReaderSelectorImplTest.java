/*
 * NoteReaderSelectorImplTest.java
 *
 * Created on 22 oct. 2007, 01:00:18
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.io.impl;


import net.sourceforge.musicsvg.io.NoteReader;
import net.sourceforge.musicsvg.io.NoteReaderGP4File;
import net.sourceforge.musicsvg.io.NoteReaderTextFile;
import org.easymock.classextension.EasyMock;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Dav
 */
public class NoteReaderSelectorImplTest {
    @Test
    public void testGetNoteReader() {
        NoteReaderSelectorImpl selector = new NoteReaderSelectorImpl();

        NoteReader result;

        String filename = null;
        NoteReaderTextFile readerTextFile = EasyMock.createMock(NoteReaderTextFile.class);
        selector.setNoteReaderTextFile(readerTextFile);
        filename = "file.txt";
        result = selector.getNoteReader(filename);
        Assert.assertSame(readerTextFile, result);

        NoteReaderGP4File readerGp4File = EasyMock.createMock(NoteReaderGP4File.class);
        selector.setNoteReaderGP4File(readerGp4File);
        filename = "file.gp4";
        result = selector.getNoteReader(filename);
        Assert.assertSame(readerGp4File, result);

        filename = "tefdgdfvd.ouvdfvd";
        result = selector.getNoteReader(filename);
        Assert.assertSame(null, result);
    // filename = "file.gp3";
        // filename = "file.gtp";
        // filename = "file.ptb";
        // filename = "file.gp5";
    }
}