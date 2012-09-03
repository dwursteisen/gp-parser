package com.github.gp.parser.model.measures;

import com.github.gp.parser.model.beats.Beat;

import java.util.List;

/**
 * User: Wursteisen David Date: 02/09/12 Time: 23:35
 */
public class Measure implements Comparable<Measure> {

    private final int trackIndex;

    private final int measureIndex;

    private final int numberOfBeats;

    private final MeasureHeader header;

    private final List<Beat> beats;

    public Measure(int trackIndex, int measureIndex, int numberOfBeats, MeasureHeader header, List<Beat> beats) {
        this.trackIndex = trackIndex;
        this.measureIndex = measureIndex;
        this.numberOfBeats = numberOfBeats;
        this.header = header;
        this.beats = beats;
    }

    public int getTrackIndex() {
        return trackIndex;
    }

    public int getMeasureIndex() {
        return measureIndex;
    }

    public int getNumberOfBeats() {
        return numberOfBeats;
    }

    public MeasureHeader getHeader() {
        return header;
    }

    public List<Beat> getBeats() {
        return beats;
    }

    public int compareTo(Measure o) {
        boolean isSameMeasure = o.getMeasureIndex() == measureIndex;
        boolean isSameTrack = o.getTrackIndex() == trackIndex;
        if (isSameMeasure && isSameTrack) {
            return 0;
        }

        if (isSameMeasure) {
            return trackIndex < o.getTrackIndex() ? -1 : 1;
        }

        return measureIndex < o.getMeasureIndex() ? -1 : 1;
    }
}
