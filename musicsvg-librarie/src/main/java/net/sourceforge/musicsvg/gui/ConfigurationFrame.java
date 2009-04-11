/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.sourceforge.musicsvg.gui.listener.LoadConfigurationListener;
import net.sourceforge.musicsvg.gui.listener.SaveConfigurationListener;
import net.sourceforge.musicsvg.model.UserConfiguration;

/**
 *
 * @author Dav
 */
public class ConfigurationFrame extends JFrame implements LoadConfigurationListener {
    private final JButton buttonCancel;
    private final JButton buttonOk;
    private InputFileBrowser path;

    public ConfigurationFrame() {
        path = new InputFileBrowser("Guitar Pro : ");
        
        JPanel gpPanel = new JPanel();
        gpPanel.add(path);
        
        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Annuler");
        
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
        commandPanel.add(buttonOk);
        commandPanel.add(buttonCancel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        mainPanel.add(Box.createHorizontalGlue());
        
        mainPanel.add(gpPanel);
        mainPanel.add(commandPanel);

        setTitle("Configuration");
        setSize(450, 150);
        add(mainPanel);

        buttonCancel.addActionListener(new CloseActionListenerImpl(this));
        buttonOk.addActionListener(new CloseActionListenerImpl(this));
    }

    public void setOnSaveConfigurationListeners(final List<SaveConfigurationListener> listeners) {
        buttonOk.addActionListener(new ActionListenerImpl(listeners));
    }

    private UserConfiguration getUserConfigurationFromForm() {
        UserConfiguration config = new UserConfiguration();
        config.setGuitarProFile(path.getFile());
        config.setPowerTabFile(null);
        return config;
    }

    public void loadConfiguration(UserConfiguration userConfig) {
        path.setFile(userConfig.getGuitarProFile());
    }

    private class CloseActionListenerImpl implements ActionListener {
        JFrame parent;

        public CloseActionListenerImpl(JFrame parent) {
            this.parent = parent;
        }

        public void actionPerformed(ActionEvent e) {
            this.parent.setVisible(false);
        }

    }
    private class ActionListenerImpl implements ActionListener {

        private final List<SaveConfigurationListener> listeners;
        

        public ActionListenerImpl(List<SaveConfigurationListener> listeners) {
        
            this.listeners = listeners;
        }

        public void actionPerformed(ActionEvent e) {
            for (SaveConfigurationListener l : listeners) {
                l.save(getUserConfigurationFromForm());
            }
        }
    }

}
