package main;
import java.util.Scanner;

public class MoblimaApp {

	public static void main(String[] args) {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Loading data...");
		// add a function to create/load initial data.
		System.out.println("Data loading successful.");
		
		System.out.println("\nWelcome to MOBLIMA! What would you like to do today?");
		
		do {
			System.out.println("\n====================== Main Menu ======================");
			System.out.println("(1) Log in as movie-goer");
			System.out.println("(2) Log in as cinema staff");
			System.out.println("(3) Exit");
			System.out.println("=======================================================");
			
			System.out.print("\nChoose an option: ");
			choice = sc.nextInt();
			System.out.println();
			switch (choice) {
				case 1:
					MovieGoerApp mgApp = new MovieGoerApp();
					mgApp.run();
					break;
				case 2:
					CinemaStaffApp csApp = new CinemaStaffApp();
					csApp.run();
					break;
				case 3:
					System.out.println("Thank you for using MOBLIMA!");
					System.out.println("Program exiting...");
					break;
				default:
					System.out.println("<Warning> Please input a valid option.");
					break;
			}
		} while (choice != 3);
	}
}
