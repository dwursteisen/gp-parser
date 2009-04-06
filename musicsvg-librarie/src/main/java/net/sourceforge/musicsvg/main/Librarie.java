/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.main;

import com.l2fprod.common.swing.JDirectoryChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import net.sourceforge.musicsvg.io.GP4Parser;
import net.sourceforge.musicsvg.io.impl.GP4InfoParserListenerImpl;
import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import net.sourceforge.musicsvg.model.dao.impl.SongDAOMapImpl;
import net.sourceforge.musicsvg.model.factory.SongFactory;
import net.sourceforge.musicsvg.model.factory.impl.SongFactoryImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Dav
 */
public class Librarie {

    public static void main(String args[]) {
        // Librarie l = new Librarie();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        JFrame frame = (JFrame) context.getBean("frame");
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setTitle("Librarie...");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    }
    public Librarie() {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(800, 800);
        mainFrame.setTitle("Librarie...");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        final JMenuBar jMenuBar = new JMenuBar();
        final JMenu scanMenu = new JMenu("Scan...");
        jMenuBar.add(scanMenu);

        SongDAOMapImpl dao =  new SongDAOMapImpl();
        dao.setPersistantMap(new HashMap<Integer, Song>());
        SongFactory factory = new SongFactoryImpl();

        final Bidule b = new Bidule();
        b.setDao(dao);
        
        GP4InfoParserListenerImpl listener = new GP4InfoParserListenerImpl();
        listener.setSongDAO(dao);
        listener.setSongFactory(factory);

        final GP4Parser parser = new GP4Parser();
        parser.setListener(listener);
        final JMenuItem jMenuItem = new JMenuItem("item");
        scanMenu.add(jMenuItem);
        jMenuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JDirectoryChooser chooser = new JDirectoryChooser();
                
                chooser.showOpenDialog(scanMenu);
                File files = chooser.getSelectedFile();
                FilenameFilter filter = new FilenameFilter() {

                    public boolean accept(File dir, String name) {
                        return name.endsWith("gp4");
                    }
                };
                for(File file : files.listFiles(filter)) {
                    try {
                        parser.openFile(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        parser.close();
                    }

                }
                b.fin();
            }
        });
        mainFrame.add(jMenuBar);
    }

    class Bidule {

        SongDAO dao;

        public void setDao(SongDAO dao) {
            this.dao = dao;
        }
        
        public void fin() {
            List<Song> list = dao.findAll();
            for(Song s : list) {
                System.out.println("==> " + s.getTitle());
            }
        }
    }
}
