/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.svg.impl;

import net.sourceforge.musicsvg.render.svg.SVGRendererImpl;
import java.io.File;
import java.io.IOException;
import net.sourceforge.musicsvg.model.dao.impl.NoteDAOMapImpl;
import net.sourceforge.musicsvg.svg.impl.SVGModelDAOMapImpl;
import net.sourceforge.musicsvg.svg.SVGDocument;
import net.sourceforge.musicsvg.svg.SVGWidget;
import net.sourceforge.musicsvg.svg.model.SVGModel;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.easymock.EasyMock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Element;

/**
 *
 * @author Dav
 */
public class SVGRendererImplTest {

    MusicSVGLogger log;

    @BeforeClass
    public void setUpClass() {
        log = EasyMock.createMock(MusicSVGLogger.class);
    }

    @Test
    public void testRender() {

        SVGDocument doc = EasyMock.createNiceMock(SVGDocument.class);
        EasyMock.expect(doc.createElement(SVGWidget.ROOT.getType(), 1)).andReturn(null);
        EasyMock.replay(doc);

        SVGModelDAOMapImpl dao = new SVGModelDAOMapImpl();

        SVGModel n = new SVGModel();
        n.setId(1);
        n.setSvgWidget(SVGWidget.ROOT);
        dao.saveOrUpdate(n);

        NoteDAOMapImpl noteDAO = new NoteDAOMapImpl();

        SVGRendererImpl renderer = new SVGRendererImpl();
        renderer.injectMusicSVGLogger(log);
        renderer.injectSVGDocument(doc);
        renderer.injectSVGModelDAO(dao);
        renderer.injectNoteDAO(noteDAO);

        renderer.render();

        EasyMock.verify(doc);
    }

    @Test
    public void testRenderChilds() {

        Element element = EasyMock.createMock(Element.class);
        element.setAttribute("href", SVGWidget.WHITE.getWidgetName());
        EasyMock.replay(element);

        Element mockElement = EasyMock.createMock(Element.class);

        SVGDocument doc = EasyMock.createNiceMock(SVGDocument.class);
        EasyMock.expect(doc.createElement("g", 1)).andStubReturn(mockElement);
        EasyMock.expect(doc.createElement(SVGWidget.WHITE.getType(), 2)).andStubReturn(element);
        EasyMock.replay(doc);

        SVGModelDAOMapImpl dao = new SVGModelDAOMapImpl();

        SVGModel n = new SVGModel();
        n.setId(1);
        n.setSvgWidget(SVGWidget.ROOT);


        SVGModel child = new SVGModel();
        child.setId(2);
        child.setSvgWidget(SVGWidget.WHITE);
        n.getChildrens().add(child);

        // child will be saved
        dao.saveOrUpdate(n);

        NoteDAOMapImpl noteDAO = new NoteDAOMapImpl();

        SVGRendererImpl renderer = new SVGRendererImpl();
        renderer.injectMusicSVGLogger(log);
        renderer.injectSVGDocument(doc);
        renderer.injectSVGModelDAO(dao);
        renderer.injectNoteDAO(noteDAO);

        renderer.render();

        EasyMock.verify(doc);
    }

    @Test
    public void testInit() {
        SVGDocument doc = EasyMock.createNiceMock(SVGDocument.class);
        doc.importElement(SVGWidget.ANCHOR.getWidgetName());
        doc.importElement(SVGWidget.ANCHOR2.getWidgetName());
        doc.importElement(SVGWidget.ANCHOR3.getWidgetName());
        EasyMock.replay(doc);
        
        SVGRendererImpl renderer = new SVGRendererImpl();
        renderer.injectMusicSVGLogger(log);
        renderer.injectSVGDocument(doc);
        
        renderer.init();
        
        EasyMock.verify(doc);
    }
    
    @Test
    public void testExport() throws IOException {
        File file = new File("fakeFile");
        SVGDocument svgDoc = EasyMock.createMock(SVGDocument.class);
        svgDoc.save(file);
        EasyMock.replay(svgDoc);
        
        SVGRendererImpl renderer = new SVGRendererImpl();
        renderer.injectSVGDocument(svgDoc);
        renderer.injectMusicSVGLogger(log);
        
        renderer.exportFile(file);
        
        EasyMock.verify(svgDoc);
    }
}
