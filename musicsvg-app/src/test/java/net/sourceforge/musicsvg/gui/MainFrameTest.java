/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.gui;

import net.sourceforge.musicsvg.gui.mainframe.MainFrame;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.easymock.classextension.EasyMock;
import org.fest.swing.fixture.FrameFixture;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 *
 * @author Dav
 */
public class MainFrameTest {

    private FrameFixture windowFixture;
    private MainFrame frame;
    private MusicSVGLogger logger;
    
    @BeforeTest
    public void setUp() {
        frame = new MainFrame();
        windowFixture = new FrameFixture(frame);
        logger = EasyMock.createMock(MusicSVGLogger.class);
        
        
    }

    @AfterTest
    public void tearDown() {
        windowFixture.cleanUp();
    }

    @Test(groups = { "gui" })
    public void testClickOnQuit() {

        MainController controller = EasyMock.createMock(MainController.class);
        controller.quitApplication();
        EasyMock.replay(controller);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.injectLogger(logger);
        dispatcher.injectController(controller);
        dispatcher.setQuitButton(frame.getQuitButton());
        windowFixture.show();
        windowFixture.menuItem("fileMenu").click();
        windowFixture.menuItem("quitItem").click();

        EasyMock.verify(controller);
    }

    @Test(groups = { "gui" })
    public void testClickOnOpen() {
        
        MainController controller = EasyMock.createMock(MainController.class);
        controller.prompOpenFile();
        EasyMock.replay(controller);
        
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.injectLogger(logger);
        dispatcher.injectController(controller);
        dispatcher.setOpenButton(frame.getOpenButton());
        windowFixture.show();
        windowFixture.menuItem("fileMenu").click();
        windowFixture.menuItem("openItem").click();

        EasyMock.verify(controller);
        
    }
    
    @Test(groups = {"gui"})
    public void testClickOnExport() {
        MainController controller = EasyMock.createMock(MainController.class);
        controller.exportFile();
        EasyMock.replay(controller);
        
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.injectLogger(logger);
        dispatcher.injectController(controller);
        dispatcher.setExportButton(frame.getExportButton());
        
        windowFixture.show();
        windowFixture.menuItem("fileMenu").click();
        windowFixture.menuItem("exportItem").click();
        EasyMock.verify(controller);
        
    }
 
}
