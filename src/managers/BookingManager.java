package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Booking;
import serializers.AbstractSerializer;
import serializers.AccountSerializer;
import serializers.BookingSerializer;

public class BookingManager {
	private static final String DATABASE_NAME = "bookingdata";
	private static ArrayList<Booking> records = null;
	
	public Booking getBookingByID(int bookingID) {
		for (Booking b: records) {
			if (b.getId() == bookingID) {
				return b;
			}
		}
		return null;
	}

	public void addReceipt() {
		
	}
	
	public void printReceipt() {
		
	}

	public void listTop5ByTicketSales() {
		// TODO Auto-generated method stub
		
	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new BookingSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new BookingSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}
