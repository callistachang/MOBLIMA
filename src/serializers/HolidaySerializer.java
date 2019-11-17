package serializers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import managers.Formatter;
import models.Holiday;
import models.ISerializable;
import models.Showtime;

/**
 * Handles the deserialization for Holiday class.
 * @version 1.0
 * @since 2019-11-17
 * @author balad
 *
 */
public class HolidaySerializer extends AbstractSerializer {
	
	/**
	 * Converts data of string type to Holiday type.
	 */
	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		int id = Formatter.getIntFromString(d[0]);
		
		LocalDate date = Formatter.getLocalDateFromString(d[2]);
		
		return new Holiday(id, d[1], date);
	}
}
