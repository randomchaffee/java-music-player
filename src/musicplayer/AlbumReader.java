package musicplayer;

import java.io.BufferedReader;
import java.io.IOException;

public class AlbumReader {
	BufferedReader reader;
	
	// constructor
	public AlbumReader(BufferedReader reader) {
		this.reader = reader;
	}
	
	// read the first line of the album text file (album details)
	public Album readAlbum() {
		try {
			String line = reader.readLine();
			if (line == null) {
				System.out.println("Error: Album file is empty.");
				return null;
			}
			
			String[] data = line.split(",");
			
			if (data.length < 4) {
				System.out.println("Error: Album data is incomplete ->" + line);
				return null;
			}
			
			int duration = parseDuration(data[3]);
			return new Album(data[0].trim(), data[1].trim(), data[2].trim(), duration);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// read song data after the first line
	public void readSongs(Album album) {
		if (album == null) {
			System.out.println("Error: Album is null.");
			return;
		}
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				
				if (data.length < 4) {
					System.out.println("invalid song line. skipped " + line);
					continue;
				}
				
				int trackNum = Integer.parseInt(data[0].trim());
				String title = data[1].trim();
				String artist = data[2].trim();
				String length = data[3].trim();
				
				album.addSong(new Song(trackNum, title, artist, length));
				
				// This line is an error! makes the program skip every second song!
				// line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// helper method to parse mm:ss or int
	private int parseDuration(String text) {
		text = text.trim();
		
		// replace ung error in txt file
		text = text.replace(";", ":");
		
		if (text.contains(":")) {
			try {
				String[] parts = text.split(":");
				int minutes = Integer.parseInt(parts[0]);
				int seconds = Integer.parseInt(parts[1]);
				return minutes * 60 + seconds;
			} catch (NumberFormatException e) {
				System.out.println("Invalid duration format: " + text);
				return 0;
			}
		} else {
			return Integer.parseInt(text);
		}
	}
}
