package models;

import java.util.ArrayList;

public class RegularCinema extends Cinema {
	private static final int NUM_ROWS = 15;
	private static final int NUM_COLS = 15;
	
	public RegularCinema(String id, ArrayList<Showtime> showtimes) {
		super(id, showtimes);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Object> getSerializableData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCinemaClass() {
		return "Regular";
	}
	
	public int getRows() {
		return NUM_ROWS;
	}
	
	public int getCols() {
		return NUM_COLS;
	}
}