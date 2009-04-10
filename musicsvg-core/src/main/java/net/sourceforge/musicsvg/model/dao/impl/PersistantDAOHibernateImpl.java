/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import net.sourceforge.musicsvg.model.PersistantObject;
import net.sourceforge.musicsvg.model.dao.PersistantDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Dav
 */
public class PersistantDAOHibernateImpl<T extends PersistantObject> extends HibernateDaoSupport implements PersistantDAO<T>{

     private Class<T> persistentClass;
    

    public PersistantDAOHibernateImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public List<T> findAll() {
        return getHibernateTemplate().loadAll(getPersistentClass());
    }

    public T findById(Integer id) {
        return (T) getHibernateTemplate().get(getPersistentClass(), id);
    }

    public void saveOrUpdate(T n) {
        getHibernateTemplate().saveOrUpdate(n);
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
