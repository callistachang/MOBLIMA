package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Movie;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import serializers.MovieSerializer;
import serializers.AbstractSerializer;

public class MovieManager {
	private final static String DATABASE_NAME = "moviedata";
	private static ArrayList<Movie> records = null;
	
	public MovieManager() {
		if (records == null) {
			initializeDatabase();
		}
	}

	public void create(String title, String status, String director, String synopsis, 
			ArrayList<String> casts, int duration, String type) {
		Movie movie = new Movie(records.size()+1, title, ShowingStatus.getByValue(status), director, 
				synopsis, casts, duration, null, MovieType.getByValue(type));
		records.add(movie); // add to records
		updateDatabase();
	}
	
	public void listAll() {
		for (Movie m: records) {
			System.out.println("(ID: " + m.getId() + ") " + m.getTitle());
		}
	}
	
	public void listAllByCineplex(int cineplexID) {
		CineplexManager cxm = new CineplexManager();
		cxm.getAllMovies(cineplexID);
	}
	
	public void listShowingByCineplex(int cineplexID) {
		CineplexManager cxm = new CineplexManager();
		cxm.getShowingMovies(cineplexID);
	}
	
	protected static void initializeDatabase() {
		ArrayList<String> movieData = DatabaseHandler.readDatabase(DATABASE_NAME);
		System.out.println(movieData);
		AbstractSerializer serializer = new MovieSerializer();
		System.out.println("hi");
		records = serializer.deserialize(movieData);
	}
	
	protected void updateDatabase() {
		AbstractSerializer serializer = new MovieSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}
