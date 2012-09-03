package com.github.gp.parser.model.beats;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Wursteisen David
 * Date: 03/09/12
 * Time: 22:10
 */
public class BeatTest {
    @Test
    public void testCompareTo() throws Exception {
        Beat beat1 = new Beat(0, 2, 0, 5);
        Beat beat2 = new Beat(0, 3, 0, 5);
        Beat beat3 = new Beat(1, 3, 0, 5);

        assertThat(beat1.compareTo(beat2)).isLessThan(0);
        assertThat(beat1.compareTo(beat3)).isLessThan(0);
        assertThat(beat1.compareTo(beat3)).isLessThan(0);
        assertThat(beat1.compareTo(beat1)).isEqualTo(0);
    }
}
