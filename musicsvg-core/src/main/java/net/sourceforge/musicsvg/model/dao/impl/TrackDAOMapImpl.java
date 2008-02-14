/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.sourceforge.musicsvg.model.Track;
import net.sourceforge.musicsvg.model.dao.TrackDAO;

/**
 *
 * @author Dav
 */
public class TrackDAOMapImpl implements TrackDAO{

    private Map<Integer, Track> core = new HashMap<Integer, Track>();
    
    @Override
    public List<Track> findAll() {
        return new Vector<Track>(core.values());
    }

    @Override
    public Track findById(Integer id) {
        return core.get(id);
    }

    @Override
    public void saveOrUpdate(Track n) {
        core.put(n.getId(), n);
    }

}
