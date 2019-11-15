package managers;
import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Account;
import models.Movie;
import models.Review;
import serializers.AbstractSerializer;
import serializers.ReviewSerializer;
import serializers.ShowtimeSerializer;

public class ReviewManager {
	private static final String DATABASE_NAME = "reviewdata";
	private static ArrayList<Review> records = null;
	public static ArrayList<Review> getRecords() {
		return records;
	}
	public static void setRecords(ArrayList<Review> records) {
		ReviewManager.records = records;
	}
	public static String getDatabaseName() {
		return DATABASE_NAME;
	}
	
	public Review getReviewByID(int reviewID) {
		for (Review r: records) {
			if (r.getId() == reviewID) {
				return r;
			}
		}
		return null;
	}
	
	
	public ArrayList<Review> getReviews(ArrayList<Integer> id){
		int i;
		for (i=0; i<id.size();i++) {
			//get the reviews from database, which matches the id
			//System.out.println(Review.getReview());
		}
	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new ReviewSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new ReviewSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
	
	public Review createReview(int ratings,Account user, String content) {
		int reviewId = records.size();
		Review review = new Review(reviewId, ratings, user, content);
		//Store review into the database
		return review;
	}
}
