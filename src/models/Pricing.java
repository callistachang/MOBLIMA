package models;

import java.util.ArrayList;

import managers.Formatter;
/**
 * Contains information of the base ticket price, discounts and premiums.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */

public class Pricing implements ISerializable {
	/**
	 * Standard price for a movie ticket, before discounts and premiums.
	 */
	private double basePrice;
	/**
	 * Additional cost of buying ticket for movies shown on weekends.
	 */
	private double weekendPremium;
	/**
	 * The decrease in cost of buying movie ticket for a student.
	 */
	private double studentDiscount;
	/**
	 * The decrease in cost of buying movie ticket for a senior citizen.
	 */
	private double seniorCitizenDiscount;
	/**
	 * Additional cost of buying ticket for movies with special features.
	 */
	private double movieTypePremium;
	/**
	 * Additional cost of buying ticket for movies shown in Platinum cinema.
	 */
	private double platinumCinemaPremium;
	/**
	 * Additional cost of buying ticket for movies shown in Gold cinema.
	 */
	private double goldCinemaPremium;
	
/**
 * Creates a new pricing object with the given parameters.
 * @param basePrice This pricing's basic price.
 * @param weekendPremium This pricing's cost of weekend premium.
 * @param studentDiscount This pricing's decrease due to student discount.
 * @param seniorCitizenDiscount This pricing's decrease due to senior citizen discount.
 * @param movieTypePremium This pricing's cost of movie type premium.
 * @param platinumCinemaPremium This pricing's cost of movie being screened at platinum cinema. 
 * @param goldCinemaPremium This pricing's cost of movie being screened at gold cinema. 
 */
	public Pricing(double basePrice, double weekendPremium, double studentDiscount, double seniorCitizenDiscount,
			double movieTypePremium, double platinumCinemaPremium, double goldCinemaPremium) {
		this.basePrice = basePrice;
		this.weekendPremium = weekendPremium;
		this.studentDiscount = studentDiscount;
		this.seniorCitizenDiscount = seniorCitizenDiscount;
		this.movieTypePremium = movieTypePremium;
		this.platinumCinemaPremium = platinumCinemaPremium;
		this.goldCinemaPremium = goldCinemaPremium;
	}

	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	@Override
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(Formatter.getStringFromDouble(basePrice));
		data.add(Formatter.getStringFromDouble(weekendPremium));
		data.add(Formatter.getStringFromDouble(studentDiscount));
		data.add(Formatter.getStringFromDouble(seniorCitizenDiscount));
		data.add(Formatter.getStringFromDouble(movieTypePremium));
		data.add(Formatter.getStringFromDouble(platinumCinemaPremium));
		data.add(Formatter.getStringFromDouble(goldCinemaPremium));
		return data;
	}
	
	/**
	 * Gets the basic price of the movie.
	 * @return The basic price of a movie ticket.
	 */
	public double getBasePrice() {
		return basePrice;
	}
	
	/**
	 * Basic price of the movie to be set.
	 * @param basePrice This pricing'sS basic price of a movie ticket.
	 */
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	/**
	 * Gets the weekend additional cost for the movie.
	 * @return This pricing's weekend additional cost of a movie ticket.
	 */
	public double getWeekendPremium() {
		return weekendPremium;
	}
	/**
	 * Additional cost of weekend movie to be set.
	 * @param weekendPremium This pricing's weekend additional cost of a movie ticket.
	 */
	public void setWeekendPremium(double weekendPremium) {
		this.weekendPremium = weekendPremium;
	}
	/**
	 * Gets the movie discount value for student.
	 * @return This pricing's movie ticket discount value for student.
	 */
	public double getStudentDiscount() {
		return studentDiscount;
	}
	/**
	 * Movie discount value for student to be set.
	 * @param studentDiscount This pricing's movie ticket discount value for student.
	 */
	public void setStudentDiscount(double studentDiscount) {
		this.studentDiscount = studentDiscount;
	}
	
	/**
	 * Gets the movie discount value for senior citizens.
	 * @return This pricing's movie ticket discount value for senior citizen.
	 */
	public double getSeniorCitizenDiscount() {
		return seniorCitizenDiscount;
	}
	/**
	 * Movie discount value for senior citizen to be set.
	 * @param studentDiscount This pricing's movie ticket discount value for senior citizen.
	 */
	public void setSeniorCitizenDiscount(double seniorCitizenDiscount) {
		this.seniorCitizenDiscount = seniorCitizenDiscount;
	}
	/**
	 * Gets the additional price for movie type.
	 * @return This pricing's additional cost for movie type.
	 */
	public double getMovieTypePremium() {
		return movieTypePremium;
	}
	
	/**
	 * Additional cost of movie type to be set.
	 * @param weekendPremium This pricing's additional cost for movie type.
	 */
	public void setMovieTypePremium(double movieTypePremium) {
		this.movieTypePremium = movieTypePremium;
	}
	
	/**
	 * * Gets the additional cost of watching movie in platinum cinema.
	 * @return This pricing's additional cost for platinum cinema.
	 */
	public double getPlatinumCinemaPremium() {
		return platinumCinemaPremium;
	}
	
	/**
	 * Additional cost of watching movie in platinum cinema to be set.
	 * @param weekendPremium This pricing's additional cost for platinum cinema.
	 */
	public void setPlatinumCinemaPremium(double platinumCinemaPremium) {
		this.platinumCinemaPremium = platinumCinemaPremium;
	}
	
	/**
	 * * Gets the additional cost of watching movie in gold cinema.
	 * @return This pricing's additional cost for gold cinema.
	 */
	public double getGoldCinemaPremium() {
		return goldCinemaPremium;
	}
	
	/**
	 * Additional cost of watching movie in gold cinema to be set.
	 * @param weekendPremium This pricing's additional cost for gold cinema.
	 */
	public void setGoldCinemaPremium(double goldCinemaPremium) {
		this.goldCinemaPremium = goldCinemaPremium;
	}

	
	
	
}
