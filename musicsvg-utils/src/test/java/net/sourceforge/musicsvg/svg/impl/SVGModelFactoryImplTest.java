/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg.impl;

import net.sourceforge.musicsvg.model.Note;
import net.sourceforge.musicsvg.model.NoteDuration;
import net.sourceforge.musicsvg.svg.model.SVGModel;
import net.sourceforge.musicsvg.svg.SVGWidget;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Dav
 */
public class SVGModelFactoryImplTest {

    @Test
    public void testCreateSVGModel() {
        SVGModelFactoryImpl factory = new SVGModelFactoryImpl();
        Note note = new Note();
        SVGModel result;
        
        note.setNoteDuration(NoteDuration.wholeNote);
        result = factory.createSVGModel(note);
        Assert.assertEquals(result.getSvgWidget(), SVGWidget.ROOT);
        Assert.assertEquals(result.getChilds().size(), 1);
        
        note.setNoteDuration(NoteDuration.thirtySecondNote);
        result = factory.createSVGModel(note);
        Assert.assertEquals(result.getSvgWidget(), SVGWidget.ROOT);
        Assert.assertEquals(result.getChilds().size(), 2);
        
        note.setNoteDuration(NoteDuration.sixtyFourthNote);
        result = factory.createSVGModel(note);
        Assert.assertEquals(result.getSvgWidget(), SVGWidget.ROOT);
        Assert.assertEquals(result.getChilds().size(), 2);
        
        note.setNoteDuration(NoteDuration.sixteenthNote);
        result = factory.createSVGModel(note);
        Assert.assertEquals(result.getSvgWidget(), SVGWidget.ROOT);
        Assert.assertEquals(result.getChilds().size(), 3);
        
        note.setNoteDuration(NoteDuration.quarterNote);
        result = factory.createSVGModel(note);
        Assert.assertEquals(result.getSvgWidget(), SVGWidget.ROOT);
        Assert.assertEquals(result.getChilds().size(), 3);
        
        note.setNoteDuration(NoteDuration.halfNote);
        result = factory.createSVGModel(note);
        Assert.assertEquals(result.getSvgWidget(), SVGWidget.ROOT);
        Assert.assertEquals(result.getChilds().size(), 3);
        
        note.setNoteDuration(NoteDuration.eighthNote);
        result = factory.createSVGModel(note);
        Assert.assertEquals(result.getSvgWidget(), SVGWidget.ROOT);
        Assert.assertEquals(result.getChilds().size(), 3);
    }
}
