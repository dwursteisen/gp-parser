package com.github.gp.parser.model;

import com.github.gp.parser.model.header.Headers;
import com.github.gp.parser.model.header.PieceInformation;
import com.github.gp.parser.model.measures.Measure;
import com.github.gp.parser.model.measures.MeasureHeader;
import com.github.gp.parser.model.tracks.TrackHeader;
import net.sourceforge.musicsvg.io.gp.GP4Parser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 21:52
 */
public class ParserToModelListenerTest {

    private ParserToModelListener listener;

    @BeforeTest
    public void setUp() throws URISyntaxException, IOException {
        File file = new File(getClass().getResource("/test_gp4.gp4").toURI());
        GP4Parser parser = new GP4Parser();
        listener = new ParserToModelListener();

        parser.setListener(listener);
        parser.openFile(file);
        parser.close();

    }

    @Test
    public void testGetHeaders() throws Exception {
        Headers headers = listener.getHeaders();
        assertThat(headers.getVersion()).isEqualTo("FICHIER GUITAR PRO v4.06");
        assertThat(headers.getTitle()).isEqualTo("title");
    }

    @Test
    public void testGetPieceInformation() {
        PieceInformation pieceInformation = listener.getPieceInformation();
        assertThat(pieceInformation.getTempo()).isEqualTo(120);
        assertThat(pieceInformation.getNumberOfTrack()).isEqualTo(1);
        assertThat(pieceInformation.getNumberOfMeasure()).isEqualTo(3);
    }

    @Test
    public void testGetMeasureHeaders() {
        List<MeasureHeader> measureHeaders = listener.getMeasureHeaders();
        assertThat(measureHeaders).hasSize(3);
    }

    @Test
    public void testGetTrackHeaders() {
        List<TrackHeader> trackHeaders = listener.getTrackHeaders();
        assertThat(trackHeaders).hasSize(1);
        TrackHeader header = trackHeaders.get(0);
        assertThat(header.getNumberOfFrets()).isEqualTo(24);
        assertThat(header.getNumberOfString()).isEqualTo(6);
        assertThat(header.getName()).isEqualTo("Track 1");
    }

    @Test
    public void testGetMeasures() {
        List<Measure> measures = listener.getMeasures();
        assertThat(measures).hasSize(3);
        assertThat(measures.get(1).getNumberOfBeats()).isEqualTo(1);
        assertThat(measures.get(0).getMeasureIndex()).isEqualTo(0);
        assertThat(measures.get(1).getMeasureIndex()).isEqualTo(1);
        assertThat(measures.get(2).getMeasureIndex()).isEqualTo(2);
    }
}
