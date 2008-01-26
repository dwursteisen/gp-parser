/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.factory.impl;

import net.sourceforge.musicsvg.model.NoteTablature;
import net.sourceforge.musicsvg.model.factory.NoteTablatureFactory;

/**
 *
 * @author Dav
 */
public class NoteTablatureFactoryImpl implements NoteTablatureFactory {

    @Override
    public NoteTablature createNoteTablature() {
        return new NoteTablature();
    }

}
