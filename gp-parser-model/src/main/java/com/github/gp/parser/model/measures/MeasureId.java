package com.github.gp.parser.model.measures;

import com.google.common.primitives.Ints;

public class MeasureId implements Comparable<MeasureId> {
    private final int index;

    public MeasureId(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeasureId measureId = (MeasureId) o;

        if (index != measureId.index) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public int compareTo(MeasureId o) {
        if (o == null) {
            return 1;
        }
        return Ints.compare(index, o.index);
    }

    public int getIndex() {
        return index;
    }
}
