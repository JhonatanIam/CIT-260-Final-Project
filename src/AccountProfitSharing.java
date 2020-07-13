
public class AccountProfitSharing extends Account  {

	
	AccountProfitSharing(String name, String hireDate, String type, double balance) {
		super(name, hireDate, type, balance);
	}
	
	@Override
	protected void display() {
		System.out.println("Name: " + getName() + "   |   Account Type: " + getType() + "   |   Balance: " + getBalance() + "   |   Vested Balance: ");
	}

	@Override
	protected void showAllInfo() {
		System.out.println("Name: " + getName());
		System.out.println("Hire Date: " + getHireDate());
		System.out.println("Account Type: " + getType());
		System.out.println("Balance: " + getBalance());
	}
	
}