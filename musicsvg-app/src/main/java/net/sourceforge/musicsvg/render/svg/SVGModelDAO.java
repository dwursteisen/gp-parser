/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.render.svg;

import net.sourceforge.musicsvg.model.dao.*;
import java.util.List;
import net.sourceforge.musicsvg.svg.SVGWidget;
import net.sourceforge.musicsvg.svg.model.SVGModel;

/**
 *
 * @author Dav
 */
public interface SVGModelDAO extends PersistantDAO<SVGModel>{
    public List<SVGModel> findAllWhereSVGWidget(SVGWidget cond);
    @Override
    public void saveOrUpdate(SVGModel model);
}
