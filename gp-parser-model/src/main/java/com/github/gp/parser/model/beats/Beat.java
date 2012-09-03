package com.github.gp.parser.model.beats;

public class Beat {
    private final int beatIndex;

    private final int fret;

    private final int string;

    private final int duration; // should be an enum ?

    public Beat(int beatIndex, int fret, int string, int duration) {
        this.beatIndex = beatIndex;
        this.fret = fret;
        this.string = string;
        this.duration = duration;
    }

    public int getBeatIndex() {
        return beatIndex;
    }

    public int getFret() {
        return fret;
    }

    public int getString() {
        return string;
    }

    public int getDuration() {
        return duration;
    }
}
