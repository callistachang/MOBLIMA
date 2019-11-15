package managers;

import java.util.ArrayList;

import models.Cinema;
import models.Movie;
import models.Showtime;

public class CinemaManager {
	private static ArrayList<Cinema> records = null;
	
	public void listAllByCineplex(int cineplexID) {
		
	}	
	
	
//	public ArrayList<Showtime> getShowtimesInCinema(Cinema cinema) {
//		for (Cinema c: records) {
//			
//		}
//	}
	
	public void listAvailabilitiesForMovie(Cinema cinema, int movieID) {
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showtimes = cinema.getShowtimes();
		
		for (Showtime showtime: showtimes) {
			Movie movie = showtime.getMovie();
			if (movie.getId() == movieID) {
				System.out.println(cinema);
				System.out.println(showtime);
				cinema.getTotalNumSeats() - showtime.getTotalNumSeatsTaken();
			}
		}
	}
	
	public void listShowtimesInCinemaForMovie(Cinema cinema, int movieID) {
		ShowtimeManager sm = new ShowtimeManager();
		ArrayList<Showtime> showtimes = cinema.getShowtimes();
		
		for (Showtime showtime: showtimes) {
			Movie movie = showtime.getMovie();
			if (movie.getId() == movieID) {
				System.out.println(cinema);
				System.out.println(showtime);
				}
			}
//			sm.getShowtimeMovie(showtime,movie)
//			
//			sm.getShowtimes(movieID);
		}
	}
}
