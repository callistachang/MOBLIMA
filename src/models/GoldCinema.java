package models;

import java.util.ArrayList;
/**
 * Subclass of Cinema. 
 * Midtier premium price, with a medium seat capacity.
 * @author balad
 * version 1.0
 * since 2019-11-17
 */
public class GoldCinema extends Cinema {
	/**
	 * Number of rows of seats in a Gold Cinema.
	 */
	private static final int NUM_ROWS = 5;
	/**
	 * Number of columns of seats in a Gold Cinema.
	 */
	private static final int NUM_COLS = 15;
	/**
	 * Creates a Gold Class Cinema with the respective paramaters.
	 * @param id This cinema's ID.
	 * @param showtimes This cinema's showtimes.
	 */
	public GoldCinema(String id, ArrayList<Showtime> showtimes) {
		super(id, showtimes);
	}
	/**
	 * Gets the class of this cinema as a string.
	 * @return This cinema's class, Gold.
	 */
	public String getCinemaClass() {
		return "Gold";
	}
	/**
	 * Gets the number of rows of seats in this cinema.
	 * @return This cinema's number of rows of seats.
	 */
	public int getRows() {
		return NUM_ROWS;
	}
	/**
	 * Sets the number of columns of seats in this cinema.
	 * @return This cinema's number of columns of seats.
	 */
	public int getCols() {
		return NUM_COLS;
	}
	
}
