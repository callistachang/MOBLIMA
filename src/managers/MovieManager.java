package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Movie;
import serializers.MovieSerializer;
import serializers.AbstractSerializer;

public class MovieManager {
	private static final String DATABASE_NAME = "moviedata";
	private static ArrayList<Movie> records = null;
	
//	public static void main(String[] args) {
//		MovieManager mm = new MovieManager();
//		mm.initializeDatabase();
//	}
	
	// Initialize database into static variable, records
	public void initializeDatabase() {
		records = new ArrayList<Movie>();
		ArrayList<String> movieData = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new MovieSerializer();
		records = serializer.deserialize(movieData);
	}
	
	public void create(String title, String showingStatus, String director, String synopsis, ArrayList<String> casts, int duration) {
		if (records == null) {
			initializeDatabase();
		}
				
		// create new movie
		Movie movie = new Movie(title, showingStatus, synopsis, director, casts, duration);
		
		// add movie to records
		records.add(movie);
		
		updateDatabase();
	}
	
	public void updateDatabase() {
		AbstractSerializer serializer = new MovieSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
	
	public void update(int rowNumber) {
		if (records == null) {
			initializeDatabase();
		}
	}
	
	public int listAll() {
		if (records == null) {
			initializeDatabase();
		}
		int i = 1;
		// List all movie titles in the database
		
		for (int j = 0; i < records.size(); i++) {
			((Movie) records.get(j)).getTitle();
		}
		
		for (Movie m: records) {
			System.out.println("(" + i + ") " + m.getTitle());
			i++;
		}
		// Return the number of movies there are
		return records.size();
	}
	
	public void listByTitle() {
		
	}
}
