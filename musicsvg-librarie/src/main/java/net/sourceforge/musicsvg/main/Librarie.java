/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.main;

import javax.swing.JFrame;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Dav
 */
public class Librarie {

    public static void main(String args[]) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    new String[]{
                        "classpath:dao-context.xml",
                        "classpath:persistant-context.xml",
                        "classpath:applicationContext.xml"});

            LibrarieController controller = (LibrarieController) context.getBean("controller");
            controller.endAddDirectory();
            
            JFrame frame = (JFrame) context.getBean("frame");
            frame.setVisible(true);
            frame.setSize(800, 800);
            frame.setTitle("Librarie...");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
