package serializers;

import java.util.ArrayList;

import models.ISerializable;
import models.Showtime;

public class ShowtimeSerializer extends AbstractSerializer {
	
	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		ArrayList<String> timings = parseArrayToStrings(d, 2);
		
		int[] seatInts = parseArrayToIntegers(d, 4);
		ArrayList<Integer> seats = new ArrayList<Integer>();
		for (int i: seatInts) {
			seats.add(i);
		}
		
		return new Showtime(d[0], d[1], timings, d[3], seats);
	}
}
