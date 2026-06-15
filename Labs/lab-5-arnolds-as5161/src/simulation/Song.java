package simulation;

import java.util.Objects;

public class Song implements Comparable<Song> {
    private final String artist;
    private final String title;

    public Song(String artist, String name) {
        this.artist = artist;
        this.title = name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;  // Same object reference check
        if (!(other instanceof Song)) return false;
        Song otherSong = (Song) other;
        return Objects.equals(this.artist, otherSong.artist) &&
                Objects.equals(this.title, otherSong.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, title);
    }

    @Override
    public int compareTo(Song other) {
        int artistCompare = this.artist.compareTo(other.artist);
        if (artistCompare == 0) {
            return this.title.compareTo(other.title);
        }
        return artistCompare;
    }

    @Override
    public String toString() {
        return "Artist: " + this.artist + ", Title: " + this.title;
    }
}
