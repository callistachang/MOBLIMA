package main;
import java.util.Scanner;
<<<<<<< HEAD
import models.Account;
=======
import java.util.ArrayList;

import managers.*;
//import managers.CineplexManager;
//import managers.ShowtimeManager;
//import managers.CinemaManager;
//import managers.PriceManager;
//import managers.TicketManager;
//import managers
>>>>>>> 407b950c1bdc2862675a093de899b400a77ba509

// none of the logic shld actually b here
public class MovieGoerApp extends GuestApp {
	private Account account;
	
	public MovieGoerApp(Account account) {
		this.account = account;
	}
	
	public void run() {
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		// Should add a function to let moviegoer create account or login to their account.
		// Name, age, mobile number, email address.
		// Hence we create a new MovieGoer object here.
		
		System.out.println("=================== Movie-Goer Menu ===================");
		System.out.println("(1) Book and purchase a movie ticket");
		System.out.println("(2) List movies");
		System.out.println("(3) View movie details (including reviews and ratings)");
		System.out.println("(4) Check seat availabilities");
		System.out.println("(5) View booking history");
		System.out.println("(6) List top five movies");
		System.out.println("(7) Leave a rating and review on a movie");
		System.out.println("(8) Exit");
		// List pricing details?
		System.out.println("=======================================================");
		
		do {
			System.out.print("\nChoose an option: ");
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					bookTicket();
					break;
				case 2:
					listMovies();
					break;
				case 3:
					viewMovieDetails();
					break;
				case 4:
					checkSeatAvailabilities();
					break;
				case 5:
					viewBookingHistory();
					break;
				case 6:
					listTop5Movies();
					break;
				case 7:
					rateMovie();
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
		CineplexManager cm = new CineplexManager();
		ShowtimeManager sm = new ShowtimeManager();
		CinemaManager cinema = new CinemaManager();
		PriceManager pm = new PriceManager();
		TicketManager tm  = new TicketManager();
		
		System.out.println("Which movie would you like to watch?");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movie_chosen = sc.nextInt();
		
		System.out.println("Which cineplex would you like to watch the movie from?");
		cm.listAll();
		System.out.println("Choose a number option:");
		int cineplex_chosen = sc.nextInt();
		
		System.out.println("Which showtime would you like to watch?");
		sm.listAll();
		System.out.println("Choose a number option:");
		int showtime_chosen = sc.nextInt();
		
		int noOfTickets;
		do {
		System.out.println("How many tickets would you like to purchase?");
		noOfTickets = sc.nextInt();
		} while (noOfTickets > cinema.seatsAvailable());
		
		cinema.showSeating();
		System.out.println("Please select your seat numbers");
		ArrayList <String> seatsChosen =new ArrayList <String>();
		for (int i=0; i<noOfTickets;i++)
		{
			seatsChosen.add(sc.nextLine());
		}
		
		cinema.acceptSeat(seatsChosen, noOfTickets); //  to take in the seatNumbers and change the seating arrangement accordingly 
		
		
		System.out.println("The total price is: ");
		pm.calculatePrice(movie_chosen,cineplex_chosen,showtime_chosen,noOfTickets);
		System.out.println("/n");
		
		System.out.println("Can your booking be confirmed?(Y/N");
		char booking_confirm = sc.next().charAt(0);
		do {
		if (booking_confirm =='Y') {
			tm.addReceipt();
			tm.printReceipt();
		}
		else if (booking_confirm == 'N'){ //
			return;
		}
		}while (booking_confirm != 'Y' || booking_confirm != 'N');
		
		
	}
	
	// (5) View booking history
	private void viewBookingHistory() {
		CustomerManager cust_manager = new CustomerManager();

		System.out.println("Your booking history is as follows:\n");
		cust_manager.printBookingHistory();
		
	}
	
	
	// (7) Leave a review or rating on a movie
	private void rateMovie() {
		Scanner sc= new Scanner(System.in);
		
		MovieManager mm = new MovieManager();
		
		System.out.println("Which movie would you like to watch?");
		mm.listAll();
		System.out.println("Choose a number option:");
		int movie_chosen = sc.nextInt();
		
		int rating;
		do {
			System.out.println("Please give your rating (between 1-5): ");
			rating = sc.nextInt();
		}while (rating <0 || rating >5);
		
		mm.createMovieRating(movie_chosen, rating);
		
		System.out.println("Would you like to give a review?(Y/N)");
		char review_option = sc.next().charAt(0);
		do {
			if (review_option=='Y') {
				System.out.println("Please enter your review");
				String review_input = sc.next();
				mm.createMovieReview(movie_chosen, review_input);
			}
			else if (review_option=='N') {
				break;
			}
			
		} while (review_option !='Y'|| review_option != 'N');
		
		
	}
}
