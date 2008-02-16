/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import net.sourceforge.musicsvg.model.dao.NoteDAO;
import net.sourceforge.musicsvg.model.dao.SongDAO;
import net.sourceforge.musicsvg.model.dao.TrackDAO;
import net.sourceforge.musicsvg.model.dao.impl.NoteDAOMapImpl;
import net.sourceforge.musicsvg.model.dao.impl.SongDAOMapImpl;
import net.sourceforge.musicsvg.model.dao.impl.TrackDAOMapImpl;
import net.sourceforge.musicsvg.model.factory.NoteTablatureFactory;
import net.sourceforge.musicsvg.model.factory.SongFactory;
import net.sourceforge.musicsvg.model.factory.TrackFactory;
import net.sourceforge.musicsvg.model.factory.impl.NoteTablatureFactoryImpl;
import net.sourceforge.musicsvg.model.factory.impl.SongFactoryImpl;
import net.sourceforge.musicsvg.model.factory.impl.TrackFactoryImpl;

/**
 *
 * @author Dav
 */
public class CoreModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(NoteTablatureFactory.class).to(NoteTablatureFactoryImpl.class);
        binder.bind(NoteDAO.class).to(NoteDAOMapImpl.class).in(Scopes.SINGLETON);
        
        binder.bind(TrackFactory.class).to(TrackFactoryImpl.class);
        binder.bind(TrackDAO.class).to(TrackDAOMapImpl.class).in(Scopes.SINGLETON);
        
        binder.bind(SongFactory.class).to(SongFactoryImpl.class);
        binder.bind(SongDAO.class).to(SongDAOMapImpl.class).in(Scopes.SINGLETON);
    }

}
