package models;

import java.util.ArrayList;
	/**
	 * Subclass of Cinema. 
	 * Highest tier of premium price, with the smallest seat capacity.
	 * @author balad
	 * version 1.0
	 * since 2019-11-17
	 */
public class PlatinumCinema extends Cinema {
	/**
	 * Number of rows of seats in a Platinum Cinema.
	 */
	private static final int NUM_ROWS = 5;
	/**
	 * Number of columns of seats in a Platinum Cinema.
	 */
	private static final int NUM_COLS = 10;
	/**
	 * Creates a Platinum Class Cinema with the respective paramaters.
	 * @param id This cinema's ID.
	 * @param showtimes This cinema's showtimes.
	 */
	public PlatinumCinema(String id, ArrayList<Showtime> showtimes) {
		super(id, showtimes);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	@Override
	public ArrayList<Object> getSerializableData() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Gets the class of this cinema as a string.
	 * @return This cinema's class, Platinum.
	 */
	public String getCinemaClass() {
		return "Platinum";
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
