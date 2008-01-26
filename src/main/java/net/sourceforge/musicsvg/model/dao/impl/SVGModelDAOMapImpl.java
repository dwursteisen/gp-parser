/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.musicsvg.model.dao.SVGModelDAO;
import net.sourceforge.musicsvg.svg.SVGWidget;
import net.sourceforge.musicsvg.svg.model.SVGModel;

/**
 *
 * @author Dav
 */
public class SVGModelDAOMapImpl extends PersistantDAOMapImpl<SVGModel> implements SVGModelDAO {

    @Override
    public List<SVGModel> findAllWhereSVGWidget(SVGWidget cond) {
        List<SVGModel> result = new ArrayList<SVGModel>();
        for (SVGModel model : findAll()) {
            if ( model.getSvgWidget() == cond) {
                result.add(model);
            }
        }
        return result;
    }

    @Override
    public void saveOrUpdate(SVGModel model) {
        super.saveOrUpdate(model);
        
        List<SVGModel> childrens = model.getChildrens();
        for ( SVGModel child: childrens) {
            this.saveOrUpdate(child);
        }
    }
    

}
