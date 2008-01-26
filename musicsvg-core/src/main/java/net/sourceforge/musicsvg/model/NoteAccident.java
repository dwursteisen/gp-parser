/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.model;

/**
 *
 * @author Dav
 */
public enum NoteAccident {

    Flat(-1), None(0), Sharp(1);

    private int accident;

    private NoteAccident(int accident) {
        this.accident = accident;
    }
    
    public int getAccident() {
        return this.accident;
    }
    
    public static final NoteAccident[] SCALE = {
        NoteAccident.None, 
        NoteAccident.Sharp, 
        NoteAccident.None, 
        NoteAccident.Sharp, 
        NoteAccident.None, 
        NoteAccident.None, 
        NoteAccident.Sharp,
        NoteAccident.None,
        NoteAccident.Sharp, 
        NoteAccident.None, 
        NoteAccident.None, 
        NoteAccident.Sharp
    };
    
}
