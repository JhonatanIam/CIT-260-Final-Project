
public abstract class Account {
	
	protected String name, hireDate, type;
	protected double balance;
	
	public Account(String name, String hireDate, String type, double balance) {
		this.name = name;
		this.hireDate = hireDate;
		this.type = type;
		this.balance = balance;
	}


	protected String getName() { return name; }
	protected String getHireDate() { return hireDate; }
	protected String getType() { return type; }
	protected double getBalance() { return balance; }
	protected abstract void display();
	protected abstract void showAllInfo();
	
	
}
