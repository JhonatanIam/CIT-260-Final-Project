
public class Account401k extends Account {
	
	Account401k(String type, String hireDate, double balance) {
		super(type, hireDate, balance);
	}
	
	protected void deposit(double amt) { setBalance(amt); }
	
	@Override
	protected void display() {
		System.out.println("Account Type: " + getType() + "   |   Balance: " + getBalance() + "   |   Vested Balance: ");
	}
	
	@Override
	protected void showAllInfo() {
		System.out.println("Account Type: " + getType() + "\nHire Date: " + getHireDate() + "\nBalance: " + getBalance());
	}
	
	@Override
	protected String saving() {
		return getType() + " " + getHireDate() + " " + getBalance();
	}
	
}
