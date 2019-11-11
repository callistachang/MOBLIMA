package main;
import managers.AccountManager;

public class LogIn {
	public int userLogin(String username, String password) {

		AccountManager am = new AccountManager();
		
		if(!am.checkUsernameExist(username)) {
			System.out.println("Please enter a valid username!");
			return -1;
		}
		

		if(!am.checkPasswordMatch(username, password)) {
			System.out.println("Please enter a valid password!");
			return -1;
		}
		
		if(username.equalsIgnoreCase("Admin")) {
			
			CinemaStaffApp csApp = new CinemaStaffApp();
			csApp.run();
		}
		
		else {
			MovieGoerApp mgApp = new MovieGoerApp(am.getAccount(username));
			mgApp.run();
		}
		return 0;
	}
}
