/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Dav
 */
public interface Parser {
    
    void openFile(File file) throws IOException;
    void close();
}
