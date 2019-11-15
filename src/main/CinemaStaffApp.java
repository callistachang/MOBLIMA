package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import managers.CinemaManager;
import managers.CineplexManager;
import managers.MovieManager;
import managers.ShowtimeManager;
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
				
		System.out.println("================== Cinema Staff Menu ==================");
		int i;
		for (i = 1; i <= menuOptions.length; i++) {
			System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
		}
		System.out.println("=======================================================");
		String[] guestOptions = super.getMenuOptions();
		for (int j = 1; j <= guestOptions.length; i++, j++) {
			System.out.printf("(%d) %s \n", i, guestOptions[j-1]);
		}
		System.out.printf("(%d) %s \n", i, "Exit");
		System.out.println("=======================================================");
		
		do {
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
		mm.listAll();	// list of all movies with their IDs
		System.out.println("Which movie would you like to update? Type the movie ID:");
		int movieID = sc.nextInt();
		
		// show movie info after choosing the movie
		mm.printMovieInfo(movieID);
		
		System.out.println("What attribute would you like to change about the movie? Choose number option:");
		mm.listAttributes();
		int attrNum = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Insert new value for that attribute:");
		String attrVal = sc.nextLine();
		
//		System.out.println(movieID + " " + attrNums + " " + attrChanges);
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
		} while (!(choice == 'Y' || choice == 'N'));	
		
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

		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose a number option:");
		int cineplexID = sc.nextInt();
		
		System.out.println("Which cinema?");
		cxm.listAllByCineplex(cineplexID);
		System.out.println("Choose a number option:");
		int cinemaID = sc.nextInt();
		
		System.out.println("Enter the date. Which year?");
		int showtimeYear = sc.nextInt();
		System.out.println("Which month?");
		int showtimeMonth = sc.nextInt();
		System.out.println("Which day?");
		int showtimeDay = sc.nextInt();

		System.out.println("Enter the date in DDMMYYYY format.");
		int showtimeDate = sc.nextInt();
		System.out.println("Enter the time in 24-hour format.");
		int showtimeTime = sc.nextInt();
		
		// ask for date
		// ask for time
		
		Showtime showtime = sm.create(cinemaID, date, time, movieID);
		cm.addShowtime(cinemaID, showtime);

		
//		sm.create(movieID, cineplexID, cinemaID, showtimeDate, showtimeTime, mm.getduration(movieRow));
// error checking handled by sm? return different error messages
// logic linking no. option to movie should be same for cineplex, cinema, showtime		
//		
//		 ask for movie (show list of movies)
//		 ask for cineplex (show list of cineplexes)
//		 create showtime. will return error if all halls already occupied.
//		 must make sure it don't overlap with other movies. MUST STORE MOVIE DURATION.
//		 update to movie showtime database
//		
//		update database twice.
	}
	
	private void updateMovieShowtime() {
//		MovieManager mm = new MovieManager();
//		CineplexManager cxm = new CineplexManager();
//		CinemaManager cam = new CinemaManager();
//		ShowtimeManager sm = new ShowtimeManager();
		
//		System.out.println("Which movie would you like to view?");
//		int noOfMovies = mm.listAll();
//		System.out.println("Choose a number option:");
//		int movieRow = sc.nextInt();
//		int movieID = mm.returnMovieID(movieRow);
		
		Scanner sc = new Scanner(System.in);


		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose a number option:");
		int cineplexID = sc.nextInt();		
		
		System.out.println("Which cinema?");
		cxm.listAllByCineplex(cineplexID);
		System.out.println("Choose a number option:");
		int cinemaID = sc.nextInt();
		
		System.out.println("List of showtimes in cinema:");
		cm.getShowtimes(cinemaID);
		int showtimeID = sc.nextInt();
		
		int attrNum = sm.chooseAttributes(showtimeID); //??
		sm.updateShowtime(showtimeID, attrNum, attrVal);
		
		
//		System.out.println("Which showtime would you like to update?");
//		int noOfShowtimes = cam.listAll();
//		System.out.println("Choose a number option:");
//		int showtimeRow = sc.nextInt();
//		int showtimeID = sm.returnShowtimeID(showtimeRow);

		
//		System.out.println("Which attribute would you like to change about the movie?");
//		sm.showAttributes(showtimeID);
//		System.out.println("Choose a number option:");
//		int attr = sc.nextInt();

		//sm.update(option);
		
		
		// ask for movie (show list of movies)
		// ask for cineplex (show list of cineplexes)
		// program will list out all the showtimes for that movie in that cineplex. 
		// choose to update the timings.
		// update to movie showtime database
	}
	
	private void removeMovieShowtime() {
		Scanner sc = new Scanner(System.in);
		
		CineplexManager cxm = new CineplexManager();
		CinemaManager cm = new CinemaManager();
		ShowtimeManager sm = new ShowtimeManager();

		System.out.println("Which cineplex?");
		cxm.listAll();
		System.out.println("Choose a number option:");
		int cineplexID = sc.nextInt();		
		
		System.out.println("Which cinema?");
		cxm.listAllByCineplex(cineplexID);
		System.out.println("Choose a number option:");
		int cinemaID = sc.nextInt();
		
		System.out.println("List of showtimes in cinema:");
		cm.getShowtimes(cinemaID);
		int showtimeID = sc.nextInt();
		
		sm.removeShowtime(showtimeID);
		
		
		
		
		
//		Scanner sc = new Scanner(System.in);
//		
//		MovieManager mm = new MovieManager();
//		CineplexManager cxm = new CineplexManager();
//		CinemaManager cm = new CinemaManager();
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

		
//		System.out.println("Which showtime would you like to remove?");
//		int noOfShowtimes = cm.listAll();
//		System.out.println("Choose a number option:");
//		int showtimeRow = sc.nextInt();
//		int showtimeID = sm.returnShowtimeID(showtimeRow);

	
//		do {
//		System.out.println("Confirm? Y/N");
//		choice = sc.next().charAt(0);
//		switch (choice) {
//		case 'Y':
//			mm.remove(showtimeID);
//			System.out.println("Showtime has been removed.");
//			break;
//		case 'N':
//			System.out.println("Transaction cancelled. No showtimes removed.");
//			break;
//		default:
//			System.out.println("Please input a valid option.");
//			break;
//			}
//		} while (true);	
		

		// ask for movie (show list of movies)
		// ask for cineplex (show list of cineplexes)
		// program will list out all the showtimes for that movie in that cineplex. 
		// choose to delete.
		// remove from movie showtime database
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
