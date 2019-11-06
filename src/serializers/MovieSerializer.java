package serializers;

import java.util.ArrayList;
import java.util.Arrays;

import models.Movie;
import models.ISerializable;

public class MovieSerializer extends AbstractSerializer {
	
	protected ISerializable deserialize(String data) {
		System.out.println(data);
		
		String[] dataStrings = data.split(",");
		String[] casts = dataStrings[4].split(";");
		ArrayList<String> castsAsArray = new ArrayList<String>(Arrays.asList(casts));
		int duration = Integer.parseInt(dataStrings[5]);
		
		return new Movie(dataStrings[0], dataStrings[1], dataStrings[2], dataStrings[3], castsAsArray, duration);
	}
}
