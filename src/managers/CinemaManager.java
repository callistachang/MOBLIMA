package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Account;
import models.Booking;
import models.Cinema;
import models.Movie;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.CinemaSerializer;

/**
 * Controller for the entity labelled Cinema.
 * Contains the logic to link Cinema to other classes. 
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */
public class CinemaManager {
	/**
	 * The name of the csv file used. 
	 */
	private static final String DATABASE_NAME = "cinemadata";
	
	/**
	 * Initialises empty array of Cinema objects.
	 */
	private static ArrayList<Cinema> records = null;
	
	/**
	 * Checks if array list of Cinema objects is null.
	 * If null, data fom the csv file is written to the list.
	 */
	public CinemaManager() {
		if (records == null)
			initializeDatabase();
	}
	
	/**
	 * Iterates through records to compare given cinema ID against list of cinema ID already present.
	 * @param cinemaID Unique transaction ID of each cinema.
	 * @return Cinema that is present in the records.
	 */
	public Cinema getCinemaByID(String cinemaID) {
		for (Cinema c: records) {
			if (c.getId().equals(cinemaID)) {
				return c;
			}
		}
		return null;
	}
	/**
	 * Prints the list of all cinemas present in a cineplex.
	 * @param cineplexID Unique identification number of each cineplex.
	 */
	public void listAllByCineplex(int cineplexID) {
		
	}	
	
	
//	public ArrayList<Showtime> getShowtimesInCinema(Cinema cinema) {
//		for (Cinema c: records) {
//			
//		}
//	}
	
	/**
	 * Prints the number of seats available and showtime for a particular cinema and movie.
	 * @param cinema The chosen cinema for the query.
	 * @param movie The chosen movie for the query.
	 */
	public void listAvailabilitiesForMovie(Cinema cinema, Movie movie) {
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showtimes = cinema.getShowtimes();
		
		for (Showtime showtime: showtimes) {
			int movieId = showtime.getMovieID();
			if (movieId == movie.getId()) {
				System.out.println("CinemaID = " + cinema.getId());
				System.out.println("Class: " + cinema.getCinemaClass());
				System.out.println("ShowtimeId: " + showtime.getId());
				int availableSeats = cinema.getTotalNoSeats() - showtime.getNoSeatsTaken();
				System.out.println(availableSeats + " seats are currently available");
				System.out.println("======================================================="); 
			}
		}
	}
	/**
	 * Prints the number of seats available and showtime for a particular cinema.
	 * @param cinema The chosen cinema for the query.
	 */
	public void listAllAvailabilities(Cinema cinema) {
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showtimes = cinema.getShowtimes();
		
		for (Showtime showtime: showtimes) {
			System.out.println("CinemaID = " + cinema.getId());
			System.out.println("Class: " + cinema.getCinemaClass());
			System.out.println("ShowtimeId: " + showtime.getId());
			int availableSeats = cinema.getTotalNoSeats() - showtime.getNoSeatsTaken();
			System.out.println(availableSeats + "are currently available");
			System.out.println("=======================================================");
			}
		}
	

	/**
	 * Retrieves information from external csv file and converts it into an array of Cinema objects.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new CinemaSerializer();
		records = serializer.deserialize(data);
	}
	
	/**
	 * Converts current array of Cinema objects into string to be stored in external csv file. 
	 */
	private void updateDatabase() {
		AbstractSerializer serializer = new CinemaSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}

	/**
	 * Adds the showtime details to the records and updates the external csv file.
	 * @param cinemaID Unique identification number of the cinema to which the showtime is added.
	 * @param showtime New showtime that is being added.
	 */
	public void addShowtime(String cinemaID, Showtime showtime) {
		Cinema cinema = getCinemaByID(cinemaID);
		cinema.addShowtime(showtime);
		updateDatabase();
	}
	
	/**
	 * Removes the showtime details from the records and updates the external csv file.
	 * @param cinema Cinema from which the showtime is removed.
	 * @param showtime Particular showtime that is being removed.
	 */
	public void removeShowtime(Cinema cinema, Showtime showtime) {
		cinema.removeShowtime(showtime);
		updateDatabase();
	}
	
	/**
	 * Iterate through records to compare given showtimeID against the list of showtimeID already present.
	 * @param showtimeID Unique identication number of a particular showtime.
	 * @return Cinema that contains the given showtimeID.
	 */
	public Cinema getCinemaByShowtimeID(int showtimeID) {
		for(Cinema c: records) {
			for (Showtime s: c.getShowtimes()) {
				if(s.getId() == showtimeID) return c;
			}
		}
		return null;
	}
}
