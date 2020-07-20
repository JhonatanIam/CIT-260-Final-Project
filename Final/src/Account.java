import java.time.Year;

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
	
	protected double getVested() {
		int dif = Runner.largerDate(getHireDate(), java.time.LocalDate.now().getMonthValue() + "/" + java.time.LocalDate.now().getDayOfMonth() + "/" + Year.now().getValue());
		if(dif <= 11) {
			return getBalance();
		}
		else if(dif >= 11 && dif < 24) {
			return getBalance() * 1.20;
		}
		else if(dif >= 24 && dif < 36) {
			return getBalance() * 1.40;
		}
		else if(dif >= 36 && dif < 48) {
			return getBalance() * 1.60;
		}
		else if(dif >= 48 && dif < 60) {
			return getBalance() * 1.80;
		}
		return getBalance() * 2;
	}
	
}
