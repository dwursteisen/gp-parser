/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg.impl;

import abc.notation.Tune;
import abc.notation.Tune.Music;
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
        
        Music music = EasyMock.createMock(Music.class);
        music.addElement(noteA);
        music.addElement(noteB);
        music.addElement(noteC);
        EasyMock.replay(music);
        
        Tune tune = EasyMock.createMock(Tune.class);
        tune.getMusic();
        EasyMock.expectLastCall().andStubReturn(music);
        EasyMock.replay(tune);
        
        JScoreComponent jscore = EasyMock.createMock(JScoreComponent.class);
        jscore.setTune(tune);
        EasyMock.replay(jscore);
        
        EasyMock.verify(music);
        EasyMock.verify(jscore);
    }
            
}
