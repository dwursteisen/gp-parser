/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.sourceforge.musicsvg.model.PersistantObject;
import net.sourceforge.musicsvg.model.dao.PersistantDAO;

/**
 *
 * @author Dav
 */
public class PersistantDAOMapImpl<T extends PersistantObject> implements PersistantDAO<T> {
    private Map<Integer, T> notes = new HashMap<Integer, T>();
    private int newId = 0;
    
    public void setNoteMaps(Map<Integer, T> notes) {
        this.notes = notes;
    }
    
    @Override
    public List<T> findAll() {
        return new Vector<T>(notes.values());
    }

    @Override
    public T findById(Integer id) {
        return notes.get(id);
    }

    public Map<Integer, T> getNotes() {
        return notes;
    }

    @Override
    public void saveOrUpdate(T n) {
        if ( n.getId() == null) {
            n.setId(++newId);
        }
        notes.put(n.getId(), n);
    }

}
