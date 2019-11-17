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
import managers.MovieManager;
import managers.Printer;
import managers.ShowtimeManager;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Movie.MovieType;
import models.Movie.ShowingStatus;
import models.Showtime;

public class CinemaStaffApp extends UserApp {
	private final String menuOptions[] = {
		"Create movie listing",
		"Update movie listing",
		"Remove movie listing",
		"Create movie showtime",
		"Update movie showtime",
		"Remove movie showtime",
		"Configure system settings"
	};
	
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
			
			System.out.print("\nChoose an option: ");
			choice = sc.nextInt();
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
	
	private void createMovieListing() {
		Scanner sc = new Scanner(System.in);
		String title, status, synopsis, director, cast, type;
		ArrayList<String> casts = new ArrayList<String>();
		int duration;

		MovieManager mm = new MovieManager();
		
		// Prompt the user for inputs
		do {
			System.out.print("\nInsert movie title: ");
			title = sc.nextLine();
		} while (title == null);
		
		do {
			System.out.println("\nInsert movie showing status (Coming Soon, Now Showing, No Longer Showing): ");
			status = sc.nextLine();
		} while (!(status.equals("Coming Soon") || status.equals("Now Showing") || status.equals("No Longer Showing")));
		
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
			System.out.print("\nInsert duration of movie: ");
			duration = sc.nextInt();
		} while (duration == 0);
		
		do {
			System.out.println("\nInsert movie type (2D, 3D): ");
			type = sc.next();
		} while (!(type.equals("2D") || type.equals("3D")));
		
		mm.create(title, status, director, synopsis, casts, duration, type);
	}
	
	private void updateMovieListing() {
		Scanner sc = new Scanner(System.in);
		MovieManager mm = new MovieManager();
		
		System.out.println("");
		mm.listAll();
		System.out.println("Which movie would you like to update? Type the movie ID:");
		int movieID = sc.nextInt();
		
		// Print movie info after choosing the movie
		mm.printMovieInfo(movieID);
		
		System.out.println("What attribute would you like to change about the movie? Choose number option:");
		Printer.printMovieAttributes();
		
		int attrNum = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Insert new value for that attribute:");
		String attrVal = sc.nextLine();
		
		System.out.println(attrNum + " " + attrVal);
		mm.update(movieID, attrNum, attrVal);
		System.out.println("Successfully updated");
	}
	
	private void removeMovieListing() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		
		System.out.println("Which movie would you like to update? Type the movie ID:");
		mm.listAll();	// list of all movies with their IDs
		int movieID = sc.nextInt();
		
		char choice;
		do {
		System.out.println("Confirm? Y/N");
		choice = sc.next().charAt(0);
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
	
	private void createMovieShowtime() {
		
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		CinemaManager cm = new CinemaManager();
		ShowtimeManager sm = new ShowtimeManager();

		System.out.println("Choose a movie:");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movieID = sc.nextInt();
//		Movie movie = mm.getMovieByID(movieID);
		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose by String ID:");
		String cineplexID = sc.next();
//		Cineplex cineplex = cxm.getCineplexByID(cineplexID);
		System.out.println("Which cinema?");
		cxm.listAllCinemasByCineplex(cineplexID);
		System.out.println("Choose by String ID:");
		String cinemaID = sc.next();
//		Cinema cinema = cm.getCinemaByID(cinemaID);
		
//		Cinema cinema = cm.getCinemaByID("AA");
//		int movieID = 2;
//		LocalDate date = Formatter.getLocalDateFromString("2019-10-10");
//		LocalTime time = Formatter.getLocalTimeFromString("23:23:23");
		
		
		System.out.println("Enter the date in yyyy-MM-dd format.");
		String dateInput = sc.next();
		LocalDate date = Formatter.getLocalDateFromString(dateInput);
		
		System.out.println("Enter the new time in HH:mm:ss format");
		String timeInput = sc.next();
		LocalTime time = Formatter.getLocalTimeFromString(timeInput);
		
		Showtime showtime = sm.create(date, time, movieID);
		cm.addShowtime(cinemaID, showtime);
		
//		cinema.addShowtime(showtime); //error checking
	}
	
	private void updateMovieShowtime() {
		Scanner sc = new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		CinemaManager cm = new CinemaManager();
		ShowtimeManager sm = new ShowtimeManager();
		
		// choose a movie i wanna update.
		System.out.println("Choose a movie:");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movieID = sc.nextInt();
//		Movie movie = mm.getMovieByID(movieID);
		
		
		// choose the cineplex.
		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose a number option:");
		String cineplexID = sc.next();
		System.out.println("Which cinema?");
		cxm.listAllCinemasByCineplex(cineplexID);
		System.out.println("Choose a number option:");
		String cinemaID = sc.next();
		Cinema cinema = cm.getCinemaByID(cinemaID);
		
		System.out.println("Which showtime would you like to update?");
		Printer.printShowtimeDetails(cinema);
		System.out.println("Choose a number option:"); // int showtimeRow =
		int showtimeId = sc.nextInt();
		Showtime showtime = sm.getShowtimeByID(showtimeId);
		
		Printer.printShowtimeDetails(showtime); //
		System.out.println("Choose a number option:");
		int choice = sc.nextInt();
		switch(choice) {
		case(1):{
			mm.listAll();
			System.out.println("Enter the new movie id:");
			break;
		}
		case(2):{
			System.out.println("Enter the new date in yyyy-MM-dd format.");
			break;
		}
		case(3):{
			System.out.println("Enter the new time in HH:mm:ss format.");
			break;
		}
		}
		String updateInput = sc.next();
		sm.update(showtime, choice, updateInput);
	}
	
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
		System.out.println("Choose a number option:");
		String cinemaID = sc.next();
		Cinema cinema = cm.getCinemaByID(cinemaID);
		System.out.println("Which showtime would you like to remove?");
		Printer.printShowtimeDetails(cinema);
		System.out.println("Choose a number option:");
		int showtimeId = sc.nextInt();
		Showtime showtime = sm.getShowtimeByID(showtimeId);
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