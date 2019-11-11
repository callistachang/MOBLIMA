package managers;
import models.Account;;

public class AccountManager {
	
	Account[] accountList = new Account()[];
	public boolean checkUsernameExist(String username) {
		
		//check the username in the database
		//if exist return true else return false
		return true;
	}
	public boolean checkPasswordMatch(String username, String password) {
		
		//check the password in the database match the username
		//if exist return true else return false
		return true;
	}
	public Account getAccount(String username) {
		//get the account from the array
		Account acc = new Account();
		return acc;
	}
	
}
