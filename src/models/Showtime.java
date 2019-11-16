package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import managers.Formatter;
import managers.MovieManager;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;

/**
 *Contains information of each screening time of movie. 
 * @author penel
 * @version 1.0
 *@since 2019-11-17
 */

public class Showtime implements ISerializable {
	/**
	 * Unique identification number to represent a showtime.
	 */
	private int id;
	/**
	 * Date of movie screening at a particular cinema.
	 */
	private LocalDate date;
	/**
	 * Time of movie screening at a particular cinema.
	 */
	private LocalTime time;
	/**
	 * Unique identification number to represent a movie.
	 */
	private int movieID;
	/**
	 * List of seats already booked in the cinema at the given time. 
	 */
	private ArrayList <Integer> seatsTaken;


	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(Formatter.getStringFromInt(id));
		data.add(Formatter.getStringFromLocalDate(date));
		data.add(Formatter.getStringFromLocalTime(time));
		data.add(Formatter.getStringFromInt(movieID));
		data.add(Formatter.getStringArrayFromIntegerArray(seatsTaken));
		return data;
	}

	/**
	 * Creates a new showtime object with the given parameters.
	 * @param id This showtime's id.
	 * @param date This showtime's date.
	 * @param time This showtime's time.
	 * @param movieID This showtime's movieID.
	 * @param seatsTaken This showtime's list of seats taken.
	 */
	public Showtime(int id, LocalDate date, LocalTime time, int movieID, ArrayList<Integer> seatsTaken) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.movieID = movieID;
		this.seatsTaken = seatsTaken;
	}
	
	
	/**
	 * Gets the id of the showtime.
	 * @return This showtime's id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Gets the list of seats already booked for the showtime.
	 * @return This showtime's list of seats booked.
	 */
	public ArrayList<Integer> getSeatsTaken() {
		return seatsTaken;
	}
	

	/**
	 * Gets the list of seats already booked as the data type String.
	 * @return This showtime's list of seats booked as String.
	 */
	
	public ArrayList<String> getSeatsTakenAsString() {
		ArrayList<String> seatsTakenStrings = new ArrayList<String>();
		
		for (int i: this.seatsTaken) {
			seatsTakenStrings.add(String.valueOf(i));
		}
		return seatsTakenStrings;
	}
	
	/**
	 * Showtime id to set.
	 * @param id This showtime's id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * List of seats booked to be set.
	 * @param seatsTaken This showtime's list of seats booked.
	 */
	public void setSeatsTaken(ArrayList<Integer> seatsTaken) {
		this.seatsTaken = seatsTaken;
	}

	/**
	 * Gets the date of showtime in the specified format of dd/MM/yyyy
	 * @return This showtime's formatted date.
	 */
	public String getDateAsString() {
		return this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * Gets the time of showtime in the specified format of HH:mm:ss.
	 * @return This showtime's formatted time. 
	 */
	public String getDateAsTime() {
		return this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	/**
	 * Gets the ID of the movie showing during the specific showtime.
	 * @return ID of the movie corresponding to this showtime. 
	 */
	public int getMovieID() {
		return movieID;
	}
	
	/**
	 * ID of movie showing at the showtime to be set. 
	 * @param movieID ID of the movie corresponding to this showtime's movie.
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	/**
	 * Gets the date of the showtime.
	 * @return This showtime's date.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Date of the showtime to be set. 
	 * @param date This  showtime's date.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Gets the time of the showtime.
	 * @return This showtime's time.
	 */
	public LocalTime getTime() {
		return time;
	}
	/**
	 * Time of the showtime to be set.
	 * @param time This showtime's time. 
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	/**
	 * Gets the total number of seats booked during the showtime.
	 * @return This showtime's count of seats taken.
	 */
	public int getNoSeatsTaken() {
		return seatsTaken.size();
	}
	
	/**
	 *Includes the new seat booked to the already present list of booked seats. 
	 * @param seatNo The seat number of the new seat that is booked. 
	 */
	public void bookSeat(int seatNo) {
		seatsTaken.add(seatNo);
	}
	
	/**
	 * Allows values of different data type to be accepted for the different
	 * attributes of the showtime.
	 * Based on the attribute type, value of attribute will be formatted and set.
	 * @param attrNum This showtime's attributes that are of the data type integer.
	 * @param attrVal This showtime's attributes that are of the data type string.
	 */
	public void setAttr(int attrNum, String attrVal) {
		switch (attrNum) {
			case 1:
				movieID = Integer.parseInt(attrVal);
				break;
			case 2:
				date = Formatter.getLocalDateFromString(attrVal);
				break;
			case 3:
				time = Formatter.getLocalTimeFromString(attrVal);
				break;
			
		}
	}	
}
