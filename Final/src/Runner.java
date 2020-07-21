import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Period;
import java.time.Year;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {

	private static final Temporal Temporal = null;
	static private List<Person> ppl = new ArrayList<>();
	static private Person user;
	static private int choice;
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		File file = new File("Accounts.txt");
		reader = null;
		try {
			reader = new Scanner(new File("Accounts.txt"));
		}
		catch (IOException e) {
		}
		if(reader != null) {
			while(reader.hasNextLine()) {
				Scanner line = new Scanner(reader.nextLine());
				ppl.add(new Person(line.next(), line.next(), line.next() + " " + line.next(), line.next(),
						(Account401k) findAccount(line), (AccountProfitSharing) findAccount(line)));
			}
		}
		
		Boolean running = true;
		while(running) {
			System.out.println("Welcome to CIT260 Bank! How may we help you today?");
			
			choice = options(new Scanner(System.in), "   [1] Log in\n   [2] Sign Up\n   [3] Leave", 3);
			
			if(choice == 1) {
				user = logIn(new Scanner(System.in));
				interaction();
			}
			else if(choice == 2) {
				newUser();
			}
			else {
				running = false;
			}
		}
		System.out.println("\n\nThank you for visiting and choosing CIT260 Bank!\n\n\n");
		saveData();
	}
	
	
	
	private static int options(Scanner input, String opt, int max) {
		int num = 0;
		boolean boo = true;
		while(boo) {
			System.out.println("Pick one of the following: ");
			System.out.println(opt);
			try {
				num = input.nextInt();
				boo = false;
				if(num > max || num < 1) {
					System.out.println("Error: input not an option.\n");
					input = new Scanner(System.in);
					boo = true;
				}
			}
			catch(Exception ex) {
				System.out.println("Error: input must be an integer.\n");
				input = new Scanner(System.in);
			}
		}
		System.out.println();
		return num;
	}
	
	private static void newUser() {
		System.out.println("\nWhat type of account would you like to open?");
		int a = options(new Scanner(System.in), "   [1] 401k\n   [2] Profit Sharing\n   [3] Back", 3);
		if(a == 1) {
			createAccount(new Scanner(System.in), "401k Account", false);
		}
		else if(a == 2) {
			createAccount(new Scanner(System.in), "Profit Sharing Account", false);
		}
	}
	
	private static Person logIn(Scanner input) {
		String username;
		String pass;
		Person per = null;
		boolean u = false;
		boolean p = false;
		System.out.println("\nSign in using your username and password");
		while(!u && !p) {
			System.out.print("\nEnter username: ");
			username = input.next();
			System.out.print("Enter password: ");
			pass = input.next();
			for (int i = 0; i < ppl.size(); i++) {
				Person test = ppl.get(i);
				if(test.getUsername().equals(username)) {
					u = true;
					if(test.getPassword().equals(pass)) {
						p = true;
						per = test;
						break;
					}
				}
			}
			if(u && !p) {
				System.out.println("Error: Incorrect password.\n");
				if(yesOrNo(new Scanner(System.in), "Try again?").equalsIgnoreCase("n")) {
					return null;
				}
				u = false;
			}
			else if(!u) {
				System.out.println("Error: Username doesn't exist.\n");
				if(yesOrNo(new Scanner(System.in), "Try again?").equalsIgnoreCase("n")) {
					return null;
				}
			}
		}
		return per;
	}
	
	private static void interaction() {
		if(user == null) {
			System.out.println("\n\n");
			return;
		}
		System.out.println("\n\n\nHello! Welcome " + user.getName() + "!");
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println();
			if(user.get401k() == null && user.getPS() != null) {
				int o = options(new Scanner(System.in), "   [1] Sign up for a 401k account\n   [2] Display balance\n   [3] Close profit sharing account\n   [4] Log Out", 4);
				if(o == 1) {
					createAccount(new Scanner(System.in), "401k Account", true);
				}
				else if(o == 2) {
					user.profile();
				}
				else if(o == 3) {
					closingAccount(new Scanner(System.in), "Profit Sharing Account");
				}
				else {
					System.out.println("\n\n");
					saveData();
					break;
				}
			}
			else if(user.get401k() != null && user.getPS() == null) {
				int o = options(new Scanner(System.in), "   [1] Sign up for a profit sharing account\n   [2] Deposit money into 401k account\n   [3] Display balance\n   [4] Close 401k account\n   [5] Log Out", 5);
				if(o == 1) {
					createAccount(new Scanner(System.in), "Profit Sharing Account", true);
				}
				else if(o == 2) {
					user.get401k().deposit(getDeposit(new Scanner(System.in)));
					System.out.println("Deposit complete! New balance: " + user.get401k().getBalance());
				}
				else if(o == 3) {
					user.profile();
				}
				else if(o == 4) {
					closingAccount(new Scanner(System.in), "401k Account");
					
				}
				else {
					System.out.println("\n\n");
					saveData();
					break;
				}
			}
			else if(user.get401k() == null && user.get401k() == null) {
				int o = options(new Scanner(System.in), "   [1] Sign up for a 401k account\n   [2] Sign up for a profit sharing account\n   [3] Log Out", 3);
				if(o == 1) {
					createAccount(new Scanner(System.in), "401k Account", true);
				}
				else if(o == 2) {
					createAccount(new Scanner(System.in), "Profit Sharing Account", true);
				}
				else {
					System.out.println("\n\n");
					saveData();
					break;
				}
			}
			else {
				int o = options(new Scanner(System.in), "   [1] Deposit money into 401k account\n   [2] Display balance\n   [3] Close 401k account\n   [4] Close profit sharing account\n   [5] Log Out", 5);
				if(o == 1) {
					user.get401k().deposit(getDeposit(new Scanner(System.in)));
					System.out.println("Deposit complete! New balance: " + user.get401k().getBalance());
				}
				else if(o == 2) {
					user.profile();
				}
				else if(o == 3) {
					closingAccount(new Scanner(System.in), "401k Account");
				}
				else if(o == 4) {
					closingAccount(new Scanner(System.in), "Profit Sharing Account");
				}
				else {
					System.out.println("\n\n");
					saveData();
					break;
				}
			}
			System.out.println("\n\n\n");
		}
	}
	
	private static void createAccount(Scanner input, String account, boolean hasOtherAccount) {
		System.out.println("We require the following information to open a " + account + ":");
		if(!hasOtherAccount) {
			System.out.println(" - Your Name\n - Username\n - Password\n - Date of Birth\n - Hire Date\n");
			if(account.equals("401k Account")) {
				System.out.print(" - Deposit\n");
			}
		}
		else {
			System.out.print(" - hire date\n");
			if(account.equals("401k Account")) {
				System.out.print(" - Deposit\n");
			}
			else {
				System.out.println();
			}
		}
		
		while(true) {
			String name = "";
			String username = "";
			String pass = "";
			String dob = "";
			if(!hasOtherAccount) {
				name = getName(new Scanner(System.in));
				username = createUsername(new Scanner(System.in));
				pass = createPassword(new Scanner(System.in));
				dob = getDate(input, "date of birth");
			}
			String hiringDate = "";
			while(true) {
				hiringDate = getDate(new Scanner(System.in), "hiring date");
				if(hasOtherAccount) {
					if(largerDate(user.getDOB(), hiringDate) > 180) {
						break;
					}
					else {
						System.out.println("Error: You must be at least 16 years old to work\n");
					}
				}
				else {
					if(largerDate(dob, hiringDate) > 180) {
						break;
					}
					else {
						System.out.println("Error: You must be at least 16 years old to work\n");
					}
				}
				
			}
			if(checkExisting(new Person(username, pass, name, dob, null, null), account) < 0) {
				if(hasOtherAccount) {
					if(account.equals("Profit Sharing Account")) {
						user.setPS(new AccountProfitSharing("PS", hiringDate, 0));
						System.out.println("\nAccount Created Successfully!");
						user.getPS().display();
					}
					else {
						user.set401k(new Account401k("401k", hiringDate, getDeposit(new Scanner(System.in))));
						System.out.println("\nAccount Created Successfully!");
						user.get401k().display();
					}
				}
				else {
					if(account.equals("Profit Sharing Account")) {
						ppl.add(new Person(username, pass, name, dob, null, new AccountProfitSharing("PS", hiringDate, 0)));
						System.out.println("\nAccount Created Successfully! To enter account, log in.");
						user = ppl.get(ppl.size() - 1);
						user.getPS().display();
						System.out.println("\n\n");
					}
					else {
						ppl.add(new Person(username, pass, name, dob, new Account401k("401k", hiringDate, getDeposit(new Scanner(System.in))), null));
						System.out.println("\nAccount Created Successfully! To enter account, log in.");
						user = ppl.get(ppl.size() - 1);
						user.get401k().display();
						System.out.println("\n\n");
					}
				}
				break;
			}
			else {
				System.out.println("Error: Account creation failed. Another account with the given information already exists.\nContact HR for more information.\n");
				if(yesOrNo(new Scanner(System.in), "Try again?").equalsIgnoreCase("n")) {
					System.out.println("\n\n");
					break;
				}
			}
		}
	}
	
	private static void closingAccount(Scanner input, String account) {
		System.out.println("Closing " + account);
		String yon = yesOrNo(input, "Are you still employed with your company?");
		if(yon.equalsIgnoreCase("y")) {
			System.out.println("Your funds are not eligible for withdrawal.");
		}
		else {
			if(account.equals("401k Account")) {
				double vested = user.get401k().getVested();
				user.set401k(null);
				System.out.println("Your " + account + " has been closed. A check of $" + vested + " will be disbursed to you.");
			}
			else {
				double vested = user.getPS().getVested();
				user.setPS(null);
				System.out.println("Your " + account + " has been closed. A check of $" + vested + " will be disbursed to you.");
			}
		}
	}
	private static void saveData() {
		PrintWriter printer = null;
		try {
			printer = new PrintWriter(new File("Accounts.txt"));
		}
		catch (IOException e) {
			System.out.println("Can't create file!");
		}
		for (int i = 0; i < ppl.size(); i++) {
			printer.println(ppl.get(i).saving());
		}
		printer.close();
	}
	
	
	
	private static Account findAccount(Scanner line) {
		String n = line.next();
		if(n.equals("null")) {
			return null;
		}
		else {
			String hr = line.next();
			double b = line.nextDouble();
			if(n.equals("401k")) {
				return new Account401k(n, hr, b);
			}
			else if(n.equals("PS")) {
				return new AccountProfitSharing(n, hr, b);
			}
		}
		return null;
	}
	
	private static String getName(Scanner input) {
		System.out.print("Enter your first name: ");
		String first = input.next();
		System.out.print("Enter your last name: ");
		String last = input.next();
		return first + " " + last;
	}
	
	private static String createUsername(Scanner input) {
		String username = null;
		while(true) {
			System.out.print("Enter desired username: ");
			username = input.nextLine();
			if(username.indexOf(" ") < 0) {
				break;
			}
			else {
				System.out.println("Error: Username cannot contain any spaces.\n");
			}
		}
		return username;
	}
	
	private static String createPassword(Scanner input) {
		String pass = null;
		while(true) {
			System.out.print("Enter password: ");
			pass = input.nextLine();
			System.out.print("Confirm password: ");
			String confirm = input.nextLine();
			if(pass.indexOf(" ") < 0) {
				if(pass.equals(confirm)) {
					break;
				}
				System.out.println("Error: Passwords do not match.\n");
			}
			else {
				System.out.println("Error: Password cannot contain any spaces.\n");
			}
		}
		return pass;
	}
	
	private static int checkExisting(Person p, String account) {
		for (int i = 0; i < ppl.size(); i++) {
			Person test = ppl.get(i);
			if(test.getUsername().equals(p.getUsername()) && test.getPassword().equals(p.getPassword())) {
				if(test.getName().equals(p.getName()) && test.getDOB().equals(p.getDOB())) {
					if(account.equals("401k Account") && test.get401k() != null) {
						return i;
					}
					else if(account.equals("Profit Sharing Account") && test.getPS() != null) {
						return i;
					}
				}
			}
		}
		return -1;
	}
	
	private static String getDate(Scanner input, String event) {
		String date = null;
		boolean boo = false;
		while(!boo) {
			System.out.print("Enter " + event + ": (mm/dd/yyyy) ");
			date = input.next();
			if(validateDate(date)) {
				boo = true;
			}
			else {
				System.out.println("Error: Incorrect format or unavailable date.\n");
			}
		}
		return date;
	}
	
 	private static String yesOrNo(Scanner input, String Q) {
		String yon = null;
		boolean boo = false;
		while(!boo) {
			System.out.print(Q  + " [Y/N] ");
			yon = input.next();
			if(yon.equalsIgnoreCase("y") || yon.equalsIgnoreCase("n")) {
				boo = true;
			}
			else {
				System.out.println("Error: Incorrect format or response.\n");
				input = new Scanner(System.in);
			}
		}
		return yon;
	}
	
	private static double getDeposit(Scanner input) {
		double deposit = 0;
		boolean boo = false;
		while(!boo) {
			System.out.print("Enter deposit: $");
			try {
				deposit = input.nextDouble();
				if(deposit >= 0) {
					boo = true;
				}
				else {
					System.out.println("Error: input must be a positive numerical value.\n");
				}
			}
			catch(Exception ex) {
				System.out.println("Error: input must be a positive numerical value.\n");
				input = new Scanner(System.in);
			}
		}
		return deposit;
	}
	
	static int largerDate(String date1, String date2) {
		String m1 = date1.substring(0, date1.indexOf("/"));
		String d1 = date1.substring(date1.indexOf("/") + 1);
		String y1 = d1.substring(d1.indexOf("/") + 1);
		d1 = d1.substring(0, d1.indexOf("/"));
		
		String m2 = date2.substring(0, date2.indexOf("/"));
		String d2 = date2.substring(date2.indexOf("/") + 1);
		String y2 = d2.substring(d2.indexOf("/") + 1);
		d2 = d2.substring(0, d2.indexOf("/"));
		
		Period timeDif = java.time.Period.between(java.time.LocalDate.of(Integer.parseInt(y1), Integer.parseInt(m1), Integer.parseInt(d1)), java.time.LocalDate.of(Integer.parseInt(y2), Integer.parseInt(m2), Integer.parseInt(d2)));
		int m = timeDif.getMonths();
		int y = timeDif.getYears() * 12;
		return m + y;
	}
	
	private static boolean validateDate(String date) {
		if(date.indexOf("/") < 0) {
			return false;
		}
		String m = date.substring(0, date.indexOf("/"));
		if(Integer.parseInt(m) > 12 || Integer.parseInt(m) < 1) {
			return false;
		}
		String d = date.substring(date.indexOf("/") + 1);
		if(d.indexOf("/") < 0) {
			return false;
		}

		String y = d.substring(d.indexOf("/") + 1);
		d = d.substring(0, d.indexOf("/"));
		if(Integer.parseInt(d) > java.time.LocalDate.now().getDayOfMonth()) {
			if(Integer.parseInt(m) >= java.time.LocalDate.now().getMonthValue()) {
				if(Integer.parseInt(y) >= Year.now().getValue() || Integer.parseInt(y) < 1) {
					return false;
				}
			}
		}
		switch(Integer.parseInt(m)) {
			case 1:
				if(Integer.parseInt(d) > 31 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 2:
				if(java.time.Year.isLeap(Integer.parseInt(y))) {
					if(Integer.parseInt(d) > 29 || Integer.parseInt(d) < 1) {
						return false;
					}
				}
				else {
					if(Integer.parseInt(d) > 28 || Integer.parseInt(d) < 1) {
						return false;
					}
				}
				
			case 3:
				if(Integer.parseInt(d) > 31 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 4:
				if(Integer.parseInt(d) > 30 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 5:
				if(Integer.parseInt(d) > 31 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 6:
				if(Integer.parseInt(d) > 30 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 7:
				if(Integer.parseInt(d) > 31 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 8:
				if(Integer.parseInt(d) > 31 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 9:
				if(Integer.parseInt(d) > 30 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 10:
				if(Integer.parseInt(d) > 31 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 11:
				if(Integer.parseInt(d) > 30 || Integer.parseInt(d) < 1) {
					return false;
				}
				
			case 12:
				if(Integer.parseInt(d) > 31 || Integer.parseInt(d) < 1) {
					return false;
				}
		}
		return true;
	}
	
}
