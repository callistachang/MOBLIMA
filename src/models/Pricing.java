package models;

import java.util.ArrayList;

import managers.Formatter;

public class Pricing implements ISerializable {
	private double basePrice;
	private double weekendPremium;
	private double studentDiscount;
	private double seniorCitizenDiscount;
	private double movieTypePremium;
	private double platinumCinemaPremium;
	private double goldCinemaPremium;
	

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
	
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getWeekendPremium() {
		return weekendPremium;
	}
	public void setWeekendPremium(double weekendPremium) {
		this.weekendPremium = weekendPremium;
	}
	public double getStudentDiscount() {
		return studentDiscount;
	}
	public void setStudentDiscount(double studentDiscount) {
		this.studentDiscount = studentDiscount;
	}
	public double getSeniorCitizenDiscount() {
		return seniorCitizenDiscount;
	}
	public void setSeniorCitizenDiscount(double seniorCitizenDiscount) {
		this.seniorCitizenDiscount = seniorCitizenDiscount;
	}
	public double getMovieTypePremium() {
		return movieTypePremium;
	}
	public void setMovieTypePremium(double movieTypePremium) {
		this.movieTypePremium = movieTypePremium;
	}
	public double getPlatinumCinemaPremium() {
		return platinumCinemaPremium;
	}
	public void setPlatinumCinemaPremium(double platinumCinemaPremium) {
		this.platinumCinemaPremium = platinumCinemaPremium;
	}
	public double getGoldCinemaPremium() {
		return goldCinemaPremium;
	}
	public void setGoldCinemaPremium(double goldCinemaPremium) {
		this.goldCinemaPremium = goldCinemaPremium;
	}

	
	
	
}
