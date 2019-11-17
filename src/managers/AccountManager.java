package managers;
import java.util.ArrayList;

import handlers.DatabaseHandler;
import main.CinemaStaffApp;
import models.Account;
import serializers.AbstractSerializer;
import serializers.AccountSerializer;
import serializers.ShowtimeSerializer;
/**
 * Controller for the entity labelled Account.
 * Contains the logic to link Account to other classes. 
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 */
public class AccountManager {
	/**
	 * The name of the csv file used. 
	 */
	private static final String DATABASE_NAME = "accountdata";
	/**
	 * Initialises empty array of Account objects.
	 */
	private static ArrayList<Account> records = null;
	/**
	 * The staff username used to login.
	 */
	private final String adminUsername = "q";
	/**
	 * The staff password used to login. 
	 */
	private final String adminPassword = "q";
	
	/**
	 * Checks if array list of Account objects is null.
	 * If null, data fom the csv file is written to the list.
	 */
	public AccountManager() {
		if (records == null) {
			initializeDatabase();
		}
	}
	/**
	 * Iterates through records to compare given username against list of usernames for an account.
	 * @param username Unique identification name of the user.
	 * @return Account of the user whose username is present in records. 
	 */
	public Account getAccountByUsername(String username) {
		for (Account a: records) {
			if (a.getUsername().equals(username)) {
				return a;
			}
		}
		return null;
	}
	/**
	 * Iterates through records to compare given username against list of usernames for an account.
	 * @param username  Account name of the user.
	 * @return Boolean value to indicate the presence of an account for the user. 
	 */
	public boolean isExistingUsername(String username) {
		for (Account a: records) {
			if (a.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifies the user's input password matches the one in the given account.
	 * @param username Account name of the user.
	 * @param password Unique authentication key entered by the user.
	 * @return Boolean value to indicate whether the input password matches the account's password. 
	 */
	public boolean isMatchingPassword(String username, String password) {
		Account account = getAccountByUsername(username);
		if (account.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Checks if the password entered is valid in terms of length.
	 * @param password Unique authentication key entered by the user.
	 * @return Boolean value to indicate whether the input password is of appropriate length.
	 */
	public boolean isValidPassword(String password) {
		if (password.length() < 6 || password.length() > 20) {
			System.out.println("Your password must be between 6-20 characters long.");
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Controls the ability of an user to enter the system.
	 * @param username Account name of the user.
	 * @param password Unique authentication key entered in by the user.
	 * @return Error if username or password does not match.
	 */
	public int login(String username, String password) {
		
		if (username.equals(adminUsername)) {
			if (password.equals(adminPassword)) {
				return 1;
			}
		}
		
		if (!isExistingUsername(username)) {
			System.out.println("Username doesn't exist!");
			return 0;
		}
		
		if (!isMatchingPassword(username, password)) {
			System.out.println("Password doesn't match!");
			return 0;
		}
		
		return 2;
	}
	
	/**
	 * Creates a single account given the username and password and user details.
	 * @param username New username given by user.
	 * @param password New password given by the user.
	 * @param confirmPassword Verifies that the given password is the same as the password to be confirmed. 
	 * @param age Age given by the user.
	 * @param mobileNumber Contact number given by the user. 
	 * @param emailAddress Email address given by the user. 
	 * @return A new account if the input details are correct and an error if any of the input details are wrong.
	 */
	public int create(String username, String password, String confirmPassword, int age, String mobileNumber, String emailAddress) {
		if (isExistingUsername(username)) {
			System.out.println("Username is taken!");
			return 1;
		}
		

		if (!isValidPassword(password)) {
			System.out.println("Please enter a password between 6-20 characters!");
			return 2;
		}
		
		if (!password.equals(confirmPassword)) {
			System.out.println("The passwords do not match!");
			return 3;
		}
		
		if(age<=0)
		{
			System.out.println("Please enter a valid age");
			return 4;
		}
		
		if (mobileNumber.charAt(0) != '9' && mobileNumber.charAt(0) != '8' && mobileNumber.charAt(0) != '6') {
			System.out.println("Please enter a valid mobile number");
			return 5;
		}
		
		if(mobileNumber.length() != 8){
			System.out.println("Please enter a valid mobile number");
			return 5;
		}
		
		try {
	        Integer.parseInt(mobileNumber);
	    } catch (NumberFormatException | NullPointerException nfe) {
	    	System.out.println("Please enter a valid mobile number");
	        return 5;
		}
		
		if (!emailAddress.contains("@")) {
			System.out.println("Please enter a valid email address!");
			return 6;
		}
		
		else {
			Account account = new Account(username, age, mobileNumber, emailAddress, password, null);
			records.add(account); // add to records
			updateDatabase();
			return 0;
		}
	}
	
	/**
	 * Retrieves information from external csv file and converts it into an array of account objects.
	 */
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new AccountSerializer();
		records = serializer.deserialize(data);
	}
	
	/**
	 * Converts current array of accounts objects into string to be stored in external csv file. 
	 */
	private void updateDatabase() {
		AbstractSerializer serializer = new AccountSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
	
}
