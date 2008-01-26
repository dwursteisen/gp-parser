/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg.impl;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import net.sourceforge.musicsvg.svg.SVGWidget;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.easymock.EasyMock;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Dav
 */
public class SVGDocumentTest {
    final static String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    
    @Test
    public void testCreateElement() {
    
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        Document d = impl.createDocument(svgNS, "svg", null);
        
        SVGDocumentImpl doc = new SVGDocumentImpl();
        doc.injectDocument(d);
        
        Element elt = doc.createElement("g",5);
        
        Assert.assertEquals("g", elt.getTagName());
        Assert.assertEquals("5", elt.getAttribute(SVGDocumentImpl.ATTRIBUTE_ID));
        
        elt = doc.createElement((String)null, null);
        Assert.assertNull(elt);
        
        elt = doc.createElement("g", null);
        Assert.assertNotNull(elt);
        Assert.assertEquals("", elt.getAttribute(SVGDocumentImpl.ATTRIBUTE_ID));
        
    }
    
    @Test
    public void testCreateElementFromSVGWidget() {
    
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        Document d = impl.createDocument(svgNS, "svg", null);
        
        SVGDocumentImpl doc = new SVGDocumentImpl();
        doc.injectDocument(d);
        
        Element elt = doc.createElement(SVGWidget.ROOT,5);
        
        Assert.assertEquals("g", elt.getTagName());
        Assert.assertEquals("5", elt.getAttribute(SVGDocumentImpl.ATTRIBUTE_ID));
        Assert.assertEquals("", elt.getAttribute(SVGDocumentImpl.ATTRIBUTE_HREF));
        
        elt = doc.createElement((SVGWidget)null, null);
        Assert.assertNull(elt);
        
        elt = doc.createElement(SVGWidget.ROOT, null);
        Assert.assertNotNull(elt);
        Assert.assertEquals("", elt.getAttribute(SVGDocumentImpl.ATTRIBUTE_ID));
        
        elt = doc.createElement(SVGWidget.ANCHOR, null);
        Assert.assertNotNull(elt);
        Assert.assertEquals(SVGWidget.ANCHOR.getType(), elt.getTagName());
        final String attributeNS = elt.getAttributeNS(SVGDocumentImpl.XLINK_NAMESPACE, SVGDocumentImpl.ATTRIBUTE_HREF);
        Assert.assertEquals("#"+SVGWidget.ANCHOR.getWidgetName(),attributeNS);
        
    }
    
    @Test
    public void testImportNode() {
        Element mockElement = EasyMock.createMock(Element.class);
        
        String widgetName = SVGWidget.BEMOL.getWidgetName();
        Document source = EasyMock.createStrictMock(Document.class);
        EasyMock.expect(source.getElementById(widgetName)).andStubReturn(mockElement);
        EasyMock.replay(source);
        
        Node importedNode = EasyMock.createMock(Node.class);
        
        Element rootElement = EasyMock.createMock(Element.class);
        EasyMock.expect(rootElement.appendChild(importedNode)).andStubReturn(null);
        EasyMock.replay(rootElement);
        
        Document target = EasyMock.createStrictMock(Document.class);
        EasyMock.expect(target.importNode(mockElement, true)).andStubReturn(importedNode);
        EasyMock.expect(target.getDocumentElement()).andStubReturn(rootElement);
        EasyMock.replay(target);
        
        
        SVGDocumentImpl doc = new SVGDocumentImpl();
        doc.injectDocument(target);
        doc.injectSourceDocument(source);
        
        doc.importElement(SVGWidget.BEMOL.getWidgetName());
        
        
        EasyMock.verify(target);
        EasyMock.verify(source);
        EasyMock.verify(rootElement);
        
    }
    
    @Test
    public void testSave() throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
         
        final File input = new File(this.getClass().getResource("/svg/note.svg").toURI());
        
        
        Document expResult = docBuilder.parse(input);
        SVGDocumentImpl svgDoc = new SVGDocumentImpl();
        svgDoc.injectDocument(expResult);
        
        // TODO: delete the file in the tearDown
        final File output = new File("test_output.xml");
        svgDoc.save(output);
        
	Document result = docBuilder.parse (output);
        output.delete();
        
        Assert.assertEquals(result.getTextContent(), expResult.getTextContent());
        
    }
}
