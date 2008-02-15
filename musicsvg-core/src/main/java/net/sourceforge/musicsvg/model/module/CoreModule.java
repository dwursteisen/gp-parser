/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.model.dao.impl.NoteDAOMapImpl;
import net.sourceforge.musicsvg.model.factory.NoteTablatureFactory;
import net.sourceforge.musicsvg.model.factory.impl.NoteTablatureFactoryImpl;

/**
 *
 * @author Dav
 */
public class CoreModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(NoteTablatureFactory.class).to(NoteTablatureFactoryImpl.class);
        binder.bind(NoteDAO.class).to(NoteDAOMapImpl.class).in(Scopes.SINGLETON);
    }

}
