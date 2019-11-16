package models;

import java.util.ArrayList;
	/**
	 * Subclass of Cinema. 
	 * Regular price, with the largest seat capacity.
	 * @author balad
	 * version 1.0
	 * since 2019-11-17
	 */
public class RegularCinema extends Cinema {
	/**
	 * Number of rows of seats in a Regular Cinema.
	 */
	private static final int NUM_ROWS = 15;
	/**
	 * Number of columns of seats in a Regular Cinema.
	 */
	private static final int NUM_COLS = 15;
	/**
	 * Creates a Regular Class Cinema with the respective paramaters.
	 * @param id This cinema's ID.
	 * @param showtimes This cinema's showtimes.
	 */
	public RegularCinema(String id, ArrayList<Showtime> showtimes) {
		super(id, showtimes);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Gets the class of this cinema as a string.
	 * @return This cinema's class, Regular.
	 */
	public String getCinemaClass() {
		return "Regular";
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