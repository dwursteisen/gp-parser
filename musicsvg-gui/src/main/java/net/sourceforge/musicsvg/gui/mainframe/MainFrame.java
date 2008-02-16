/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui.mainframe;

import com.google.inject.Inject;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import net.sourceforge.musicsvg.annotations.MainCanvas;

/**
 *
 * @author Dav
 */
public class MainFrame extends JFrame {

    JMenuItem quitItem = new JMenuItem();
    JMenuItem openItem = new JMenuItem();
    JMenuItem exportItem = new JMenuItem();
    JMenuItem scoreInformationItem = new JMenuItem();
    JComponent mainCanvas;
    
    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JMenuBar menu = new JMenuBar();
        
        JMenu fileMenu = new JMenu();
        fileMenu.setName("fileMenu");
        fileMenu.setText("File");
        fileMenu.add(openItem);
        fileMenu.add(exportItem);
        fileMenu.add(scoreInformationItem);
        fileMenu.add(quitItem);
        
        openItem.setName("openItem");
        openItem.setText("Open...");
        
        exportItem.setName("exportItem");
        exportItem.setText("Export...");
        
        scoreInformationItem.setName("infoItem");
        scoreInformationItem.setText("Score properties");
        
        quitItem.setName("quitItem");
        quitItem.setText("Quit...");
        
        menu.add(fileMenu);
        
        setJMenuBar(menu);
        
    }
    
    public AbstractButton getQuitButton() {
        return quitItem;
    }
    
    public AbstractButton getOpenButton() {
        return openItem;
    }

    public AbstractButton getExportButton() {
        return exportItem;
    }
    
    public AbstractButton getSongInformation() {
        return scoreInformationItem;
    }
    
    public JComponent getMainCanvas() {
        return mainCanvas;
    }
    
    @Inject
    public void injectMainCanvas(@MainCanvas JComponent mainCanvas) {
        this.mainCanvas = mainCanvas;
        add(this.mainCanvas);
    }
    
}
