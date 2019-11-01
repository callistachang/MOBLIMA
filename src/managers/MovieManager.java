package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Movie;
import serializers.MovieSerializer;

public class MovieManager {
	private static final String DATABASE_NAME = "moviedata";
	protected static ArrayList<Movie> records = null;
	
	// Initialize database into static variable, records
	public void initializeDatabase() {
		records = new ArrayList<Movie>();
		ArrayList<String> movieData = DatabaseHandler.readDatabase(DATABASE_NAME);
		records = MovieSerializer.deserialize(movieData);
	}
	
	public void create(String title, String showingStatus, String director, String synopsis, ArrayList<String> casts, int duration) {
		if (records == null) {
			initializeDatabase();
		}
		
		// create new movie
		Movie movie = new Movie(title, showingStatus, synopsis, director, casts, duration);
		
		// add movie to records
		records.add(movie);
		
		// serialize records so we can update the database
		ArrayList<String> dataToAdd = MovieSerializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, dataToAdd);
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
