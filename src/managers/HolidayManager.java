package managers;

import java.util.ArrayList;
import java.time.LocalDate;

import handlers.DatabaseHandler;
import models.Holiday;
import serializers.AbstractSerializer;
import serializers.HolidaySerializer;
/**
 * Controller for the entity labelled Holiday.
 * Contains the logic to link holidays to other classes.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class HolidayManager {
	/**
	 * The name of the csv file used.
	 */
	private static final String DATABASE_NAME = "holidaydata";
	/**
	 * Initialises empty array of holiday objects.
	 */
	protected static ArrayList<Holiday> records = null;
	/**
	 * Checks if array list of holiday objects is null.
	 * If null, data from the csv file is written to the list.
	 */
	public HolidayManager() {
		if (records == null)
			initializeDatabase();
	}
	/**
	 * Lists all the movies in the database.
	 * Prints the name and date of holidays.
	 */
	public void listAll() {
		for (Holiday h: records) {
			System.out.println("Name: " + h.getHolidayName() + " Date: " + Formatter.getStringFromLocalDate(h.getHolidayDate()));
		}
	}
	
	/**
	 * This creates a holiday and updates the database.
	 * @param name The name of the holiday to be added.
	 * @param date The date of the holiday to be added.
	 */
	public void create(String name, LocalDate date) {
		Holiday h = new Holiday(records.size()+1, name, date);
		records.add(h);
		updateDatabase();
	}
	/**
	 * This removes a holiday from the database.
	 * @param name The name of the holiday to be removed
	 */
	public void remove(String name) {
		for (Holiday h: records) {
			if (h.getHolidayName().equals(name)) {
				records.remove(h);
				updateDatabase();
				return;
			}
		}
	}
	
	
	/**
	 * Checks if given movie date falls on a recognised holiday in the records.
	 * @param movieDate Date of the chosen movie.
	 * @return Boolean value on whether the date is a holiday.
	 */
	public Boolean isHoliday(LocalDate movieDate) {
		for (Holiday h: records) {
			LocalDate date = h.getHolidayDate();
			if(movieDate == date) return true;
		}
		return false;
	}

	/**
	 * Retrieve information from external csv file and converts it into an array of Holiday objects.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new HolidaySerializer();
		records = serializer.deserialize(data);
	}
	/**
	 * Converts current array of holiday objects into string to be stored in external csv file.
	 */
	private void updateDatabase() {
		AbstractSerializer serializer = new HolidaySerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}

}
