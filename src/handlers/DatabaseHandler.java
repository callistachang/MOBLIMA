package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHandler {
	
//	// testing lol
//	public static void main(String[] args) {
//		ArrayList<String> dataToAdd = new ArrayList<String>();
////		dataToAdd.add("hi,yes,yeet");
////		dataToAdd.add("hi,more yeet");
//		addDataToDatabase("moviedata", dataToAdd);
//		ArrayList<String> d = readDatabase("moviedata");
//		System.out.println(Arrays.toString(d.get(1).split(",")));
//		
//	}
	
//	public static boolean addDataToDatabase(String databaseName, ArrayList<String> dataToAdd) {
//		// reads database into an array
//		ArrayList<String> dataArray = new ArrayList<String>();
//		dataArray = readDatabase(databaseName);
//		
//		// appends the data we wish to add into that array
//		dataArray.addAll(dataToAdd);
//		
//		// write the array back into the database
//		return writeToDatabase(databaseName, dataArray);
//	}
	
	public static ArrayList<String> readDatabase(String databaseName) {
		try {
			File f = new File("database/" + databaseName + ".csv");
			if (!f.canRead()) {
				throw new FileNotFoundException();
			}
			
			// scan CSV file into an array
            Scanner sc = new Scanner(f);
            ArrayList<String> dataArray = new ArrayList<String>();
            while (sc.hasNextLine()) {
            	String row = sc.nextLine();
            	if (row.charAt(0) != '#') { // I use this symbol to signify column names
            		dataArray.add(row);
            	}
            }
            sc.close();
            
            // nothing read from the CSV file
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
	
	public static boolean writeToDatabase(String databaseName, ArrayList<String> dataArray) {
		
		PrintWriter out = null;
		
		try {
			// create a new file if it doesn't exist yet
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
