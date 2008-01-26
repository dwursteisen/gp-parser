package net.sourceforge.musicsvg.model;

public enum NoteHeight {

    C(0), D(2), E(4), F(5), G(7), A(9), B(11);
    public static final NoteHeight[] SCALE = {
        NoteHeight.C, 
        NoteHeight.C, 
        NoteHeight.D, 
        NoteHeight.D, 
        NoteHeight.E, 
        NoteHeight.F, 
        NoteHeight.F,
        NoteHeight.G,
        NoteHeight.G, 
        NoteHeight.A, 
        NoteHeight.B, 
        NoteHeight.B
    };
    private int height;
    NoteHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return this.height;
    }

}
