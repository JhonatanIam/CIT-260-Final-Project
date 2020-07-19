
public class Person {
	
	private String name, DOB, username, password;
	private Account401k a401k;
	private AccountProfitSharing aPS;
	
	Person(String username, String password, String name, String DOB, Account401k a401k, AccountProfitSharing aPS) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.DOB = DOB;
		this.a401k = a401k;
		this.aPS = aPS;
	}
	
	protected String getName() { return name; }
	protected String getDOB() { return DOB; }
	protected String getUsername() { return username; }
	protected String getPassword() { return password; }
	protected Account401k get401k() { return a401k; }
	protected AccountProfitSharing getPS() { return aPS; }
	protected void set401k(Account401k a401k) { this.a401k = a401k; }
	protected void setPS(AccountProfitSharing aPS) { this.aPS = aPS; }
	
	protected void showAllInfo() {
		System.out.println("Name: " + getName() + "\nUser: " + getUsername() + "\nPass: " + getPassword() + "\nDOB: " + getDOB());
		if(this.get401k() != null) {
			get401k().showAllInfo();
		}
		else {
			System.out.println("No 401k account");
		}
		if(this.getPS() != null) {
			this.getPS().showAllInfo();
		}
		else {
			System.out.println("No PS account");
		}
		System.out.println();
	}
	
	protected String saving() {
		String s = getUsername() + " " + getPassword() + " " + getName() + " " + getDOB();
		if(this.get401k() != null) {
			s += " " + get401k().saving();
		}
		else {
			s += " null";
		}
		if(this.getPS() != null) {
			s += " " + getPS().saving();
		}
		else {
			s += " null";
		}
		return s;
	}
	
}
