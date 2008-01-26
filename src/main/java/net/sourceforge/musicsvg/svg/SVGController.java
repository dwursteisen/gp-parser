/*
 * SVGController.java
 *
 * Created on 22 juin 2007, 22:20
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.svg;


import net.sourceforge.musicsvg.svg.model.SVGModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.dao.SVGModelDAO;

/**
 *
 * @author Dav
 */
public class SVGController {
    
    SVGView view = new SVGView();
    private Map<Integer, SVGModel> modelMap = new HashMap<Integer, SVGModel>();
    private SVGModelDAO svgModelDAO;
    
    public void createNote(final Note n, final int x, final int y) {
        SVGModel model = new SVGModel();
        model.setId(n.getId());
        // model.setSVGType("g");
        model.setX(x);
        model.setY(y);
        modelMap.put(n.getId(), model);
        
    }
    
    public void addRondBlanc(final Note n) {
        addChild(n, SVGWidget.WHITE.getWidgetName());
    }
    public void addRond(final Note n) {
        addChild(n, SVGWidget.BLACK.getWidgetName());
    }
    public void addAnchor(final Note n) {
        addChild(n, SVGWidget.ANCHOR.getWidgetName());
    }
    
    public void addBar(final Note n) {
        addChild(n, SVGWidget.BAR.getWidgetName());
    }
    
    public void addAnchor2(final Note n) {
        addChild(n, SVGWidget.ANCHOR2.getWidgetName());
    }
    
    public void addAnchor3(final Note n) {
        addChild(n, SVGWidget.ANCHOR3.getWidgetName());
    }
    
    public void addSharp(Note n) {
        addChild(n, SVGWidget.SHARP.getWidgetName());
    }
    
    public void addBecare(Note n) {
        addChild(n, SVGWidget.BECARE.getWidgetName());
    }
    
    public void addBemol(Note n) {
        addChild(n, SVGWidget.BEMOL.getWidgetName());
    }
    
    private void addChild(final Note n, final String type) {
        SVGModel model = modelMap.get(n.getId());
        SVGModel child = new SVGModel();
        child.setType(type);
        model.addChild(child);
    }
    
    public void validate() {
        for (SVGModel m: modelMap.values()) {
            view.put(m);
        }
        Logger.getLogger("musicSVG").log(Level.INFO, "SVGController valided");
    }
    public SVGView getView() {
        return view;
    }
    
    public void linkNotes(List<Note> lst) {
        /*
        Note first = lst.get(0);
        Note last = lst.get(lst.size()-1);
        
        SVGModel firstModel = modelMap.get(first.getId());
        SVGModel lastModel = modelMap.get(last.getId());
        
        int size = Math.abs(lastModel.getX() - firstModel.getX());
        int height = Math.abs(lastModel.getY() - firstModel.getY());
        */
//        double r = Math.atan( size / height);
//        int width = (int)(Math.cos(1-r) * size);
//        SVGModel child = new SVGModel();
//        child.setType("link");
//        child.setWidth(width);
//        child.setRotation(1-r);
        //firstModel.addChild(child);
        
//        for (Note n: lst) {
//            SVGModel m = modelMap.get(n.getId());
//            m.removeChild("ancre2");
        
//        }
        
    }

    public void clear() {
        view = new SVGView();
    }
    
    
    
}



