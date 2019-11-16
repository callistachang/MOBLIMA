package serializers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import managers.Formatter;
import models.ISerializable;
import models.Showtime;

public class ShowtimeSerializer extends AbstractSerializer {
	
	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		
		int id = Formatter.getIntFromString(d[0]);
		LocalDate date = Formatter.getLocalDateFromString(d[1]);
		LocalTime time = Formatter.getLocalTimeFromString(d[2]);
		int movieID = Formatter.getIntFromString(d[3]);
		ArrayList<Integer> seats = splitArrayToIntegers(d[4]);

		return new Showtime(id, date, time, movieID, seats);
	}
}
