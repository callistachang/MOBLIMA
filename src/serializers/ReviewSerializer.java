package serializers;

import java.util.ArrayList;

import managers.ReviewManager;
import models.Account;
import models.ISerializable;
import models.Review;

public class ReviewSerializer extends AbstractSerializer {

	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
//		for (String str: d) {
//			System.out.println("hi" + str);
//		}
		int id = parseInteger(d, 0);
		int rating = parseInteger(d, 1);
		Account account = null;
//		Account account = (Account) deserialize(d[2]);
		
//		System.out.println(account);

		
		return new Review(id, rating, account, d[3]);
	}
	
	// reviewStrings is in the form of e.g. ["1", "2", "3"]
	// returns actual review objects in an array.
	public ArrayList<Review> parseIdArrayToReview(int[] reviewIDs) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		ReviewManager rm = new ReviewManager();
		for (int id: reviewIDs) {
			Review review = rm.getReviewByID(id);
			reviews.add(review);
		}
		return reviews;
	}
}
