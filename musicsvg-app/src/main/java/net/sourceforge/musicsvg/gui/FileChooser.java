/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.gui;

import com.google.inject.Inject;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Dav
 */
public class FileChooser {
    private JFileChooser fileChooser;

    @Inject
    public void injectJFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
    
    public File prompt(JFrame parentFrame) {
        int result = fileChooser.showOpenDialog(parentFrame);
        if (result != JFileChooser.CANCEL_OPTION) {
            File f = fileChooser.getSelectedFile();
            return f;
        }
        return null;
    }

    public File saveFile(JFrame parentFrame) {
        int result = fileChooser.showSaveDialog(parentFrame);
        if (result != JFileChooser.CANCEL_OPTION) {
            File f = fileChooser.getSelectedFile();
            return f;
        }
        return null;
    }
}
