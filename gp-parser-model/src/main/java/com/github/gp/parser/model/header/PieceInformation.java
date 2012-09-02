package com.github.gp.parser.model.header;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 22:09
 */
public class PieceInformation {
    private final int tempo;
    private final int key; // TODO: replace with enum
    private final int octave;
    private final int numberOfMeasure;
    private final int numberOfTrack;

    public PieceInformation(int tempo, int key, int octave, int numberOfMeasure, int numberOfTrack) {
        this.tempo = tempo;
        this.key = key;
        this.octave = octave;
        this.numberOfMeasure = numberOfMeasure;
        this.numberOfTrack = numberOfTrack;
    }


    public int getTempo() {
        return tempo;
    }

    public int getKey() {
        return key;
    }

    public int getOctave() {
        return octave;
    }

    public int getNumberOfMeasure() {
        return numberOfMeasure;
    }

    public int getNumberOfTrack() {
        return numberOfTrack;
    }
}
