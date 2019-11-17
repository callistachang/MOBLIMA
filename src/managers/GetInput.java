package managers;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
/**
 * Controller to ensure the inputs are valid.
 * @author balad
 * @version 1.0
 * @since 2019-11-17
 *
 */
public class GetInput {

	/**
	 * Ensures that the input is integer.
	 * @param prompt Print line prompting user to enter an input.
	 * @return An integer.
	 */
	public static int getIntInput(String prompt){
		String input;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println(prompt);
			input = sc.next();
			if (ValidityChecker.isNumeric(input)) {
		        return Formatter.getIntFromString(input);
		    } 
			else {
		        System.out.println("Enter an integer!");
		    }
		}
	}

	
	/**
	 * Ensures that the input is within range.
	 * @param prompt Print line prompting user to enter an input.
	 * @param min Minimum value possible for the input.
	 * @param max Maximum value possible for the input. 
	 * @return The input entered by the user.
	 */
	public static int getIntInputWithinRange(String prompt, int min, int max) {
		while(true) {
			int input = getIntInput(prompt);
			if (input >= min && input <= max) {
				return input;
			}
			else {
				System.out.println("Enter a valid option!");
			}
		}
		
	}
	/**
	 * Ensures that the input of cinemaID is valid. 
	 * @param prompt Print line prompting user to enter a cinema ID. 
	 * @return Valid cinema ID entered by the user. 
	 */
	public static String getCinemaIDInput(String prompt){
		Scanner sc = new Scanner(System.in);
		String cinemaID;
		while(true) {
			System.out.println(prompt);
			cinemaID = sc.next();
			if (ValidityChecker.cinemaIDValid(cinemaID)) {
		        return cinemaID;
		    } 
			else {
		        System.out.println("Enter a valid CinemaID!");
		    }
		}
		
	}
	/**
	 * Ensures that the input of cineplexID is valid. 
	 * @param prompt Print line prompting user to enter a cineplex ID. 
	 * @return Valid cineplex ID entered by the user. 
	 */
	public static String getCineplexIDInput(String prompt){
		Scanner sc = new Scanner(System.in);
		String cineplexID;
		while(true) {
			System.out.println(prompt);
			cineplexID = sc.next();
			if (ValidityChecker.cineplexIDValid(cineplexID)) {
		        return cineplexID;
		    } 
			else {
		        System.out.println("Enter is a valid CineplexID!");
		    }
		}
		
	}
	
	/**
	 * Ensures that the date entered by the user is valid.
	 * @param prompt Print line prompting user to enter date.
	 * @return Valid date entered by user.
	 */
	public static LocalDate getDateInput(String prompt){
		Scanner sc = new Scanner(System.in);
		String date;
		while(true) {
			System.out.println(prompt);
			date = sc.next();
			if (ValidityChecker.dateValid(date)) {
		        return Formatter.getLocalDateFromString(date);
		    } 
			else {
		        System.out.println("Enter a valid date!");
		    }
		}
	}
	
	/**
	 * Ensures that the time entered by the user is valid.
	 * @param prompt Print line prompting user to enter time.
	 * @return Valid time entered by user.
	 */
	public static LocalTime getTimeInput(String prompt){
		Scanner sc = new Scanner(System.in);
		String time;
		while(true) {
			System.out.println(prompt);
			time = sc.next();
			if (ValidityChecker.timeValid(time)) {
		        return Formatter.getLocalTimeFromString(time);
		    } 
			else {
		        System.out.println("Enter a valid time!");
		    }
		}
	}
	
	/**
	 * Ensures that user only enters one character for certain choices.
	 * @param prompt Print line prompting user to type in a character.
	 * @return Valid character input by user.
	 */
	public static char getCharInput(String prompt) {
		Scanner sc = new Scanner(System.in);
		String input;
		while(true) {
			System.out.println(prompt);
			input = sc.next();
			input.toUpperCase();
			if (ValidityChecker.isChar(input)) {
		        return input.charAt(0);
		    }
			else {
		        System.out.println("Enter is a valid Choice!");
		    }
		}
	}
	public static String getStringInput(String prompt) {
		Scanner sc = new Scanner(System.in);
		String input;
		while(true) {
			System.out.println(prompt);
			input = sc.nextLine();
			if (!input.isEmpty()) {
		        return input;
		    }
			else {
		        System.out.println("Enter item!");
		    }
		}
	}
	
	
//	public static void main(String[] args) {
//		String time;
//		do {
//		time = getCinemaIDInput("Please enter cinema");
//		System.out.println(time + " is valid!");
//		}
//		while(true);
//	}
}
