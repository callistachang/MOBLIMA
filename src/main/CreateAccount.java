package main;
import java.util.Scanner;


public class CreateAccount {
	public void newAccount() {
		Scanner sc = new Scanner(System.in);
		String username, password;
		int age;
		
		//get username
		while(true) {
			System.out.println("Enter Username:");
			username = sc.nextLine();
			//need to compare to database so ensure that username not already taken
			if(username.compareTo("") != 0){
				System.out.println("Username is already taken!");
				System.out.println("Press (Y) to retry, any other key to exit");
				if(sc.next().compareToIgnoreCase("y") == 0){
					continue;
				}
				else {
					return;
				}
				
			}
			else {
				break;
			}
		}
			//get password
		while(true) {
			System.out.println("Enter Password:");
			password = sc.nextLine();
			System.out.println("Confirm Password:");
			if(password.compareTo(sc.nextLine()) != 0){
				System.out.println("The passwords do not match!");
				System.out.println("Press (Y) to retry, any other key to exit");
				if(sc.next().compareToIgnoreCase("y") == 0){
					continue;
				}
				else {
					return;
				}
				
			}
			else {
				break;
			}
		
		}
		//get age
		System.out.println("Enter Age:");
		age = sc.nextInt();
		//enter the particulars into the database as new user
		
	}
}