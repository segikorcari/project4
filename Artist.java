import java.util.ArrayList;
import java.util.List;

public class Artist {
    private int id;
    private String name;
    private List<Album> albums;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }
}
