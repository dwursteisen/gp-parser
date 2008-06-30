/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.drums.gui;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Dav
 */
public class DrumPadComponent extends JComponent {
    final static byte BUTTON_INACTIVE = 0x00;
    final static byte BUTTON_ACTIVE = 0x01;
    
    final byte[][] drumPadMatrix = new byte[DRUM_PAD_WITH][DRUM_PAD_HEIGHT];
    final static int DRUM_PAD_WITH = 8;
    final static int DRUM_PAD_HEIGHT = 8;
    
    public void push(final int x, final int y) {
        drumPadMatrix[x][y] ^= BUTTON_ACTIVE;
    }
    
    
    public void paintPad(final Graphics g, final int x, final int y, final byte state) {
        int width = 20;
        int height = 20;
        int space = 5;
        g.drawRect(x*(width+space), y*(height+space), width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
    
        for(int x = 0; x < DRUM_PAD_WITH ; x++) {
            for(int y = 0 ; y < DRUM_PAD_HEIGHT ; y++) {
                paintPad(g, x, y, drumPadMatrix[x][y]);
            }
        }
    }
    
    
}
