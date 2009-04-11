/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.model;

import java.io.File;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Dav
 */
@Entity
public class UserConfiguration implements PersistantObject, Serializable {

    File guitarProFile;
    File powerTabFile;
    @Id
    Integer id;

    public File getGuitarProFile() {
        return guitarProFile;
    }

    public void setGuitarProFile(File guitarProFile) {
        this.guitarProFile = guitarProFile;
    }

    public File getPowerTabFile() {
        return powerTabFile;
    }

    public void setPowerTabFile(File powerTabFile) {
        this.powerTabFile = powerTabFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
