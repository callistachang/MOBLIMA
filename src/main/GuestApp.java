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
//		Scanner sc = new Scanner(System.in);
//		
//		MovieManager mm = new MovieManager();
//		
//		System.out.println("(1) List All Movies");
//		System.out.println("(2) Choose Specific Cineplex");
//		System.out.println("(3) Exit");
//		System.out.println("Choose a number option:");
//		
//		
//		do {
//		System.out.println("Choose an option");
//		choice = sc.nextInt();
//		switch (choice) {
//		case 1:
//			int noOfMovies = mm.listAll();
//			break;
//		case 2:
//			int noOfFilteredMoview = mm.filterByCineplex();
//			break;
//		case 3:
//			System.out.println("Exited from List Cinema Menu.");
//			break;
//		default:
//			System.out.println("Please input a valid option.");
//			break;
//			}
//		} while (choice != 3);	

		// Ask the user if they want to 1. list ALL movies, or 2. just movies playing in a certain cineplex
		// If option 2, ask them to pick the cineplex then show movies playing only in that cineplex.
		
		// Read from an external CSV file containing movie information, filter as necessary
	}
	
// (3) View movie details (including reviews and ratings)
	protected void viewMovieDetails() {
//		Scanner sc = new Scanner(System.in);
//		
//		MovieManager mm = new MovieManager();
//		CustomerManager cm = new CustomerManager();
		
//		System.out.println("Which movie would you like to query?");
//		int noOfMovies = mm.listAll();
//		System.out.println("Choose a number option:");
//		int movieRow = sc.nextInt();
		
//		System.out.println("Please select from the options below:");
//		System.out.println("(1) Movie Information");
//		System.out.println("(2) Movie Ratings and Reviews");
//		System.out.println("(3) Exit");
//		System.out.println("Choose a number option:");
//		int movieinfoRow = sc.nextInt();	
//		
//		do {
//		System.out.println("Choose an option");
//		choice = sc.nextInt();
//		switch (choice) {
//		case 1:
//			int movieID = mm.returnMovieID(movieRow);
//			mm.movieInformation(movieID);
//			break;
//		case 2:
//			int movieID = mm.returnMovieID(movieRow);
//			cm.movieRatings(movieID);
//			break;
//		case 3:
//			System.out.println("Exited from Movie Details Menu.");
//			break;
//		default:
//			System.out.println("Please input a valid option.");
//			break;
//			}
//		} while (choice != 3);
		
		// Ask user which movie they want to query.
		
		// Read from an external CSV file containing movie information, but find only the specific movie
		// Read from an external CSV file containing review information, but find it for that movie only
		// Read from an external CSV file containing ratings information, but find it for that movie only
	}

// (4) Check seat availabilities
	protected void checkSeatAvailabilities() {
//		Scanner sc = new Scanner(System.in);
//		
//		MovieManager mm = new MovieManager();
//		CineplexManager cxm = new CineplexManager();
//		
//		System.out.println("Which movie would you like to view?");
//		int noOfMovies = mm.listAll();
//		System.out.println("Choose a number option:");
//		int movieRow = sc.nextInt();
//		int movieID = mm.returnMovieID(movieRow);


//		System.out.println("Which cineplex?");
//		int noOfCineplexes = cxm.listAll();
//		System.out.println("Choose a number option:");
//		int cineplexRow = sc.nextInt();
//		int cineplexID = cxm.returnCineplexID(cineplexRow);

//		System.out.println("Showtimes Available:");
//		int noOfShowtimes = cm.listAll();
		
		
	// ask user to pick cineplex
	// then pick movie
	// then show the number of available seats per showtime for that movie remaining in the day.
	// maybe can do that 'selling fast' 'available' 'fully booked' stuff.
	// listAll function for showtime will include seat availability beside each movie
	}


// (6) List top five movies
	protected void listTop5Movies() {
//		Scanner sc = new Scanner(System.in);
//		
//		MovieManager mm = new MovieManager();
//		TicketManager tm = new TicketManager();
//		
//		System.out.println("Would you like to filter by:");
//		System.out.println("(1) Ticket Sales");
//		System.out.println("(3) Reviewer Ratings");
//		System.out.println("Choose a number option:");//		
//		
//		do {
//		System.out.println("Choose an option");
//		choice = sc.nextInt();
//		switch (choice) {
//		case 1:
//			tm.topFiveReceipt();
//			break;
//		case 2:
//			mm.topFiveRating();
//			break;
//		case 3:
//			System.out.println("Exited from Top Movies Menu.");
//			break;
//		default:
//			System.out.println("Please input a valid option.");
//			break;
//			}
//		} while (choice != 3);	

		
		
	// Ask user if they want to filter by 1. ticket sales, or 2. reviewer ratings.
	
	// If 1, look at the booking receipts database and do the math.
	
	// If 2, look at the ratings database and do the math.
	}

}