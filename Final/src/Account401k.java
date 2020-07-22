
public class Account401k extends Account {
	/**
	* class for 401k account procedures that inherits from the Account class
	*/
	
	Account401k(String type, String hireDate, double balance) {
		/**
		* call the variables/parameters from the superclass - Account
		* @param type
		* @param hireDate
		* @param balance
		*/
		
		super(type, hireDate, balance);
	}
	
	protected void deposit(double amt) { setBalance(amt); }
	
	@Override
	protected void display() {
		/**
		* overrides parent display() method to print account type, balance and vested balance
		*/
		
		System.out.println(" - Account Type: " + getType() + "\n      Balance: " + getBalance() + "\n      Vested Balance: " + getVested());
	}
	
	@Override
	protected String saving() {
		/**
		* overrides parent saving method
		* @return - type, hire date and balance
		*/
		
		return getType() + " " + getHireDate() + " " + getBalance();
	}
	
}
