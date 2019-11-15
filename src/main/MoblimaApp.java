package main;
import java.util.Scanner;

import managers.AccountManager;
import models.Account;

public class MoblimaApp {
	
	public static void main(String[] args) {
		MoblimaApp mApp = new MoblimaApp();
		mApp.run();
	}
	
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
			System.out.print("\nChoose an option: ");
			choice = sc.nextInt();
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
	
	// TODO
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
					System.out.println("Enter Age:");
					age = sc.nextInt();
					sc.nextLine();
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

