package serializers;

import java.util.ArrayList;

import managers.Formatter;
import managers.ReviewManager;
import models.Movie;
import models.Review;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import models.ISerializable;

public class MovieSerializer extends AbstractSerializer {
		
	protected ISerializable deserialize(String data) {	
		String[] d = splitByAttribute(data);
		int id = Formatter.getIntFromString(d[0]);
		ArrayList<String> casts = splitArrayToStrings(d[5]);
		int duration = Formatter.getIntFromString(d[6]);
		
		// "1;2;3"
		// "1, 2, 3"
		// ArrayList of Review objects
		ReviewSerializer rs = new ReviewSerializer();
		ArrayList<Integer> reviewIDs = splitArrayToIntegers(d[7]);
		ArrayList<Review> reviews = rs.parseIdArrayToReview(reviewIDs);
				
		return new Movie(id, d[1], ShowingStatus.getByValue(d[2]), d[3], d[4], casts, 
				duration, reviews, MovieType.getByValue(d[8]));
	}
}
