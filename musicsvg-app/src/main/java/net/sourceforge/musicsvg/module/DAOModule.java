/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.render.svg.SVGModelDAO;
import net.sourceforge.musicsvg.model.dao.impl.NoteDAOMapImpl;
import net.sourceforge.musicsvg.svg.impl.SVGModelDAOMapImpl;

/**
 *
 * @author david
 */
public class DAOModule implements Module {

    @Override
    public void configure(Binder binder) {
        // DAO
        binder.bind(SVGModelDAO.class).to(SVGModelDAOMapImpl.class).in(Scopes.SINGLETON);
        binder.bind(NoteDAO.class).to(NoteDAOMapImpl.class).in(Scopes.SINGLETON);
    }
}
