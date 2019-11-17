package managers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import handlers.DatabaseHandler;
import models.Booking;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Review;
import models.Showtime;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import serializers.AbstractSerializer;
import serializers.AccountSerializer;
import serializers.BookingSerializer;

/**
 * Controller for the entity labelled Booking.
 * Contains the logic to link Booking to other classes. 
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */
public class BookingManager {
	/**
	 * The name of the csv file used. 
	 */
	private static final String DATABASE_NAME = "bookingdata";
	/**
	 * Initialises empty array of Booking objects.
	 */
	private static ArrayList<Booking> records = null;
	
	/**
	 * Checks if array list of Booking objects is null.
	 * If null, data fom the csv file is written to the list.
	 */
	public BookingManager() {
		if (records == null)
			initializeDatabase();
	}
	
	/**
	 * Iterates through records to compare given TID against list of TID already present.
	 * @param bookingTID Unique transaction ID of the booking done.
	 * @return Booking that is present in the records.
	 */
	public Booking getBookingByID(String bookingTID) {
		for (Booking b: records) {
			if (b.getTID().equals(bookingTID)) {
				return b;
			}
		}
		return null;
	}
	/**
	 * Adds the booking details to the records and updates external csv file.
	 * @param cinemaID Unique identification key for the cinema showing the chosen movie.
	 * @param showtimeID Unique identification key for the showtime chosen.
	 * @param price Total price of the tickets bought by the user.
	 * @return Transaction ID of the booking that was completed.
	 */
	public Booking addReceipt(String cinemaID, int showtimeID, double price) {
		ShowtimeManager sm = new ShowtimeManager();
		CinemaManager cm = new CinemaManager();
		LocalDate today = LocalDate.now();
	    LocalTime now = LocalTime.now();

		String bookingDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String bookingTime = now.format(DateTimeFormatter.ofPattern("HHmm"));
		Showtime s = sm.getShowtimeByID(showtimeID);
		Cinema c = cm.getCinemaByID(cinemaID);
		String TID = cinemaID + bookingDate + bookingTime;
		Booking booking = new Booking(TID, today, now, s, c, price);
		records.add(booking); 
		updateDatabase();
		
		return booking;
	} 
	
	/**
	 * Prints out the booking receipt.
	 * @param TID Unique transaction ID of the booking done.
	 */
	public void printReceipt(String TID) {
		System.out.println("BOOKING RECEIPT");
		BookingManager bm = new BookingManager();
		Booking b =bm.getBookingByID(TID);
		Printer.printBookingInfo(b);
	}
	/**
	 * Prints out the top 5 movies in terms of the number of tickets sold.
	 */
	public void listTop5ByTicketSales() {
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<Integer> movieIDs = new ArrayList<Integer>();
		ArrayList<Integer> ticketSales = new ArrayList<Integer>();
		for (Booking b: records) {
			int movieID = b.getShowtime().getMovieID();	

			if (!movieIDs.contains(movieID)) {
				movieIDs.add(movieID);
				ticketSales.add(0);
			}
		}
		
		for (Booking b: records) {
			int movieID = b.getShowtime().getMovieID();	
			int movieIDIndex = movieIDs.indexOf(movieID);
			ticketSales.set(movieIDIndex, ticketSales.get(movieIDIndex) + 1);
		}
		


		for (int i = 0; i < movieIDs.size(); i++) {
			map.put(movieIDs.get(i), ticketSales.get(i));
		}

		Object[] a = map.entrySet().toArray();
		Arrays.sort(a, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
		        return ((Map.Entry<Integer, Integer>) o2).getValue().compareTo(
		               ((Map.Entry<Integer, Integer>) o1).getValue());
		    }
		});
		
		MovieManager mm = new MovieManager();
		
		for (int i = 0; i < a.length; i++) {
			String title = mm.getMovieByID(((Map.Entry<Integer, Integer>) a[i]).getKey()).getTitle();
			
		    System.out.println((i+1) + ". " + title + ": "
                    + ((Map.Entry<Integer, Integer>) a[i]).getValue() + " tickets");
		    
		    if (i == 4) break;
		}
		
	}
	/**
	 * Retrieves information from external csv file and converts it into an array of Booking objects.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new BookingSerializer();
		records = serializer.deserialize(data);
	}

	/**
	 * Converts current array of Booking objects into string to be stored in external csv file. 
	 */
	private void updateDatabase() {
		AbstractSerializer serializer = new BookingSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
	
	
}
