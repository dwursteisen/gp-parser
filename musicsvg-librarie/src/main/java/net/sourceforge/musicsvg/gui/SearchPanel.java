/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private List<FilterListener> filterListeners;

    public SearchPanel() {
        searchLabel = new JLabel("Filtre...");
        searchField = new JTextField(40);
        searchButton = new JButton("OK");

        add(searchLabel);
        add(searchField);
        add(searchButton);
    }

    public void setOnFilterListeners(final List<FilterListener> listeners) {
        this.filterListeners = listeners;
        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fireFilterEvent();
            }
        });

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fireFilterEvent();
                }
            }
        });
    }

    private void fireFilterEvent() {
        for (FilterListener l : filterListeners) {
            l.filter(searchField.getText());
        }
    }
}
