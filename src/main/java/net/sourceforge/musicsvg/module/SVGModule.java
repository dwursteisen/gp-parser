/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JComponent;
import net.sourceforge.musicsvg.annotations.MainCanvas;
import net.sourceforge.musicsvg.annotations.SVGSource;
import net.sourceforge.musicsvg.svg.SVGDocument;
import net.sourceforge.musicsvg.svg.SVGModelFactory;
import net.sourceforge.musicsvg.render.Renderer;
import net.sourceforge.musicsvg.svg.SVGTransform;
import net.sourceforge.musicsvg.svg.impl.SVGDocumentImpl;
import net.sourceforge.musicsvg.svg.impl.SVGModelFactoryImpl;
import net.sourceforge.musicsvg.render.svg.SVGRendererImpl;
import net.sourceforge.musicsvg.svg.impl.SVGTransformImpl;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 *
 * @author Dav
 */
public class SVGModule implements Module {

    @Override
    public void configure(Binder binder) {
        // SVG Rendering
        binder.bind(SVGTransform.class).to(SVGTransformImpl.class);

        binder.bind(SVGDocument.class).to(SVGDocumentImpl.class);
        binder.bind(Renderer.class).to(SVGRendererImpl.class);
        binder.bind(SVGModelFactory.class).to(SVGModelFactoryImpl.class);

        DOMImplementation domImpl =
                GenericDOMImplementation.getDOMImplementation();
        String svgNamespaceURI = "http://www.w3.org/2000/svg";
        Document doc = domImpl.createDocument(svgNamespaceURI, "svg", null);

        binder.bind(Document.class).toInstance(doc);
        JSVGCanvas svgCanvas = new JSVGCanvas();
        // svgCanvas.setDocument(doc);
        binder.bind(JComponent.class).annotatedWith(MainCanvas.class).toInstance(svgCanvas);

        try {
            String parserBatik = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parserBatik);
            URL scoreURL = getClass().getResource("/note.svg");
            String uri = scoreURL.toURI().toString();
            Document source = f.createDocument(uri);
            binder.bind(Document.class).annotatedWith(SVGSource.class).toInstance(source);
        } catch (URISyntaxException ex) {
            System.err.println("shit happens about the URL -> URI");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("shit happens");
            ex.printStackTrace();
        }

    }
}
