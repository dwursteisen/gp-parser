/*
 * SVGModel.java
 *
 * Created on 21 juillet 2007, 22:32
 *
 *
 * See the enclosed file COPYING for license information (LGPL). If you
 * did not receive this file, see http://www.fsf.org/copyleft/lgpl.html.
 */

package net.sourceforge.musicsvg.svg.model;

import net.sourceforge.musicsvg.svg.*;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.musicsvg.model.PersistantObject;

/**
 *
 * @author Dav
 */
public class SVGModel implements PersistantObject {
    
    // use a vector here
    private List<SVGModel> childrens = new ArrayList<SVGModel>();
    
    private int x = 0;
    private int y = 0;
    private int width = 1;
    private int height = 1;
    private double rotation = 0;
    private Integer id = null;
    private String type = null;
    private SVGWidget svgWidget;
    
    // ref to parent ?
    private Integer parentId;
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    
    public String getType() {
        return this.type;
    }
    
    @Override
    public Integer getId() {
        return this.id;
    }
    
    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    @Override
    public void setId(final Integer id) {
        this.id = id;
    }
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setParentId(final Integer id) {
        this.parentId = id;
    }
    
    // Parent ID will be deleted...or not :P
    @Deprecated
    public void addChild(final SVGModel child) {
        child.setParentId(this.getId());
        childrens.add(child);
    }
    
    @Deprecated
    public List<SVGModel> getChilds() {
        return childrens;
    }
    
    public void setHeight(final int expResult) {
        this.height = expResult;
    }

    public void setWidth(final int expResult) {
        this.width = expResult;
    }

    public SVGWidget getSvgWidget() {
        return svgWidget;
    }

    public void setSvgWidget(SVGWidget svgWidget) {
        this.svgWidget = svgWidget;
    }
    
    public double getRotation() {
        return this.rotation;
    }
    public void setRotation(final double r) {
        this.rotation = r;
    }
    
    public void removeChild(final String type) {
        this.childrens.remove(type);
    }
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID: " + getId());
        buffer.append(" - NbChild: " + getChilds().size());
        return buffer.toString();
    }

    public List<SVGModel> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<SVGModel> childrens) {
        this.childrens = childrens;
    }

    
}
