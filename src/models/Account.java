package models;

import java.util.ArrayList;

public class Account {
	private String username;
	private int age;
	private String mobileNumber;
	private String emailAddress;
	private String password;
	private ArrayList<Booking> bookings;
	

	
	public Account(int id, String name, int age, String mobileNumber, String emailAddress, String password,
			ArrayList<Booking> bookings) {
		this.username = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		this.bookings = bookings;
	}

	public String getUserame() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
}
