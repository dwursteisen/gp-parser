/*
 * SVGWidget.java
 * 
 * Created on 23 oct. 2007, 01:09:06
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sourceforge.musicsvg.svg;

/**
 *
 * @author Dav
 */
public enum SVGWidget {
    ROOT("g"),
    WHITE("use", "note_blanche"), 
    BLACK("use", "note"),
    ANCHOR("use", "ancre"),
    ANCHOR2("use", "ancre2"),
    ANCHOR3("use", "ancre3"),
    BAR("use", "barre"),
    SHARP("use", "sharp"),
    BECARE("use", "becare"),
    BEMOL("use", "bemol");

    private String widgetName;
    private String type;
    private SVGWidget(String type, String widgetName) {
        this.widgetName = widgetName;
        this.type = type;
    }
    
    private SVGWidget(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getWidgetName() {
        return this.widgetName;
    }
}
