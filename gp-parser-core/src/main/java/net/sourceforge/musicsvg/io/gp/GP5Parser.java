/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io.gp;

import com.google.inject.Inject;
import net.sourceforge.musicsvg.io.Parser;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Dav
 */
public class GP5Parser implements Parser {

    protected FileInputStream is;
    protected GP4ParserListener listener;

    public void openFile(File file) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Inject
    public void setListener(GP4ParserListener listener) {
        this.listener = listener;
    }
}
