/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.svg.impl;

import com.google.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import net.sourceforge.musicsvg.annotations.SVGSource;
import net.sourceforge.musicsvg.svg.SVGDocument;
import net.sourceforge.musicsvg.svg.SVGWidget;
import org.apache.batik.dom.util.DOMUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Dav
 */
public class SVGDocumentImpl implements SVGDocument {

    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_HREF = "href";
    public static final String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
    public static final String XLINK_NAMESPACE = "http://www.w3.org/1999/xlink";
    private Document doc;
    private Document sourceDocument;

    @Inject
    void injectSourceDocument(
            
            @SVGSource Document source) {
        sourceDocument = source;
    }

    @Inject
    public void injectDocument(Document doc) {
        this.doc = doc;
    }

    @Override
    public Element createElement(String type, Integer id) {
        if (type == null) {
            return null;
        }

        Element elt = doc.createElementNS(SVG_NAMESPACE, type);

        if (id != null) {
            elt.setAttribute(ATTRIBUTE_ID, id.toString());
        }
        return elt;
    }

    @Override
    public void addElement(Element elt) {
        doc.getDocumentElement().appendChild(elt);
    }

    @Override
    public Element createElement(SVGWidget svgWidget, Integer id) {
        if (svgWidget == null) {
            return null;
        }
        Element elt = createElement(svgWidget.getType(), id);
        elt.setAttributeNS(XLINK_NAMESPACE, ATTRIBUTE_HREF, "#" + svgWidget.getWidgetName());
        return elt;
    }

    @Override
    public void importElement(String widgetName) {
        Element elt = sourceDocument.getElementById(widgetName);
        Node importedNode = doc.importNode(elt, true);
        doc.getDocumentElement().appendChild(importedNode);
    }

    @Override
    public void save(File file) throws IOException {
        // TODO: create a FileWriter and inject FileWriter and PrintWriter ?
        FileWriter fileWrite = new FileWriter(file);
        PrintWriter writer = new PrintWriter(fileWrite);
        DOMUtilities.writeDocument(doc, writer);
        writer.close();
    }
}
