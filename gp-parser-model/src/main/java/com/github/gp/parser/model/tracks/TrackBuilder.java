package com.github.gp.parser.model.tracks;

import com.github.gp.parser.model.measures.Measure;

import java.util.List;

public class TrackBuilder {
    private int trackIndex;

    private List<Measure> measures;

    private TrackHeader header;

    public TrackBuilder withTrackIndex(int trackIndex) {
        this.trackIndex = trackIndex;
        return this;
    }

    public TrackBuilder withMeasures(List<Measure> measures) {
        this.measures = measures;
        return this;
    }

    public TrackBuilder withHeader(TrackHeader header) {
        this.header = header;
        return this;
    }

    public Track createTrack() {
        return new Track(new TrackId(trackIndex), measures, header);
    }
}