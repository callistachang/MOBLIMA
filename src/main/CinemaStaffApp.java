package main;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import managers.CinemaManager;
import managers.CineplexManager;
import managers.Formatter;
import managers.GetInput;
import managers.MovieManager;
import managers.Printer;
import managers.ShowtimeManager;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import models.Showtime;
/**
 * Subclass of UserApp. Allows access to functions for cinema staff.
 * Accessed via a general admin username and password.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class CinemaStaffApp extends UserApp {
	/**
	 * An array of strings with the options for the menu choices for cinema staff.
	 */
	private final String menuOptions[] = {
		"Create movie listing",
		"Update movie listing",
		"Remove movie listing",
		"Create movie showtime",
		"Update movie showtime",
		"Remove movie showtime",
		"Configure system settings"
	};
	/**
	 * Runs the main logic of this app.
	 * Displays menu options for cinema staff.
	 * (1) Add a movie to the database.
	 * (2) Update a movie in the database.
	 * (3) Remove a movie in the database.
	 * (4) Add a showtime for a movie in the database.
	 * (5) Update a showtime for a movie in the database.
	 * (6) Remove a showtime for a movie in the database.
	 * (7) Admin controls for cinema staff such as changes in price, holiday dates, etc.
	 * (8-11) Same menu options extended from UserApp.
	 * (12) Exits Cinema Staff Menu and CinemaStaffApp, enters Main Menu in MoblimaApp again.
	 */

	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
				
		
		int i;
		int j;
		
		
		do {
			System.out.println("================== Cinema Staff Menu ==================");
			for (i = 1; i <= menuOptions.length; i++) {
				System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
			}
			System.out.println("=======================================================");
			String[] guestOptions = super.getMenuOptions();
			for (j = 1; j <= guestOptions.length; i++, j++) {
				System.out.printf("(%d) %s \n", i, guestOptions[j-1]);
			}
			System.out.printf("(%d) %s \n", i, "Exit");
			System.out.println("=======================================================");
			
			choice = GetInput.getIntInput("Choose an option");
			switch (choice) {
				case 1:
					createMovieListing();
					break;
				case 2:
					updateMovieListing();
					break;
				case 3:
					removeMovieListing();
					break;
				case 4:
					createMovieShowtime();
					break;
				case 5:
					updateMovieShowtime();
					break;
				case 6:
					removeMovieShowtime();
					break;
				case 7:
					configureSettings();
					break;
				case 8:
					listMovies();
					break;
				case 9:
					viewMovieDetails();
					break;
				case 10:
					checkSeatAvailabilities();
					break;
				case 11:
					listTop5Movies();
					break;
				case 12:
					System.out.println("Exited from the cinema staff menu.");
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
			}
		} while (choice != 12);
	}
	/**
	 * Adds a new movie into the movie database. 
	 * Takes in the necessary details from cinema staff and stores it 
	 * in external movie csv.
	 */
	private void createMovieListing() {
		Scanner sc = new Scanner(System.in);
		String title, status, synopsis, director, cast, type;
		ArrayList<String> casts = new ArrayList<String>();
		int duration,choice;

		MovieManager mm = new MovieManager();
		
		// Prompt the user for inputs
		do {
			System.out.print("\nInsert movie title: ");
			title = sc.nextLine();
		} while (title == null);
		
		do {
			System.out.println();
			choice = GetInput.getIntInput("Insert movie showing status ((1)Coming Soon, (2)Now Showing, (3)No Longer Showing): ");
			switch(choice) {
			case 1:
				status = "Coming Soon";
				break;
			case 2:
				status = "Now Showing";
				break;
			case 3:
				status = "No Longer Showing";
				break;
			default:
				status = "";
				System.out.println("Please input a valid option.");
				break;
			}
		}
		
		while (!(status.equals("Coming Soon") || status.equals("Now Showing") || status.equals("No Longer Showing")));
		
		do {
			System.out.print("\nInsert movie synopsis: ");
			synopsis = sc.nextLine();
		} while (synopsis == null);
		
		do {
			System.out.print("\nInsert movie's director name: ");
			director = sc.nextLine();
		} while (director == null);
		
		System.out.println("\nInsert movie's cast members (enter '#' to quit): ");
		while (true) {
			cast = sc.nextLine();
			if (cast.equals("#")) 
				break;
			else {
				casts.add(cast);
			}
		}
		
		do {
			duration = GetInput.getIntInput("\nInsert duration of movie:");
		} while (duration == 0);
		
		do {
			System.out.println("\nInsert movie type (2D, 3D): ");
			type = sc.next();
		} while (!(type.equals("2D") || type.equals("3D")));
		
		mm.create(title, status, director, synopsis, casts, duration, type);
	}
	/**
	 * Updates a movie currently in the movie database. 
	 * Cinema staff can choose which attribute of the movie they want to edit, before
	 * updating the external movie csv.
	 */
	private void updateMovieListing() {
		Scanner sc = new Scanner(System.in);
		MovieManager mm = new MovieManager();
		
		System.out.println("");
		mm.listAll();
		int movieID = GetInput.getIntInput("Which movie would you like to update? Enter the movie ID:");
		
		// Print movie info after choosing the movie
		mm.printMovieInfo(movieID);
		
		System.out.println("What attribute would you like to change about the movie?");
		Printer.printMovieAttributes();
		
		int choice = GetInput.getIntInput("Choose number option:");
		switch(choice) {
		case(1):{
			String newTitle = GetInput.getStringInput("Enter the new title: ");
			sm.updateTitle(movieID, newTitle);
			break;
		}
		case(2):{
			String newStatus;
			do {
				System.out.println();
				int choice2 = GetInput.getIntInput("Insert movie showing status ((1)Coming Soon, (2)Now Showing, (3)No Longer Showing): ");
				switch(choice2) {
				case 1:
					newStatus = "Coming Soon";
					break;
				case 2:
					newStatus = "Now Showing";
					break;
				case 3:
					newStatus = "No Longer Showing";
					break;
				default:
					newStatus = "";
					System.out.println("Please input a valid option.");
					break;
				}
			}
			while (!(newStatus.equals("Coming Soon") || newStatus.equals("Now Showing") || newStatus.equals("No Longer Showing")));
			mm.updateShowingStatus(movieID, ShowingStatus.getByValue(newStatus));
		}
		case(3):{
			String newSynopsis = GetInput.getStringInput("Enter the new Synopsis: ");
			sm.updateTitle(movieID, newSynopsis);
			break;
		}
		System.out.println("(1) Title");
		System.out.println("(2) Showing Status");
		System.out.println("(3) Synopsis");
		System.out.println("(4) Director");
		System.out.println("(5) Duration");
		System.out.println("(6) Type");
		}
		String attrVal = sc.nextLine();
		
		System.out.println(attrNum + " " + attrVal);
		mm.update(movieID, attrNum, attrVal);
		System.out.println("Successfully updated");
	}
	/**
	 * Removes a movie from the movie database. 
	 * Determines movie to be removed via movieID from cinema staff and removes it 
	 * from external movie csv.
	 */
	private void removeMovieListing() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		
		
		mm.listAll();	// list of all movies with their IDs
		int movieID = GetInput.getIntInput("Which movie would you like to update? Enter movie ID:");
		System.out.println();
		char choice;
		do {
			choice = GetInput.getCharInput("Confirm? (Y)/(N)");
			switch (choice) {
			case 'y':
			case 'Y':
				mm.remove(movieID);
				System.out.println("Movie has been removed.");
				break;
			case 'n':
			case 'N':
				System.out.println("Transaction cancelled. No movies removed.");
				break;
			default:
				System.out.println("Please input a valid option.");
				break;
				}
		} while (!(choice == 'Y' || choice == 'y' || choice == 'n' || choice == 'N'));	
		
		// ask staff which movie they want to delete (show list of movies)
		// confirm?
		// will delete from movie list csv file.
	}
	/**
	 * Adds a new showtime into the showtime database. 
	 * Takes in the necessary details from cinema staff and stores it 
	 * in external showtime csv.
	 */
	private void createMovieShowtime() {
		
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		CinemaManager cm = new CinemaManager();
		ShowtimeManager sm = new ShowtimeManager();

		System.out.println("Choose a movie:");
		mm.listAll();
		int movieID = GetInput.getIntInput("Choose a number option:");
//		Movie movie = mm.getMovieByID(movieID);
		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose by String ID:");
		String cineplexID = sc.next();
//		Cineplex cineplex = cxm.getCineplexByID(cineplexID);
		System.out.println("Which cinema?");
		cxm.listAllCinemasByCineplex(cineplexID);
		String cinemaID = GetInput.getCinemaIDInput("Enter cinemaID:");
//		Cinema cinema = cm.getCinemaByID(cinemaID);
		
//		Cinema cinema = cm.getCinemaByID("AA");
//		int movieID = 2;
//		LocalDate date = Formatter.getLocalDateFromString("2019-10-10");
//		LocalTime time = Formatter.getLocalTimeFromString("23:23:23");
		
		
		LocalDate date = GetInput.getDateInput("Enter the date in yyyy-MM-dd format.");
		LocalTime time = GetInput.getTimeInput("Enter the new time in HH:mm:ss format");
		
		Showtime showtime = sm.create(date, time, movieID);
		cm.addShowtime(cinemaID, showtime);
		
//		cinema.addShowtime(showtime); //error checking
	}
	/**
	 * Updates a showtime currently in the showtime database. 
	 * Cinema staff can choose which attribute of the showtime they want to edit, before
	 * updating the external showtime csv.
	 */
	private void updateMovieShowtime() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		CinemaManager cm = new CinemaManager();
		ShowtimeManager sm = new ShowtimeManager();
		
		// choose a movie i wanna update.
		System.out.println("Choose a movie:");
		mm.listAll();
		int movieID = GetInput.getIntInput("Choose an option");

		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose a number option:");
		String cineplexID = sc.next();
		System.out.println("Which cinema?");
		cxm.listAllCinemasByCineplex(cineplexID);
		String cinemaID = GetInput.getCinemaIDInput("Enter cinemaID:");
		Cinema cinema = cm.getCinemaByID(cinemaID);
		
		System.out.println("Which showtime would you like to update?");
		Printer.printShowtimeDetails(cinema);
		int showtimeID = GetInput.getIntInput("Choose an option");
		Showtime showtime = sm.getShowtimeByID(showtimeID);
		
		Printer.printShowtimeDetails(showtime); //
		int choice = GetInput.getIntInputWithinRange("Choose an option",1,3);
		switch(choice) {
			case(1):{
				mm.listAll();
				int newMovieID = GetInput.getIntInput("Enter the new movieID");
				sm.updateMovie(showtime, newMovieID);
				break;
			}
			case(2):{
				LocalDate newDate = GetInput.getDateInput("Enter the new date in yyyy-MM-dd format.");
				sm.updateDate(showtime, newDate);
				break;
			}
			case(3):{
				LocalTime newTime = GetInput.getTimeInput("Enter the new time in HH:mm:ss format.");
				sm.updateTime(showtime, newTime);
				break;
			}
		}
		System.out.println("Showtime have been updated!");
		
		 
	}
	/**
	 * Removes a showtime from the showtime database. 
	 * Determines showtime to be removed via showtime from cinema staff and removes it 
	 * from external showtime csv.
	 */
	private void removeMovieShowtime() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		CinemaManager cm = new CinemaManager();
		ShowtimeManager sm = new ShowtimeManager();

		
		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose a cineplexID:");
		String cineplexID = sc.next();
		System.out.println("Which cinema?");
		cxm.listAllCinemasByCineplex(cineplexID);
		String cinemaID = GetInput.getCinemaIDInput("Enter cinemaID:");
		Cinema cinema = cm.getCinemaByID(cinemaID);
		System.out.println("Which showtime would you like to remove?");
		Printer.printShowtimeDetails(cinema);
		int showtimeID = GetInput.getIntInput("Choose an option");
		Showtime showtime = sm.getShowtimeByID(showtimeID);
		char choice;
		do {
		System.out.println("Confirm? Y/N");
		choice = sc.next().toUpperCase().charAt(0);
		switch (choice) {
		case 'Y':
			cm.removeShowtime(cinema, showtime);
			System.out.println("Showtime has been removed.");
			break;
		case 'N':
			System.out.println("Transaction cancelled. No showtimes removed.");
			break;
		default:
			System.out.println("Please input a valid option.");
			break;
			}
		} while (choice != 'Y' && choice != 'N');	
		

	}
	
	private void configureSettings() {
		// settings menu:
		// 1. staff can choose to change holiday timings.
		// 2. or change pricings.
		// 3. any more ideas?
		
		// 1.
		// name the holiday
		// name the date
		// update to holiday csv file.
		
		// 2.
		// choose which type of pricing they wanna change. e.g. senior citizen wanna change from 6 to 7.
		// then update the prices in the prices csv file.
	}
	
}