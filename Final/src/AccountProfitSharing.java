
public class AccountProfitSharing extends Account  {

	
	AccountProfitSharing(String type, String hireDate, double balance) {
		super(type, hireDate, balance);
	}
	
	@Override
	protected void display() { 
		System.out.println("Account Type: " + getType() + "   |   Balance: " + getBalance() + "   |   Vested Balance: ");
	}

	@Override
	protected void showAllInfo() {
		System.out.println("Account Type: " + getType() + "\nHire Date: " + getHireDate() + "\nBalance: " + getBalance());
	}

	protected String saving() {
		return getType() + " " + getHireDate() + " " + getBalance();
	}
	
}