/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sourceforge.musicsvg.gui.listener.SaveConfigurationListener;
import net.sourceforge.musicsvg.model.UserConfiguration;

/**
 *
 * @author Dav
 */
public class ConfigurationFrame extends JFrame {
    private final JButton buttonCancel;
    private final JButton buttonOk;
    private JTextField path;

    public ConfigurationFrame() {
        
        JLabel label = new JLabel("Guitar Pro :");
        path = new JTextField(40);
        JButton browser = new JButton("Parcourir...");

        JPanel gpPanel = new JPanel();
        gpPanel.setLayout(new BoxLayout(gpPanel, BoxLayout.X_AXIS));
        gpPanel.add(label);
        gpPanel.add(path);
        gpPanel.add(browser);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Annuler");
        
        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
        commandPanel.add(buttonOk);
        commandPanel.add(buttonCancel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(gpPanel);
        mainPanel.add(commandPanel);

        setTitle("Configuration");
        setSize(200, 200);
        add(mainPanel);

    }

    public void setOnSaveConfigurationListeners(final List<SaveConfigurationListener> listeners) {
        buttonOk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                for(SaveConfigurationListener l : listeners) {
                    l.save(getUserConfigurationFromForm());
                }
            }


        });
    }

    private UserConfiguration getUserConfigurationFromForm() {
        UserConfiguration config = new UserConfiguration();
        config.setGuitarProFile(new File(path.getText()));
        config.setPowerTabFile(null);
        return config;
    }

}
