package models;

import java.util.ArrayList;

public class GoldCinema extends Cinema {
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 15;
	
	public GoldCinema(int id, ArrayList<Showtime> showtimes) {
		super(id, showtimes);
	}

	public ArrayList<Object> getSerializableData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCinemaClass() {
		return "Gold";
	}
}
