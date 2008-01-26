/*
 * SVGTranformTest.java
 * JUnit based test
 *
 * Created on 29 juillet 2007, 20:41
 */

package net.sourceforge.musicsvg.svg;


import net.sourceforge.musicsvg.svg.model.Matrix3d;
import net.sourceforge.musicsvg.svg.impl.SVGTransformImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



/**
 *
 * @author Dav
 */
public class SVGTranformTest  {
    
        
    private int i;
    private int j;
    private final int MATRIX_SIZE = 3;
    
    @BeforeTest
    protected void setUp() throws Exception {
        i = 0;
        j = 0;
    }
    
    protected void tearDown() throws Exception {
    }
    
    /**
     * Test of getTranslationMatrix method, of class SVG.SVGTranform.
     */
    @Test
    public void testGetTranslationMatrix() {
        System.out.println("getTranslationMatrix");
        
        SVGTransformImpl instance = new SVGTransformImpl();
        double xTranslation = 56;
        double yTranslation = 34;
        
        instance.setTranslation(xTranslation, yTranslation);
        double[] tab = {1, 0, xTranslation, 0, 1, yTranslation, 0, 0, 1 };
        Matrix3d expResult = new Matrix3d(tab);
        Matrix3d result = instance.getTranslationMatrix();
        Matrix3d result_trans = instance.getTransformationMatrix();
        
        Assert.assertTrue(expResult.equals(result));
        Assert.assertTrue(expResult.equals(result_trans));
        
    
    }
    
    /**
     * Test of getScaleMatrix method, of class SVG.SVGTranform.
     */
    @Test
    public void testGetScaleMatrix() {
        System.out.println("getScaleMatrix");
        
        SVGTransformImpl instance = new SVGTransformImpl();
        
        double xScale = 34;
        double yScale = 65;
        
        instance.setScale(xScale, yScale);
        
        double[] tmp = { xScale, 0, 0, 0, yScale, 0, 0, 0, 1 };
        Matrix3d expResult = new Matrix3d(tmp);
        Matrix3d result = instance.getScaleMatrix();
        Matrix3d result_trans = instance.getTransformationMatrix();
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(expResult, result_trans);
    }
    
    /**
     * Test of getRotationMatrix method, of class SVG.SVGTranform.
     */
    @Test
    public void testGetRotationMatrix() {
        System.out.println("getRotationMatrix");
        
        SVGTransformImpl instance = new SVGTransformImpl();
        
        double angle = 0.8; //radian
        
        instance.setRotation(angle);
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        double[] tmp = { c, -s, 0, s, c, 0, 0, 0, 1};
        Matrix3d expResult = new Matrix3d(tmp);
        Matrix3d result = instance.getRotationMatrix();
        Matrix3d result_trans = instance.getTransformationMatrix();
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(expResult, result_trans);
    }
    
    /**
     * Test of getTransformationMatrix method, of class SVG.SVGTranform.
     */
    /*
    public void testGetTransformationMatrix_trans_scale_rotate() {
        System.out.println("getTransformationMatrix");
        
        SVGTranform instance = new SVGTranform();
        
        double xT = 15;
        double yT = 20;
        instance.setTranslation(xT, yT);
        
        double xS = 5;
        double yS = 9;
        instance.setScale(xS, yS);
        
        double r = Math.toDegrees(0.8);
        instance.setRotation(r);
        
        double[] tmp = {
            0.6967045,-0.7173582, 0,
            0.7173582,0.6967045, 0,
            0.0,0.0, 1.0
        };
        Matrix3d expResult = new Matrix3d(tmp);
        Matrix3d result = instance.getTransformationMatrix();
        assertTrue(expResult.equals(result));
    }
    */
    @Test
    public void testGetTransformationMatrix_trans_scale() {
        System.out.println("getTransformationMatrix");
        
        SVGTransformImpl instance = new SVGTransformImpl();
        
        double xT = 15;
        double yT = 20;
        instance.setTranslation(xT, yT);
        
        double xS = 5;
        double yS = 9;
        instance.setScale(xS, yS);
        
        double[] tmp = {
            5.0, 0.0, 15.0,
            0.0, 9.0, 20.0,
            0.0, 0.0, 1.0
        };
        Matrix3d expResult = new Matrix3d(tmp);
        Matrix3d result = instance.getTransformationMatrix();
        
        Assert.assertTrue(expResult.equals(result));
    }
    
    @Test
    public void testGetTransformationMatrix_nothing() {
        System.out.println("getTransformationMatrix");
        
        SVGTransformImpl instance = new SVGTransformImpl();
        
        Matrix3d expResult = new Matrix3d();
        expResult.setIdentity();
        
        Matrix3d result = instance.getTransformationMatrix();
        Assert.assertTrue(expResult.equals(result));
    }
    
    /**
     * Test of getSVGTransformation method, of class SVG.SVGTranform.
     */
    /**
    public void testGetSVGTransformation() {
        System.out.println("getSVGTransformation");
        
        SVGTranform instance = new SVGTranform();
        
        double xT = 15;
        double yT = 20;
        instance.setTranslation(xT, yT);
        String result = instance.getSVGTransformation();
        String expResult = "matrix(1.0,0.0,0.0,1.0,15.0,20.0)";
        assertEquals(expResult, result);
        
        double xS = 5;
        double yS = 9;
        instance.setScale(xS, yS);
        
        double r = 0.8;
        instance.setRotation(r);
        
        expResult = "matrix(0.6967045,-0.7173582,0.7173582,0.6967045,0,0)";
        result = instance.getSVGTransformation();
        assertEquals(expResult, result);
        
    }
    */
}
