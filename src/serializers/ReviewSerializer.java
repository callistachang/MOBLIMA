package serializers;

import java.util.ArrayList;

import managers.AccountManager;
import managers.Formatter;
import managers.ReviewManager;
import models.Account;
import models.ISerializable;
import models.Review;

/**
 * Handles the deserialization for Review class.
 * @version 1.0
 * @since 2019-11-17
 * @author balad
 *
 */
public class ReviewSerializer extends AbstractSerializer {

	/**
	 * Converts data of string type to Review type.
	 */
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		int id = Formatter.getIntFromString(d[0]);
		int rating = Formatter.getIntFromString(d[1]);
		AccountManager am = new AccountManager();
		Account account = am.getAccountByUsername(d[2]);
		return new Review(id, rating, account, d[3]);
	}

	/**
	 * Array of reviewID converted into array of review objects.
	 * @param reviewIDs Array of unique review identification number to be converted.
	 * @return Array of review objects.
	 */
	public ArrayList<Review> parseIdArrayToReview(ArrayList<Integer> reviewIDs) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		ReviewManager rm = new ReviewManager();
		for (int id: reviewIDs) {
			Review review = rm.getReviewByID(id);
			reviews.add(review);
		}
		return reviews;
	}
}
