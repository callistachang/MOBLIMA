package models;

import java.util.ArrayList;

public class Cinema implements ISerializable {
	private int id;	// cinema code
	private ArrayList<Showtime> showtimes;
	

	public ArrayList<Object> getSerializableData() {
		return null;
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
}
