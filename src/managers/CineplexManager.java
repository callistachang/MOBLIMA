package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.CinemaSerializer;
import serializers.CineplexSerializer;
import serializers.MovieSerializer;

public class CineplexManager {
	private static final String DATABASE_NAME = "cineplexdata";
	private static ArrayList<Cineplex> records = null;
	
	public CineplexManager() {
		if (records == null)
			initializeDatabase();
	}
	
	public Cineplex getCineplexByID(String cineplexID) {
		for (Cineplex cx: records) {
			if (cx.getId().equals(cineplexID)) {
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
	
	public void listCineplexByMovie(int movieID) {
		MovieManager mm = new MovieManager();
		Movie movie = mm.getMovieByID(movieID);

		Boolean printed = false;
		for (Cineplex cx: records) {
			printed = false;
			ArrayList<Cinema> cinemas = cx.getCinemas();
			for (Cinema c: cinemas) {
				ArrayList<Showtime> showtimes = c.getShowtimes();
				for (Showtime s: showtimes) {
					if (movie == s.getMovie() & !printed) {
						Printer.printMovieInfo(movie);
						printed = true;
					}
				}
			}
		}
	}
	
	public void listAllMovies(String cineplexID) {
		Cineplex cx = getCineplexByID(cineplexID);
		
		Boolean printed = false;
		
		ArrayList<Cinema> cinemas = cx.getCinemas();
		System.out.println("Cinemas: " + cinemas);
		for (Cinema c: cinemas) {
			ArrayList<Showtime> showtimes = c.getShowtimes();
			for (Showtime s: showtimes) {
				Printer.printMovieInfo(s.getMovie());
				printed = true;
			}
		}
	}
	
	
	
//	// gets movies by cineplex
//	public ArrayList<Movie> getAllMovies(int cineplexID) {
//		Cineplex cineplex = getCineplexByID(cineplexID);
//		return cineplex.getMovies();
//	}
//	
//	// gets showing movies by cineplex
//	public ArrayList<Movie> getShowingMovies(int cineplexID) {
//		Cineplex cineplex = getCineplexByID(cineplexID);
//		return cineplex.getShowingMovies();
//	}
//
//	
//	public void listAllShowtimesInCineplexByMovie(int cineplexID, int movieID) {
//		Cineplex cineplex = getCineplexByID(cineplexID);
//		ArrayList<Cinema> cinemas = cineplex.getCinemas();
//		CinemaManager cm = new CinemaManager();
//		
//		for (Cinema cinema: cinemas) {
//			cm.getShowtimes(cinema, movieID);
//		}
//	}
	
	public void listAllSeatAvailabilitiesInCineplexByMovie(Cineplex cineplex, Movie movie) {
		ArrayList<Cinema> cinemas = cineplex.getCinemas();
		CinemaManager cm = new CinemaManager();
		
		for (Cinema cinema: cinemas) {
			cm.listAvailabilitiesForMovie(cinema, movie);
	}
}

	public void listAll() {
		// TODO Auto-generated method stub
		
	}

	public void listAllCinemasByCineplex(Cineplex cineplex) {
		ArrayList<Cinema> cinemas = cineplex.getCinemas();
		CinemaManager cm = new CinemaManager();
		for (Cinema cinema: cinemas) {
			cm.listAllAvailabilities(cinema);
		}
	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new CineplexSerializer();
		records = serializer.deserialize(data);
		System.out.println("CineplexManager " + records.get(0).getCinemas());
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new CineplexSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}




