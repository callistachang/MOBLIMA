package serializers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import managers.Formatter;
import models.ISerializable;
import models.Showtime;

/**
 * Handles the deserialization for Showtime class.
 * @version 1.0
 * @since 2019-11-17
 * @author balad
 *
 */
public class ShowtimeSerializer extends AbstractSerializer {
	
	/**
	 * Converts data of string type to Showtime type.
	 */
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
