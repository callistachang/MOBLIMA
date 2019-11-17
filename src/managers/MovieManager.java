package managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import handlers.DatabaseHandler;
import models.Account;
import models.Movie;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import models.Review;
import serializers.MovieSerializer;
import serializers.AbstractSerializer;

public class MovieManager {
	private final static String DATABASE_NAME = "moviedata";
	private static ArrayList<Movie> records = null;
	
	public MovieManager() {
		if (records == null) {
			initializeDatabase();
		}
	}
	
	public Movie getMovieByID(int movieID) {
		for (Movie m: records) {
			if (m.getId() == movieID) {
				return m;
			}
		}
		return null;
	}

	public void create(String title, String status, String director, String synopsis, 
			ArrayList<String> casts, int duration, String type) {
		Movie movie = new Movie(records.size()+1, title, ShowingStatus.getByValue(status), director, 
				synopsis, casts, duration, null, MovieType.getByValue(type));
		records.add(movie); // add to records
		updateDatabase();
	}
	
	public void update(int movieID, int attrNum, String attrVal) {
		Movie movie = getMovieByID(movieID);
		movie.setAttr(attrNum, attrVal);
		System.out.println("======");
//		remove(movieID);
//		records.add(movie);
		updateDatabase();
	}
	
	public void remove(int movieID) {
		Movie movie = getMovieByID(movieID);
		records.remove(movie);
		updateDatabase();
	}
	
	public void listAll() {
		System.out.println("The list of all movies is as follows: ");
		for (Movie m: records) {
			Printer.printMovieListing(m);
//			int id = m.getId();
//			String title = m.getTitle();
//			String type = m.getType().toString();
//			Printer.printMovieListing(id, title, type);
		}
	}
	
	public void printMovieInfo(int movieID) {
		Movie movie = getMovieByID(movieID);
		Printer.printMovieInfo(movie);
	}
	
	public void printMovieReviews(int movieID) {
		Movie movie = getMovieByID(movieID);
		Printer.printMovieReviews(movie);
	}
//	
//	public void printMovieRatings(int movieID) {
//		Movie movie = getMovieByID(movieID);
//		movie.printReviews();
//	}
	
	// think should go to cineplex.
	// it should call on mm to get the movies.
	// FIND movie by id.
//	public void listAllByCineplex(String cineplexID) {
//		CineplexManager cxm = new CineplexManager();
//		cxm.getAllMovies(cineplexID);
//	}
//	
//	public void listShowingByCineplex(String cineplexID) {
//		CineplexManager cxm = new CineplexManager();
//		cxm.getShowingMovies(cineplexID);
	
//	}
	
//	public void listAttributes() {
//		Movie.printAttributes();
//	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new MovieSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new MovieSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}

	public void listTop5ByRatings() {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Movie m: records) {
			int movieRating = 0;
			for (Review r: m.getReviews()) {
				movieRating += r.getRating();
			}
			map.put(m.getTitle(), movieRating/m.getReviews().size());
		}
		
		Object[] a = map.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
		               ((Map.Entry<String, Integer>) o1).getValue());
		    }
		});
		for (Object e : a) {
		    System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
		                     + ((Map.Entry<String, Integer>) e).getValue());
		}      
		
		
//		System.out.println(map.entrySet().stream().sorted((o1, o2) -> {
//	        return o2.getValue().compareTo(o1.getValue());
//	    }).f
//				
//				.findFirst());
		
//		int i, totalRating = 0;
//		ArrayList<Review> reviews = m.getReviews();
//		for (i = 0; i < reviews.size(); i++) {
//			Review review = reviews.get(i);
//			System.out.printf("(%d)\n", i+1);
//			System.out.println("User: " + review.getUser());
//			System.out.println("Rating: " + review.getRating());
//			totalRating += review.getRating();
//			if (!review.getContent().equals("null")) {
//				System.out.println("Review: " + review.getContent());
//			}
//		}
//		System.out.println("\nAverage Rating: " + totalRating/reviews.size());
//		
	}
	
	public void addReviewToMovie(int movieId, int rating, Account account, String content) {
		Movie movie = getMovieByID(movieId);
		ReviewManager rm = new ReviewManager();
		// create review in the review manager side
		Review newReview = rm.create(rating, account, content);
		
		// append the review to the current movie
		ArrayList<Review> reviews = movie.getReviews();
		reviews.add(newReview);
//		movie.setReviews(reviews);
		System.out.println(movie.getReviews());
//		int movieIndex = records.indexOf(movie);
//		records.set(movieIndex, movie);
		
		updateDatabase();
	}
}
