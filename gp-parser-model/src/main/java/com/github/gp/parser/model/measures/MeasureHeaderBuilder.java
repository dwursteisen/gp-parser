package com.github.gp.parser.model.measures;

public class MeasureHeaderBuilder {
    private boolean doubleBar;

    private boolean repeat;

    private int numberOfAlternateEnding;

    private int numberOfRepeats;

    private int measureIndex;

    public MeasureHeaderBuilder withDoubleBar(boolean doubleBar) {
        this.doubleBar = doubleBar;
        return this;
    }

    public MeasureHeaderBuilder withRepeat(boolean repeat) {
        this.repeat = repeat;
        return this;
    }

    public MeasureHeaderBuilder withNumberOfAlternateEnding(int numberOfAlternateEnding) {
        this.numberOfAlternateEnding = numberOfAlternateEnding;
        return this;
    }

    public MeasureHeaderBuilder withNumberOfRepeats(int numberOfRepeats) {
        this.numberOfRepeats = numberOfRepeats;
        return this;
    }

    public MeasureHeaderBuilder withMeasureIndex(int index) {
        this.measureIndex = index;
        return this;
    }

    public MeasureHeader createMeasureHeader() {
        return new MeasureHeader(new MeasureId(measureIndex), doubleBar, repeat, numberOfAlternateEnding,
                numberOfRepeats);
    }
}