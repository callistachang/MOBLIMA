package boundaries;
import java.util.Scanner;

import managers.AccountManager;
import models.Account;
/**
 * The main function for the entire application. 
 * Contains the preliminary overview of the entire flow of the application.
 * Determines access privileges of user.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class MoblimaApp {
	/**
	 * Establishing the main function.
	 * @param args No arguments were passed.
	 */
	public static void main(String[] args) {
		MoblimaApp mApp = new MoblimaApp();
		mApp.run();
	}
	/**
	 * Runs the main logic of this app.
	 * Displays startup menu for app.
	 * (1) Allows users to log into their account.
	 * (2) Allows user to access app as guest and use common 
	 * functions without having to log in.
	 * (3) Allows new users to make a customer account.
	 * (4) Exits Main Menu and terminates the application.
	 */
	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome to MOBLIMA! What would you like to do today?");
		
		do {
			System.out.println("\n====================== Main Menu ======================");
			System.out.println("(1) Log in to your account");
			System.out.println("(2) Continue as guest");
			System.out.println("(3) Create an account");
			System.out.println("(4) Exit");
			System.out.println("=======================================================");
			choice = InputBoundary.getIntInput("\nChoose an option: ");
			System.out.println();
			
			
			switch (choice) {
				case 1:
					login();
					break;
				case 2:
					UserApp uApp = new UserApp();
					uApp.run();
					break;
				case 3:
					createNewAccount();
					break;
				case 4:
					System.out.println("Thank you for using MOBLIMA!");
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("<Warning> Please input a valid option.");
					break;
			}
		} while (choice != 4);
	}
	
	/**
	 * Prompts user for access credentials.
	 * Determines which access privilege user should have.
	 */
	public void login() {
		Scanner sc = new Scanner(System.in);
		UserApp app = null;
		int retry;
		
		do {
			retry = 0;
			System.out.println("Enter username:");
			String username = sc.next();
			
			System.out.println("Enter password:");
			String password = sc.next();
			
			AccountManager am = new AccountManager();
			
			int userType = am.login(username, password);
			
			switch (userType) {
				case 1:
					app = new CinemaStaffApp();
					break;
				case 2:
					Account account = am.getAccountByUsername(username);
					app = new MovieGoerApp(account);
					break;
				default:
					System.out.println("Press (Y) to retry, and any other key to exit:");
					if (sc.next().compareToIgnoreCase("y") == 0) // if y chosen
						retry = 1;
					else
						return;
			}
		} while (retry == 1);
		
		app.run();
	}
	/**
	 * Creates a new customer account for users to access customer privileges.
	 * All customers need an account to book tickets or leave reviews.
	 */

	public void createNewAccount() {
		Scanner sc = new Scanner(System.in);
		String username = null, password = null, confirmPassword = null, mobileNumber = null, emailAddress = null;
		int control = 1, age = 0;

		AccountManager am = new AccountManager();

		while (control != 0) {
			switch (control) {
				case 1:
					System.out.println("Enter Username:");
					username = sc.nextLine();
				case 2:
				case 3:
					System.out.println("Enter Password:");
					password = sc.nextLine();
					System.out.println("Confirm Password:");
					confirmPassword = sc.nextLine();
				case 4:
					age = InputBoundary.getIntInput("Enter Age:");
//					sc.nextLine();
				case 5:
					System.out.println("Enter mobile number:");
					mobileNumber = sc.nextLine();
				case 6:
					System.out.println("Enter email address:");
					emailAddress = sc.nextLine();
				default:
					break;
			}	
			
			control = am.create(username, password, confirmPassword, age, mobileNumber, emailAddress);
			if (control == 0) {
				System.out.println("Account successfully created.");
				break;
			}
			
			System.out.println("Press (Y) to retry, and any other key to exit:");
			if (sc.nextLine().compareToIgnoreCase("y") != 0)
				break;
		}
	}
}

