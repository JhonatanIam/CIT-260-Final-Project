
public class AccountProfitSharing extends Account  {

	
	AccountProfitSharing(String type, String hireDate, double balance) {
		super(type, hireDate, balance);
	}
	
	@Override
	protected void display() { 
		System.out.println(" - Account Type: " + getType() + "\n      Balance: " + getBalance() + "\n      Vested Balance: " + getVested());
	}

	protected String saving() {
		return getType() + " " + getHireDate() + " " + getBalance();
	}
	
}