/*
 * WidgetFactory.java
 *
 * Created on 26 mai 2007, 10:46
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Dav
 */
final public class WidgetFactory {
    
    private final static String filewidget = "/resources/note.svg";
    private Document doc = null;
    public WidgetFactory() {
        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
            String uri;
            
            uri = this.getClass().getResource(filewidget).toURI().toString();
            
            doc = f.createDocument(uri);
        }catch (IOException e) {
            Logger.getLogger("musicSVG").log(Level.SEVERE, "WidgetFactory: " + e.toString());
            
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public Element getElement(String id) {
        return doc.getElementById(id);
    }
}
