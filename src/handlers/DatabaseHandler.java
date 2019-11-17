package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * Handles all the functions for editing the database csv file from a String array.
 * @author penel
 * @version 1.0
 * @since 2019-11-17
 */
public class DatabaseHandler {
	

	/**
	 * Converts information in external csv file to an ArrayList of strings.
	 * @param databaseName The name of the csv file used.
	 * @return ArrayList of database information in string form.
	 */
	public static ArrayList<String> readDatabase(String databaseName) {
		try {
			File f = new File("database/" + databaseName + ".csv");
			if (!f.canRead()) {
				throw new FileNotFoundException();
			}
			
            Scanner sc = new Scanner(f);
            ArrayList<String> dataArray = new ArrayList<String>();
            while (sc.hasNextLine()) {
            	String row = sc.nextLine();
            	if (row.charAt(0) != '#') { 
            		dataArray.add(row);
            	}
            }
            sc.close();
            
            if (dataArray.size() == 0) {
            	System.out.println("The database " + databaseName + " currently has no data.");
            	return null;
            }
            
            return dataArray;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
            System.out.println("File could not be found. Error message: " + e.getMessage());
            return null;
        }
	}
	/**
	 * Records information about the database to the external csv file.
	 * Information read is in the form of an ArrayList of Strings.
	 * @param databaseName The name of the csv file used.
	 * @param dataArray The String array that stores the information to be added to the csv file.
	 * @return Boolean value to indicate whether the process is complete. 
	 */
	public static boolean writeToDatabase(String databaseName, ArrayList<String> dataArray) {
		
		PrintWriter out = null;
		
		try {
			File file = new File("database/" + databaseName + ".csv");
			file.createNewFile();
			
			out = new PrintWriter(new FileWriter("database/" + databaseName + ".csv"));
			
			for (int i = 0; i < dataArray.size(); i++) {
				out.println(dataArray.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (out != null)
				out.close();
		}
		
		return true;
	}
}
