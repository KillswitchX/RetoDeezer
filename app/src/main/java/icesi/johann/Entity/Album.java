package icesi.johann.Entity;

public class Album {

    private String id;
    private String title;
    private String cover_medium;
    private String release_date;


    public Album() {

    }

    public Album(String id, String title, String cover_medium, String release_date) {
        this.id = id;
        this.title = title;
        this.cover_medium = cover_medium;
        this.release_date=release_date;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_medium() {
        return cover_medium;
    }

    public void setCover_medium(String cover_medium) {
        this.cover_medium = cover_medium;
    }
}
