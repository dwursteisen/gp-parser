package com.github.gp.parser.model.measures;

public class MeasureBuilder {
    private int trackIndex;
    private int measureIndex;
    private int numberOfBeats;

    public MeasureBuilder withTrackIndex(int trackIndex) {
        this.trackIndex = trackIndex;
        return this;
    }

    public MeasureBuilder withMeasureIndex(int measureIndex) {
        this.measureIndex = measureIndex;
        return this;
    }

    public MeasureBuilder withNumberOfBeats(int numberOfBeats) {
        this.numberOfBeats = numberOfBeats;
        return this;
    }

    public Measure createMeasure() {
        return new Measure(trackIndex, measureIndex, numberOfBeats);
    }
}