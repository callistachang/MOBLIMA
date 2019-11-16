package models;

import java.util.ArrayList;

import managers.Formatter;

/**
 * A hall in a cineplex that screens movies.
 * Contains showtimes that indicate what movie is screening at any given point in time.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */

public abstract class Cinema implements ISerializable {
	/**
	 * Unique identification number of a Cinema. In the format XXY.
	 * XX refers to the cineplex the cinema is in
	 * Y refers to the class of the cinema:
	 * - A refers to Platinum Cinema
	 * - B refers to Gold Cinema
	 * - Any other letter refers to Regular Cinema
	 */
	protected String id; // must be cinema code
	/**
	 * The list of showtimes shown in this cinema.
	 */
	protected ArrayList<Showtime> showtimes;
	/**
	 * Creates a new Cinema with a list of showtimes.
	 * @param id This cinema's ID.
	 * @param showtimes This cinema's showtimes.
	 */
	public Cinema(String id, ArrayList<Showtime> showtimes) {
		this.id = id;
		this.showtimes = showtimes;
	}
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(id);
		data.add(getShowtimeIDs());
		data.add(getCinemaClass());
		return data;
	}
	/**
	 * Gets the ID of the cinema indicating its cineplex and class.
	 * @return This cinema's ID.
	 */
	public String getId() {
		return id;
	}
	/**
	 * ID to set.
	 * @param id This cinema's ID.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Gets the list of showtime objects that have been made in this cinema.
	 * @return This cinema's showtimes.
	 */
	public ArrayList<Showtime> getShowtimes() {
		return showtimes;
	}
	/**
	 * Array of showtime objects to set.
	 * @param showtimes This cinema's showtimes.
	 */
	public void setShowtimes(ArrayList<Showtime> showtimes) {
		this.showtimes = showtimes;
	}
	/**
	 * Gets the list of showtimes by their ID that have been made in this cinema.
	 * @return This cinema's showtimeIDs.
	 */
	public ArrayList<String> getShowtimeIDs() {
		if (showtimes == null)
			return null;
		
		ArrayList<String> showtimesStr = new ArrayList<String>();
		for (Showtime s: showtimes) {
			showtimesStr.add(Formatter.getStringFromInt(s.getId()));
		}
		return showtimesStr;
	}
	/**
	 * Gets the class of the cinema: Platinum, Gold or Regular.
	 * Is an abstract method.
	 * @return This cinema's class.
	 */
	public abstract String getCinemaClass();
	/**
	 * Gets number of rows of seats in the cinema.
	 * Is an abstract method.
	 * @return This cinema's rows of seats.
	 */
	public abstract int getRows();
	/**
	 * Gets number of columns of seats in the cinema.
	 * Is an abstract method.
	 * @return This cinema's columns of seats.
	 */
	public abstract int getCols();
	/**
	 * Gets cinema's maximum seat capacity.
	 * @return This cinema's maximum number of seats.
	 */
	public int getTotalNoSeats() {
		return getRows()*getCols();
	}
	/**
	 * Gets cinema's available number of unbooked seats at a given showtime.
	 * @param showtime This cinema's showtime.
	 * @return This cinema's number of unbooked seats at a showtime.
	 */
	public int getNoSeatsAvailable(Showtime showtime) {
		return getTotalNoSeats() - showtime.getNoSeatsTaken();
	}
	/**
	 * Prints the current seating plan of a cinema at a given showtime. 
	 * Prints seating in alphanumerical format. Letter stands for Row, Number stands for Column.
	 * Available seats are printed as "(__)" while booked seats are printed as "(xx)";
	 * @param showtime Showtime given for query.
	 */
	public void printSeatingPlan(Showtime showtime) {
		int i, j;
		System.out.print(" ");
		
		for(i=0; i<getCols(); i++) {
			if(i+1<10) {
				System.out.print(" 0" + (i+1) + " ");
			}
			else {
				System.out.print(" " + (i+1) + " ");
			}
		}
		System.out.println();
		
		for(i=0; i<getRows(); i++) {
			System.out.print((char)('A' + i));
			for(j=0; j<getCols(); j++) {
				if(showtime.getSeatsTaken().contains(i*getCols() + j + 1)) {
					System.out.print("(xx)");
				}
				else {
					System.out.print("(__)");
				}
				
			}
			System.out.println();
		}
	}
	/**
	 * Adds a showtime object to the cinema.
	 * Inserted when new showtime is created in a cinema.
	 * @param showtime Showtime object to be created.
	 */
	public void addShowtime(Showtime showtime) {
		if (showtimes == null) {
			showtimes = new ArrayList<Showtime>();
		}
		showtimes.add(showtime);
		
	}
	public void removeShowtime(Showtime showtime) {
		showtimes.remove(showtime);
	}
}
