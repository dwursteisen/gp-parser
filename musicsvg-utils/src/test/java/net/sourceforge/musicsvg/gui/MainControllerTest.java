/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;


import java.io.File;
import java.io.IOException;
import net.sourceforge.musicsvg.gui.mainframe.MainFrame;
import net.sourceforge.musicsvg.render.Renderer;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.easymock.classextension.EasyMock;
// import org.testng.internal.annotations.
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class MainControllerTest {

    @Test(groups={"functional"})
    public void testStartApplication() {
        MusicSVGLogger logger = EasyMock.createMock(MusicSVGLogger.class);
        
        MainFrame frame = EasyMock.createMock(MainFrame.class);
        frame.setVisible(true);
        EasyMock.replay(frame);
        
        MainController controller = new MainController();
        controller.injectMainFrame(frame);
        controller.injectLog(logger);
        
        controller.startApplication();
        
        EasyMock.verify(frame);
    }
    
    @Test(groups={"functional"})
    public void testExportFile() throws IOException {
        File file = EasyMock.createMock(File.class);
        MainFrame parentFrame = EasyMock.createMock(MainFrame.class);
        MusicSVGLogger logger = EasyMock.createMock(MusicSVGLogger.class);
        
        FileChooser fileChooser = EasyMock.createMock(FileChooser.class);
        EasyMock.expect(fileChooser.saveFile(parentFrame)).andStubReturn(file);
        EasyMock.replay(fileChooser);
        
        Renderer renderer = EasyMock.createMock(Renderer.class);
        renderer.exportFile(file);
        EasyMock.replay(renderer);
        
        MainController controller = new MainController();
        controller.injectMainFrame(parentFrame);
        controller.injectLog(logger);
        controller.injectSVGRenderer(renderer);
        controller.injectFileChooser(fileChooser);
        controller.exportFile();
        
        EasyMock.verify(fileChooser);
        EasyMock.verify(renderer);
    }
    
    @Test(groups={"functional"})
    public void testExportFile_cancel() {
        MainFrame parentFrame = EasyMock.createMock(MainFrame.class);
        MusicSVGLogger logger = EasyMock.createMock(MusicSVGLogger.class);
        
        FileChooser fileChooser = EasyMock.createMock(FileChooser.class);
        EasyMock.expect(fileChooser.saveFile(parentFrame)).andStubReturn(null);
        EasyMock.replay(fileChooser);
        
        Renderer renderer = EasyMock.createStrictMock(Renderer.class);
        EasyMock.replay(renderer);
        
        MainController controller = new MainController();
        controller.injectMainFrame(parentFrame);
        controller.injectLog(logger);
        controller.injectSVGRenderer(renderer);
        controller.injectFileChooser(fileChooser);
        controller.exportFile();
        
        EasyMock.verify(fileChooser);
        EasyMock.verify(renderer);
    }
}
