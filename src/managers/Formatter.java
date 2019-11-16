package managers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Formatter {
	public static String getStringFromLocalDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String getStringFromLocalTime(LocalTime time) {
		return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public static LocalDate getLocalDateFromString(String date) {
		return LocalDate.parse(date);
	}
	
	public static LocalTime getLocalTimeFromString(String time) {
		return LocalTime.parse(time);
	}
	
	public static String getStringFromInt(int integer) {
		return String.valueOf(integer);
	}
	
	public static int getIntFromString(String str) {
		if (str.equals("null"))
			return 0;
		return Integer.parseInt(str);
	}	
	
	public static String getStringFromDouble(double db) {
		return String.valueOf(db);
	}
	
	public static double getDoubleFromString(String str) {
		return Double.parseDouble(str);
	}
	
	public static ArrayList<String> getStringArrayFromIntegerArray(ArrayList<Integer> intArray) {
		if (intArray == null) {
			System.out.println("lmao fuk u");
			return null;
		}
		ArrayList<String> strArray = new ArrayList<String>();
		for (int i: intArray) {
			strArray.add(String.valueOf(i));
		}
		return strArray;
	}
}






