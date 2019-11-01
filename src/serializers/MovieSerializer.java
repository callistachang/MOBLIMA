package serializers;

import java.util.ArrayList;
import java.util.Arrays;

import models.Movie;

// maybe i should hv 1 serializer and 1 deserializer...?
public class MovieSerializer {
	
	// lol testing again
//	public static void main(String[] args) {
//		ArrayList<String> casts = new ArrayList<String>();
//		casts.add("hi");
//		casts.add("more hi");
//		Movie m1 = new Movie("Test", "Coming Soon", "test", "test", casts, 120);
//		String dataString = serialize(m1);
//		System.out.println(dataString);
//		
//		deserialize(dataString);
//	}
	
	public static String serialize(Movie movie) {
		ArrayList<String> castsArray = movie.getCasts();
		
		String castData = "";
		for (int i = 0; i < castsArray.size(); i++) {
			castData += castsArray.get(i) + ";";
		}
		
		return movie.getTitle() + "," + movie.getShowingStatus() + "," + movie.getSynopsis() + "," +
		       movie.getDirector() + "," + castData + "," + movie.getDuration();
	}
	
	public static ArrayList<String> serialize(ArrayList<Movie> movieArray) {
		ArrayList<String> dataArray = new ArrayList<String>();
		
		for (Movie m: movieArray) {
			dataArray.add(serialize(m));
		}
		
		return dataArray;
	}
	
	public static Movie deserialize(String data) {
		String[] dataStrings = data.split(",");
		// System.out.println(Arrays.toString(test));
		String[] casts = dataStrings[4].split(";");
		ArrayList<String> castsAsArray = new ArrayList<String>(Arrays.asList(casts));
		int duration = Integer.parseInt(dataStrings[5]);
		
		return new Movie(dataStrings[0], dataStrings[1], dataStrings[2], dataStrings[3], castsAsArray, duration);
	}
	
	// feels like making this an all purpose function
	// formula???
	public static ArrayList<Movie> deserialize(ArrayList<String> dataArray) {
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		for (String data: dataArray) {
			movieArray.add(deserialize(data));
		}
		return movieArray;
	}
}
