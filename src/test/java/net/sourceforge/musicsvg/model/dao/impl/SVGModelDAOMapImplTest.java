/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model.dao.impl;

import java.util.List;
import net.sourceforge.musicsvg.svg.SVGWidget;
import net.sourceforge.musicsvg.svg.model.SVGModel;
import net.sourceforge.musicsvg.utils.MusicSVGLogger;
import org.easymock.EasyMock;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Dav
 */
public class SVGModelDAOMapImplTest {

    @Test
    public void testFindAllRoot() {
        SVGModelDAOMapImpl dao = new SVGModelDAOMapImpl();
        
        SVGModel model;
        model = new SVGModel();
        model.setSvgWidget(SVGWidget.ROOT);
        dao.saveOrUpdate(model);
        
        model = new SVGModel();
        model.setSvgWidget(SVGWidget.BLACK);
        dao.saveOrUpdate(model);
        
        model = new SVGModel();
        model.setSvgWidget(SVGWidget.ROOT);
        dao.saveOrUpdate(model);
        
        List<SVGModel> findAllWhereSVGWidget = dao.findAllWhereSVGWidget(SVGWidget.ROOT);
        Assert.assertEquals(findAllWhereSVGWidget.size(), 2);
    }
    
    @Test
    public void testSaveOrUpdate() {
        
        SVGModel model = new SVGModel();
        SVGModel child1 = new SVGModel();
        SVGModel child2 = new SVGModel();
        SVGModel childOf2 = new SVGModel();
        
        model.getChildrens().add(child1);
        model.getChildrens().add(child2);
        child2.getChildrens().add(childOf2);
        
        Assert.assertNull(model.getId());
        Assert.assertNull(child1.getId());
        Assert.assertNull(child2.getId());
        Assert.assertNull(childOf2.getId());
        
        SVGModelDAOMapImpl dao = new SVGModelDAOMapImpl();
        
        dao.saveOrUpdate(model);
        
        Assert.assertNotNull(model.getId());
        Assert.assertNotNull(child1.getId());
        Assert.assertNotNull(child2.getId());
        Assert.assertNotNull(childOf2.getId());
    }
}
