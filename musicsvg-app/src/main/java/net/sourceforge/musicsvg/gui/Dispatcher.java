/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import com.google.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import net.sourceforge.musicsvg.gui.mainframe.MainFrame;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;

/**
 *
 * @author Dav
 */
public class Dispatcher implements ActionListener {
    private AbstractButton exportButton;

    private AbstractButton quitButton;
    private AbstractButton fileButton;
    
    private MainController controller;
    private MusicSVGLogger log;

    @Inject
    public void injectLogger(MusicSVGLogger logger) {
        this.log = logger;
    }

    public void setExportButton(AbstractButton exportButton) {
        this.exportButton = exportButton;
        exportButton.addActionListener(this);
    }
    
    public void setQuitButton(AbstractButton button) {
        quitButton = button;
        quitButton.addActionListener(this);
    }
    
    public void setOpenButton(AbstractButton button) {
        fileButton = button;
        fileButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
       AbstractButton obj = (AbstractButton)e.getSource();
       log.info(getClass(), "Action Performed by " + obj.getName());
       if ( obj == quitButton ) {
           controller.quitApplication();
       } else if (obj == fileButton) {
           controller.prompOpenFile();
       } else if (obj == exportButton) {
           controller.exportFile();
       }
    }

    @Inject 
    void injectFrame(MainFrame frame) {
        setQuitButton(frame.getQuitButton());
        setOpenButton(frame.getOpenButton());
        setExportButton(frame.getExportButton());
    }
    
    @Inject
    public void injectController(MainController controller) {
        this.controller = controller;
    }

}
