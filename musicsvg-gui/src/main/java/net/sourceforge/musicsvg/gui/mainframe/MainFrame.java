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
import net.sourceforge.musicsvg.utils.I18n;

/**
 *
 * @author Dav
 */
public class MainFrame extends JFrame {

    private I18n i18n;
    JMenuItem quitItem = new JMenuItem();
    JMenuItem openItem = new JMenuItem();
    JMenuItem exportItem = new JMenuItem();
    JMenuItem scoreInformationItem = new JMenuItem();
    private JMenu fileMenu = new JMenu();
    JComponent mainCanvas;
    

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menu = new JMenuBar();

        fileMenu.setName("fileMenu");
        fileMenu.add(openItem);
        fileMenu.add(exportItem);
        fileMenu.add(scoreInformationItem);
        fileMenu.add(quitItem);

        openItem.setName("openItem");
        exportItem.setName("exportItem");
        scoreInformationItem.setName("infoItem");
        quitItem.setName("quitItem");

        menu.add(fileMenu);

        setJMenuBar(menu);
    }

    @Inject
    public void injectI18n(I18n i18n) {
        this.i18n = i18n;
    }

    public void setLabels() {
        fileMenu.setText(i18n.getString("mainFrame.fileMenu"));
        openItem.setText(i18n.getString("mainFrame.openItem"));
        exportItem.setText(i18n.getString("mainFrame.exportItem"));
        scoreInformationItem.setText(i18n.getString("mainFrame.infoItem"));
        quitItem.setText(i18n.getString("mainFrame.quitItem"));
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
