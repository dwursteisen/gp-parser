package com.github.gp.parser.model.tracks;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 23:04
 */
public class TrackHeader {
    private final String name;
    private final int numberOfString;
    private final int numberOfFrets;
    private final int capodastrePosition;
    private final boolean drumTrack;
    private final boolean banjoTrack;
    private final boolean twelveStringTrack;

    public TrackHeader(String name, int numberOfString, int numberOfFrets, int capodastrePosition, boolean drumTrack, boolean banjoTrack, boolean twelveStringTrack) {
        this.name = name;
        this.numberOfString = numberOfString;
        this.numberOfFrets = numberOfFrets;
        this.capodastrePosition = capodastrePosition;
        this.drumTrack = drumTrack;
        this.banjoTrack = banjoTrack;
        this.twelveStringTrack = twelveStringTrack;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfString() {
        return numberOfString;
    }

    public int getNumberOfFrets() {
        return numberOfFrets;
    }

    public int getCapodastrePosition() {
        return capodastrePosition;
    }
}
