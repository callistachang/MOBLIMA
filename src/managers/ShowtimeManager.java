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
	 * 
	 * @return
	 */
	public int listAll() {
		return 0; //to change the code accordingly 
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
	 * 
	 * @param showtimeID
	 * @param attrNum
	 */
	public void updateShowtime(int showtimeID, int attrNum) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 
	 * @param showtimeID
	 */
	public void remove(int showtimeID) {
		// TODO Auto-generated method stub
		
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
//		int i, id = 0;
//		Showtime showtimeCheck = records.get(0);
//		for(i=0; i<records.size(); i++) {
//			showtimeCheck = records.get(i);
//			if (showtimeCheck.getId() == i+1) {
//				id = showtimeCheck.getId();
//			}
//		}
//		if (id == 0){
//			id = records.size() + 1;
//		}
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
	 * @param showtime Showtime selected by user.
	 * @param attrNum Attribute selected by user from a given list of options.
	 * @param attrVal Changed value of attribute given by user.
	 */
	public void update(Showtime showtime, int attrNum, String attrVal) {
		showtime.setAttr(attrNum, attrVal);
		System.out.println("======");
		updateDatabase();
	}
}
