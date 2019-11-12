package managers;
import java.util.ArrayList;
import models.Review;

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
	
	public ArrayList<Review> getReviews(ArrayList<Integer> id){
		int i;
		for (i=0; i<id.size();i++) {
			//get the reviews from database, which matches the id
			//System.out.println(Review.getReview());
		}
	}
}
