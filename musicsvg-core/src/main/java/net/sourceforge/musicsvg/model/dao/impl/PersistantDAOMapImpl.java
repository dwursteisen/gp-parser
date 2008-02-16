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
    private Map<Integer, T> persistantMap = new HashMap<Integer, T>();
    private int newId = 0;
    
    public void setPersistantMap(Map<Integer, T> map) {
        this.persistantMap = map;
    }
    
    @Override
    public List<T> findAll() {
        return new Vector<T>(persistantMap.values());
    }

    @Override
    public T findById(Integer id) {
        return persistantMap.get(id);
    }

    @Override
    public void saveOrUpdate(T n) {
        if ( n.getId() == null) {
            n.setId(++newId);
        }
        persistantMap.put(n.getId(), n);
    }

    @Override
    public void clear() {
        persistantMap.clear();
    }

}
