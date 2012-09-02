package com.github.gp.parser.model;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 23:38
 */
class MeasureTrackKey {
    private final int measureIndex;
    private final int trackIndex;

    MeasureTrackKey(int trackIndex, int measureIndex) {
        this.measureIndex = measureIndex;
        this.trackIndex = trackIndex;
    }

    public int getMeasureIndex() {
        return measureIndex;
    }

    public int getTrackIndex() {
        return trackIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasureTrackKey that = (MeasureTrackKey) o;

        if (measureIndex != that.measureIndex) return false;
        if (trackIndex != that.trackIndex) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = measureIndex;
        result = 31 * result + trackIndex;
        return result;
    }
}
