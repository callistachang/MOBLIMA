package models;

import java.util.ArrayList;

public class PlatinumCinema extends Cinema {

	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 10;
	
	public PlatinumCinema(int id, ArrayList<Showtime> showtimes) {
		super(id, showtimes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Object> getSerializableData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCinemaClass() {
		return "Platinum";
	}
	
	public int getRows() {
		return NUM_ROWS;
	}
	
	public int getCols() {
		return NUM_COLS;
	}
}
