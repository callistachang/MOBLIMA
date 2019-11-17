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
		Boolean printed = false;
		MovieManager mm = new MovieManager();
		System.out.println("Cineplex that contain the movie:");
		for (Cineplex cx: records) {
			printed = false;
			ArrayList<Cinema> cinemas = cx.getCinemas();
			for (Cinema c: cinemas) {
				ArrayList<Showtime> showtimes = c.getShowtimes();
				for (Showtime s: showtimes) {
					if (movieID == s.getMovieID() & !printed) {
						Movie movie = mm.getMovieByID(movieID);
						System.out.println("CineplexID: " + cx.getId());;
						printed = true;
					}
				}
			}
		}
	}
	
	public void listAllMovies(String cineplexID) {
		MovieManager mm = new MovieManager();
		ArrayList <Integer> printedMovieID = null;
		Cineplex cx = getCineplexByID(cineplexID);
		ArrayList<Cinema> cinemas = cx.getCinemas();
		System.out.print("Cinemas: ");
		for (Cinema c: cinemas) {
			System.out.print(c.getId() + " ");
		}
		System.out.println();
		System.out.println();
		for (Cinema c: cinemas) {
			ArrayList<Showtime> showtimes = c.getShowtimes();
			for (Showtime s: showtimes) {
				int movieID = s.getMovieID();
				if(!printedMovieID.contains(movieID)) {
					Movie movie = mm.getMovieByID(movieID);
					Printer.printMovieInfo(movie);
					System.out.println();
					printedMovieID.add(movieID);
				}
				
			}
		}
	}
	
	public void listAllShowingMovies(String cineplexID) {
		MovieManager mm = new MovieManager();
		ArrayList <Integer> printedMovieID = null;
		Cineplex cx = getCineplexByID(cineplexID);
		ArrayList<Cinema> cinemas = cx.getCinemas();
		System.out.print("Cinemas: ");
		for (Cinema c: cinemas) {
			System.out.print(c.getId() + " ");
		}
		System.out.println();
		System.out.println();
		for (Cinema c: cinemas) {
			ArrayList<Showtime> showtimes = c.getShowtimes();
			for (Showtime s: showtimes) {
				int movieID = s.getMovieID();
				Movie movie = mm.getMovieByID(movieID);
				if(!printedMovieID.contains(movieID) && movie.getStatus().toString().compareTo("Now Showing") == 0) {
					Printer.printMovieInfo(movie);
					System.out.println();
					printedMovieID.add(movieID);
				}
				
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

	
	public void listAllSeatAvailabilitiesInCineplexByMovie(Cineplex cineplex, Movie movie) {
		ArrayList<Cinema> cinemas = cineplex.getCinemas();
		CinemaManager cm = new CinemaManager();
		
		for (Cinema cinema: cinemas) {
			cm.listAvailabilitiesForMovie(cinema, movie);
	}
}

	public void listAll() {
		for (Cineplex m: records) {
			System.out.printf("(ID: %s) %s\n", m.getId(), m.getName());
		}		
	}
	
	public void listAllCinemasByCineplex(String cineplexID) {
		Cineplex cineplex = getCineplexByID(cineplexID);
		ArrayList<Cinema> cinemas = cineplex.getCinemas();
		for (Cinema c: cinemas) {
			Printer.printCinemaInfo(c);
		}
		
//		CinemaManager cm = new CinemaManager();
//		
//		
//		for (Cinema cinema: cinemas) {
//			cm.listAllAvailabilities(cinema);
//		}
	}

//	public void listAllCinemasByCineplex(String cineplexID) {
//		Cineplex cineplex = getCineplexByID(cineplexID);
//		ArrayList<Cinema> cinemas = cineplex.getCinemas();
//		CinemaManager cm = new CinemaManager();
//		for (Cinema cinema: cinemas) {
//			cm.listAllAvailabilities(cinema);
//		}
//	}
	
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




