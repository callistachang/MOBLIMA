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
/**
 * Calculates price of a ticket based on user type and booking conditions.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class PricingCalculator {
	/**
	 * The name of the csv file used.
	 */
	private static final String DATABASE_NAME = "pricingdata";
	/**
	 * Initializes Pricing object.
	 */
	private static Pricing pricing = null;
	/**
	 * Checks if pricing object is null.
	 * If null, data from the csv file is written to the list.
	 */
	public PricingCalculator() {
		if (pricing == null)
			initializeDatabase();
	}
	/**
	 * Prints the pricing details
	 */
	
	public void printPricing() {
		Printer.printPricingInfo(pricing);
	}
	/**
	 * Updates the pricing attributes and update the database
	 * (1) Updates the base price
	 * (2) Updates the weekend premium
	 * (3) Updates the movie type premium
	 * (4) Updates the premium for a platinum class cinema
	 * (5) Updates the premium for a gold class cinema
	 * (6) Updates the discount for students
	 * (7) Updates the discount for senior citizens
	 * @param choice Choice of which pricing attribute to update
	 * @param newVal The new value of the attribute updated
	 */
	
	public void update(int choice, double newVal) {
		switch (choice) {
		case 1:
			pricing.setBasePrice(newVal);
			break;
		case 2:
			pricing.setWeekendPremium(newVal);
			break;
		case 3:
			pricing.setMovieTypePremium(newVal);
			break;
		case 4:
			pricing.setPlatinumCinemaPremium(newVal);
			break;
		case 5:
			pricing.setGoldCinemaPremium(newVal);
			break;
		case 6:
			pricing.setStudentDiscount(newVal);
			break;
		case 7:
			pricing.setSeniorCitizenDiscount(newVal);
			break;
		}
		updateDatabase();
	}
	
	/**
	 * Calculates the price of a ticket based on booking conditions and user type.
	 * @param discountType Applicable discount of user. 1: Student, 2: Senior Citizen.
	 * @param cinemaID Unique identification number of cinema selected by user.
	 * @param showtimeID Unique identification number of showtime selected by user.
	 * @return The final price of the ticket.
	 */
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
			System.out.println("Weekend premium added.");
			ticketPrice += pricing.getWeekendPremium();
		}
		//cinema class?
		switch(discountType) {
		case 0:
			break;
		case 1:
			System.out.println("Student discount applied.");
			ticketPrice -= pricing.getStudentDiscount();
			break;
		case 2:
			System.out.println("Senior citizen discount added.");
			ticketPrice -= pricing.getSeniorCitizenDiscount();
			break;
	}
		
		if(mt.equals("3D")) {
			System.out.println("3D movie premium added.");
			ticketPrice += pricing.getMovieTypePremium();
		}
		
		if (c.getCinemaClass().equals("Gold")) {
			System.out.println("Gold premium added.");
			ticketPrice += pricing.getGoldCinemaPremium();
		}
		
		if (c.getCinemaClass().equals("Platinum")) {
			System.out.println("Platinum premium added.");
			ticketPrice += pricing.getPlatinumCinemaPremium();
		}
		System.out.println("Ticket Price: " + ticketPrice);
		
		return ticketPrice;
	}
	/**
	 * Retrieve information from external csv file and converts it into a Pricing object.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new PricingSerializer();
		pricing = (Pricing) serializer.deserialize(data).get(0);
	}
	
	/**
	 * Converts current array of Cineplex objects into string to be stored in external csv file.
	 */
	private void updateDatabase() {
		ArrayList<Pricing> pricings = new ArrayList<Pricing>();
		pricings.add(pricing);
		AbstractSerializer serializer = new PricingSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(pricings);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
}

