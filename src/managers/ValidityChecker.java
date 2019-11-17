package managers;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Cinema;
import models.Showtime;
public class ValidityChecker {

	public static boolean dateValid(String date) {
		try {
			Formatter.getLocalDateFromString(date);
			return true;
	    } 
		catch (DateTimeParseException | NullPointerException e) {
	        return false;
		}
	}
	
	public static boolean timeValid(String time) {
		try {
			Formatter.getLocalTimeFromString(time);
			return true;
	    } 
		catch (DateTimeParseException | NullPointerException e) {
	        return false;
		}
	}
	
	public static boolean cineplexIDValid(String cineplexID) {
		CineplexManager cxm = new CineplexManager();
		if(cxm.getCineplexByID(cineplexID) == null) {
			return false;
		}
		else
			return true;
	}
	
	public static boolean cinemaIDValid(String cinemaID) {
		CinemaManager cm = new CinemaManager();
		if(cm.getCinemaByID(cinemaID) == null) {
			return false;
		}
		else
			return true;
	}
	
	public static boolean isNumeric(String strNum) {
	    try {
	        int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isChar(String strChar) {
		if(strChar.length() != 1)
			return false;
	    return true;
	}
	
	public static boolean isExistingMovieID(int movieID) {
		MovieManager mm = new MovieManager();
		if(mm.getMovieByID(movieID) == null) {
			return false;
		}
		else
			return true;
	}
	
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
/*
 * public static void main(String[] args) { String date; Scanner sc = new
 * Scanner(System.in); do {
 * System.out.println("Enter date in yyyy-mm-dd format: "); date =
 * sc.nextLine(); System.out.println(DateValid(date)); }
 * while(!date.contains("#")); }
 */
}