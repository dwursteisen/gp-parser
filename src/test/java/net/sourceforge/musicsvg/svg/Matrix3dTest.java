/*
 * Matrix3dTest.java
 * JUnit based test
 *
 * Created on 7 septembre 2007, 00:54
 */

package net.sourceforge.musicsvg.svg;

import net.sourceforge.musicsvg.svg.model.Matrix3d;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Dav
 */
public class Matrix3dTest  {

    //@Test
    public void testHashCode() {
        System.out.println("HashCode");
        double[] tmp = {
            1, 4, 7,
            2, 5, 8,
            3, 6, 9};
        Matrix3d m1 = new Matrix3d(tmp);
        Matrix3d m2 = new Matrix3d(tmp);
        
        Assert.assertTrue(m1.equals(m2));
        Assert.assertEquals(m1.hashCode(), m2.hashCode());
        
        tmp[3] = 99.0;
        Matrix3d m3 = new Matrix3d(tmp);
        
        Assert.assertFalse(m1.equals(m3));
        Assert.assertTrue(m1.hashCode() != m3.hashCode());
    }
 
    @Test
    public void testConstructor() {
        System.out.println("Matrix3D Constructor");
        
        double[] tmp = {
            1, 4, 7,
            2, 5, 8,
            3, 6, 9};
        Matrix3d result = new Matrix3d(tmp);
        Assert.assertEquals(tmp[0], result.m00);
        Assert.assertEquals(tmp[3], result.m10);
        Assert.assertEquals(tmp[6], result.m20);
        Assert.assertEquals(tmp[1], result.m01);
        Assert.assertEquals(tmp[4], result.m11);
        Assert.assertEquals(tmp[7], result.m21);
        Assert.assertEquals(tmp[2], result.m02);
        Assert.assertEquals(tmp[5], result.m12);
        Assert.assertEquals(tmp[8], result.m22);
        
    }
    /**
     * Test of mul method, of class SVG.Matrix3d.
     */
    @Test
    public void testMul() {
        System.out.println("mul");
        
        Matrix3d source = new Matrix3d();
        Matrix3d instance = new Matrix3d();
        
        source.setIdentity();
        instance.setIdentity();
        
        instance.mul(source);
        
        // Idn * Idn = Idn
        Assert.assertTrue(instance.equals(source));
        
        double[] tmp = {
            1, 1, 1,
            2, 2, 2,
            3, 3, 3
        };
        
        instance = new Matrix3d(tmp);
        Matrix3d copieInstance = new Matrix3d(tmp);
        
        instance.mul(source);
        
        // M * Idn = M
        Assert.assertTrue(instance.equals(copieInstance));
        
        double[] tmp2 = {
            1, 4, 7,
            2, 5, 8,
            3, 6, 9
        };
        
        instance = new Matrix3d(tmp2);
        
        instance.mul(copieInstance);
        
        double[] r = {
            30, 30, 30,
            36, 36, 36,
            42, 42, 42
        };
        Matrix3d result = new Matrix3d(r);
        
        Assert.assertTrue(instance.equals(result));
        
        
       /* double[] r2 = { 30, 36, 42, 30, 36, 42, 30, 36, 42};
        Matrix3d result2 = new Matrix3d(r2);
        assertTrue(instance.equals(result2)); */
        
    }
    
    /**
     * Test of setElement method, of class SVG.Matrix3d.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");
        
        double r[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        Matrix3d instance = new Matrix3d(r);
        
        int x = 0;
        int y = 0;
        double value = 5.0;
        instance.setElement(x, y, value);
        Assert.assertEquals(instance.m00, value);
        
        x = 1;
        y = 2;
        value = 6.0;
        instance.setElement(x, y, value);
        Assert.assertEquals(instance.m12, value);
        
    }
    
    /**
     * Test of setIdentity method, of class SVG.Matrix3d.
     */
    @Test
    public void testSetIdentity() {
        System.out.println("setIdentity");
        
        double expR[] = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        double r[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        Matrix3d expResult = new Matrix3d(expR);
        Matrix3d result = new Matrix3d(r);
        result.setIdentity();
        Assert.assertTrue(expResult.equals(result));
    }
    
    /**
     * Test of setColumn method, of class SVG.Matrix3d.
     */
    @Test
    public void testSetColumn() {
        System.out.println("setColumn");
        
        int column = 1;
        double[] col = {2.0, 3.0, 9.0};
        Matrix3d instance = new Matrix3d();
        
        instance.setColumn(column, col);
        
        Assert.assertEquals(col[0], instance.m01);
        Assert.assertEquals(col[1], instance.m11);
        Assert.assertEquals(col[2], instance.m21);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        double[] r = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Matrix3d result = new Matrix3d(r);
        
        Matrix3d expResult = new Matrix3d(r);
        
        Assert.assertTrue(result.equals(expResult));
        Assert.assertTrue(expResult.equals(result));
        
        r[2] = 0;
        expResult = new Matrix3d(r);
        
        Assert.assertFalse(result.equals(expResult));
        Assert.assertFalse(expResult.equals(result));
    }
    
}

