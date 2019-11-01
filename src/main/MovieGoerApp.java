package main;
import java.util.Scanner;

// none of the logic shld actually b here
public class MovieGoerApp {
	
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
		// Which movie? (Show the total list of movies)
		// Which cineplex? (Show list of cineplexes showing that movie that day)
		// Which showtime? (Show list of showtimes)
		// Choose a seat (Show the seat arrangements)
		// Show ticket price according to our pricing rules, allow user to confirm or not
		// If user confirms, show the booking receipt, save it to receipt CSV file.
	}
	
	// (2) List movies
	private void listMovies() {
		// Ask the user if they want to 1. list ALL movies, or 2. just movies playing in a certain cineplex
		// If option 2, ask them to pick the cineplex then show movies playing only in that cineplex.
		
		// Read from an external CSV file containing movie information, filter as necessary
	}
	
	// (3) View movie details (including reviews and ratings)
	private void viewMovieDetails() {
		// Ask user which movie they want to query.
		
		// Read from an external CSV file containing movie information, but find only the specific movie
		// Read from an external CSV file containing review information, but find it for that movie only
		// Read from an external CSV file containing ratings information, but find it for that movie only
	}
	
	// (4) Check seat availabilities
	private void checkSeatAvailabilities() {
		// ask user to pick cineplex
		// then pick movie
		// then show the number of available seats per showtime for that movie remaining in the day.
		// maybe can do that 'selling fast' 'available' 'fully booked' stuff.
	}
	
	// (5) View booking history
	private void viewBookingHistory() {
		// Read from an external CSV file for all the past booking histories for the USER who's logged in.
	}
	
	// (6) List top five movies
	private void listTop5Movies() {
		// Ask user if they want to filter by 1. ticket sales, or 2. reviewer ratings.
		
		// If 1, look at the booking receipts database and do the math.
		
		// If 2, look at the ratings database and do the math.
	}
	
	// (7) Leave a review or rating on a movie
	private void rateMovie() {
		// Ask user which movie they want to leave a rating on.
		
		// Ask them to give their rating (1-5).
		// Ask them to give a review (not necessary).
		// Must rmb the rating/review is given by the user who's logged in.
	}
}
