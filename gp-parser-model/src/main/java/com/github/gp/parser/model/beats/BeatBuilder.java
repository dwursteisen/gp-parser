package com.github.gp.parser.model.beats;

public class BeatBuilder {
    private int beatIndex;

    private int fret;

    private int string;

    private int duration;

    public BeatBuilder withBeatIndex(int beatIndex) {
        this.beatIndex = beatIndex;
        return this;
    }

    public BeatBuilder withFret(int fret) {
        this.fret = fret;
        return this;
    }

    public BeatBuilder withString(int string) {
        this.string = string;
        return this;
    }

    public BeatBuilder withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Beat createBeat() {
        return new Beat(beatIndex, string, fret, duration);
    }
}