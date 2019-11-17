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

/**
 * Controller for the entity labelled Cineplex.
 * Contains the logic to link CIneplex to other classes. 
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */

public class CineplexManager {
	/**
	 * The name of the csv file used. 
	 */
	private static final String DATABASE_NAME = "cineplexdata";
	/**
	 * Initialises empty array of Cineplex objects.
	 */
	private static ArrayList<Cineplex> records = null;
	
	/**
	 * Checks if array list of Cineplex objects is null.
	 * If null, data fom the csv file is written to the list.
	 */
	public CineplexManager() {
		if (records == null)
			initializeDatabase();
	}
	
	/**
	 * Iterates through records to compare given cineplex ID against list of cineplex ID already present.
	 * @param cineplexID Unique identification number of a cineplex.
	 * @return Cineplex that is present in the records.
	 */
	public Cineplex getCineplexByID(String cineplexID) {
		for (Cineplex cx: records) {
			if (cx.getId().equals(cineplexID)) {
				return cx;
			}
		}
		return null;
	}

	/**
	 * Prints the details of all cineplexes in the records.
	 */
	public void listAllCineplexes() {
		for (Cineplex cx: records) {
			System.out.println("(ID: " + cx.getId() + ") " + cx.getName());
		}
	}
	
	/**
	 * Prints the list of all cineplexes that contain a specific movie.
	 * @param movieID Unique identification number of a movie.
	 */
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
	
	/**
	 * Prints the list of all movies that is being shown at a particular cineplex, regardless of showing status.
	 * @param cineplexID Unique identification number of a specific cineplex.
	 */
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
	
	/**
	 * Prints the list of movies that is currently showing at a particular cineplex.
	 * @param cineplexID Unique identification number of a specific cineplex.
	 */
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
				if(!printedMovieID.contains(movieID)) {
					Movie movie = mm.getMovieByID(movieID);
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

	/**
	 * Prints the number of seats available and showtime for all cinemas in a particular cineplex for a particular movie.
	 * @param cineplex The chosen cineplex object for the query.
	 * @param movie The chosen movie object for the query.
	 */
	public void listAllSeatAvailabilitiesInCineplexByMovie(Cineplex cineplex, Movie movie) {
		ArrayList<Cinema> cinemas = cineplex.getCinemas();
		CinemaManager cm = new CinemaManager();
		
		for (Cinema cinema: cinemas) {
			cm.listAvailabilitiesForMovie(cinema, movie);
	}
}

	/**
	 * Prints the details of all cineplexes in the records.
	 */
	public void listAll() {
		for (Cineplex m: records) {
			System.out.printf("(ID: %s) %s\n", m.getId(), m.getName());
		}		
	}
	
	/**
	 * Prints the details of all cinemeas within a particular cineplex.
	 * @param cineplexID Unique identification number of a cineplex. 
	 */
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
	/**
	 * Retrieves information from external csv file and converts it into an array of Cineplex objects.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new CineplexSerializer();
		records = serializer.deserialize(data);
		System.out.println("CineplexManager " + records.get(0).getCinemas());
	}
	
	/**
	 * Converts current array of Cineplex objects into string to be stored in external csv file. 
	 */
	private void updateDatabase() {
		AbstractSerializer serializer = new CineplexSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}




