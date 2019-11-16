package serializers;

import java.util.ArrayList;

import managers.ReviewManager;
import models.Movie;
import models.Review;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import models.ISerializable;

public class MovieSerializer extends AbstractSerializer {
		
	protected ISerializable deserialize(String data) {		
		String[] d = splitByAttribute(data);
		int id = parseInteger(d, 0);
		ArrayList<String> casts = parseArrayToString(d, 5);
		int duration = parseInteger(d, 6);
		
		AbstractSeria
		
		
		
		// "1", "2", "3"
		ArrayList<Review> reviews = parseArrayToReview(parseArrayToString(d, 7));
		
//		AbstractSerializer rSerializer = new ReviewSerializer();
//		ArrayList<Review> reviews = rSerializer.deserialize(data[7]);
		
//		ArrayList<Review> reviews = null;
		
		return new Movie(id, d[1], ShowingStatus.getByValue(d[2]), d[3], d[4], casts, 
				duration, reviews, MovieType.getByValue(d[8]));
	}
	
	private ArrayList<Review> parseArrayToReview(ArrayList<String> reviewStrings) {
		AbstractSerializer rSerializer = new ReviewSerializer();
		System.out.println(reviewStrings);
		ReviewManager rm = new ReviewManager();
		
		ArrayList<Review> reviews = null;
		for (String str: reviewStrings) {
			reviews.add(rm.getReviewByID(Integer.parseInt(str)));
		}
		
		// for review strings, convert to review objects.
		
//		ArrayList<Review> reviews = rSerializer.deserialize(reviewStrings);
		return reviews;
	}
}
