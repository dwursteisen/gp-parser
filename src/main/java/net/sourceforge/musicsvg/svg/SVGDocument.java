/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Element;

/**
 *
 * @author Dav
 */
public interface SVGDocument {

    public Element createElement(SVGWidget svgWidget, Integer id);
    public Element createElement(String type,Integer id);
    public void addElement(Element elt);
    public void importElement(String widgetName);
    public void save(File file) throws IOException;
    
}
