package models;

import java.util.ArrayList;

import managers.Formatter;

/**
 * Represents the personal data of account holders.
 * An account can belong to a customer or a staff.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */

public class Account implements ISerializable {
	
	/**
	 * The unique account name of the user.
	 */
	private String username;
	/**
	 * The age of the user. 
	 */
	private int age;
	/**
	 * The contact number of the user.
	 */
	private String mobileNumber;
	/**
	 * The email address of the user.
	 */
	private String emailAddress;
	/**
	 * The account password of the user.
	 */
	private String password;
	/**
	 * The past list of bookings done by user using the account.
	 * Each booking is represented by a BookingID
	 */
	private ArrayList<Booking> bookings;
	
	/**
	 * Creates a new account with the given personal details.
	 * @param username This user's username.
	 * @param age This user's age.
	 * @param mobileNumber This user's mobile number.
	 * @param emailAddress This user's email address.
	 * @param password This user's password.
	 * @param bookings This user's list of past bookings.
	 */
	public Account(String username, int age, String mobileNumber, String emailAddress, String password,
			ArrayList<Booking> bookings) {
		this.username = username;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		this.bookings = bookings;
	}
	
	/**
	 * Retrieves data from entity for serializing.
	 * @return Array of required details.
	 */
	@Override
	public ArrayList<Object> getSerializableData() {
		ArrayList<Object> data =  new ArrayList<Object>();
		data.add(username);
		data.add(Formatter.getStringFromInt(age));
		data.add(mobileNumber);
		data.add(emailAddress);
		data.add(password);
		data.add(getBookingIDs());
		return data;
	}
	/**
	* Gets the username of this user. 
	* @return This user's username.
	*/
	public String getUsername() {
		return username;
	}
	/**
	 * Username to set.
	 * @param username This user's username. Should be unique.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	* Gets the age of this user.
	* @return This user's age.
	*/
	public int getAge() {
		return age;
	}
	
	/**
	 * Age to set.
	 * @param age This user's age. 
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	* Gets the mobile number of this user.
	* @return This user's mobile number.
	*/
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	/**
	 * Mobile number to set.
	 * @param mobileNumber This user's mobile number.
	 * 			  Should start with 6,8 or 9. 
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
	* Gets the email address of this user.
	* @return This user's email address.
	*/
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Email address to set.
	 * @param emailAddress This user's email address.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	* Gets the password of this user.
	* @return This user's password.
	*/
	public String getPassword() {
		return password;
	}
	
	/**
	 * Password is set.
	 * @param password This user's account password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	* Gets the list of booking of user.
	* @return This user's list of bookings.
	*/
	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
	/**
	* Gets the list of bookingID of user.
	* @return This user's list of TID from the bookings.
	*/
	public ArrayList<String> getBookingIDs() {
		ArrayList<String> bookingsStr = new ArrayList<String>();
		if (bookings == null || bookings.get(0) == null)
			return null;
		for (Booking b: bookings) {
			bookingsStr.add(b.getTID());
		}
		return bookingsStr;
	}
	

	/**
	 * Bookings list is set.
	 * @param bookings This user's list of past bookings.
	 */
	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	/**
	 * Gets the username of this user as its string representation.
	 * @return This user's username.
	 */
	public String toString() {
		return username;
	}
	
	/**
	 * Add a booking object into the list of bookings the account made
	 * @param booking The booking object to be added
	 */
	public void addBooking(Booking booking) {
		if (bookings == null) {
			bookings = new ArrayList<Booking>();
		}
		bookings.add(booking);
		
	}
	
}
