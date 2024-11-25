import java.util.List; // For List
import java.util.ArrayList; // For ArrayList

public class Proj4 {
    public static void main(String[] args) {
        // Create an instance of MusicDB
        MusicDB musicDB = new MusicDB();
        
        // Fetch artists and their albums
        List<Artist> artists = musicDB.getArtistsWithAlbums();

        // Display the list of artists
        System.out.println("List of Artists:");
        for (Artist artist : artists) {
            System.out.println(artist.getName());
        }

        // Display the list of albums
        System.out.println("\nList of Albums:");
        for (Artist artist : artists) {
            for (Album album : artist.getAlbums()) {
                System.out.println(album.getTitle());
            }
        }

        // Display albums grouped by artist
        System.out.println("\nAlbums by Artist:");
        for (Artist artist : artists) {
            System.out.println(artist.getName() + ":");
            for (Album album : artist.getAlbums()) {
                System.out.println("  - " + album.getTitle());
            }
        }
    }
}
