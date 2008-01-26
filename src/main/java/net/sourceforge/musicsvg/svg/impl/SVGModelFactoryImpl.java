/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.svg.impl;

import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.svg.model.SVGModel;
import net.sourceforge.musicsvg.svg.SVGModelFactory;
import net.sourceforge.musicsvg.svg.SVGWidget;

/**
 *
 * @author Dav
 */
public class SVGModelFactoryImpl implements SVGModelFactory {

    @Override
    public SVGModel createSVGModel(Note note) {
        SVGModel model = new SVGModel();
        model.setSvgWidget(SVGWidget.ROOT);
        decorateBaseNote(model, note);
        decorateBarNote(model, note);
        decorateAnchor(model, note);
        return model;
    }

    private void decorateBaseNote(SVGModel model, Note note) {

        SVGModel child = new SVGModel();
        // TODO: refactor this method
        switch (note.getNoteDuration()) {
            case wholeNote:
                child.setSvgWidget(SVGWidget.WHITE);
                break;
            case thirtySecondNote:
                child.setSvgWidget(SVGWidget.WHITE);
                break;
            default:
                child.setSvgWidget(SVGWidget.BLACK);
                break;
        }
        model.getChildrens().add(child);
    }

    private void decorateBarNote(SVGModel model, Note note) {

       

        switch (note.getNoteDuration()) {
            case wholeNote:
                break;
            default:
                SVGModel child = new SVGModel();
                child.setSvgWidget(SVGWidget.BAR);
                model.getChildrens().add(child);
                break;
        }
        
    }

    private void decorateAnchor(SVGModel model, Note note) {
        
        SVGModel child;
        switch (note.getNoteDuration()) {
            case thirtySecondNote:
            case wholeNote:
            case sixtyFourthNote:
                break;

            case sixteenthNote:
                child = new SVGModel();
                child.setSvgWidget(SVGWidget.ANCHOR);
                model.getChildrens().add(child);
                break;
            case quarterNote:
                child = new SVGModel();
                child.setSvgWidget(SVGWidget.ANCHOR2);
                model.getChildrens().add(child);
                break;

            case halfNote:
                child = new SVGModel();
                child.setSvgWidget(SVGWidget.ANCHOR3);
                model.getChildrens().add(child);
                break;

            case eighthNote:
                child = new SVGModel();
                child.setSvgWidget(SVGWidget.ANCHOR3);
                model.getChildrens().add(child);
                break;
        }
        
    }
}
