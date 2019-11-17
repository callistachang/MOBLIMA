package managers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Movie;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.ShowtimeSerializer;
/**
 * Controller for the entity labelled Showtime.
 * Contains the logic to link showtime to other classes.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class ShowtimeManager {
	/**
	 * The name of the csv file used.
	 */
	private static final String DATABASE_NAME = "showtimedata";
	/**
	 * Initialises empty array of Showtime objects.
	 */
	protected static ArrayList<Showtime> records = null;
	/**
	 * Checks if array list of showtime objects is null.
	 * If null, data from the csv file is written to the list.
	 */
	public ShowtimeManager() {
		if (records == null)
			initializeDatabase();
	}
	/**
	 * Iterates through records to compare given ID against list of IDs for a showtime.
	 * @param showtimeID Unique identification number given to a showtime.
	 * @return Showtime that is present in records. 
	 */
	
	public Showtime getShowtimeByID(int showtimeID) {
		for (Showtime s: records) {
			if (s.getId() == showtimeID) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Checks if the result is empty.
	 * @return Boolean value representing whether the result is empty.
	 */
	public boolean isEmpty() {
		if(records == null) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Retrieve information from external csv file and converts it into an array of Showtime objects.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new ShowtimeSerializer();
		records = serializer.deserialize(data);
	}
	/**
	 * Converts current array of Showtime objects into string to be stored in external csv file.
	 */
	private void updateDatabase() {
		AbstractSerializer serializer = new ShowtimeSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
	
		
	
	/**
	 * Takes in user input on which seat to be booked.
	 * Adds booked seat to chosen showtime.
	 * @param showtime Showtime selected by the user.
	 * @param seatNo Seat number selected by the user.
	 */
	public void bookSeat(Showtime showtime, int seatNo) {
		System.out.println("seatno: " + seatNo);
		showtime.addSeat(seatNo);
		updateDatabase();
	}
	/**
	 * Creates a single showtime given the date, time and movie that will be screened.
	 * @param date The date of the showtime.
	 * @param time The time the showtime starts.
	 * @param movieID The movie to be screened at the showtime.
	 * @return The new showtime created.
	 */
	public Showtime create(LocalDate date, LocalTime time, int movieID) {

		Showtime showtime = new Showtime(records.size()+1, date, time, movieID, null);
		records.add(showtime);
		updateDatabase();
		return showtime;
	}
	/**
	 * Removes a single showtime from the database given the Showtime object.
	 * @param showtime The showtime to be removed.
	 */
	public void removeShowtime(Showtime showtime) {
		records.remove(showtime);
		updateDatabase();
	}
	
	/**
	 * Updates a showtime's information.
	 * A user chosen attribute of the showtime will be updated.
	 * @param movieID Unique identification number of movie.
	 * @param showtime Showtime selected by user.
	 */
	public void updateMovie(Showtime showtime, int movieID) {
		showtime.setMovieID(movieID);
		System.out.println("======");
		updateDatabase();
	}
	
	/**
	 * Updates the date attribute under showtime.
	 * @param showtime Indicates the specific showtime, whose date needs to be changed. 
	 * @param date Indicates the new date of the showtime.
	 */
	public void updateDate(Showtime showtime, LocalDate date) {
		showtime.setDate(date);
		System.out.println("======");
		updateDatabase();
	}
	
	/**
	 * Updates the time attribute under showtime. 
	 * @param showtime Indicates the specific showtime, whose timing needs to be changed. 
	 * @param time Indicates the new timing for the showtime.
	 */
	public void updateTime(Showtime showtime, LocalTime time) {
		showtime.setTime(time);
		System.out.println("======");
		updateDatabase();
	}
	
}
