/*
 * NoteReaderSelector.java
 * 
 * Created on 22 oct. 2007, 00:55:11
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.io;

/**
 *
 * @author Dav
 */
public interface NoteReaderSelector {
    NoteReader getNoteReader(final String filename);
}
