package main;
import java.util.Scanner;
import models.Account;
import models.Booking;
import models.Cinema;
import models.Showtime;

import java.util.ArrayList;

import managers.*;

public class MovieGoerApp extends UserApp {
	private Account account;
	private final String menuOptions[] = {
		"Book and purchase a movie ticket",
		"Leave a rating and review on a movie",
		"View booking history"
	};
	
	public MovieGoerApp(Account account) {
		this.account = account;
	}
	
	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		// Should add a function to let moviegoer create account or login to their account.
		// Name, age, mobile number, email address.
		// Hence we create a new MovieGoer object here.
		
		System.out.println("Welcome to MOBLIMA, " + account.getUsername() + "!");
		
		System.out.println("=================== Movie-Goer Menu ===================");
		int i;
		for (i = 1; i <= menuOptions.length; i++) {
			System.out.printf("(%d) %s \n", i, menuOptions[i-1]);
		}
		System.out.println("======================================================");
		String[] guestOptions = super.getMenuOptions();
		for (int j = 1; j <= guestOptions.length; i++, j++) {
			System.out.printf("(%d) %s \n", i, guestOptions[j-1]);
		}
		System.out.printf("(%d) %s \n", i, "Exit");
		System.out.println("======================================================");
		
		do {
			System.out.print("\nChoose an option: ");
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					bookTicket();
					break;
				case 2:
					rateMovie();
				case 3:
					viewBookingHistory();
					break;
				case 4:
					listMovies();
					break;
				case 5:
					viewMovieDetails();
					break;
				case 6:
					checkSeatAvailabilities();
					break;
				case 7:
					listTop5Movies();
					break;
				case 8:
					System.out.println("Exited from the movie-goer menu.");
					sc.nextLine();
					break;
				default:
					System.out.println("Please input a valid option.");
					break;
				}
		} while (choice != 8);
		sc.close();
	}
	
	// (1) Book and purchase a movie ticket
	// should create like some TicketManager etc
	private void bookTicket() {
		Scanner sc= new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		CineplexManager cxm = new CineplexManager();
		ShowtimeManager sm = new ShowtimeManager();
		CinemaManager cm = new CinemaManager();
		BookingManager bm  = new BookingManager();
		PricingCalculator pm = new PricingCalculator();
		
		System.out.println("Which movie would you like to watch?");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movieID = sc.nextInt();
		
		System.out.println("Which cineplex would you like to watch the movie from?");
		cxm.listCineplexByMovie(movieID);
		System.out.println("Choose a number option:");
		int cineplexID = sc.nextInt();
		
		// which movie type?
		
		System.out.println("Which showtime would you like to watch?");
		cxm.listAllSeatAvailabilitiesInCineplexByMovie(cineplexID, movieID);
		System.out.println("Choose a number option:");
		int showtimeID = sc.nextInt();
		
		int noOfTickets;
		
		Showtime showtime = sm.getShowtimeByID(showtimeID);
		Cinema c = showtime.getCinema();
		do {
		System.out.println("How many tickets would you like to purchase?");
		noOfTickets = sc.nextInt();
		} while (noOfTickets > c.getAvailableSeats());
		
		
		cm.showSeating(showtimeID);
		double price = 0;
		for(int i=0;i<noOfTickets;i++)
		{
		System.out.println("Please select your seat number");
		String seatChosen =sc.next();
		sm.addSeat(showtimeID,seatChosen);
		System.out.println("Any discounts applicable?");
		System.out.println("0: None");
		System.out.println("1: Student");
		System.out.println("2: Senior Citizen");
		int discountType = sc.nextInt();
		price+=pm.calculatePrice(discountType,showtimeID);
		}
				
		
		System.out.println("The total price is: " + price);
		
		bm.addReceipt(showtimeID, price);
	}
	
	// (5) View booking history
	private void viewBookingHistory() {
//		AccountManager am = new AccountManager();

		ArrayList<Booking> BookingHistory = account.getBookings();
		System.out.println("Your booking history is as follows:"+ BookingHistory);		
	}
	
	
	// (7) Leave a review or rating on a movie
	private void rateMovie() {
		Scanner sc= new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		
		System.out.println("Which movie would you like to watch?");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movieId = sc.nextInt();
		
		int rating;
		do {
			System.out.println("Please give your rating (between 1-5): ");
			rating = sc.nextInt();
		}while (rating <0 || rating >5);

		System.out.println("Would you like to give a review?(Y/N)");
		String review_option = sc.next();
		do {
			if (review_option.compareToIgnoreCase("y") == 0) {
				System.out.println("Please enter your review");
				String review_input = sc.next();
				mm.createReview(movieId, rating, account, review_input);
				break;
			}
			else if (review_option.compareToIgnoreCase("n") == 0) {
				mm.createReview(movieId, rating, account, null);
				break;
			}
			
		} while (true);
		
		
	}
}
