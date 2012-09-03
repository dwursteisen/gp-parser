package com.github.gp.parser.model;

class MeasureTrackBeatKey {
    private final MeasureTrackKey measureTrackKey;

    private final int beat;

    public MeasureTrackBeatKey(int trackIndex, int measureIndex, int beat) {
        this.measureTrackKey = new MeasureTrackKey(trackIndex, measureIndex);
        this.beat = beat;
    }

    public MeasureTrackKey getMeasureTrackKey() {
        return measureTrackKey;
    }

    public int getBeat() {
        return beat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MeasureTrackBeatKey that = (MeasureTrackBeatKey) o;

        if (beat != that.beat)
            return false;
        if (measureTrackKey != null ? !measureTrackKey.equals(that.measureTrackKey)
            : that.measureTrackKey != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = measureTrackKey != null ? measureTrackKey.hashCode() : 0;
        result = 31 * result + beat;
        return result;
    }
}
