package serializers;

import java.util.ArrayList;

import managers.BookingManager;
import managers.Formatter;
import managers.ShowtimeManager;
import models.Account;
import models.Booking;
import models.ISerializable;
import models.Showtime;

/**
 * Handles the deserialization for Account class.
 * @version 1.0
 * @since 2019-11-17
 * @author balad
 *
 */
public class AccountSerializer extends AbstractSerializer {

	/**
	 * Converts data of string type to Account type.
	 */
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		int age = Formatter.getIntFromString(d[1]);
		ArrayList<String> bookingTIDs = splitArrayToStrings(d[5]);
		ArrayList<Booking> bookings = parseIdArrayToBookings(bookingTIDs);

		
		return new Account(d[0], age, d[2], d[3], d[4], bookings);
	}
	
	public ArrayList<Booking> parseIdArrayToBookings(ArrayList<String> bookingTIDs) {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		BookingManager bm = new BookingManager();
		for (String tid: bookingTIDs) {
			Booking booking = bm.getBookingByID(tid);
			bookings.add(booking);
		}
		return bookings;
	}

}

