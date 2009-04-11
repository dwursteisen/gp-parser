/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.model;

/**
 *
 * @author Dav
 */
public enum TablatureType {

    GUITAR_PRO("GP*"),
    POWERTAB("PTB");

    private String sqlType;
    private TablatureType(String sqlType) {
        this.sqlType = sqlType;
    }
}
