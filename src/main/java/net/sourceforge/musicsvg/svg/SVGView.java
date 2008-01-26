/*
 * SVGView.java
 *
 * Created on 21 juillet 2007, 22:35
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.svg;

import net.sourceforge.musicsvg.svg.model.SVGModel;
import net.sourceforge.musicsvg.svg.impl.SVGTransformImpl;
import net.sourceforge.musicsvg.model.WidgetFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.util.XMLConstants;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Dav
 */
public class SVGView {
    
    private final static String[] idWidget = {
        "note",
        "note_blanche",
        "score_bar",
        "ancre", // TODO: rename to anchor
        "ancre2",
        "ancre3",
        "barre",
        "key",
        "link",
        "sharp",
        "bemol",
        "becare"
    };
    
    Document doc = null;
    final static String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    final static WidgetFactory factory = new WidgetFactory();
    
    Element score = null;
    
    public SVGView() {
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        doc = impl.createDocument(svgNS, "svg", null);
        
        Element e = doc.createElementNS(svgNS, "defs");
        e.setAttribute("id", "defs");
        doc.getDocumentElement().appendChild(e);
        
        score = doc.createElementNS(svgNS, "g");
        score.setAttribute("id", "score");
        score.setAttributeNS(null, "transform", "translate(40,50)");
        doc.getDocumentElement().appendChild(score);
        loadWidget();
        
    }
    
    private void loadWidget() {
        for (String id: idWidget) {
            Element elt = factory.getElement(id);
            importElement(elt);
        }
    }
    
    private void importElement(Element elt) {
        elt.setAttributeNS(null, "transform", "translate(0,0)");
        Node imported = doc.importNode(elt, true);
        doc.getElementById("defs").appendChild(imported);
    }
    
    public void refresh(SVGModel model) {
        Element elt = doc.getElementById(model.getId().toString());
        setProperties(elt, model);
    }
    
    public void put(SVGModel model) {
        String type = "use";
        /**
        if ( model.getSVGType() != null ) {
         type = model.getSVGType();
        } 
         */
        Element elt = doc.createElementNS(svgNS,type);
        
        setProperties(elt, model);
        
        String parentId = model.getParentId().toString();
        Element parent = score;
        if (parentId != null) {
            parent = doc.getElementById(parentId);
        }
        parent.appendChild(elt);
        
        for (SVGModel m : model.getChilds()) {
            this.put(m);
        }
    }
    
    private void setProperties(final Element elt, final SVGModel model) throws DOMException {
        if (model.getType() != null) {
            elt.setAttributeNS(XMLConstants.XLINK_NAMESPACE_URI, "href", "#" + model.getType());
            // Inkscape
            elt.setAttribute("xlink:href", "#" + model.getType());
        }
        String id = model.getId().toString();
        if (id != null) {
            elt.setAttributeNS(null, "id", id);
        }
        int x = model.getX();
        int y = model.getY();
        
        SVGTransformImpl t = new SVGTransformImpl();
        t.setTranslation(x, y);
        t.setScale(model.getWidth(), model.getHeight());
        t.setRotation(model.getRotation());
        
        elt.setAttributeNS(null, "transform", t.getSVGTransformation());
        
    }
    
    public void remove(SVGModel model) {
        Element note = doc.getElementById(model.getId().toString());
        Node widget = getWidget(note, model.getType());
        if (widget != null) {
            note.removeChild(widget);
        }
    }
    
    private Node getWidget(final Element parent, final String type) {
        
        final String anchor = "#" + type;
        final NodeList nlist = parent.getChildNodes();
        final int length = nlist.getLength();
        for ( int i = 0 ; i < length ; i++) {
            final Node node = nlist.item(i);
            final NamedNodeMap attr = node.getAttributes();
            final Node nAttr = attr.getNamedItemNS(XMLConstants.XLINK_NAMESPACE_URI, "href");
            if (anchor.equals(nAttr.getNodeValue())) {
                return node;
            }
        }
        return null;
    }
    
    public Document getDocument() {
        return doc;
    }
    
}
