package managers;
import java.util.ArrayList;

import handlers.DatabaseHandler;
import main.CinemaStaffApp;
import models.Account;
import serializers.AbstractSerializer;
import serializers.AccountSerializer;
import serializers.ShowtimeSerializer;

public class AccountManager {
	private static final String DATABASE_NAME = "accountdata";
	private static ArrayList<Account> records = null;
	private final String adminUsername = "adminuser";
	private final String adminPassword = "adminpassword";
	
	public AccountManager() {
		if (records == null) {
			initializeDatabase();
		}
	}

	public Account getAccountByUsername(String username) {
		for (Account a: records) {
			if (a.getUsername().equals(username)) {
				return a;
			}
		}
		return null;
	}
	
	public boolean isExistingUsername(String username) {
		for (Account a: records) {
			if (a.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isMatchingPassword(String username, String password) {
		Account account = getAccountByUsername(username);
		if (account.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isValidPassword(String password) {
		if (password.length() < 6 || password.length() > 20) {
			System.out.println("Your password must be between 6-20 characters long.");
			return false;
		}
		else {
			return true;
		}
	}
	
	public int login(String username, String password) {
		
		if (username.equals(adminUsername)) {
			if (password.equals(adminPassword)) {
				return 1;
			}
		}
		
		if (!isExistingUsername(username)) {
			System.out.println("Please enter a valid username!");
			return 0;
		}
		
		if (!isMatchingPassword(username, password)) {
			System.out.println("Please enter a valid password!");
			return 0;
		}
		
		return 2;
	}
	
	public int create(String username, String password, String confirmPassword, int age, String mobileNumber, String emailAddress) {
		if (!isExistingUsername(username)) {
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
			Account acc = new Account(username, age, mobileNumber, emailAddress, password, null);
			//create new account and store it into the database
			return 0;
		}
	}
	
	private static void initializeDatabase() {
		ArrayList<String> data = DatabaseHandler.readDatabase(DATABASE_NAME);
		AbstractSerializer serializer = new AccountSerializer();
		records = serializer.deserialize(data);
	}
	
	private void updateDatabase() {
		AbstractSerializer serializer = new AccountSerializer();
		ArrayList<String> updatedRecords = serializer.serialize(records);
		DatabaseHandler.writeToDatabase(DATABASE_NAME, updatedRecords);
	}
	
}
