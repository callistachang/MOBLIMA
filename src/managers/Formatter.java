package managers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Formats data types into necessary format for functions.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class Formatter {
	/**
	 * Converts LocalDate to String data type.
	 * @param date LocalDate variable to be converted to String.
	 * @return String converted from LocalDate.
	 */
	public static String getStringFromLocalDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	/**
	 * Converts LocalTime to String data type.
	 * @param time LocalTime variable to be converted to String.
	 * @return String converted from LocalTime.
	 */
	public static String getStringFromLocalTime(LocalTime time) {
		return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	/**
	 * Converts String to LocalDate data type.
	 * @param date String variable to be converted to LocalDate.
	 * @return LocalDate converted from String.
	 */
	public static LocalDate getLocalDateFromString(String date) {
		return LocalDate.parse(date);
	}
	/**
	 * Converts String to LocalTime data type.
	 * @param time String variable to be converted to LocalTime.
	 * @return LocalTime converted from String.
	 */
	public static LocalTime getLocalTimeFromString(String time) {
		return LocalTime.parse(time);
	}
	/**
	 * Converts int to String data type.
	 * @param integer Integer variable to be converted to String.
	 * @return String converted from Integer.
	 */
	public static String getStringFromInt(int integer) {
		return String.valueOf(integer);
	}
	/**
	 * Converts String to int data type.
	 * @param str String variable to be converted to int.
	 * @return Integer converted from String.
	 */
	public static int getIntFromString(String str) {
		if (str.equals("null"))
			return 0;
		return Integer.parseInt(str);
	}	
	/**
	 * Converts Double to String data type.
	 * @param db Double variable to be converted to String.
	 * @return String converted from Double.
	 */
	public static String getStringFromDouble(double db) {
		return String.valueOf(db);
	}
	/**
	 * Converts String to Double data type.
	 * @param str String variable to be converted to double.
	 * @return Double converted from String.
	 */
	public static double getDoubleFromString(String str) {
		return Double.parseDouble(str);
	}
	/**
	 * Converts array of integers to a string array.
	 * @param intArray Array of integers to be converted to string array.
	 * @return String array converted from integer array.
	 */
	public static ArrayList<String> getStringArrayFromIntegerArray(ArrayList<Integer> intArray) {
		if (intArray == null) {
			return null;
		}
		ArrayList<String> strArray = new ArrayList<String>();
		for (int i: intArray) {
			strArray.add(String.valueOf(i));
		}
		return strArray;
	}
}






