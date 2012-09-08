package com.github.gp.parser.model.measures;

import com.github.gp.parser.model.beats.Beat;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * User: Wursteisen David Date: 02/09/12 Time: 23:35
 */
public class Measure implements Comparable<Measure> {

    private final int trackIndex;

    private final MeasureId measureIndex;

    private final int numberOfBeats;

    private final MeasureHeader header;

    private final List<Beat> beats;

    public Measure(int trackIndex, MeasureId measureIndex, int numberOfBeats, MeasureHeader header, List<Beat> beats) {
        this.trackIndex = trackIndex;
        this.measureIndex = measureIndex;
        this.numberOfBeats = numberOfBeats;
        this.header = header;
        this.beats = beats;
    }

    public int getTrackIndex() {
        return trackIndex;
    }

    public MeasureId getMeasureIndex() {
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

        int measureCompare = this.measureIndex.compareTo(o.measureIndex);
        if (measureCompare != 0) {
            return measureCompare;
        }

        return Ints.compare(this.trackIndex, o.trackIndex);
    }
}
