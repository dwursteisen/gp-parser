/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dav
 */
public class InputFileBrowser extends JPanel{
    private JTextField path;
    private File file;

    public InputFileBrowser(String l) {
        JLabel label = new JLabel(l);
        path = new JTextField(20);
        path.setEditable(false);
        JButton browser = new JButton("Parcourir...");
        browser.addActionListener(new BrowserListener(this));
        add(label);
        add(path);
        add(browser);
    }

    public InputFileBrowser() {
        this("");
    }

    public String getValue() {
        if(file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public File getFile() {
        return this.file;
    }

    private void setFile(File file) {
        this.file = file;
        if(file != null) {
            path.setText(file.getAbsolutePath());
        }else {
            path.setText(null);
        }
    }

    class BrowserListener implements ActionListener {
        private InputFileBrowser parent;

        public BrowserListener(InputFileBrowser parent) {
            this.parent =parent;
        }


        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser= new JFileChooser(file);
            chooser.showOpenDialog((Component) e.getSource());
            File file = chooser.getSelectedFile();
            parent.setFile(file);
        }
        
    }
}
