/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao;

import java.util.List;

/**
 *
 * @author Dav
 */
public interface PersistantDAO<T> {

    List<T> findAll();

    T findById(Integer id);

    void saveOrUpdate(T n);

}
