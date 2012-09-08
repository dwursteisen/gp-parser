package com.github.gp.parser.model.tracks;

public class TrackHeaderBuilder {
    private int trackIndex;

    private String name;

    private int numberOfString;

    private int numberOfFrets;

    private int capodastrePosition;

    private boolean drumTrack;

    private boolean banjoTrack;

    private boolean twelveStringTrack;

    public TrackHeaderBuilder withTrackIndex(int trackIndex) {
        this.trackIndex = trackIndex;
        return this;
    }

    public TrackHeaderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TrackHeaderBuilder withNumberOfString(int numberOfString) {
        this.numberOfString = numberOfString;
        return this;
    }

    public TrackHeaderBuilder withNumberOfFrets(int numberOfFrets) {
        this.numberOfFrets = numberOfFrets;
        return this;
    }

    public TrackHeaderBuilder withCapodastrePosition(int capodastrePosition) {
        this.capodastrePosition = capodastrePosition;
        return this;
    }

    public TrackHeader createTrackHeader() {
        return new TrackHeader(new TrackId(trackIndex), name, numberOfString, numberOfFrets,
                capodastrePosition, drumTrack, banjoTrack, twelveStringTrack);
    }

    public TrackHeaderBuilder withDrumTrack(boolean drumTrack) {
        this.drumTrack = drumTrack;
        return this;
    }

    public TrackHeaderBuilder withBanjoTrack(boolean banjoTrack) {
        this.banjoTrack = banjoTrack;
        return this;
    }

    public TrackHeaderBuilder withTwelveStringTrack(boolean twelveStringTrack) {
        this.twelveStringTrack = twelveStringTrack;
        return this;
    }
}