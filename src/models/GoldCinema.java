package models;

import java.util.ArrayList;

public class GoldCinema extends Cinema {
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 15;
	
	public GoldCinema(String id, ArrayList<Showtime> showtimes) {
		super(id, showtimes);
	}
	
	public String getCinemaClass() {
		return "Gold";
	}
	
	public int getRows() {
		return NUM_ROWS;
	}
	
	public int getCols() {
		return NUM_COLS;
	}
	
}
