package managers;
import java.util.ArrayList;
import java.util.Scanner;
public class ValidityChecker {

	public static boolean DateValid(String date) {
		String year, month, day;
		int i = 0;
		ArrayList<Integer> shortMonths = new ArrayList<Integer>();
		shortMonths.add(2);
		shortMonths.add(4);
		shortMonths.add(6);
		shortMonths.add(9);
		shortMonths.add(11);
		
		//check length
		if(date.length() != 10) {
			System.out.println(i);
			return false;
		}
		i++;
		//check dashes
		if(date.charAt(4) != '-' || date.charAt(7) != '-') {
			System.out.println(i);
			return false;
		}
		i++;
		//check year
		year = date.substring(0, 4);
		if(!isNumeric(year)) {
			System.out.println(i);
			return false;
		}
		else if(Formatter.getIntFromString(year) <= 0 || Formatter.getIntFromString(year) > 9999) {
			System.out.println(i);
			return false;
		}
		i++;
		//check month
		month = date.substring(5, 7);
		if(!isNumeric(month)) {
			System.out.println(i);
			return false;
		}
		else if(Formatter.getIntFromString(month) < 1 || Formatter.getIntFromString(month) > 12) {
			System.out.println(i);
			return false;
		}
		i++;
		//check day
		day = date.substring(8, 10);
		if(!isNumeric(day)) {
			System.out.println(i);
			return false;
		}
		else if(Formatter.getIntFromString(day) < 1 || Formatter.getIntFromString(day) > 31) {
			System.out.println(i);
			return false;
		}
		//short months
		else if(shortMonths.contains(Formatter.getIntFromString(month)) && Formatter.getIntFromString(day) >30){
			return false;
		}
		//February
		else if(Formatter.getIntFromString(month) == 2) {
			if (Formatter.getIntFromString(day) > 29) {
				System.out.println(i);
				return false;
			}
			//not leap year
			if(Formatter.getIntFromString(year)%4 != 0 && Formatter.getIntFromString(day) > 28) {
				System.out.println(i);
				return false;
			}
		}
		//passed all checks
		return true;
		
	}
	
	public static boolean cineplexIDValid(String cineplexID) {
		CineplexManager cxm = new CineplexManager();
		if(cxm.getCineplexByID(cineplexID) == null) {
			return false;
		}
		else
			return true;
	}
	
	public static boolean cinemaIDValid(String cinemaID) {
		CinemaManager cm = new CinemaManager();
		if(cm.getCinemaByID(cinemaID) == null) {
			return false;
		}
		else
			return true;
	}
	
	public static boolean isNumeric(String strNum) {
	    try {
	        int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) {
		String date;
		Scanner sc = new Scanner(System.in);
		do {
		System.out.println("Enter date in yyyy-mm-dd format: ");
		date = sc.nextLine();
		System.out.println(DateValid(date));
		}
		while(!date.contains("#"));
	}
}
