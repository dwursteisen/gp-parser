/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io;

import java.io.File;
import java.io.IOException;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

/**
 *
 * @author Dav
 */
public interface Parser {
    
    void openFile(File file) throws IOException;
    void close();
}
