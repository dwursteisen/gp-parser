package com.github.gp.parser.model.header;

public class HeadersBuilder {
    private String version;
    private String title;
    private String subtitle;
    private String interpret;
    private String album;
    private String songAuthor;

    public HeadersBuilder withVersion(String version) {
        this.version = version;
        return this;
    }

    public HeadersBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public HeadersBuilder withSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public HeadersBuilder withInterpret(String interpret) {
        this.interpret = interpret;
        return this;
    }

    public HeadersBuilder withAlbum(String album) {
        this.album = album;
        return this;
    }

    public HeadersBuilder withSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
        return this;
    }

    public Headers createHeaders() {
        return new Headers(version, title, subtitle, interpret, album, songAuthor);
    }
}