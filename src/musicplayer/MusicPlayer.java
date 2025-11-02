package musicplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MusicPlayer implements Serializable{
	// has name, stores a collection(arraylist) of albums and playlists;
	private String name;
	private ArrayList<Playlist> playlistCollection;
	private ArrayList<Album> albumCollection;
	
	// constructor
	public MusicPlayer(String name) {
		this.name = name;
		playlistCollection = new ArrayList<Playlist>();
		albumCollection = new ArrayList<Album>();
	}

	
	// add album method
	public void addAlbum(Path path) {
		try {
			BufferedReader reader = Files.newBufferedReader(path);
			AlbumReader ar = new AlbumReader(reader);
			Album album = ar.readAlbum();
			ar.readSongs(album);
			albumCollection.add(album);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	// print all songs
	public void viewAllSongs() {
		System.out.println("\n=== All Songs ===");
		for (Album album : albumCollection) {
			for (Song song : album.getSongList()) {
				song.showSong();
			}
		}
	}
	
	// print all albums
	public void viewAllAlbums() {
		System.out.println("\n=== All Albums ===");
		for (Album album : albumCollection) {
			album.showAlbum();
		}
	}
	
	// find a specific album
	public void findAlbum(String name) {
		for (Album album : albumCollection) {
			if (album.getName().equalsIgnoreCase(name)) {
				System.out.println("\nAlbum found:");
				album.showAlbum();
				return;
			}
		}
		
		//print if not found
		System.out.println("\nAlbum not found: " + name);
	}
	
	//return a song from an album
	public Song getSongFromAlbum(String albumName, String songTitle) {
		for (Album album : albumCollection) {
			if (album.getName().equalsIgnoreCase(albumName)) {
				for (Song song : album.getSongList()) {
					if (song.getTitle().equalsIgnoreCase(songTitle)) {
						return song; // success galing
					}
				}
			}
		}
		
		// print if song was not found
		System.out.println("Song not found: " + songTitle);
		return null;
	}
	
	// print all songs of an artist
	public void viewAllSongsByArtist(String artist) {
		System.out.println("\n=== Songs by " + artist + " ===");
		for (Album album : albumCollection) {
			for (Song song : album.getSongList()) {
				if (song.getArtist().equalsIgnoreCase(artist)) {
					song.showSong();
				}
			}
		}
	}
	
	// add a playlist
	public void addPlaylist(String name) {
		playlistCollection.add(new Playlist(name));
	}
	
	// add a song to the playlist
	public void addSongToPlaylist(String playlistName, Song song) {
		if (song == null) {
			return; // no song
		}
		
		for (Playlist playlist : playlistCollection) {
			if (playlist.getName().equalsIgnoreCase(playlistName)) {
				playlist.addSong(song);
				System.out.println("Added " + song.getTitle() + " to playlist " + playlist.getName());
				return;
			}
		}
		
		// print if playlist was not found
		System.out.println("Playlist not found: " + playlistName);
	}
	
	// print all playlist
	public void viewAllPlaylists() {
		System.out.println("\n=== All Playlists ===");
		for (Playlist playlist : playlistCollection) {
			System.out.println(playlist.getName());
		}
	}
	
	// find a playlist being searched for
	public void findPlaylist(String name) {
		for (Playlist playlist : playlistCollection) {
			if (playlist.getName().equalsIgnoreCase(name)) {
				playlist.showPlaylist();
				return;
			}
		}
	}
	
	// load a file 
	public static MusicPlayer load(Path loadpath) {
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(loadpath));
			return (MusicPlayer) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// save to obj file
	public void save(Path savepath) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(savepath));
			out.writeObject(this);
			System.out.println("Player saved to " + savepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
