package com.github.gp.parser.model.measures;

import com.github.gp.parser.model.beats.Beat;

import java.util.ArrayList;
import java.util.List;

public class MeasureBuilder {
    private int trackIndex;

    private int measureIndex;

    private int numberOfBeats;

    private MeasureHeader header;
    private List<Beat> beats;

    public int getMeasureIndex() {
        return measureIndex;
    }

    public int getTrackIndex() {
        return trackIndex;
    }

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

    public MeasureBuilder withHeader(MeasureHeader header) {
        this.header = header;
        return this;
    }

    public MeasureBuilder withBeats(List<Beat> beats) {
        this.beats = new ArrayList<Beat>(beats);
        return this;
    }

    public Measure createMeasure() {
        return new Measure(trackIndex, new MeasureId(measureIndex), numberOfBeats, header, beats);
    }
}