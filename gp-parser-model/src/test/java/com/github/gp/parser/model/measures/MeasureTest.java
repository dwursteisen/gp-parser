package com.github.gp.parser.model.measures;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 23:59
 */
public class MeasureTest {
    @Test
    public void testCompareTo() throws Exception {
        Measure measure1 = new Measure(0, 1, 5);
        Measure measure2 = new Measure(0, 2, 5);
        Measure measure3 = new Measure(1, 1, 5);
        Measure measure4 = new Measure(1, 2, 5);

        // expected = 1,3,2,4
        assertThat(measure1.compareTo(measure1)).isEqualTo(0);
        assertThat(measure1.compareTo(measure2)).isLessThan(0);
        assertThat(measure1.compareTo(measure3)).isLessThan(0);
        assertThat(measure1.compareTo(measure4)).isLessThan(0);

        assertThat(measure2.compareTo(measure3)).isGreaterThan(0);
        assertThat(measure2.compareTo(measure4)).isLessThan(0);

        assertThat(measure4.compareTo(measure1)).isGreaterThan(0);
        assertThat(measure4.compareTo(measure2)).isGreaterThan(0);
        assertThat(measure4.compareTo(measure3)).isGreaterThan(0);
        assertThat(measure4.compareTo(measure4)).isEqualTo(0);
    }
}
