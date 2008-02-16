/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.factory.impl;

import net.sourceforge.musicsvg.model.Song;
import net.sourceforge.musicsvg.model.factory.SongFactory;

/**
 *
 * @author Dav
 */
public class SongFactoryImpl implements SongFactory {

    @Override
    public Song createSong() {
        return new Song();
    }

}
