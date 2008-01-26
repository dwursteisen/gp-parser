/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg;

import net.sourceforge.musicsvg.svg.model.SVGModel;
import net.sourceforge.musicsvg.model.Note;

/**
 *
 * @author Dav
 */
public interface SVGModelFactory {
    public SVGModel createSVGModel(Note note);
}
