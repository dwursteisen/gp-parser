/*
 * NoteReader.java
 *
 * Created on 10 ao�t 2007, 22:37
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.io;

import java.io.File;
import java.io.IOException;
import net.sourceforge.musicsvg.model.*;

/**
 *
 * @author Dav
 */
public interface NoteReader {
    void openFile(final File openFile);
    Note readNote() throws IOException;
    
}
