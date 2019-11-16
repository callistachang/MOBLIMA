package main;

import java.util.Scanner;

import managers.AccountManager;
import managers.BookingManager;
import managers.CineplexManager;
import managers.MovieManager;
import managers.Printer;
import managers.ShowtimeManager;
import models.Cineplex;
import models.Movie;

public class UserApp {
	private final String menuOptions[] = {
		"List movies",
		"View movie details and reviews",
		"Check seat availabilities",
		"List top five movies"
	};
	
	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("===================== Guest Menu ======================");
			int i;
			for (i = 1; i <= menuOptions.length; i++) {
				System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
			}
			System.out.printf("(%d) %s \n", i, "Exit");
			System.out.println("=======================================================");
			
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
				System.out.println("Exited from the Guest menu.");
				sc.nextLine();
				break;
			default:
				System.out.println("Please input a valid option.");
				break;
			}
		} while (choice != 5);
		sc.close();
	}
		
	protected void listMovies() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		
		int choice;
		do {
			System.out.println("(1) List all movies");
			System.out.println("(2) Filter by cineplex");
			System.out.println("(3) Exit");
			
			System.out.println("Choose a number option:");
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					mm.listAll();
					break;
				case 2:
					System.out.println("Choose a cineplex:");
					cxm.listAllCineplexes();		// prints out all the cineplexes
					String cineplexID = sc.next();	// user inputs chosen cineplex
					Cineplex cineplex = cxm.getCineplexByID(cineplexID);
					System.out.println("(1) Get all movies from the cineplex");
					System.out.println("(2) Get only showing movies from the cineplex");
					
					int allOrShowingChoice;
					do {
						System.out.println("Choose a number option:");
						allOrShowingChoice = sc.nextInt();
					} while (allOrShowingChoice != 1 || allOrShowingChoice != 2);
					
					switch (allOrShowingChoice) {
						case 1:
							cxm.listAllByCineplex(cineplex);
						case 2:
							cxm.listShowingByCineplex(cineplex);
					}
					break;
				case 3:
					System.out.println("Exited from List Movies Menu.");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
		} while (choice != 3);
	}

// (3) View movie details (including reviews and ratings)
	protected void viewMovieDetails() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		
		System.out.println("Which movie would you like to query?");
		mm.listAll();
		System.out.println("Type ID of movie:");
		int movieID = sc.nextInt();
		Movie movie = mm.getMovieByID(movieID);
		int choice;
		do {
			System.out.println("Please select from the options below:");
			System.out.println("(1) Movie Information");
			System.out.println("(2) Movie Ratings and Reviews");	
			System.out.println("(3) Exit");
			
			System.out.println("Choose an option");
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					Printer.printMovieInfo(movie);
					break;
				case 2:
					Printer.printMovieRatings(movie);
					break;
				case 3:
					System.out.println("Exited from Movie Details Menu.");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
		} while (choice != 3);
		
		// Ask user which movie they want to query.

		// Read from an external CSV file containing movie information, but find only
		// the specific movie
		// Read from an external CSV file containing review information, but find it for
		// that movie only
		// Read from an external CSV file containing ratings information, but find it
		// for that movie only
	}

// (4) Check seat availabilities
	protected void checkSeatAvailabilities() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		
		System.out.println("Which movie would you like to view?");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movieID = sc.nextInt();
		Movie movie = mm.getMovieByID(movieID)
		System.out.println("Which cineplex?");
		cxm.listAllByMovie(movie);
		
		System.out.println("Choose a number option:");
		int cineplexID = sc.nextInt();

		System.out.println("Showtimes Available:");
		cxm.listAllSeatAvailabilitiesInCineplexByMovie(cineplexID, movieID);
		
//		cxm.listAllShowtimesInCineplexByMovie(cineplexID, movieID);
		
		
		
//		  //// Scanner sc = new Scanner(System.in); //// //// MovieManager mm = new
//		  MovieManager(); //// CineplexManager cxm = new CineplexManager(); //// ////
//		  System.out.println("Which movie would you like to view?"); //// int
//		  noOfMovies = mm.listAll(); ////
//		  System.out.println("Choose a number option:"); //// int movieRow =
//		  sc.nextInt(); //// int movieID = mm.returnMovieID(movieRow); // // ////
//		  System.out.println("Which cineplex?"); //// int noOfCineplexes =
//		  cxm.listAll(); //// System.out.println("Choose a number option:"); //// int
//		  cineplexRow = sc.nextInt(); //// int cineplexID =
//		  cxm.returnCineplexID(cineplexRow); // ////
//		  System.out.println("Showtimes Available:"); //// int noOfShowtimes =
//		  cm.listAll();
		 

		// ask user to pick cineplex
		// then pick movie
		// then show the number of available seats per showtime for that movie remaining
		// in the day.
		// maybe can do that 'selling fast' 'available' 'fully booked' stuff.
		// listAll function for showtime will include seat availability beside each
		// movie
	}

// (6) List top five movies
	protected void listTop5Movies() {
		Scanner sc = new Scanner(System.in);

		MovieManager mm = new MovieManager();
		BookingManager bm = new BookingManager();

		System.out.println("Would you like to filter by:");
		System.out.println("(1) Ticket Sales");
		System.out.println("(3) Reviewer Ratings");
		System.out.println("Choose a number option:");
		
		int choice;
		do {
			System.out.println("Choose an option");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				bm.listTop5ByTicketSales();
				break;
			case 2:
				mm.listTop5ByRatings();
				break;
			case 3:
				System.out.println("Exited from Top Movies Menu.");
				break;
			default:
				System.out.println("Please input a valid option.");
				break;
			}
		} while (choice != 3);

		// Ask user if they want to filter by 1. ticket sales, or 2. reviewer ratings.

		// If 1, look at the booking receipts database and do the math.

		// If 2, look at the ratings database and do the math.
	}

	public String[] getMenuOptions() {
		return menuOptions;
	}
}