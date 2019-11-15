package managers;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import models.Cinema;
import models.Pricing;
import models.Showtime;

import java.time.LocalDate;

public class PricingCalculator {
	private static final String DATABASE_NAME = "pricingdata";
	protected static ArrayList<Pricing> records = null;

	
	
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
}

