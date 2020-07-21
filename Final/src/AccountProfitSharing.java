
public class AccountProfitSharing extends Account  {
	
	
	AccountProfitSharing(String type, String hireDate, double balance) {
		/**
		* inherits parameters from superclass Account
		* @param type
		* @param hireDate
		* @param balance
		*/
		
		super(type, hireDate, balance);
	}
	
	@Override
	protected void display() { 
		/**
		* overrides primary display class to display profit sharing type, balance and vested balance
		*/
		
		System.out.println(" - Account Type: " + getType() + "\n      Balance: " + getBalance() + "\n      Vested Balance: " + getVested());
	}

	protected String saving() {
		/**
		* returns profit sharing type, hire date and balance
		*/
		
		return getType() + " " + getHireDate() + " " + getBalance();
	}
	
}
