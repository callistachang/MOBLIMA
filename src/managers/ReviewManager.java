package managers;
import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Account;
import models.Movie;
import models.Review;
import serializers.AbstractSerializer;
import serializers.ReviewSerializer;
import serializers.ShowtimeSerializer;
/**
 * Controller for the entity labelled Review.
 * Contains the logic to link review to other classes.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class ReviewManager {
	/**
	 * The name of the csv file used.
	 */
	private static final String DATABASE_NAME = "reviewdata";
	/**
	 * Initialises empty array of Review objects.
	 */
	private static ArrayList<Review> records = null;
	/**
	 * Checks if array list of showtime objects is null.
	 * If null, data from the csv file is written to the list.
	 */
	public ReviewManager() {
		if (records == null)
			initializeDatabase();
	}
	/**
	 * Iterates through records to compare given ID against list of IDs for a review.
	 * @param reviewID Unique identification number given to a review.
	 * @return Review that is present in records.
	 */
	public Review getReviewByID(int reviewID) {
		for (Review r: records) {
			if (r.getId() == reviewID) {
				return r;
			}
		}
		return null;
	}
	/**
	 * Creates a single review given the rating, username and review content written by the user.
	 * @param rating The user's rating of the movie that is being reviewed..
	 * @param account The account of the user that gave the review.
	 * @param content The content of the review.
	 * @return The new review created.
	 */
	public Review create(int rating, Account account, String content) {
		Review review = new Review(records.size()+1, rating, account, content);
		records.add(review);
		updateDatabase();
		return review;
	}
	
	/**
	 * Retrieve information from external csv file and converts it into an array of Review objects.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new ReviewSerializer();
		records = serializer.deserialize(data);
	}
	/**
	 * Converts current array of Review objects into string to be stored in external csv file.
	 */
	private void updateDatabase() {
		AbstractSerializer serializer = new ReviewSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}
