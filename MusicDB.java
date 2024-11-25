import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicDB {
    private static final String DB_URL = "jdbc:sqlite:music_artists.sqlite";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public List<Artist> getArtistsWithAlbums() {
        List<Artist> artists = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Query artists
            ResultSet artistRS = stmt.executeQuery("SELECT * FROM artists");
            while (artistRS.next()) {
                int artistId = artistRS.getInt("id");
                String artistName = artistRS.getString("name");
                Artist artist = new Artist(artistId, artistName);
                artists.add(artist);
            }

            // Query albums and assign to artists
            ResultSet albumRS = stmt.executeQuery("SELECT * FROM albums");
            while (albumRS.next()) {
                int albumId = albumRS.getInt("id");
                String albumTitle = albumRS.getString("title");
                int artistId = albumRS.getInt("artist_id");
                Album album = new Album(albumId, albumTitle, artistId);

                for (Artist artist : artists) {
                    if (artist.getId() == artistId) {
                        artist.addAlbum(album);
                        break;
                    }
                }
            }

            // Sort artists and albums
            Collections.sort(artists, (a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));
            for (Artist artist : artists) {
                artist.getAlbums().sort((al1, al2) -> al1.getTitle().compareToIgnoreCase(al2.getTitle()));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return artists;
    }
}
