package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import managers.Formatter;

public class Booking implements ISerializable {
	private String TID;
	private LocalDate bookingDate;
	private LocalTime bookingTime;
	private Showtime showtime;
	private Cinema cinema;
	private double price;
	
	public Booking(String TID, LocalDate bookingDate, LocalTime bookingTime, Showtime showtime, Cinema cinema,
			double price) {
		this.TID = TID;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.showtime = showtime;
		this.cinema = cinema;
		this.price = price;
	}
	
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
	
	public String getTID() {
		return TID;
	}
	public void setTID(String TID) {
		this.TID = TID;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}



	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}



	public LocalTime getBookingTime() {
		return bookingTime;
	}



	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}



	public Showtime getShowtime() {
		return showtime;
	}
	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}
