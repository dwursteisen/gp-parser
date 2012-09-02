package com.github.gp.parser.model.header;

/**
 * User: Wursteisen David
 * Date: 02/09/12
 * Time: 21:05
 */
public class Headers {
    private final String version;
    private final String title;
    private final String subtitle;
    private final String interpret;
    private final String album;
    private final String songAuthor;

    public Headers(String version, String title, String subtitle, String interpret, String album, String songAuthor) {
        this.version = version;
        this.title = title;
        this.subtitle = subtitle;
        this.interpret = interpret;
        this.album = album;
        this.songAuthor = songAuthor;
    }

    public String getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getInterpret() {
        return interpret;
    }

    public String getAlbum() {
        return album;
    }

    public String getSongAuthor() {
        return songAuthor;
    }
}
