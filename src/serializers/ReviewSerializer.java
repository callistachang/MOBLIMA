package serializers;

import java.util.ArrayList;

import managers.AccountManager;
import managers.Formatter;
import managers.ReviewManager;
import models.Account;
import models.ISerializable;
import models.Review;

public class ReviewSerializer extends AbstractSerializer {

	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		int id = Formatter.getIntFromString(d[0]);
		int rating = Formatter.getIntFromString(d[1]);
		AccountManager am = new AccountManager();
		Account account = am.getAccountByUsername(d[2]);
		return new Review(id, rating, account, d[3]);
	}

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
