package assignment04;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ATM {
	// map to assign a transaction code to the corresponding enum constant:
	static Map<Integer, ATMtransaction> map = new TreeMap<>();
	static Scanner keyboard = new Scanner(System.in);
	static {
		for(ATMtransaction t : ATMtransaction.values())
			map.put(t.getCode(), t);
		if(Server.DEBUG) System.out.println(map);
	}

	public static void main(String[] args) {
		// REMEMBER You need to construct the ObjectOutputStream 
		// before the ObjectInputStream, at both ends.
		// There is discussion at many places, e.g.
		// https://www.reddit.com/r/learnprogramming/comments/138w9vc/create_objectoutputstream_before/
		
		System.out.println("Which Account to use (1, 2, 3, 4, or, 5)?");
		String input = keyboard.nextLine();
		int accountNum = Integer.parseInt(input.trim());

		try (Socket bankSocket = new Socket("localhost", 6000);
				ObjectOutputStream outObject = new ObjectOutputStream(bankSocket.getOutputStream());
				ObjectInputStream inObject = new ObjectInputStream(bankSocket.getInputStream());
				) {
			String lineInput = (String)inObject.readObject();
			System.out.println(lineInput);
			if (lineInput.contains("ACCOUNT NUMBER")) {
				outObject.writeObject(""+accountNum);
			} else return;

			do {
				Customer customer = (Customer)inObject.readObject();
				var trOut = getTransaction(customer);
				outObject.writeObject(trOut);
				System.out.println("Another Transaction (Y/N)?");
				if(!keyboard.nextLine().toLowerCase().startsWith("y")) {
					outObject.writeObject("END");
					break; // this ATM program ends		
				}
			} while (true);
		} catch (Exception e) {
			System.err.println("Couldn't get I/O for the connection to localhost");
			System.exit(1);
		}
	}	
	
	static BankTransaction getTransaction(Customer customer) {
		BankTransaction transaction = null;
		while (true) {
			System.out.println("Which Transaction (1-8)?");
			System.out.println("\t1. Deposit to Savings");
			System.out.println("\t2. Deposit to Checking");
			System.out.println("\t3. Withdraw from Savings");
			System.out.println("\t4. Withdraw from Checking");
			System.out.println("\t5. Transfer from Savings to Checking");
			System.out.println("\t6. Transfer from Checking to Savings");
			System.out.println("\t7. Account Balances");
			System.out.println("\t8. CANCEL");
			int trCode = Integer.parseInt(keyboard.nextLine().trim());

			if (trCode == 7)
			{
				showBalances(customer);
				break;
			}
			else if (trCode == 8) break;
			// TODO
			// if trCode is 7, call showBalances, then break;
			// if trCode < 1 or trCode > 8
			// print "Please select a value between 1 and 7", then continue (NOT break)
			else if (trCode < 1 || trCode > 8)
			{
				System.out.println("Please select a value between 1 and 7");
				continue;
			}
			
			System.out.println("How much?");
			double amount = Double.parseDouble(keyboard.nextLine().trim());
			ATMtransaction transact = map.get(trCode);
			// Ask the ATMtransaction enum to check this transaction can
			// be done:
			boolean doable = transact.doable(amount, customer);
			if (amount <= 0 || !doable) {
				System.out.println("Please give a valid amount for a valid account");
				System.out.println("If necessary check your balances");
				continue;
			}
			transaction = transact.getTransaction();
			break;
		}
		return transaction;
	}
	public static void showBalances(Customer customer) {
		System.out.print("Savings: ");
		if(customer.getSavings() == null) 
			System.out.println("No Account");
		else 
			System.out.printf("%.2f\n", customer.getSavings().getBalance());
		System.out.print("Checking: ");
		if(customer.getChecking() == null) 
			System.out.println("No Account");
		else 
			System.out.printf("%.2f\n", customer.getChecking().getBalance());		
	}
}
