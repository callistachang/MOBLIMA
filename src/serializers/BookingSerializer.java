package serializers;

import java.time.LocalDate;
import java.time.LocalTime;

import managers.CinemaManager;
import managers.Formatter;
import managers.ShowtimeManager;
import models.Booking;
import models.Cinema;
import models.ISerializable;
import models.Showtime;

public class BookingSerializer extends AbstractSerializer {

	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		System.out.println(d[1]);
		LocalDate localdate = Formatter.getLocalDateFromString(d[1]);
		LocalTime localtime = Formatter.getLocalTimeFromString(d[2]);
		ShowtimeSerializer ss = new ShowtimeSerializer();
		ShowtimeManager sm = new ShowtimeManager();
		Showtime showtime = sm.getShowtimeByID(Formatter.getIntFromString(d[3]));
		CinemaManager cm = new CinemaManager();
		Cinema cinema = cm.getCinemaByID(d[5]);
		double price = Formatter.getDoubleFromString(d[5]);
		
		return new Booking(d[0], localdate, localtime, showtime, cinema, price);
	}

}
