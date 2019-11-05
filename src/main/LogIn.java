package main;
import java.util.Scanner;

public class LogIn {
	public void userLogin() {
		Scanner sc = new Scanner(System.in);
		String username, password;
		System.out.println("Enter Username:");
		username = sc.nextLine();
		//if username is not in the database
		if (username.compareTo(" ") != 0)
		{
			System.out.println("Username not found!");
		}
		System.out.println("Enter Password:");
		password = sc.nextLine();
		//Admin login
		//need compare to database
		if(username.compareTo(" ") == 0 && password.compareTo(" ") == 0) {
			CinemaStaffApp csApp = new CinemaStaffApp();
			csApp.run();
		}
		//user login
		//need compare to database
		else if(username.compareTo(" ") == 0 && password.compareTo(" ") == 0) {
			MovieGoerApp mgApp = new MovieGoerApp();
			mgApp.run();
		}
		
		else {
			System.out.println("Please enter a valid username");
		}
	}
}
