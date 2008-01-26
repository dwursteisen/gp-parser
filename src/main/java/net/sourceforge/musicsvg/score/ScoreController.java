/*
 * ScoreController.java
 *
 * Created on 26 mai 2007, 21:30
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.score;

import net.sourceforge.musicsvg.svg.SVGController;
import java.util.List;
import net.sourceforge.musicsvg.model.Note;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.DOMException;


import java.util.logging.*;
/**
 *
 * @author Dav
 */
public class ScoreController extends JSVGCanvas {
    
    private SVGController view;
    
    public ScoreController() {
        super();
        setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
        view = new SVGController();
        createSVG();
        addScoreBar();
    }
    
    private void createSVG() throws DOMException {
        this.setDocument(view.getView().getDocument());
    }
    
    
    private void addScoreBar() {
        /*/
        int y = 0;
        for (int i = 0 ; i <= 5 ; i++) {
            y = i * space_bar;
            view.addWidgetToScore( "score_bar", 0, y);
        }
         
        view.addWidgetToScore("key",  0, 0);
         //*/
    }

    
    public void linkNotes(List<Note> lst) {
        view.linkNotes(lst);
        
    }
    
    @Override
    public void validate() {
        view.validate();
        
    }
    
    public void clear() {
        // TODO: do it
        // clear the canvas
        // create new view
        // set the new document
        // It doesn't work now
        this.setDocument(null);
        this.view.clear();
        createSVG();
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Clear doesnt work yet. Please implement it!");
        
    }
    
}
