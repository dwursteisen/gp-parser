/*
 * SVGModelTest.java
 * JUnit based test
 *
 * Created on 22 juillet 2007, 16:26
 */

package net.sourceforge.musicsvg.svg;

import net.sourceforge.musicsvg.svg.model.SVGModel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Dav
 */
public class SVGModelTest {
  

    /**
     * Test of getX method, of class SVG.SVGModel.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        
        SVGModel instance = new SVGModel();
        
        int expResult = 45;
        instance.setX(expResult);
        int result = instance.getX();
        Assert.assertEquals(expResult, result);
        
    }

    /**
     * Test of getY method, of class SVG.SVGModel.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        
        SVGModel instance = new SVGModel();
        
        int expResult = 234;
        instance.setY(expResult);
        int result = instance.getY();
        Assert.assertEquals(expResult, result);
       
    }

    /**
     * Test of getWidth method, of class SVG.SVGModel.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        
        SVGModel instance = new SVGModel();
        
        int expResult = 532;
        instance.setWidth(expResult);
        
        int result = instance.getWidth();
        Assert.assertEquals(expResult, result);
        
    }

    /**
     * Test of getHeight method, of class SVG.SVGModel.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        
        SVGModel instance = new SVGModel();
        
        int expResult = 1245;
        instance.setHeight(expResult);
        
        int result = instance.getHeight();
        Assert.assertEquals(expResult, result);
        
    }

    /**
     * Test of getScale method, of class SVG.SVGModel.
     */
    

    /**
     * Test of getType method, of class SVG.SVGModel.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        SVGModel instance = new SVGModel();
        
        String expResult = "type";
        instance.setType(expResult);
        String result = instance.getType();
        Assert.assertEquals(expResult, result);
        
    }

    /**
     * Test of getId method, of class SVG.SVGModel.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        
        SVGModel instance = new SVGModel();
        
        Integer expResult = 1;
        instance.setId(expResult);
        Integer result = instance.getId();
        Assert.assertEquals(expResult, result);
        
    }

    /**
     * Test of getParentId method, of class SVG.SVGModel.
     */
    @Test
    public void testGetParentId() {
        System.out.println("getParentId");
        
        SVGModel instance = new SVGModel();
        
        Integer expResult = 5;
        instance.setParentId(expResult);
        Integer result = instance.getParentId();
        Assert.assertEquals(expResult, result);
    }
   
}
