package managers;

import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Movie;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.ShowtimeSerializer;

public class ShowtimeManager {
	private static final String DATABASE_NAME = "showtimedata";
	protected static ArrayList<Showtime> records = null;

	public int listAll() {
		return 0; //to change the code accordingly 
	}
	
	public Showtime getShowtimeByID(int showtimeID) {
		for (Showtime s: records) {
			if (s.getId() == showtimeID) {
				return s;
			}
		}
		return null;
	}
	
	public int chooseAttributes(int showtimeID) {
		// TODO Auto-generated method stub
	}
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new ShowtimeSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new ShowtimeSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}

	public void updateShowtime(int showtimeID, int attrNum) {
		// TODO Auto-generated method stub
		
	}

	public void remove(int showtimeID) {
		// TODO Auto-generated method stub
		
	}
	public void bookSeat(Showtime showtime, int seatNo) {
		showtime.bookSeat(seatNo);
	}
}
