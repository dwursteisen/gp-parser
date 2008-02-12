package net.sourceforge;

import java.io.File;
import java.io.IOException;
import net.sourceforge.musicsvg.main.Main;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.util.ScreenshotTaker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainScreenshotTest {
    private String imageFolderPath;

    private FrameFixture gui;
    private ScreenshotTaker screenshotTaker = new ScreenshotTaker();

    @BeforeMethod
    public void setUp() throws IOException {

        String currentFolderPath = org.fest.util.Files.currentFolder().getCanonicalPath();

        File imageFolder = new File(currentFolderPath);
        imageFolderPath = imageFolder.getCanonicalPath() + File.separator;

        Main mainApp = new Main();
        gui = new FrameFixture(mainApp.startApplication());
        gui.show();
    }

    @Test
    public void testMainFrame() {
        //screenshotTaker.saveDesktopAsPng(imageFolderPath + "mainFrame.png");
        Assert.assertTrue(true);
    }
}
