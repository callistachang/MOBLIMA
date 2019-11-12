package managers;

import java.util.ArrayList;
import java.util.HashSet;

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
	
	private void create(Movie movie) {
		records.add(movie); // add to records
		updateDatabase();
	}
	
	public void update(int movieID, ArrayList<Integer> attrNums, ArrayList<String> attrChanges) {
		Movie updatedMovie = getUpdatedMovie(movieID, attrNums, attrChanges);
		remove(movieID);
		create(updatedMovie);
		showMovieInfo(movieID);
	}
	
	public void remove(int movieID) {
		Movie movie = getMovieByID(movieID);
		records.remove(movie);
		updateDatabase();
	}
	
	public void listAll() {
		for (Movie m: records) {
			System.out.println("(ID: " + m.getId() + ") " + m.getTitle());
		}
	}
	
	public Movie getMovieByID(int movieID) {
		for (Movie m: records) {
			if (m.getId() == movieID) {
				return m;
			}
		}
		return null;
	}
	
	// should i put this under movie
	public void showMovieInfo(int movieID) {
		for (Movie m: records) {
			if (m.getId() == movieID) {
				System.out.println("Title: " + m.getTitle());
				System.out.println("Showing Status: " + m.getStatus().toString());
				System.out.println("Synopsis: " + m.getSynopsis());
				System.out.println("Duration: " + m.getDuration() + " minutes");
				System.out.println("Movie Type: " + m.getType().toString());
				System.out.println("Director: " + m.getDirector());
				System.out.print("Cast Members: ");
				ArrayList<String> casts = m.getCasts();
				int i;
				for (i = 0; i < casts.size()-1; i++) {
					System.out.printf(casts.get(i) + ", ");
				}
				System.out.println(casts.get(i));
				break;
			}
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
	
	public void listAttributes() {
		ArrayList<String> attributes = Movie.getChangeableAttributes();
		for (int i = 1; i <= attributes.size(); i++) {
			System.out.printf("(%d) %s \n", i, attributes.get(i-1));
		}
	}
	
	private static void initializeDatabase() {
		ArrayList<String> movieData = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new MovieSerializer();
		records = serializer.deserialize(movieData);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new MovieSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
	
	private Movie getUpdatedMovie(int movieID, ArrayList<Integer> attrNums, ArrayList<String> attrChanges) {
		Movie movie = getMovieByID(movieID);
		int i = 0;
		
		if (attrNums.get(i) == 1) {
			movie.setTitle(attrChanges.get(i));
			if (++i >= attrNums.size()) return movie;
		}
		if (attrNums.get(i) == 2) {
			movie.setStatus(ShowingStatus.getByValue(attrChanges.get(i)));
			if (++i >= attrNums.size()) return movie;
		}
		if (attrNums.get(i) == 3) {
			movie.setSynopsis(attrChanges.get(i));
			if (++i >= attrNums.size()) return movie;
		}
		if (attrNums.get(i) == 4) {
			movie.setDirector(attrChanges.get(i));
			if (++i >= attrNums.size()) return movie;
		}
		if (attrNums.get(i) == 5) {
			movie.setDuration(Integer.parseInt(attrChanges.get(i)));
			if (++i >= attrNums.size()) return movie;
		}
		if (attrNums.get(i) == 6) {
			movie.setType(MovieType.getByValue(attrChanges.get(i)));
			if (++i >= attrNums.size()) return movie;
		}
		return movie;
	}
}
