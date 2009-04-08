/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.gui;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import net.sourceforge.musicsvg.gui.listener.PerformAddDirectoryListener;
import net.sourceforge.musicsvg.model.Song;

/**
 *
 * @author Dav
 */
public class LibrarieTable extends JTable implements PerformAddDirectoryListener{

    final static String[] titreColonnes = { "Nom fichier","Titre", "Auteur",
                           "Full Path"};

    public LibrarieTable() {
        super(new String[][] {{"blabla", "", "", ""}}, titreColonnes);
    }

    
    public void publish(List<Song> songs) {
        int nbLigne = getRowCount();
        

        for(int i = 0 ; i < nbLigne ; nbLigne++) {
                setValueAt("", i, 0);
                setValueAt("", i, 1);
                setValueAt("", i, 2);
                setValueAt("", i, 3);
        }
        
        int i = 0;
        for(Song s : songs) {
            setValueAt(s.getFile().getName(), i, 0);
            setValueAt(s.getTitle(), i, 1);
            setValueAt(s.getArtist(), i, 2);
            setValueAt(s.getFile().getAbsolutePath(), i, 3);
        }
    }

}
