package com.github.gp.parser.model.measures;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 22:39
 */
public class MeasureHeader {

    private final boolean doubleBar;
    private final boolean repeat;
    private final int numberOfAlternateEnding;
    private final int numberOfRepeats;

    public MeasureHeader(boolean doubleBar, boolean repeat, int numberOfAlternateEnding, int numberOfRepeats) {
        this.doubleBar = doubleBar;
        this.repeat = repeat;
        this.numberOfAlternateEnding = numberOfAlternateEnding;
        this.numberOfRepeats = numberOfRepeats;
    }

    public boolean isDoubleBar() {
        return doubleBar;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public int getNumberOfAlternateEnding() {
        return numberOfAlternateEnding;
    }

    public int getNumberOfRepeats() {
        return numberOfRepeats;
    }
}
