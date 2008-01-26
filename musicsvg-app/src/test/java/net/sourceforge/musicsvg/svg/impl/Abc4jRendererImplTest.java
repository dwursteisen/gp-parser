/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg.impl;

import abc.notation.Tune;
import abc.ui.swing.JScoreComponent;
import org.easymock.classextension.EasyMock;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class Abc4jRendererImplTest {

    @Test
    public void testExportFile() {
        
    }
    
    @Test
    public void testInit() {
        
    }
    
    // @Test
    public void testRender() {
        abc.notation.Note noteA = new abc.notation.Note(abc.notation.Note.A);
        abc.notation.Note noteB = new abc.notation.Note(abc.notation.Note.B);
        abc.notation.Note noteC = new abc.notation.Note(abc.notation.Note.C);
        
        Tune.Score score = EasyMock.createMock(Tune.Score.class);
        score.addElement(noteA);
        score.addElement(noteB);
        score.addElement(noteC);
        EasyMock.replay(score);
        
        Tune tune = EasyMock.createMock(Tune.class);
        tune.getScore();
        EasyMock.expectLastCall().andStubReturn(score);
        EasyMock.replay(tune);
        
        JScoreComponent jscore = EasyMock.createMock(JScoreComponent.class);
        jscore.setTune(tune);
        EasyMock.replay(jscore);
        
        EasyMock.verify(score);
        EasyMock.verify(jscore);
    }
            
}
