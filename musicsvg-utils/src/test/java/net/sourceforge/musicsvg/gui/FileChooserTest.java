/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.io.File;
import javax.swing.JFileChooser;
import org.easymock.classextension.EasyMock;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class FileChooserTest {

    @Test
    public void testOpenFileDialog() {
        JFileChooser concreteChooser = EasyMock.createNiceMock(JFileChooser.class);
        EasyMock.expect(concreteChooser.showOpenDialog(null)).andStubReturn(JFileChooser.CANCEL_OPTION);
        EasyMock.replay(concreteChooser);
        
        FileChooser chooser = new FileChooser();
        chooser.injectJFileChooser(concreteChooser);
        File result = chooser.prompt(null);
        
        Assert.assertNull(result);
        EasyMock.verify(concreteChooser);
    }
    
    @Test
    public void testSaveFileDialog() {
        File mockFile = EasyMock.createMock(File.class);
        JFileChooser concreteChooser = EasyMock.createNiceMock(JFileChooser.class);
        concreteChooser.showSaveDialog(null);
        EasyMock.expectLastCall().andReturn(JFileChooser.CANCEL_OPTION)
                .andStubReturn(JFileChooser.APPROVE_OPTION);
        EasyMock.expect(concreteChooser.getSelectedFile()).andStubReturn(mockFile);
        EasyMock.replay(concreteChooser);
        
        FileChooser chooser = new FileChooser();
        chooser.injectJFileChooser(concreteChooser);
        File result = chooser.saveFile(null);
        Assert.assertNull(result);
        
        result = chooser.saveFile(null);
        Assert.assertSame(result, mockFile);
        EasyMock.verify(concreteChooser);
    }
}
