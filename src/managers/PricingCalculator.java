package managers;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Cinema;
import models.ISerializable;
import models.Movie;
import models.Movie.MovieType;
import models.Pricing;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.MovieSerializer;
import serializers.PricingSerializer;

import java.time.LocalDate;

public class PricingCalculator {
	private static final String DATABASE_NAME = "pricingdata";
	private static Pricing pricing = null;
	
	public PricingCalculator() {
		if (pricing == null)
			initializeDatabase();
	}
	
	public double calculatePrice (int discountType, String cinemaID, int showtimeID) {
		ShowtimeManager sm = new ShowtimeManager();
		HolidayManager hm = new HolidayManager();
		CinemaManager cm = new CinemaManager();
		MovieManager mm = new MovieManager();
		double ticketPrice = pricing.getBasePrice();
		
		Showtime s = sm.getShowtimeByID(showtimeID);
		int movieID = s.getMovieID();
		Movie movie = mm.getMovieByID(movieID);
		String mt = movie.getType().toString();
		Cinema c = cm.getCinemaByID(cinemaID);
		DayOfWeek day = DayOfWeek.of(s.getDate().get(ChronoField.DAY_OF_WEEK));
		if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || hm.isHoliday(s.getDate())) {
			ticketPrice += pricing.getWeekendPremium();
		}
		//cinema class?
		switch(discountType) {
		case 0:
			break;
		case 1:
			ticketPrice += pricing.getStudentDiscount();
			break;
		case 2:
			ticketPrice += pricing.getSeniorCitizenDiscount();
	}
		
		if(mt.equals("3D")) {
			ticketPrice += pricing.getMovieTypePremium();
		}
		return ticketPrice;
	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new MovieSerializer();
		pricing = (Pricing) serializer.deserialize(data).get(0);
	}
	
//	private void updateDatabase() {
//		AbstractSerializer serializer = new PricingSerializer();
//		ArrayList<String> updatedRecords = serializer.serialize(pricing);
//		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
//	}
}

