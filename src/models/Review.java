package models;

import java.util.ArrayList;

import managers.Formatter;

/**
 * Remarks given by customers who have watched a movie. 
 * Contains ratings and reviews for each movie. 
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class Review implements ISerializable {
	/**
	 * Unique identification number to represent each review.
	 */
	private int id;
	/**
	 * Numeric value given for a movie by the customer to represent the quality of movie.
	 * 1 is the lowest, 5 is the highest.
	 */
	private int rating;
	/**
	 * User giving the review.
	 */
	private Account user;
	/**
	 * Comments given for the movie by the customer to represent the quality of movie. 
	 * Optional.
	 */
	private String content;
	
	/**
	 * Creates a new review object with the given parameters. 
	 * @param id This review's id.
	 * @param rating This review's rating.
	 * @param user This review's user.
	 * @param content This review's content. 
	 */
	public Review(int id, int rating, Account user, String content) {
		this.id = id;
		this.rating = rating;
		this.user = user;
		this.content = content;
	}
	
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(Formatter.getStringFromInt(id));
		data.add(Formatter.getStringFromInt(rating));
		data.add(user.getUsername());
		data.add(content);
		return data;
	}
	
	/**
	 * Gets the id of the review given by the user.
	 * @return This review's id.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Review id to set.
	 * @param id This review's id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the user's rating of the movie.
	 * @return This review's rating.
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * User's rating to be set.
	 * @param rating This review's rating.
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Gets the user details of the review giver.
	 * @return This review's user.
	 */
	public Account getUser() {
		return user;
	}

	/**
	 * User of the review to be set.
	 * @param user This review's user.
	 */
	public void setUser(Account user) {
		this.user = user;
	}

	/**
	 * Gets the comments given for the movie by the user.
	 * @return This review's content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Comment on the movie to be set.
	 * @param content This review's content.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Converts the review id from integer to String.
	 */
	public String toString() {
		return String.valueOf(id);
	}
}
