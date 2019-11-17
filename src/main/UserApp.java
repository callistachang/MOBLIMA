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
/**
 * The most basic access privileges a user can have. Accessed by guests.
 * Contains common functions shared by customer and cinema staff that do 
 * not need to be user specific.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class UserApp {
	/**
	 * An array of strings with the options for the menu choices for any user.
	 */
	private final String menuOptions[] = {
		"List movies",
		"View movie details and reviews",
		"Check seat availabilities",
		"List top five movies"
	};
	/**
	 * Runs the main logic of this app.
	 * Displays menu options for guests.
	 * (1) List movies based on user preference.
	 * (2) Provide movie information.
	 * (3) Shows availability of seats for user-specified showtimes.
	 * (4) Ranks movies based on user preference.
	 * (5) Exits Guest Menu and UserApp, enters Main Menu in MoblimaApp again.
	 */
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
	/**
	 * Lists relevant movies user wants to know about.
	 * (1) List all movies recorded in the movie database
	 * (2) Takes in user choice for cineplex, and lists movies that are associated with that cineplex.
	 * (3) Exits List Movies Menu and enters Guest Menu again.
	 */
	protected void listMovies() {
		Scanner sc = new Scanner(System.in);

		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();

		int choice;
		do {
			System.out.println("==========Movie Listing Menu==========");
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

					System.out.println("(1) Get all movies from the cineplex");
					System.out.println("(2) Get only showing movies from the cineplex");

					int allOrShowingChoice = 0;
					do {
						System.out.println("Choose a number option:");
						allOrShowingChoice = sc.nextInt();
					} while (allOrShowingChoice != 1 && allOrShowingChoice != 2);

					switch (allOrShowingChoice) {
						case 1:
							cxm.listAllMovies(cineplexID);
							System.out.println("Success!");
							break;
						case 2:
							cxm.listAllShowingMovies(cineplexID);
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
	/**
	 * Provides details about movie chosen by user via movie ID.
	 * (1) Prints information of selected movie (e.g. title, duration, director etc.).
	 * (2) Prints all the reviews of selected movie, along with the associated
	 * user and rating given.
	 * (3) Exits Movie Details Menu and enters List Movies Menu again.
	 */
	protected void viewMovieDetails() {
		Scanner sc = new Scanner(System.in);

		MovieManager mm = new MovieManager();

		System.out.println("Which movie would you like to query?");
		mm.listAll();
		System.out.println("Type ID of movie:");
		int movieID = sc.nextInt();
//		Movie movie = mm.getMovieByID(movieID);
		int choice;
		do {
			System.out.println("\nPlease select from the options below:");
			System.out.println("(1) Movie Information");
			System.out.println("(2) Movie Ratings and Reviews");
			System.out.println("(3) Exit");

			System.out.println("Choose an option");
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					mm.printMovieInfo(movieID);
					break;
				case 2:
					mm.printMovieReviews(movieID);
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
	/**
	 * Shows availability of seats at a specific showtime.
	 * Users choose movie and cineplex to choose desired showtime for query.
	 */
	protected void checkSeatAvailabilities() {
		Scanner sc = new Scanner(System.in);

		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();

		System.out.println("Which movie would you like to view?");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movieID = sc.nextInt();
		Movie movie = mm.getMovieByID(movieID);
		System.out.println("Which cineplex?");
		cxm.listCineplexByMovie(movieID);

		System.out.println("Enter cineplexID:");
		String cineplexID = sc.next();
		Cineplex cineplex = cxm.getCineplexByID(cineplexID);
		
		System.out.println("Showtimes Available:");
		cxm.listAllSeatAvailabilitiesInCineplexByMovie(cineplex, movie);

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
	/**
	 * Lists the top 5 movies based on user's requirements.
	 * (1) Lists top 5 movies by number of ticket sales.
	 * (2) Lists top 5 movies by average rating from all user ratings.
	 */
	protected void listTop5Movies() {
		Scanner sc = new Scanner(System.in);

		MovieManager mm = new MovieManager();
		BookingManager bm = new BookingManager();

		int choice;
		do {
			System.out.println("Would you like to filter by:");
			System.out.println("(1) Ticket Sales");
			System.out.println("(2) Reviewer Ratings");
			System.out.println("Choose a number option:");
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
	/**
	 * Gets all the menu options for the common functions in a String Array.
	 * @return String array of menu options.
	 */
	public String[] getMenuOptions() {
		return menuOptions;
	}
}
