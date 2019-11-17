package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import managers.Formatter;

/**
 * Represents a transaction made when user has booked movie tickets.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */

public class Booking implements ISerializable {
	/**
	 * Unique identification number of the transaction. 
	 * Should be in XXXYYYYMMDDhhmm format.
	 */
	private String TID;
	/**
	 * The date of booking.
	 */
	private LocalDate bookingDate;
	/**
	 * The time of booking.
	 */
	private LocalTime bookingTime;
	/**
	 * An instance of a movie showing. 
	 * Contains date, time and seating availability at a cinema.
	 */
	private Showtime showtime;
	/**
	 * A platform for movie screening.
	 * Contains showtimes indicating when movies are screening.
	 */
	private Cinema cinema;
	/**
	 * The value of a booking. 
	 * Decided by condition of booking and user input parameters.
	 */
	private double price;
	/**
	 * Create a new booking with the booking conditions.
	 * @param TID This booking's TID.
	 * @param bookingDate This booking's date.
	 * @param bookingTime This booking's time.
	 * @param showtime This booking's showtime.
	 * @param cinema This booking's cinema.
	 * @param price This booking's price.
	 */
	public Booking(String TID, LocalDate bookingDate, LocalTime bookingTime, Showtime showtime, Cinema cinema,
			double price) {
		this.TID = TID;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.showtime = showtime;
		this.cinema = cinema;
		this.price = price;
	}
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(TID);
		data.add(Formatter.getStringFromLocalDate(bookingDate));
		data.add(Formatter.getStringFromLocalTime(bookingTime));
		data.add(Formatter.getStringFromInt(showtime.getId()));
		data.add(cinema.getId());
		data.add(Formatter.getStringFromDouble(price));
		return data;
	}
	/**
	 * Gets the TID of the booking.
	 * @return This booking's TID.
	 */
	public String getTID() {
		return TID;
	}
	/**
	 * TID to set.
	 * @param TID This booking's TID.
	 */
	public void setTID(String TID) {
		this.TID = TID;
	}
	/**
	 * Gets the date the booking was made.
	 * @return This booking's date.
	 */
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	/**
	 * Booking date to set.
	 * @param bookingDate This booking's date.
	 */
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * Gets the time the booking was made.
	 * @return This booking's time.
	 */
	public LocalTime getBookingTime() {
		return bookingTime;
	}
	/**
	 * Booking time to set.
	 * @param bookingTime Time at which booking is done. 
	 */
	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	/**
	 * Gets the showtime this booking was made for.
	 * @return This booking's showtime.
	 */
	public Showtime getShowtime() {
		return showtime;
	}
	/**
	 * Showtime to set.
	 * @param showtime This booking's showtime.
	 */
	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
	/**
	 * Gets cinema which booking was made for.
	 * @return This booking's cinema.
	 */
	public Cinema getCinema() {
		return cinema;
	}
	/**
	 * Cinema to set.
	 * @param cinema This booking's cinema.
	 */
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	/**
	 * Gets value of booking after considering all the parameters.
	 * @return This booking's price.
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Price to set.
	 * @param price This booking's price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}


}
