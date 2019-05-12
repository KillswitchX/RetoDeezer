package icesi.johann.Entity;

import java.io.Serializable;

public class Playlist implements Serializable {

    private String id;

    private String title;

    private String description;

    private int nb_tracks;

    private String picture_small;

    private String picture_big;

    private User user;

    public Playlist() {
    }

    public Playlist(String id, String title, String description, int nb_tracks, String picture_small, String picture_big, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nb_tracks = nb_tracks;
        this.picture_small = picture_small;
        this.picture_big = picture_big;
        this.user = user;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getNb_tracks() {
        return nb_tracks;
    }

    public String getPicture_small() {
        return picture_small;
    }

    public String getPicture_big() {
        return picture_big;
    }

    public User getUser() {
        return user;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNb_tracks(int nb_tracks) {
        this.nb_tracks = nb_tracks;
    }

    public void setPicture_small(String picture_small) {
        this.picture_small = picture_small;
    }

    public void setPicture_big(String picture_big) {
        this.picture_big = picture_big;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
