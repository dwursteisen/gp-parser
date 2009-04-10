/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import net.sourceforge.musicsvg.gui.listener.PerformAddDirectoryListener;
import net.sourceforge.musicsvg.model.Song;

/**
 *
 * @author Dav
 */
public class SongTableModel extends AbstractTableModel implements PerformAddDirectoryListener{

    List<Song> items = new ArrayList<Song>();
    String[] columns = new String[]{"Nom du fichier", "Titre", "Artiste", "Chemin complet"};

    public int getRowCount() {
        return items.size();
    }

    @Override
    public String getColumnName(int column) {
        if(column < columns.length) {
            return columns[column];
        }
        return "???";
    }


    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex < items.size()) {
            Song s = items.get(rowIndex);
            switch(columnIndex) {
                default:
                case 0: return s.getFile().getName();
                case 1: return s.getTitle();
                case 2: return s.getArtist();
                case 3: return s.getFile().getAbsolutePath();
            }
        }
        return null;
    }

    public Song getItemsAt(int rowIndex) {
        if(rowIndex < items.size()) {
            return items.get(rowIndex);
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    public void publish(List<Song> songs) {
        this.items = songs;
        this.fireTableDataChanged();
    }

}
