package com.github.gp.parser.model;

public class PieceInformationBuilder {
    private int tempo;
    private int key;
    private int octave;
    private int numberOfMeasure;
    private int numberOfTrack;

    public PieceInformationBuilder withTempo(int tempo) {
        this.tempo = tempo;
        return this;
    }

    public PieceInformationBuilder withKey(int key) {
        this.key = key;
        return this;
    }

    public PieceInformationBuilder withOctave(int octave) {
        this.octave = octave;
        return this;
    }

    public PieceInformationBuilder withNumberOfMeasure(int numberOfMeasure) {
        this.numberOfMeasure = numberOfMeasure;
        return this;
    }

    public PieceInformationBuilder withNumberOfTrack(int numberOfTrack) {
        this.numberOfTrack = numberOfTrack;
        return this;
    }

    public PieceInformation createPieceInformation() {
        return new PieceInformation(tempo, key, octave, numberOfMeasure, numberOfTrack);
    }
}