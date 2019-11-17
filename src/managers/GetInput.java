package managers;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class GetInput {

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
	
	
	public static void main(String[] args) {
		String time;
		do {
		time = getCinemaIDInput("Please enter enter cinema");
		System.out.println(time + " is valid!");
		}
		while(true);
	}
}
