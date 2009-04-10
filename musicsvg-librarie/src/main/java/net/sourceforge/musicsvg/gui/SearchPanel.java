/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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


}
