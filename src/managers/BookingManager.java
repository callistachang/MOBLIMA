package managers;

import java.util.ArrayList;
import java.util.Collections;   


import handlers.DatabaseHandler;
import models.Booking;
import models.Cineplex;
import models.Movie;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.AccountSerializer;
import serializers.BookingSerializer;

public class BookingManager {
	private static final String DATABASE_NAME = "bookingdata";
	private static ArrayList<Booking> records = null;
	
	public Booking getBookingByID(int bookingID) {
		for (Booking b: records) {
			if (b.getId() == bookingID) {
				return b;
			}
		}
		return null;
	}

	public void addReceipt() {
		
	}
	
	public void printReceipt() {
		
	}

	public void listTop5ByTicketSales() {
		// TODO Auto-generated method stub
		ShowtimeManager sm = new ShowtimeManager();
		MovieManager mm = new MovieManager();
		ArrayList<Movie> listOfMovie = null;
		ArrayList<Integer> ticketSale = null;
		for (Booking b: records) {
			Showtime s = b.getShowtime();
			for(Movie m: listOfMovie) {
				if(s.getMovie() != m) {
					listOfMovie.add(s.getMovie());
				}
			}
		}
		for (Booking b: records) {
			Showtime s = b.getShowtime();
			int listMovieIndex = listOfMovie.indexOf(s.getMovie());
			ticketSale.set(listMovieIndex, ticketSale.get(listMovieIndex)+1);
		}
		Collections.sort(ticketSale, Collections.reverseOrder()); 
		for (int i=0; i<5; i++) {
		System.out.println("(ID: " + ticketSale.get(i) + ") " + mm.getMovieByID(ticketSale.get(i)));
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
