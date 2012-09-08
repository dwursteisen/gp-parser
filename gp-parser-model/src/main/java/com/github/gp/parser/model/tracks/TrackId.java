package com.github.gp.parser.model.tracks;


import com.google.common.primitives.Ints;

public class TrackId implements Comparable<TrackId> {

    private final int trackIndex;

    public TrackId(int trackIndex) {
        this.trackIndex = trackIndex;
    }

    @Override
    public int compareTo(TrackId o) {
        return Ints.compare(trackIndex, o.trackIndex);
    }

    public int getIndex() {
        return trackIndex;
    }
}
