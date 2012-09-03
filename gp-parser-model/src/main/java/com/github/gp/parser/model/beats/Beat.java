package com.github.gp.parser.model.beats;

public class Beat implements Comparable<Beat> {
    private final int beatIndex;

    private final int fret;

    private final int string;

    private final int duration; // should be an enum ?

    public Beat(int beatIndex, int string, int fret, int duration) {
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

    @Override
    public int compareTo(Beat o) {
        if (beatIndex > o.beatIndex) {
            return 1;
        } else if (beatIndex < o.beatIndex) {
            return -1;
        }

        if (string > o.string) {
            return 1;
        } else if (string < o.string) {
            return -1;
        }
        return 0;
    }
}
