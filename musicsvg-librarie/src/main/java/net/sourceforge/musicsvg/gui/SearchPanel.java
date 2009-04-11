/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sourceforge.musicsvg.gui.listener.FilterListener;

/**
 *
 * @author Dav
 */
public class SearchPanel extends JPanel {
    private JTextField searchField;
    private JLabel searchLabel;
    private JButton searchButton;

    public SearchPanel() {
        searchLabel = new JLabel("Filtre...");
        searchField = new JTextField(40);
        searchButton = new JButton("OK");
        
        add(searchLabel);
        add(searchField);
        add(searchButton);
    }

    public void setOnFilterListeners(final List<FilterListener> listeners) {
        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                for(FilterListener l : listeners) {
                    l.filter(searchField.getText());
                }
            }
        });
        
    }

}
