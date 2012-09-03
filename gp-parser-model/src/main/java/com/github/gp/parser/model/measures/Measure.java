package com.github.gp.parser.model.measures;

/**
 * User: Wursteisen David Date: 02/09/12 Time: 23:35
 */
public class Measure implements Comparable<Measure> {

    private final int trackIndex;

    private final int measureIndex;

    private final int numberOfBeats;

    private final MeasureHeader header;

    public Measure(int trackIndex, int measureIndex, int numberOfBeats, MeasureHeader header) {
        this.trackIndex = trackIndex;
        this.measureIndex = measureIndex;
        this.numberOfBeats = numberOfBeats;
        this.header = header;
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
