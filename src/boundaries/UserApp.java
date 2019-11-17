 package boundaries;

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

			choice = InputBoundary.getIntInput("Choose an option");
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
				break;
			default:
				System.out.println("Please input a valid option.");
				break;
			}
		} while (choice != 5);
	}
	/**
	 * Lists relevant movies user wants to know about.
	 * (1) List all movies recorded in the movie database
	 * (2) Takes in user choice for cineplex, and lists movies that are associated with that cineplex.
	 * (3) Exits List Movies Menu.
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
			choice = InputBoundary.getIntInput("Choose an option");
			switch (choice) {
				case 1:
					mm.listAll();
					break;
				case 2:
					cxm.listAllCineplexes();		// prints out all the cineplexes
					String cineplexID = InputBoundary.getCineplexIDInput("Enter cineplexID:");	// user inputs chosen cineplex

					System.out.println("(1) Get all movies from the cineplex");
					System.out.println("(2) Get only showing movies from the cineplex");

					int allOrShowingChoice = 0;
					do {
						allOrShowingChoice = InputBoundary.getIntInput("Choose an option");
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
		int movieID = InputBoundary.getIntInput("Type ID of movie:");
		int choice;
		do {
			System.out.println("\nPlease select from the options below:");
			System.out.println("(1) Movie Information");
			System.out.println("(2) Movie Ratings and Reviews");
			System.out.println("(3) Exit");
			choice = InputBoundary.getIntInput("Choose an option");
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

	}

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
		int movieID = InputBoundary.getIntInput("Choose an option");
		Movie movie = mm.getMovieByID(movieID);
		System.out.println("Which cineplex?");
		cxm.listCineplexByMovie(movieID);

		String cineplexID = InputBoundary.getCineplexIDInput("Enter cineplexID:");
		Cineplex cineplex = cxm.getCineplexByID(cineplexID);
		
		System.out.println("Showtimes Available:");
		cxm.listAllSeatAvailabilitiesInCineplexByMovie(cineplex, movie);
	}

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
			System.out.println("(3) Exit");
			choice = InputBoundary.getIntInput("Choose an option:");
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

	}
	/**
	 * Gets all the menu options for the common functions in a String Array.
	 * @return String array of menu options.
	 */
	public String[] getMenuOptions() {
		return menuOptions;
	}
}
