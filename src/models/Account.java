package models;

import java.util.ArrayList;

public class Account implements ISerializable {
	private String username;
	private int age;
	private String mobileNumber;
	private String emailAddress;
	private String password;
	private ArrayList<Booking> bookings;
	
	public Account(String username, int age, String mobileNumber, String emailAddress, String password,
			ArrayList<Booking> bookings) {
		this.username = username;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		this.bookings = bookings;
	}
	
	@Override
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data =  new ArrayList<Object>();
		data.add(getUsername());
		data.add(getPassword());
		data.add(getAge());
		data.add(getMobileNumber());
		data.add(getEmailAddress());
		data.add(getBookings());
		return data;
	}

	public String getUsername() {
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
