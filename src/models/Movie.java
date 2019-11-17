package models;

import java.util.ArrayList;
import java.util.EnumSet;

import managers.Formatter;
import managers.MovieManager;
import managers.ReviewManager;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
/**
 * Contains information about a movie.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */
public class Movie implements ISerializable {
	/**
	 * Unique identification number of a movie.
	 */
	private int id;
	/**
	 * The title of a movie.
	 */
	private String title;
	/**
	 * Shows whether a movie has past, present or future showtimes scheduled.
	 */
	private ShowingStatus status;
	/**
	 * A short summary of a movie's contents.
	 */
	private String synopsis;
	/**
	 * The name of the person who directed the movie.
	 */
	private String director;
	/**
	 * A list of the names of the people who acted in the movie.
	 */
	private ArrayList<String> casts;
	/**
	 * A movie's runtime in number of minutes.
	 */
	private int duration;
	/**
	 * A list of remarks given by customer who have watched a movie.
	 */
	private ArrayList<Review> reviews;
	/**
	 * Indicates whether a movie is 2D or 3D.
	 * 3D will have a higher price for tickets.
	 */
	private MovieType type;
	/**
	 * Creates a new movie to be available for screening in the cinema.
	 * @param id The movie's ID.
	 * @param title The movie's title.
	 * @param status The movie's status.
	 * @param synopsis The movie's synopsis.
	 * @param director The movie's director.
	 * @param casts The movie's casts.
	 * @param duration The movie's duration.
	 * @param reviews The movie's reviews.
	 * @param type The movie's type.
	 */
	public Movie(int id, String title, ShowingStatus status, String synopsis, String director, ArrayList<String> casts, 
			int duration, ArrayList<Review> reviews, MovieType type) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.director = director;
		this.casts = casts;
		this.duration = duration;
		this.reviews = reviews;
		this.type = type;
	}
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data =  new ArrayList<Object>();
		data.add(Formatter.getStringFromInt(id));
		data.add(title);
		data.add(status.toString());
		data.add(synopsis);
		data.add(director);
		data.add(casts);
		data.add(Formatter.getStringFromInt(duration));
		data.add(getReviewIDs()); // CHECK AGAIN for the null settings.
		data.add(type.toString());
		return data;
	}
	/**
	 * Gets the list of reviews left by customer on the movie by review IDs.
	 * @return This movie's reviews.
	 */
	public ArrayList<String> getReviewIDs() {
		ArrayList<String> reviewIDs = new ArrayList<String>();
		ArrayList<Review> reviews = getReviews();
		
		if (reviews == null)
			reviewIDs.add("null");
		else if (reviews.get(0) == null) {
			if (reviews.size() == 1)
				reviewIDs.add("null");
			else {
				reviews.remove(0);
				for (Review r: reviews)
					reviewIDs.add(r.toString());
			}
		} else {
			for (Review r: reviews)
				reviewIDs.add(r.toString());
		}
		return reviewIDs;
	}
	/**
	 * Allows values of the different data types to be accepted for the different
	 * attributes of the movie.
	 * Based on the attribute type, value of attribute will be formatted and set.
	 * @param attrNum This movie's attributes that are of the data type integer.
	 * @param attrVal This movie's attributes that are of the data type String.
	 */
	public void setAttr(int attrNum, String attrVal) {
		switch (attrNum) {
			case 1:
				title = attrVal;
				break;
			case 2:
				status = ShowingStatus.getByValue(attrVal);
				break;
			case 3:
				synopsis = attrVal;
				break;
			case 4:
				director = attrVal;
				break;
			case 5:
				duration = Integer.parseInt(attrVal);
				break;
			case 6:
				type = MovieType.getByValue(attrVal);
				break;
		}
	}
	/**
	 * Gets the ID of the movie.
	 * @return This movie's ID.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Gets the title of the movie.
	 * @return This movie's title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Title to set.
	 * @param title This movie's title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Gets the current showing status of the movie.
	 * @return This movie's showing status.
	 */
	public ShowingStatus getStatus() {
		return status;
	}
	/**
	 * Showing status to set.
	 * @param status This movie's showing status.
	 */
	public void setStatus(ShowingStatus status) {
		this.status = status;
	}
	/**
	 * Gets the synopsis of the movie.
	 * @return This movie's synopsis.
	 */
	public String getSynopsis() {
		return synopsis;
	}
	/**
	 * Synopsis to set.
	 * @param synopsis This movie's synopsis.
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	/**
	 * Gets the name of the person who directed the movie.
	 * @return The movie's director.
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * Director's name to set.
	 * @param director This movie's director.
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * Gets list of names of people who acted in this movie.
	 * @return This movie's casts.
	 */
	public ArrayList<String> getCasts() {
		return casts;
	}
	/**
	 * Casts to set.
	 * @param casts This movie's casts.
	 */
	public void setCasts(ArrayList<String> casts) {
		this.casts = casts;
	}
	/**
	 * Gets duration of a movie in number of minutes.
	 * @return This movie's duration.
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * Duration to set.
	 * @param duration This movie's duration.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * Gets list of reviews left on this movie.
	 * @return This movie's reviews.
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	/**
	 * List of reviews to set.
	 * @param reviews This movie's reviews.
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}
	/**
	 * Gets movie type to determine if movie is 3D or 2D.
	 * @return This movie's type.
	 */
	public MovieType getType() {
		return type;
	}
	/**
	 * Type of movie to set.
	 * @param type
	 */
	public void setType(MovieType type) {
		this.type = type;
	}
	/**
	 * A list of strings that show the showing status of a movie.
	 */
	public enum ShowingStatus {
		/**
		 * A movie has no current showtimes, but has showtimes scheduled in the future.
		 */
		COMING_SOON("Coming Soon"),
		/**
		 * A movie has current showtimes.
		 */
		NOW_SHOWING("Now Showing"),
		/**
		 * A movie has no current showtimes, and only has showtimes scheduled in the past.
		 */
		NO_LONGER_SHOWING("No Longer Showing");
		/**
		 * Stores String representation of the enum.
		 */
		private final String value;
		/**
		 * Initialises enum with its string representation.
		 * @param value This enum's string representation.
		 */
		private ShowingStatus(String value) {
			this.value = value;
		}
		/**
		 * Converts enum choices to their string representation.
		 */
		public String toString() {
			return value;
		}
		/**
		 * Converts string representation to enum choice.
		 * @param value This enum's string representation.
		 * @return This enum's enum choices.
		 */
		public static ShowingStatus getByValue(String value) {
			for (ShowingStatus ss: EnumSet.allOf(ShowingStatus.class)) {
				if (ss.toString().equals(value)) {
					return ss;
				}
			}
			return null;
		}
	}
	
	public enum MovieType {
		/**
		 * Movie screened in 2D.
		 */
		TWO_DIMENSIONAL("2D"),
		/**
		 * Movie screened in 3D. Has more expensive ticket prices.
		 */
		THREE_DIMENSIONAL("3D");
		/**
		 * Stores String representation of the enum.
		 */
		private final String value;
		/**
		 * Initialises enum with its string representation.
		 * @param value This enum's string representation.
		 */
		private MovieType(String value) {
			this.value = value;
		}
		/**
		 * Converts enum choices to their string representation.
		 */
		public String toString() {
			return value;
		}
		/**
		 * Converts string representation to enum choice.
		 * @param value This enum's string representation.
		 * @return This enum's enum choices.
		 */
		public static MovieType getByValue(String value) {
			for (MovieType mt: EnumSet.allOf(MovieType.class)) {
				if (mt.toString().equals(value)) {
					return mt;
				}
			}
			return null;
		}
	}
	
}
