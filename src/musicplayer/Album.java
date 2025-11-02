package musicplayer;

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class Album implements Serializable {
	// has list of songs, its name, artist, release year, and total duration
	private static final long serialVersionUID = 2L;
	private ArrayList<Song> songList;
	private String name;
	private String artist;
	private int releaseYear;
	private String duration;
	
	// constructor
	public Album(String name, String artist, String duration, int releaseYear) {
		this.name = name;
		this.artist = artist;
		this.duration = duration;
		this.releaseYear = releaseYear;
		this.songList = new ArrayList<Song>();
	}
	
	//methods
	
	//method to add a song
	public void addSong(Song song) {
		this.songList.add(song);
	}
	
	// view state
	public void showAlbum() {
		System.out.printf("%s - %s (%d)\n", this.name, this.artist, this.releaseYear);
		for (Song song: songList) {
			System.out.print("  ");
			song.showSong();
		}
	}
	
	public void serializeAlbum(Path path) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));	
			out.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Album deserializeAlbum(Path path) {
		try {
			ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));
			return (Album)in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// getter and setter
	
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public ArrayList<Song> getSongList() {
		return songList;
	}
}
