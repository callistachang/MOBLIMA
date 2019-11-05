package main;
import java.util.ArrayList;
import java.util.Scanner;

import managers.MovieManager;

public class CinemaStaffApp extends GuestApp {
	
	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		// Add a function to run the login as admin thingy
		
		System.out.println("================== Cinema Staff Menu ==================");
		System.out.println("(1) List movies");
		System.out.println("(2) View movie details (including reviews and ratings)");
		System.out.println("(3) Check seat availabilities");
		System.out.println("(4) List top five movies");
		System.out.println("(5) Create movie listing");
		System.out.println("(6) Update movie listing");
		System.out.println("(7) Remove movie listing");
		System.out.println("(8) Create movie showtime");
		System.out.println("(9) Update movie showtime");
		System.out.println("(10) Remove movie showtime");
		System.out.println("(11) Configure system settings");
		System.out.println("(12) ???"); // Some 2013 group did "Print sale revenue report by movie, cinema and period", lmao
		System.out.println("(13) Exit");
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
				createMovieListing();
				break;
			case 6:
				updateMovieListing();
				break;
			case 7:
				removeMovieListing();
				break;
			case 8:
				createMovieShowtime();
				break;
			case 9:
				updateMovieShowtime();
				break;
			case 10:
				removeMovieShowtime();
				break;
			case 11:
				configureSettings();
				break;
			case 12:
				break;
			case 13:
				System.out.println("Exited from the cinema staff menu.");
				break;
			default:
				System.out.println("Please input a valid option.");
				break;
			}
		} while (choice != 13);
	}
	
	private void createMovieListing() {
		Scanner sc = new Scanner(System.in);
		String title, showingStatus, synopsis, director, cast;
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
			showingStatus = sc.nextLine();
		} while (!(showingStatus.equals("Coming Soon") || showingStatus.equals("Now Showing") || showingStatus.equals("No Longer Showing")));
		
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

		mm.create(title, showingStatus, synopsis, director, casts, duration);
	}
	
	private void updateMovieListing() {
//		Scanner sc = new Scanner(System.in);
//		
//		MovieManager mm = new MovieManager();
//		
//		System.out.println("Which movie would you like to update?");
//		int noOfMovies = mm.listAll();
//		System.out.println("Choose a number option:");
//		int movieRow = sc.nextInt();
//		
//		System.out.println("Which attribute would you like to change about the movie?");
//		mm.showAttributes();
//		System.out.println("Choose a number option:");
//		int attr = sc.nextInt();
		
		
		
		
		
		// mm.updateMovie(option);
		
//		String title = sc.nextLine();
		
		
		// ask staff which movie they want to update (show list of movies)
		// ask staff which detail they want to update.
		// they can update as many as they wish. when they're done.
		// will update to movie list csv file.
	}
	
	private void removeMovieListing() {
		// ask staff which movie they want to delete (show list of movies)
		// confirm?
		// will delete from movie list csv file.
	}
	
	private void createMovieShowtime() {
		// ask for movie (show list of movies)
		// ask for cineplex (show list of cineplexes)
		// create showtime. will return error if all halls already occupied.
		// must make sure it don't overlap with other movies. MUST STORE MOVIE DURATION.
		// update to movie showtime database
	}
	
	private void updateMovieShowtime() {
		// ask for movie (show list of movies)
		// ask for cineplex (show list of cineplexes)
		// program will list out all the showtimes for that movie in that cineplex. 
		// choose to update the timings.
		// update to movie showtime database
	}
	
	private void removeMovieShowtime() {
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
