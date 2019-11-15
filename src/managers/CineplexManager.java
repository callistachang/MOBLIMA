package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import serializers.MovieSerializer;

public class CineplexManager {
	private static final String DATABASE_NAME = "cineplexdata";
	private static ArrayList<Cineplex> records = null;
	
	// lists all cineplexes by ID
	public void listAllCineplexes() {
		for (Cineplex cx: records) {
			System.out.println("(ID: " + cx.getId() + ") " + cx.getName());
		}
	}
	
	// returns cineplex object by cineplex ID
	public Cineplex getCineplexByID(int cineplexID) {
		for (Cineplex cx: records) {
			if (cx.getId() == cineplexID)
				return cx;
		}
		return null;
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






