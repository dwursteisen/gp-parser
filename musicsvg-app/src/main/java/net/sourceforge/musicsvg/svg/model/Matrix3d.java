/*
 * Matrix3d.java
 *
 * Created on 7 septembre 2007, 00:49
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.svg.model;

/**
 *
 * @author david wursteisen
 */
public class Matrix3d {
    public double m00 = 0.0;
    public double m01 = 0.0;
    public double m02 = 0.0;
    public double m10 = 0.0;
    public double m11 = 0.0;
    public double m12 = 0.0;
    public double m20 = 0.0;
    public double m21 = 0.0;
    public double m22 = 0.0;
    
    public Matrix3d() {
        
    }
    public Matrix3d(final double[] matrix) {
        this();
        this.m00 = matrix[0];
        this.m01 = matrix[1];
        this.m02 = matrix[2];
        this.m10 = matrix[3];
        this.m11 = matrix[4];
        this.m12 = matrix[5];
        this.m20 = matrix[6];
        this.m21 = matrix[7];
        this.m22 = matrix[8];
    }
    
    public void mul(final Matrix3d source) {
        double[] col0 = new double[3];
        double[] col1 = new double[3];
        double[] col2 = new double[3];
        
        col0[0] = this.m00*source.m00 + this.m01*source.m10 + this.m02*source.m20;
        col0[1] = this.m10*source.m00 + this.m11*source.m10 + this.m12*source.m20;
        col0[2] = this.m20*source.m00 + this.m21*source.m10 + this.m22*source.m20;
        
        col1[0] = this.m00*source.m01 + this.m01*source.m11 + this.m02*source.m21;
        col1[1] = this.m10*source.m01 + this.m11*source.m11 + this.m12*source.m21;
        col1[2] = this.m20*source.m01 + this.m21*source.m11 + this.m22*source.m21;
        
        col2[0] = this.m00*source.m02 + this.m01*source.m12 + this.m02*source.m22;
        col2[1] = this.m10*source.m02 + this.m11*source.m12 + this.m12*source.m22;
        col2[2] = this.m20*source.m02 + this.m21*source.m12 + this.m22*source.m22;
        
        setColumn(0, col0);
        setColumn(1, col1);
        setColumn(2, col2);
    }
    
    public void setElement(int x, int y, double value) {
        if (x == 0 && y == 0) {
            m00 = value;
        } else if (x == 1 && y == 0) {
            m10 = value;
        } else if (x == 2 && y == 0) {
            m20 = value;
        } else if (x == 0 && y == 1) {
            m01 = value;
        } else if (x == 1 && y == 1) {
            m11 = value;
        } else if (x == 2 && y == 1) {
            m21 = value;
        } else if (x == 0 && y == 2) {
            m02 = value;
        } else if (x == 1 && y == 2) {
            m12 = value;
        } else if (x == 2 && y == 2) {
            m22 = value;
        }
    }
    
    public void setIdentity() {
        double[] col0 = {1, 0, 0};
        setColumn(0, col0);
        double[] col1 = {0, 1, 0};
        setColumn(1, col1);
        double[] col2 = {0, 0, 1};
        setColumn(2, col2);
    }
    
    public void setColumn(int column, double col[]) {
        switch(column) {
            case 0:
                this.m00 = col[0];
                this.m10 = col[1];
                this.m20 = col[2];
                break;
                
           case 1:
                this.m01 = col[0];
                this.m11 = col[1];
                this.m21 = col[2];
                break;
                
           case 2:
                this.m02 = col[0];
                this.m12 = col[1];
                this.m22 = col[2];
                break;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        return equals((Matrix3d)obj);
    }
    
    public boolean equals(Matrix3d tmp) {
        
        boolean res =
                this.m00 == tmp.m00 &&
                this.m01 == tmp.m01 &&
                this.m02 == tmp.m02 &&
                this.m10 == tmp.m10 &&
                this.m11 == tmp.m11 &&
                this.m12 == tmp.m12 &&
                this.m20 == tmp.m20 &&
                this.m21 == tmp.m21 &&
                this.m22 == tmp.m22;
        return res;
    }
    
    
    // TODO: this method
    // if m1.equals(m2) then
    // m1.hashCode() == m2.hashCode
    @Override
    public int hashCode() {
        // throw new UnsupportedOperationException();
        return 0;
    }
}
