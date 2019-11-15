package managers;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import handlers.DatabaseHandler;
import models.Cinema;
import models.ISerializable;
import models.Pricing;
import models.Showtime;
import serializers.AbstractSerializer;
import serializers.MovieSerializer;
import serializers.PricingSerializer;

import java.time.LocalDate;

public class PricingCalculator {
	private static final String DATABASE_NAME = "pricingdata";
	private static Pricing pricing = null;
	
	public double calculatePrice (Boolean isStudent, int age, int showtimeID) {
		Pricing pricing = new Pricing();
		ShowtimeManager sm = new ShowtimeManager();
		HolidayManager hm = new HolidayManager();
		CinemaManager cm = new CinemaManager();
		MovieManager mm = new MovieManager();
		double ticketPrice = pricing.getBasePrice();
		
		Showtime s = sm.getShowtime(showtimeID);
		MovieType m = s.movie.getMovieType();
		Cinema c = cm.getCinema(s.cinemaId);
		DayOfWeek day = DayOfWeek.of(s.date.get(ChronoField.DAY_OF_WEEK));
		if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || hm.isHoliday(s.date)) {
			ticketPrice += pricing.getWeekendPremium();
		}
		if(isStudent) {
			ticketPrice -= pricing.getStudentDiscount();
		}
		if(age < 65) {
			ticketPrice -= pricing.getSeniorCitizenDiscount();
		}
		switch(c.getCinemaClass()) {
			case "Gold":
				ticketPrice += pricing.getGoldCinemaPremium();
				break;
			case "Platinum":
				ticketPrice += pricing.getPlatinumCinemaPremium();
				break;
		}
		if(m == 3D) {
			ticketPrice += pricing.getMovieTypePremium();
		}
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

