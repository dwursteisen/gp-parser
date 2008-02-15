/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.factory.impl;

import net.sourceforge.musicsvg.model.Track;
import net.sourceforge.musicsvg.model.factory.TrackFactory;

/**
 *
 * @author Dav
 */
public class TrackFactoryImpl implements TrackFactory {

    @Override
    public Track createTrack() {
        Track track = new Track();
        return track;
    }

}
