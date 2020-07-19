
public abstract class Account {
	
	private String hireDate, type;
	private double balance;
	
	public Account(String type, String hireDate, double balance) {
		this.type = type;
		this.hireDate = hireDate;
		this.balance = balance;
	}
	
	
	protected String getType() { return type; }
	protected String getHireDate() { return hireDate; }
	protected double getBalance() { return balance; }
	protected double setBalance(double amt) { return balance += amt; }
	protected abstract void display();
	protected abstract String saving();
	protected abstract void showAllInfo();
	
	
	
	
}
