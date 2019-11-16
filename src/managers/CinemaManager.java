package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Account;
import models.Booking;
import models.Cinema;
import models.Movie;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.CinemaSerializer;

public class CinemaManager {
	private static final String DATABASE_NAME = "cinemadata";
	private static ArrayList<Cinema> records = null;
	
	public CinemaManager() {
		if (records == null)
			initializeDatabase();
	}
	
	public Cinema getCinemaByID(String cinemaID) {
		for (Cinema c: records) {
			if (c.getId().equals(cinemaID)) {
				return c;
			}
		}
		return null;
	}
	
	public void listAllByCineplex(int cineplexID) {
		
	}	
	
	
//	public ArrayList<Showtime> getShowtimesInCinema(Cinema cinema) {
//		for (Cinema c: records) {
//			
//		}
//	}
	
	public void listAvailabilitiesForMovie(Cinema cinema, Movie movie) {
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showtimes = cinema.getShowtimes();
		
		for (Showtime showtime: showtimes) {
			Movie movie2 = showtime.getMovie();
			if (movie2.getId() == movie.getId()) {
				System.out.println("CinemaID = " + cinema.getId());
				System.out.println("Class: " + cinema.getCinemaClass());
				System.out.println("ShowtimeId: " + showtime.getId());
				int availableSeats = cinema.getTotalNoSeats() - showtime.getNoSeatsTaken();
				System.out.println(availableSeats + "are currently available");
			}
		}
	}
	
	public void listAllAvailabilities(Cinema cinema) {
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showtimes = cinema.getShowtimes();
		
		for (Showtime showtime: showtimes) {
			System.out.println("CinemaID = " + cinema.getId());
			System.out.println("Class: " + cinema.getCinemaClass());
			System.out.println("ShowtimeId: " + showtime.getId());
			int availableSeats = cinema.getTotalNoSeats() - showtime.getNoSeatsTaken();
			System.out.println(availableSeats + "are currently available");
			}
		}
	


	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new CinemaSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new CinemaSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}

	public void addShowtime(Cinema cinema, Showtime showtime) {
		// TODO Auto-generated method stub
		//error checking for collisions
		cinema.addShowtime(showtime);
		
	}

	public void removeShowtime(String cinemaID, int showtimeID) {
		// TODO Auto-generated method stub
		CinemaManager cm = new CinemaManager();
		ShowtimeManager sm = new ShowtimeManager();
		Cinema cinema = cm.getCinemaByID(cinemaID);
		Showtime showtime = sm.getShowtimeByID(showtimeID);
		ArrayList<Showtime> showtimes = cinema.getShowtimes();
		showtimes.remove(showtimes.indexOf(showtime));
	}

	public Cinema getCinemaByShowtimeID(int showtimeID) {
		// TODO Auto-generated method stub
		for(Cinema c: records) {
			for (Showtime s: c.getShowtimes()) {
				if(s.getId() == showtimeID) return c;
			}
		}
		return null;
	}
}
