package icesi.johann.Entity;

import android.graphics.drawable.Drawable;

public class Song {

    private String name;

    private String artist;

    private String releaseYear;

    private Drawable image;

    private Long duration;

    public Song(String name, String artist, String releaseYear, Drawable image, Long duration) {
        this.name = name;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.image = image;
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public Drawable getImage() {
        return image;
    }

    public Long getDuration() {
        return duration;
    }


}
