package musicplayer;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable{
	// has a name, and a list of songs
	private String name;
	private ArrayList<Song> songList;
	
	// constructor
	public Playlist(String name) {
		this.name = name;
		songList = new ArrayList<Song>();
		
	}
	
	// getter
	public String getName() {
		return name;
	}
	
	//method to add a song
	public void addSong(Song song) {
		songList.add(song);
	}
	
	//print the playlist
	public void showPlaylist() {
		System.out.println("Playlist: " + name);
		for (Song song : songList) {
			song.showSong();
		}
	}
}
