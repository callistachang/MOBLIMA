package managers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import handlers.DatabaseHandler;
import models.Booking;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Showtime;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import serializers.AbstractSerializer;
import serializers.AccountSerializer;
import serializers.BookingSerializer;

public class BookingManager {
	private static final String DATABASE_NAME = "bookingdata";
	private static ArrayList<Booking> records = null;
	
	public BookingManager() {
		if (records == null)
			initializeDatabase();
	}
	
	public Booking getBookingByID(String bookingTID) {
		for (Booking b: records) {
			if (b.getTID().equals(bookingTID)) {
				return b;
			}
		}
		return null;
	}

	public String addReceipt(String cinemaID, int showtimeID, double price) {
		ShowtimeManager sm = new ShowtimeManager();
		CinemaManager cm = new CinemaManager();
		LocalDate today = LocalDate.now();
	    LocalTime now = LocalTime.now();

		String bookingDate = today.format(DateTimeFormatter.ofPattern("YYYYMMDD"));
		String bookingTime = now.format(DateTimeFormatter.ofPattern("hhmm"));
		Showtime s = sm.getShowtimeByID(showtimeID);
		Cinema c = cm.getCinemaByID(cinemaID);
		String TID = cinemaID + bookingDate + bookingTime;
		Booking booking = new Booking(TID, today, now, s, c, price);
		records.add(booking); // add to records
		updateDatabase();
		
		return TID;
	} 
	
	public void printReceipt(String TID) {
		System.out.println("BOOKING RECEIPT");
		BookingManager bm = new BookingManager();
		Booking b =bm.getBookingByID(TID);
		System.out.println(b);
		
	}

	public void listTop5ByTicketSales() {
		// TODO Auto-generated method stub
		ShowtimeManager sm = new ShowtimeManager();
		MovieManager mm = new MovieManager();
		ArrayList<Integer> listOfMovie = null;
		ArrayList<Integer> ticketSale = null;
		for (Booking b: records) {
			Showtime s = b.getShowtime();
			for(Integer m: listOfMovie) {
				if(s.getMovieID() != m) {
					listOfMovie.add(s.getMovieID());
				}
			}
		}
		for (Booking b: records) {
			Showtime s = b.getShowtime();
			int listMovieIndex = listOfMovie.indexOf(s.getMovieID());
			ticketSale.set(listMovieIndex, ticketSale.get(listMovieIndex)+1);
		}
	    TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();  
	    for (int i=0; i<listOfMovie.size(); i++) {
	        map.put(ticketSale.get(i), listOfMovie.get(i));    
	      }
	    Collections.sort(ticketSale, Collections.reverseOrder());

	    for (int i=0; i<5; i++) {
	    int sale = ticketSale.get(i);
	    int movieID = map.get(sale);
	    Movie m = mm.getMovieByID(movieID);
		System.out.println( "Title: " + m.getTitle() + ", Ticket Sales: "+ sale);
		}
		
		
	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new BookingSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new BookingSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}
