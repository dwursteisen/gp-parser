package com.github.gp.parser.model;

import com.github.gp.parser.model.header.Headers;
import com.github.gp.parser.model.header.PieceInformation;
import com.github.gp.parser.model.measures.Measure;
import com.github.gp.parser.model.measures.MeasureHeader;
import com.github.gp.parser.model.measures.MeasureHeaderBuilder;
import com.github.gp.parser.model.measures.MeasureId;
import com.github.gp.parser.model.tracks.Track;
import com.github.gp.parser.model.tracks.TrackHeader;
import net.sourceforge.musicsvg.io.gp.GP4Parser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Wursteisen David Date: 02/09/12 Time: 21:52
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

    @DataProvider(name = "filename")
    public Object[][] createData(Method method) {
        return new Object[][]{{"/test_gp4.gp4"}, {"/rythme.gp4"}, {"/rythme2.gp4"},
                {"/rythme_blues.gp4"}, {"/solfege.gp4"}, {"/famous_song.gp4"}};
    }

    @Test(dataProvider = "filename")
    public void readFiles(String filename) throws Exception {
        File file = new File(getClass().getResource(filename).toURI());
        GP4Parser parser = new GP4Parser();
        ParserToModelListener listener = new ParserToModelListener();

        parser.setListener(listener);
        parser.openFile(file);
        parser.close();

        assertThat(listener.getPieceInformation()).isNotNull();
        assertThat(listener.getHeaders()).isNotNull();
        assertThat(listener.getTrackHeaders()).isNotNull();
        assertThat(listener.getMeasureHeaders()).isNotNull();
        assertThat(listener.getMeasures()).isNotNull();
        assertThat(listener.getTracks()).isNotNull();
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
        assertThat(measures.get(0).getMeasureIndex()).isEqualTo(new MeasureId(0));
        assertThat(measures.get(1).getMeasureIndex()).isEqualTo(new MeasureId(1));
        assertThat(measures.get(2).getMeasureIndex()).isEqualTo(new MeasureId(2));

        assertThat(measures.get(0).getBeats()).hasSize(5);
    }

    @Test
    public void testGetTracks() {
        List<Track> tracks = listener.getTracks();
        assertThat(tracks).hasSize(1);

        Track firstTrack = tracks.get(0);

        assertThat(firstTrack.getMeasures()).hasSize(3);

    }

    @Test
    public void shouldBuildMapMeasureHeaders() {
        List<MeasureHeaderBuilder> headersBuilder = new ArrayList<MeasureHeaderBuilder>();
        headersBuilder.add(new MeasureHeaderBuilder().withMeasureIndex(1));
        Map<MeasureId, MeasureHeader> result = listener.buildMapMeasureHeaders(headersBuilder);

        assertThat(result).hasSize(1);
        assertThat(result.get(new MeasureId(1))).isNotNull();
    }

}
