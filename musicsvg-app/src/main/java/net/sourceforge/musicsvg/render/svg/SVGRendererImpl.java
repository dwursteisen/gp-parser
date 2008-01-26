/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.render.svg;

import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.render.svg.SVGModelDAO;
import net.sourceforge.musicsvg.svg.SVGDocument;
import net.sourceforge.musicsvg.svg.SVGModelFactory;
import net.sourceforge.musicsvg.render.Renderer;
import net.sourceforge.musicsvg.svg.SVGWidget;
import net.sourceforge.musicsvg.svg.model.SVGModel;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.w3c.dom.Element;

/**
 *
 * @author Dav
 */
public class SVGRendererImpl implements Renderer {

    private SVGModelFactory svgModelFactory;
    private NoteDAO noteDAO;
    private SVGModelDAO svgModelDAO;
    private SVGDocument svgDocument;
    private MusicSVGLogger log;

    @Inject
    public void injectSVGModelDAO(SVGModelDAO dao) {
        svgModelDAO = dao;
    }

    @Inject
    public void injectNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Inject
    public void injectSVGMdelFactory(SVGModelFactory factory) {
        svgModelFactory = factory;
    }

    @Inject
    public void injectSVGDocument(SVGDocument doc) {
        svgDocument = doc;
    }

    @Inject
    public void injectMusicSVGLogger(MusicSVGLogger log) {
        this.log = log;
    }

    @Override
    public void render() {
        log.info(getClass(), "Rendering...");

        // TODO: move this part
        List<Note> notes = noteDAO.findAll();
        for (Note note : notes) {
            SVGModel model = svgModelFactory.createSVGModel(note);
            svgModelDAO.saveOrUpdate(model);
        }

        // Find just RootElement
        List<SVGModel> models = svgModelDAO.findAllWhereSVGWidget(SVGWidget.ROOT);
        for (SVGModel model : models) {
            // set propertie on each group
            String svgType = model.getSvgWidget().getType();
            Integer id = model.getId();
            Element elt = svgDocument.createElement(svgType, id);
            // put it into the SVGDocument
            svgDocument.addElement(elt);

            // TODO: this loop generate too much memory consumation
            // put all child of each root too in the same group 
            for (SVGModel child : model.getChildrens()) {
                SVGWidget svgWidget = child.getSvgWidget();
                id = child.getId();
                Element eltChild = svgDocument.createElement(svgWidget, id);
                // TODO: place this element on the canvas
                // TODO: display only visible elements ? 
                // TODO: or create SVG element
                // TODO: and change it into bitmap elements
                elt.appendChild(eltChild);
            }
        }


    }

    @Override
    public void init() {
        SVGWidget[] widgets = SVGWidget.values();
        for (SVGWidget widget : widgets) {
            String widgetName = widget.getWidgetName();
            if (widgetName != null && !"".equals(widgetName)) {
                this.svgDocument.importElement(widgetName);
            }
        }

    }

    @Override
    public void exportFile(File file) throws IOException {
        svgDocument.save(file);
    }
}
