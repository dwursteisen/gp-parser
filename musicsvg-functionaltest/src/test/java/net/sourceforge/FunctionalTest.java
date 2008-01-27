package net.sourceforge;


import net.sourceforge.musicsvg.main.Main;
import org.fest.swing.fixture.FrameFixture;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FunctionalTest {

    private FrameFixture gui;

    @BeforeMethod
    public void setUp() {
        Main mainApp = new Main();
        gui = new FrameFixture(mainApp.startApplication());
        gui.show();
    }
    
    @Test
    public void testOpen() {
        gui.menuItem("fileMenu").click();
        gui.menuItem("openItem").click();
    }
}
