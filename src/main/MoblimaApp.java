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
					UserApp gApp = new UserApp();
					gApp.run();
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
		
		while (true) {
//			System.out.println("Enter username:");
//			String username = sc.next();
//			
//			System.out.println("Enter password:");
//			String password = sc.next();
//			
//			AccountManager am = new AccountManager();
//			
//			// i think this could possibly return account instead
//			int loginCode = am.login(username, password);
			
			System.out.println("[TEMP] Enter 1 to go to cinemastaffapp, 2 to go to moviegoerapp:");
			
			int loginCode = sc.nextInt();
			
			// userapp; use polymorphism
			
			if (loginCode == 1) {
				System.out.println("Login as admin successful.");
				CinemaStaffApp csApp = new CinemaStaffApp();
				csApp.run();
			}
			else if (loginCode == 2) {
				// how should we get account...?
				Account acc = null;
				MovieGoerApp mgApp = new MovieGoerApp(acc);
				mgApp.run();
			}
			else {
				System.out.println("Press (Y) to retry, and any other key to exit:");
				if (sc.next().compareToIgnoreCase("y") != 0)
					break;
			}
		}
	}
	
	public void createNewAccount() {
		Scanner sc = new Scanner(System.in);
		String username = null, password = null, confirmPassword = null;
		int control = 1, age = 0;

		AccountManager am = new AccountManager();

		while (control != 0){
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
					break;
				default:
					break;
			}	
			
			control = am.createAccount(username, password, confirmPassword, age);
			if (control == 0) {
				System.out.println("Account successfully created.");
				break;
			}
			
			System.out.println("Press (Y) to retry, and any other key to exit:");
			if (sc.next().compareToIgnoreCase("y") != 0)
				break;
		}
	}
}

