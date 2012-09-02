package com.github.gp.parser.model.measures;

public class MeasureHeaderBuilder {
    private boolean doubleBar;
    private boolean repeat;
    private int numberOfAlternateEnding;
    private int numberOfRepeats;

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

    public MeasureHeader createMeasureHeader() {
        return new MeasureHeader(doubleBar, repeat, numberOfAlternateEnding, numberOfRepeats);
    }
}