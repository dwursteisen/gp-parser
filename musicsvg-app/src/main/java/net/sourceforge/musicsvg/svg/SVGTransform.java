/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg;

import net.sourceforge.musicsvg.svg.model.Matrix3d;

/**
 *
 * @author Dav
 */
public interface SVGTransform {

    Matrix3d getRotationMatrix();

    String getSVGTransformation();

    Matrix3d getScaleMatrix();

    Matrix3d getTransformationMatrix();

    Matrix3d getTranslationMatrix();

    void setRotation(double angle);

    void setScale(double xScale, double yScale);

    void setTranslation(double xTranslation, double yTranslation);

}
