
public class Account401k extends Account {
	
	Account401k(String type, String hireDate, double balance) {
		super(type, hireDate, balance);
	}
	
	protected void deposit(double amt) { setBalance(amt); }
	
	@Override
	protected void display() {
		System.out.println(" - Account Type: " + getType() + "\n      Balance: " + getBalance() + "\n      Vested Balance: " + getVested());
	}
	
	@Override
	protected String saving() {
		return getType() + " " + getHireDate() + " " + getBalance();
	}
	
}
