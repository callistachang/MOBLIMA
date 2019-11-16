package models;

public class Booking {
	private String TID;
	private String bookingDate;
	private String bookingTime;
	
	public Booking(String TID, String bookingDate, String bookingTime, Showtime showtime, Cinema cinema, double price) {
		this.TID = TID ;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.showtime = showtime;
		this.cinema = cinema;
		this.price = price;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
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
	private Showtime showtime;
	private Cinema cinema;
	private double price;

}
