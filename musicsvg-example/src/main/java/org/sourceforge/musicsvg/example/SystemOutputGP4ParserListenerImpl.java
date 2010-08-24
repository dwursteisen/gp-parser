/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sourceforge.musicsvg.example;

import java.io.File;
import net.sourceforge.musicsvg.io.gp.listeners.GP4ParserListener;

/**
 *
 * @author david
 */
public class SystemOutputGP4ParserListenerImpl implements GP4ParserListener{

   
    private void print(String title, String...content) {
        StringBuilder builder = new StringBuilder();

        for(String str : content) {
            builder.append(str);
            builder.append(" ");
        }
        
        
        System.out.print("+--------------------+------------------------------------------------------------+\n");
        System.out.format("|%20s|%60s|\n", title, builder.toString());

    }

    private void print(String title, boolean... booleans) {
        String strings[] = new String[booleans.length];
        for(int i = 0; i < booleans.length; i++) {
            strings[i] = booleans[i] ? "Yes" : "No";
        }
        print(title, strings);
    }


    private void print (String title, int...integers) {
         String strings[] = new String[integers.length];
        for(int i = 0; i < integers.length; i++) {
            strings[i] = ""+integers[i];
        }
        print(title, strings);
    }


    public void open(File file) {
        System.out.println("====================================");
        System.out.println("Opening the file..."+file.getName());
    }

    public void close() {
        System.out.println("Closing the file...");
    }

    public void readVersion(String version) {
        print("Version", version);
    }

    public void readTitle(String title) {
        print("Title", title);
    }

    public void readSubTitle(String subtitle) {
        print("subTitle", subtitle);
    }

    public void readArtist(String artist) {
        print("artist", artist);
    }

    public void readAlbum(String album) {
        print("album", album);
    }

    public void readSongAuthor(String songAuthor) {
        print("song author", songAuthor);
    }

    public void readCopyright(String copyright) {
        print("copyright", copyright);
    }

    public void readPieceAuthor(String pieceAuthor) {
        print("pieceAuthor", pieceAuthor);
    }

    public void readInstruction(String instructions) {
        print("instructions", instructions);
    }

    public void readNumberOfNotices(int nbNotes) {
        print("number of Notices", ""+nbNotes);
    }

    public void readNotice(String note) {
        print("notice", note);
    }

    public void readIsTripleFeel(boolean isTripleFeel) {
        print("is Tripe Feel ?", isTripleFeel);
    }

    public void readTempoValue(int tempo) {
        print("tempo value", tempo);
    }

    public void readKey(int key) {
       print("key", key);
    }

    public void readOctave(int octave) {
        print("octave", octave);
    }

    public void readMidiChannel(int port, int channel, int instrument, byte volume, byte balance, byte chorus, byte reverb, byte phaser, byte tremolo) {
        print("Midi Channel[0]", port, channel, instrument, volume, balance);
        print("Midi Channel[1]", chorus, reverb, phaser, tremolo);
    }

    public void readNumberOfTracks(int numberOfTracks) {
        print("number of tracks", numberOfTracks);
    }

    public void readNumberOfMesures(int numberOfMesures) {
        print("number of mesures", numberOfMesures);
    }

    public void readMesureMarker(int number, String name, int r, int g, int b) {
        
    }

    public void readMeasureTonality(int tonality) {
        
    }

    public void readMeasureHeader(int number, int numerator, int denominator, boolean repeatStart, boolean doubleBar, int numberOfAlternateEnding, int numberOfRepetitions) {
        
    }

    public void readTrackMidiParameter(int trackIndex, int port, int channelIndex, int effects, int numberOfFrets, int capo, int r, int g, int b) {
        
    }

    public void readTrackParameter(int trackIndex, String name, int numberOfStrings, boolean isDrumsTrack, boolean is12StringedGuitarTrack, boolean isBanjoTrack) {
        
    }

    public void readStringTunning(int number, int stringIndex, int tunning) {
        
    }

    public void readBeat(int track, int mesure, int beat, int duration, boolean dottedNotes) {
        
    }

    public void readEmptyBeat(int track, int mesure, int beat, boolean emptyBeat, boolean restBeat) {
        
    }

    public void readTuplet(int track, int mesure, int beat, int tuplet) {
        
    }

    public void readNumberOfBeats(int track, int mesure, int numberOfBeats) {
        
    }

    public void readStringPlayed(int track, int mesure, int beat, int stringsPlayed) {
        
    }

    public void readNote(int track, int mesure, int beat, int stringPlayer, int numberOfFret, int duration) {
        
    }

    public void readNoteParameter(int track, int mesure, int beat, boolean accentuated, boolean ghostNote, boolean dotted) {
        
    }

}
