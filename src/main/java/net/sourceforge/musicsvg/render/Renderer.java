/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.render;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Dav
 */
public interface Renderer {

    public void exportFile(File file) throws IOException;
    public void init();
    public void render();
}
