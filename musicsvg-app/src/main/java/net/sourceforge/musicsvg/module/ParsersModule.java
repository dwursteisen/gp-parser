/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import net.sourceforge.musicsvg.io.GP4Parser;
import net.sourceforge.musicsvg.io.GP4ParserListener;
import net.sourceforge.musicsvg.io.impl.GP4ParserListenerImpl;

/**
 *
 * @author david
 */
public class ParsersModule implements Module {

    @Override
    public void configure(Binder binder) {
        // PARSER
        binder.bind(GP4ParserListener.class).to(GP4ParserListenerImpl.class);

        GP4Parser parser = new GP4Parser();
        binder.bind(GP4Parser.class).toInstance(parser);
    }
}
