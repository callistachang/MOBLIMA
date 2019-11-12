package managers;
import java.util.ArrayList;
import main.CinemaStaffApp;
import main.MovieGoerApp;
import models.Account;
import models.Movie;;

public class AccountManager {
	private static final String DATABASE_NAME = "accountdata";
	private static ArrayList<Account> records = null;
	
	public boolean checkUsernameExist(String username) {
		
		//check the username in the database
		//if exist return true else return false
		return true;
	}
	
	public boolean checkPasswordMatch(String username, String password) {
		
		//check the password in the database match the username
		//if exist return true else return false
		return true;
	}
	
	public boolean checkPasswordValid(String password) {
		if (password.length() < 6 || password.length() > 20) {
			System.out.println("Your password must be between 6-20 characters long.");
			return false;
		}
		else {
			return true;
		}
	}
	
	public Account getAccount(String username) {
		//get the account from the array
		Account acc = new Account();
		return acc;
	}
	
	public int login(String username, String password) {

			
			if (!checkUsernameExist(username)) {
				System.out.println("Please enter a valid username!");
				return -1;
			}
			

			if (!checkPasswordMatch(username, password)) {
				System.out.println("Please enter a valid password!");
				return -1;
			}
			
			if (username.equalsIgnoreCase("Admin")) {
				
				CinemaStaffApp csApp = new CinemaStaffApp();
				csApp.run();
			}
			
			else {
				MovieGoerApp mgApp = new MovieGoerApp(getAccount(username));
				mgApp.run();
			}
			return 0;
	}
	
	public int create(String username, String password, String confirmPassword, int age) {
		if (!checkUsernameExist(username)) {
			System.out.println("Username is taken!");
			return 1;
		}
		

		if (!checkPasswordValid(password)) {
			System.out.println("Please enter a password between 6-20 characters!");
			return 2;
		}
		
		if (!password.equals(confirmPassword)) {
			System.out.println("The passwords do not match!");
			return 3;
		}
		
		else {
			Account acc = new Account();
			//create new account and store it into the database
			return 0;
		}
	}
	
}
