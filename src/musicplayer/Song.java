package musicplayer;

import java.io.Serializable;

public class Song implements Serializable {
	// has a track number, title, artist, and duration
	private static final long serialVersionUID = 1L;
	private int trackNumber;
	private String title;
	private String duration;
	private String artist;
	
	// constructor
	public Song(int trackNumber, String title, String duration, String artist) {
		this.trackNumber = trackNumber;
		this.title = title;
		this.duration = duration;
		this.artist = artist;
	}
	
	// print song info
	public void showSong() {
		System.out.printf("%-2d | %-40s - %-30s | %-6s \n", this.trackNumber, this.title, this.artist, this.duration);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
}
