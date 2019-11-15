package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import serializers.AbstractSerializer;
import serializers.CinemaSerializer;
import serializers.MovieSerializer;

public class CineplexManager {
	private static final String DATABASE_NAME = "cineplexdata";
	private static ArrayList<Cineplex> records = null;
	
	public Cineplex getCineplexByID(int cineplexID) {
		for (Cineplex cx: records) {
			if (cx.getId() == cineplexID) {
				return cx;
			}
		}
		return null;
	}
	
	// lists all cineplexes by ID
	public void listAllCineplexes() {
		for (Cineplex cx: records) {
			System.out.println("(ID: " + cx.getId() + ") " + cx.getName());
		}
	}
	
	// gets movies by cineplex
	public ArrayList<Movie> getAllMovies(int cineplexID) {
		Cineplex cineplex = getCineplexByID(cineplexID);
		return cineplex.getMovies();
	}
	
	// gets showing movies by cineplex
	public ArrayList<Movie> getShowingMovies(int cineplexID) {
		Cineplex cineplex = getCineplexByID(cineplexID);
		return cineplex.getShowingMovies();
	}
	
//	public void listAllShowtimesInCineplexByMovie(int cineplexID, int movieID) {
//		Cineplex cineplex = getCineplexByID(cineplexID);
//		ArrayList<Cinema> cinemas = cineplex.getCinemas();
//		CinemaManager cm = new CinemaManager();
//		
//		for (Cinema cinema: cinemas) {
//			cm.getShowtimes(cinema, movieID);
//		}
//	}
	
	public void listAllSeatAvailabilitiesInCineplexByMovie(int cineplexID, int movieID) {
		Cineplex cineplex = getCineplexByID(cineplexID);
		ArrayList<Cinema> cinemas = cineplex.getCinemas();
		CinemaManager cm = new CinemaManager();
		
		for (Cinema cinema: cinemas) {
			cm.listAvailabilitiesForMovie(cinema, movieID);
	}
}

	public void listAll() {
		// TODO Auto-generated method stub
		
	}

	public void listAllCinemasByCineplex(int cineplexID) {
		// TODO Auto-generated method stub
		
	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new CineplexSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new CineplexSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}




