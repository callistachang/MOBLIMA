package main;

import java.util.Scanner;

public class GuestApp {
	
	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		// Should add a function to let moviegoer create account or login to their account.
		// Name, age, mobile number, email address.
		// Hence we create a new MovieGoer object here.
		
		System.out.println("===================== Guest Menu ======================");
		System.out.println("(1) List movies");
		System.out.println("(2) View movie details (including reviews and ratings)");
		System.out.println("(3) Check seat availabilities");
		System.out.println("(4) List top five movies");
		System.out.println("(5) Exit");
		// List pricing details?
		System.out.println("=======================================================");
		
		do {
			System.out.print("\nChoose an option: ");
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					listMovies();
					break;
				case 2:
					viewMovieDetails();
					break;
				case 3:
					checkSeatAvailabilities();
					break;
				case 4:
					listTop5Movies();
					break;
				case 5:
					System.out.println("Exited from the movie-goer menu.");
					sc.nextLine();
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
				}
		} while (choice != 5);
		sc.close();
	}
	

	// (2) List movies
	protected void listMovies() {
		// Ask the user if they want to 1. list ALL movies, or 2. just movies playing in a certain cineplex
		// If option 2, ask them to pick the cineplex then show movies playing only in that cineplex.
		
		// Read from an external CSV file containing movie information, filter as necessary
	}
	
	// (3) View movie details (including reviews and ratings)
	protected void viewMovieDetails() {
		// Ask user which movie they want to query.
		
		// Read from an external CSV file containing movie information, but find only the specific movie
		// Read from an external CSV file containing review information, but find it for that movie only
		// Read from an external CSV file containing ratings information, but find it for that movie only
	}

// (4) Check seat availabilities
	protected void checkSeatAvailabilities() {
	// ask user to pick cineplex
	// then pick movie
	// then show the number of available seats per showtime for that movie remaining in the day.
	// maybe can do that 'selling fast' 'available' 'fully booked' stuff.
	}


// (6) List top five movies
	protected void listTop5Movies() {
	// Ask user if they want to filter by 1. ticket sales, or 2. reviewer ratings.
	
	// If 1, look at the booking receipts database and do the math.
	
	// If 2, look at the ratings database and do the math.
	}

}