package main;
import java.util.Scanner;
import managers.*;
public class MoblimaApp {

	public static void main(String[] args) {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		MoblimaApp ma = new MoblimaApp();
		System.out.println("Loading data...");
		// add a function to create/load initial data.
		System.out.println("Data loading successful.");
		
		System.out.println("\nWelcome to MOBLIMA! What would you like to do today?");
		
		do {
			System.out.println("\n====================== Main Menu ======================");
			System.out.println("(1) Log in");
			System.out.println("(2) Continue as guest");
			System.out.println("(3) Create an account");
			System.out.println("(4) Exit");
			System.out.println("=======================================================");
			System.out.print("\nChoose an option: ");
			choice = sc.nextInt();
			System.out.println();
			switch (choice) {
				case 1:
					ma.login();
					break;
					
				case 2:
					GuestApp gApp = new GuestApp();
					gApp.run();
					break;
				case 3:
					
					ma.newAccount();
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
	
	public void newAccount() {
		Scanner sc = new Scanner(System.in);
		String username, password , confirmPassword;
		int control, age;
		username = "";
		password = "";
		confirmPassword = "";
		age = 0;

		AccountManager am = new AccountManager();
		control = 1;
		while (control !=0){
			switch(control) {
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
			System.out.println("Press (Y) to retry, any other key to exit");
			if(sc.next().compareToIgnoreCase("y") != 0){
				break;
			}
		}
	}
	public void login() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Username:");
			String username = sc.next();
			System.out.println("Enter Password:");
			String password = sc.next();
			AccountManager am = new AccountManager();
			if (am.login(username,password) != -1) {
				break;
			}
			else {
				System.out.println("Press (Y) to retry, any other key to quit");
				if (!sc.next().equalsIgnoreCase("y")) {
					break;
				}
			}
		
		}
	}
}

