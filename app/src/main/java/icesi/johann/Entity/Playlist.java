package icesi.johann.Entity;

import android.graphics.drawable.Drawable;

public class Playlist {

    private String name;

    private String owner;

    private int numberSongs;

    private Drawable image;


    public Playlist(String name, String owner, int numberSongs, Drawable image) {
        this.name = name;
        this.owner = owner;
        this.numberSongs = numberSongs;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setNumberSongs(int numberSongs) {
        this.numberSongs = numberSongs;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public int getNumberSongs() {
        return numberSongs;
    }

    public Drawable getImage() {
        return image;
    }
}
