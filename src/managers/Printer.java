package managers;

import java.util.ArrayList;

import models.Cinema;
import models.Movie;
import models.Review;
import models.Showtime;

public class Printer {
	
	public static void printMovieAttributes() {
		System.out.println("(1) Title");
		System.out.println("(2) Showing Status");
		System.out.println("(3) Synopsis");
		System.out.println("(4) Director");
		System.out.println("(5) Duration");
		System.out.println("(6) Type");
	}
	
	public static void printMovieInfo(Movie m) {
		System.out.println("ID: " + m.getId());
		System.out.println("Title: " + m.getTitle());
		System.out.println("Showing Status: " + m.getStatus().toString());
		System.out.println("Synopsis: " + m.getSynopsis());
		System.out.println("Duration: " + m.getDuration() + " minutes");
		System.out.println("Movie Type: " + m.getType().toString());
		System.out.println("Director: " + m.getDirector());
		System.out.print("Cast Members: ");
		
		int i;
		ArrayList<String> casts = m.getCasts();
		for (i = 0; i < casts.size()-1; i++) {
			System.out.printf(casts.get(i) + ", ");
		}
		System.out.println(casts.get(i));
	}
	
	public static void printMovieReviews(Movie m) {
		int i;
		ArrayList<Review> reviews = m.getReviews();
		for (i = 0; i < reviews.size(); i++) {
			Review review = reviews.get(i);
			System.out.printf("(%d)\n", i+1);
			System.out.println("User: " + review.getUser());
			System.out.println("Rating: " + review.getRating());
			if (!review.getContent().equals("null")) {
				System.out.println("Review: " + review.getContent());
			}
		}
	}
	
	public static void printMovieListing(Movie m) {
		System.out.printf("(ID: %d) %s (%s)\n", m.getId(), m.getTitle(), m.getType());
	}
	
	public static void printCinemaInfo(Cinema cinema) {
		System.out.println("ID: " + cinema.getId());
		System.out.println("Cinema Class: " + cinema.getCinemaClass());
	}
	
	public static void printShowtimeAttributes(Showtime showtime) {
		System.out.println("(1) Movie: ");
		System.out.println("(2) Date: ");
		System.out.println("(3) Time: ");
	}
	
	public static void printShowtimeDetails(Showtime showtime) {
		System.out.println("ShowtimeID: " + showtime.getId());
		MovieManager mm = new MovieManager();
		int movieID = showtime.getMovieID();
		Movie movie = mm.getMovieByID(movieID);
		System.out.println("(1) Movie: " + movie.getTitle());
		System.out.println("(2) Date: " + showtime.getDate());
		System.out.println("(3) Time: " + showtime.getTime());
	}
	

	public static void printShowtimeDetails(Cinema cinema) {
		System.out.println("Cinema ID: " + cinema.getId());
		for(Showtime showtime : cinema.getShowtimes()) {
			printShowtimeDetails(showtime);
		}
	}
}
