package models;

import java.util.ArrayList;

public abstract class Cinema implements ISerializable {
	private int id; // must be cinema code
	private ArrayList<Showtime> showtimes;
	private ArrayList<Integer> seats;
	public Cinema(int id, ArrayList<Showtime> showtimes) {
		this.id = id;
		this.showtimes = showtimes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Showtime> getShowtimes() {
		return showtimes;
	}
	public void setShowtimes(ArrayList<Showtime> showtimes) {
		this.showtimes = showtimes;
	}
	public abstract String getCinemaClass();
	public abstract int getNoSeatsAvailable();
}
