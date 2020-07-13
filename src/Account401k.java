
public class Account401k extends Account {
	
	protected String DOB;
	
	Account401k(String name, String hireDate, String type, String DOB, double balance) {
		super(name, hireDate, type, balance);
		this.DOB = DOB;
	}
	
	
	protected String getDOB() { return DOB; }
	protected void deposit(double amt) { balance += amt; }
	protected void withdraw(double amt) { balance -= amt; }
	
	@Override
	protected void display() {
		System.out.println("Name: " + getName() + "   |   Account Type: " + getType() + "   |   Balance: " + getBalance() + "   |   Vested Balance: ");
	}


	@Override
	protected void showAllInfo() {
		System.out.println("Name: " + getName());
		System.out.println("Hire Date: " + getHireDate());
		System.out.println("Date of Birth: " + getDOB());
		System.out.println("Account Type: " + getType());
		System.out.println("Balance: " + getBalance());

	}
	
}
