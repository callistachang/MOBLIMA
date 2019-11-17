package managers;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Cinema;
import models.Showtime;
public class ValidityChecker {
	
	/**
	 * Checks if a String is in valid date format
	 * @param date The String input to be checked
	 * @return Returns a boolean value to indicate the validity of the String in date format
	 */
	public static boolean dateValid(String date) {
		try {
			Formatter.getLocalDateFromString(date);
			return true;
	    } 
		catch (DateTimeParseException | NullPointerException e) {
	        return false;
		}
	}
	
	/**
	 * Checks if a String is in valid time format
	 * @param time The string input to be checked
	 * @return Returns a boolean value to indicate the validity of the String in time format
	 */
	public static boolean timeValid(String time) {
		try {
			Formatter.getLocalTimeFromString(time);
			return true;
	    } 
		catch (DateTimeParseException | NullPointerException e) {
	        return false;
		}
	}
	
	/**
	 * Checks if a String is a valid existing cineplex ID
	 * @param cineplexID The String to be checked
	 * @return Returns a boolean value to indicate the validity of the String being an existing cineplex ID
	 */
	public static boolean cineplexIDValid(String cineplexID) {
		CineplexManager cxm = new CineplexManager();
		if(cxm.getCineplexByID(cineplexID) == null) {
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Checks if a String is a valid existing cinema ID
	 * @param cinemaID The String to be checked
	 * @return Returns a boolean value to indicate the validity of the String being an existing cinema ID
	 */
	public static boolean cinemaIDValid(String cinemaID) {
		CinemaManager cm = new CinemaManager();
		if(cm.getCinemaByID(cinemaID) == null) {
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Checks if a String is valid as an integer
	 * @param strNum The String to be checked
	 * @return Returns a boolean value to indicate the validity of the String as an integer
	 */
	public static boolean isNumeric(String strNum) {
	    try {
	        int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	/**
	 * Checks if a String is valid as a single character
	 * @param strChar The String to be checked
	 * @return Returns a boolean value to indicate the validity of the String as a single character
	 */
	
	public static boolean isChar(String strChar) {
		if(strChar.length() != 1)
			return false;
	    return true;
	}
	
	/**
	 * Checks if an integer is the movie ID of an existing movie
	 * @param movieID The integer to be checked
	 * @return Returns a boolean value to indicate if the integer is the movie ID of an existing movie
	 */
	public static boolean isExistingMovieID(int movieID) {
		MovieManager mm = new MovieManager();
		if(mm.getMovieByID(movieID) == null) {
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Checks if a String is valid as an available seat number in a cinema's showtime
	 * @param cinema The cinema of the showtime
	 * @param showtime The showtime for occupied seats to be compared to
	 * @param seatNumber The String to be checked
	 * @return Returns a boolean value to indicate validity of the String as an available seat number in that cinema's showtime
	 */
	public static boolean isAvailableSeatNumber(Cinema cinema, Showtime showtime,String seatNumber) {
		//convert to seat number
		int seatInt;
		if(seatNumber.length() != 3) {
			return false;
		}
		if(! isNumeric(seatNumber.substring(1))){
			return false;
		}
		if(Formatter.getIntFromString(seatNumber.substring(1)) == 0){
			return false;
		}
		seatInt = (seatNumber.charAt(0)-'A') * cinema.getCols() + Formatter.getIntFromString(seatNumber.substring(1));
		if(showtime.getSeatsTaken().contains(seatInt) || seatInt > cinema.getTotalNoSeats() || seatInt <= 0) {
			return false;
		}
		return true;
	}

}