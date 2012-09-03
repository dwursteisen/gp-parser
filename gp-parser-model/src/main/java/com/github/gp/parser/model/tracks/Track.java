package com.github.gp.parser.model.tracks;

import com.github.gp.parser.model.measures.Measure;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private final int trackIndex;

    private final List<Measure> measures;

    private final TrackHeader header;

    public Track(int trackIndex, List<Measure> measures, TrackHeader header) {
        this.trackIndex = trackIndex;
        this.measures = measures;
        this.header = header;
    }

    public int getTrackIndex() {
        return trackIndex;
    }

    public List<Measure> getMeasures() {
        return new ArrayList<Measure>(measures);
    }

    public TrackHeader getHeader() {
        return header;
    }
}
