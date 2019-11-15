package managers;

import java.util.ArrayList;

import models.Booking;

public class BookingManager {
	private static ArrayList<Booking> records = null;
	
	public Booking getBookingByID(int accountID) {
		for (Booking b: records) {
			if (b.getId() == accountID) {
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
}
