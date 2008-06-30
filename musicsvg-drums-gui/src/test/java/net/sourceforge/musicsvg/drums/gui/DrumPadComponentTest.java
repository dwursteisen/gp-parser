/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.drums.gui;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Dav
 */
public class DrumPadComponentTest {

    public DrumPadComponentTest() {
    }

  
    @Test
    public void testPush() {
        DrumPadComponent pad = new DrumPadComponent();
        
        pad.drumPadMatrix[0][0] = DrumPadComponent.BUTTON_INACTIVE;
        pad.push(0, 0);
        Assert.assertEquals(DrumPadComponent.BUTTON_ACTIVE, pad.drumPadMatrix[0][0]);
        pad.push(0, 0);
        Assert.assertEquals(DrumPadComponent.BUTTON_INACTIVE, pad.drumPadMatrix[0][0]);
    }

}