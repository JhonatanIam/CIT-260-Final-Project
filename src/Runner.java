import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {

	static List<Account> accounts = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		
//		System.out.println(java.time.LocalDate.now());
		
		
		File file = new File("Accounts.txt");
		reader = null;
		try {
			reader = new Scanner(new File("Accounts.txt"));
		}
		catch (IOException e) {
		}
		
		while(reader.hasNext()) {
			if(reader.next().equals("401k")) {
				accounts.add(new Account401k(reader.next() + " " + reader.next(), reader.next(), "401k", reader.next(), reader.nextDouble()));
			}
			else {
				accounts.add(new AccountProfitSharing(reader.next(), reader.next(), "Profit Sharing", reader.nextDouble()));
			}
		}
		
		System.out.println("Hello! Welcome, How may I help you today?\n");
		
		Boolean running = true;
		while(running) {
			int o = options(input);
			if(o == 1) {
				createAccount(input, "401k Account");
			}
			else if(o == 2) {
				createAccount(input, "Profit Sharing Account");
			}
			else if(o == 3) {
				deposit401k(input);
			}
			else if(o == 4) {
				System.out.println("Closing 401k Account");
				String yon = yesOrNo(input);
				if(yon.equalsIgnoreCase("y")) {
					System.out.println("Your funds are not eligible for withdrawal.");
				}
				else {
					String terminatingDate = getDate(input, "termination date");
				}
				
			}
			else if(o == 5) {
				
			}
			else if(o == 6) {
				
			}
			else {
				running = false;
			}
		}
			
		System.out.println("Thank you for visiting and choosing CIT260 Bank!");
		
		
		
	}
	
	public static int options(Scanner input) {
		int num = 0;
		boolean boo = false;
		while(!boo) {
			System.out.println("Pick one of the following: ");
			System.out.println("   [1] Sign up for a 401k account");
			System.out.println("   [2] Sign up for a profit sharing account");
			System.out.println("   [3] Deposit money into 401k account");
			System.out.println("   [4] Close 401k account");
			System.out.println("   [5] Close profit sharing account");
			System.out.println("   [6] Display balance");
			System.out.println("   [7] Leave");
			try {
				num = input.nextInt();
				boo = true;
				if(num > 7 || num < 1) {
					System.out.println("Error: input not an option.");
					input = new Scanner(System.in);
					boo = false;
				}
				
			}
			catch(Exception ex) {
				System.out.println("Error: input must be an integer.");
				input = new Scanner(System.in);
			}
			System.out.println();
		}
		System.out.println("\n");
		return num;
	}
	
	private static void createAccount(Scanner input, String account) {
		System.out.println("Opening a " + account + " will require:\n - your name\n - hire date");
		if(account.equals("401k Account")) {
			System.out.print(" - date of birth\n - starting deposit\n\n");
		}
		String name = getUserName(input);
		String hiringDate = getDate(input, "hiring date");
		if(account.equals("401k Account")) {
			String dob = getDate(input, "date of birth");
			double deposit = getDeposit(input);
			accounts.add(new Account401k(name, hiringDate, "401k", dob, deposit));
		}
		else {
			accounts.add(new AccountProfitSharing(name, hiringDate, "Profit Sharing", 0));
		}
		System.out.println("\nNew Account Created");
		accounts.get(accounts.size() - 1).display();
		System.out.println("\n\n");
	}
	
	private static void deposit401k(Scanner input) {
		System.out.println("Login into your 401k account");
		String name = getUserName(input);
		String dob = getDate(input, "date of birth");
		int accountNum = search(name, dob);
		if(accountNum >= 0) {
			System.out.println("Account found, access granted");
			((Account401k) accounts.get(accountNum)).deposit(getDeposit(input));
			System.out.println("Deposit complete! New balance: " + accounts.get(accountNum).getBalance());
		}
		else {
			System.out.println("Account not found, please contact HR for more detail.");
		}
	}
	
	
	
	
	
	private static String getUserName(Scanner input) {
		System.out.print("Enter your first name: ");
		String first = input.next();
		System.out.print("Enter your last name: ");
		String last = input.next();
		return first + " " + last;
	}
	
	private static int search(String name, String dob) {
		for (int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			if(a.getType().equals("401k")) {
				if(((Account401k) a).getName().equals(name) && ((Account401k) a).getDOB().equals(dob)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private static String getDate(Scanner input, String event) {
		String date = null;
		boolean boo = false;
		while(!boo) {
			System.out.print("Enter " + event + ": (mm/dd/yyyy)  ");
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
	
	private static String yesOrNo(Scanner input) {
		String yon = null;
		boolean boo = false;
		while(!boo) {
			System.out.print("Are you still employed with your company? [Y/N] ");
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
			System.out.print("Enter deposit: ");
			try {
				deposit = input.nextDouble();
				boo = true;
			}
			catch(Exception ex) {
				System.out.println("Error: input must be a numerical value.\n");
				input = new Scanner(System.in);
			}
		}
		return deposit;
	}
	
	private static Boolean validateDate(String date) {
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
		if(Integer.parseInt(y) > Year.now().getValue() || Integer.parseInt(y) < 1) {
			return false;
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
