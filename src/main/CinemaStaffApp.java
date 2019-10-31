package main;
import java.util.Scanner;

import org.json.simple.JSONObject;

import handlers.DatabaseHandler;

public class CinemaStaffApp {
	
	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		// Add a function to run the login as admin thingy
		
		System.out.println("================== Cinema Staff Menu ==================");
		System.out.println("(1) Create movie listing");
		System.out.println("(2) Update movie listing");
		System.out.println("(3) Remove movie listing");
		System.out.println("(4) Create movie showtime");
		System.out.println("(5) Update movie showtime");
		System.out.println("(6) Remove movie showtime");
		System.out.println("(7) Configure system settings");
		System.out.println("(8) ???"); // Some 2013 group did "Print sale revenue report by movie, cinema and period", lmao
		System.out.println("(9) Exit");
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
				break;
			case 9:
				System.out.println("Exited from the cinema staff menu.");
				break;
			default:
				System.out.println("Please input a valid option.");
				break;
			}
		} while (choice != 9);
	}
	
	private void createMovieListing() {
		JSONObject t = DatabaseHandler.getObject("movieData");

		// ask them to enter movie details - title, etc, etc.
		// will write to movie list csv file
	}
	
	private void updateMovieListing() {
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
