/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sourceforge.musicsvg.example;

import net.sourceforge.musicsvg.io.gp.GP4Parser;

import java.io.File;
import java.io.IOException;

/**
 * @author david
 */
public class MainClass {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please give a Guitar Pro file.");
            return;
        }
        GP4Parser parser = new GP4Parser();
        parser.setListener(new SystemOutputGP4ParserListenerImpl());
        try {
            parser.openFile(new File(args[0]));
        } catch (IOException ex) {
            System.err.println("Error with the file !!");
            ex.printStackTrace(System.err);
        }
    }
}
