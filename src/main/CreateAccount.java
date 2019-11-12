package main;
import java.util.Scanner;
import managers.AccountManager;

public class CreateAccount {
	public void newAccount() {
		Scanner sc = new Scanner(System.in);
		String username, password , confirmPassword;
		int control, age;
		username = "";
		password = "";
		confirmPassword = "";
		age = 0;

		AccountManager am = new AccountManager();
		control = 1;
		while (control !=0){
			switch(control) {
			case 1:
				System.out.println("Enter Username:");
				username = sc.nextLine();
			case 2:
			case 3:
				System.out.println("Enter Password:");
				password = sc.nextLine();
				System.out.println("Confirm Password:");
				confirmPassword = sc.nextLine();
			case 4:
				System.out.println("Enter Age:");
				age = sc.nextInt();
				break;
			default:
				break;
			}	
			control = am.create(username, password, confirmPassword, age);
			System.out.println("Press (Y) to retry, any other key to exit");
			if(sc.next().compareToIgnoreCase("y") != 0){
				break;
			}
		}
	}
}
