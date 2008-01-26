/*
 * SVGTranform.java
 *
 * Created on 29 juillet 2007, 20:34
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.svg.impl;

import net.sourceforge.musicsvg.svg.model.Matrix3d;
import net.sourceforge.musicsvg.svg.*;


/**
 *
 * @author Dav
 */
public class SVGTransformImpl implements SVGTransform {
    private double rotationAngle = 0.0;
    private double xScale = 1.0;
    private double yScale = 1.0;
    private double xTranslation = 0.0;
    private double yTranslation = 0.0;
    
    private final static int MATRIX_SIZE = 3;
    
    
    /** Creates a new instance of SVGTranform */
    public SVGTransformImpl() {
        
        
    }
    
    @Override
    public Matrix3d getTranslationMatrix() {
        Matrix3d m = new Matrix3d();
        m.setIdentity();
        double[] tmp = {xTranslation, yTranslation, 1.0};
        m.setColumn(2, tmp);
        return m;
    }
    
    @Override
    public Matrix3d getScaleMatrix() {
        Matrix3d m = new Matrix3d();
        m.setIdentity();
        m.setElement(0, 0, xScale);
        m.setElement(1, 1, yScale);
        return m;
    }
    
    @Override
    public Matrix3d getRotationMatrix() {
        double c = Math.cos(rotationAngle);
        double s = Math.sin(rotationAngle);

        double[] tmp = { c, -s, 0, s, c, 0, 0, 0, 1};
        Matrix3d m = new Matrix3d(tmp);
        return m;
    }
    
    @Override
    public Matrix3d getTransformationMatrix() {
        Matrix3d matrix = getRotationMatrix();
        matrix.mul(getTranslationMatrix());
        matrix.mul(getScaleMatrix());
        return matrix;
    }
    
    @Override
    public String getSVGTransformation() {
        Matrix3d matrix = getTransformationMatrix();
        StringBuffer buffer = new StringBuffer("matrix(");
        buffer.append(matrix.m00 + "," + matrix.m10 + ",");
        buffer.append(matrix.m01 + "," + matrix.m11 + ",");
        buffer.append(matrix.m02 + "," + matrix.m12 + ")");
        return buffer.toString();
    }
    
    @Override
    public void setScale(double xScale, double yScale) {
        this.xScale = xScale;
        this.yScale = yScale;
    }
    
    @Override
    public void setRotation(double angle) {
        this.rotationAngle = angle;
    }
    
    @Override
    public void setTranslation(double xTranslation, double yTranslation) {
        this.xTranslation = xTranslation;
        this.yTranslation = yTranslation;
    }
}
