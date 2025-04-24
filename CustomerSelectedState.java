package assignment04;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CustomerSelectedState implements ServerState {
	AccountServerThread ownerThread;
	
	public CustomerSelectedState(AccountServerThread owner) {
		ownerThread = owner;
	}

	public ServerState nextState() throws Exception {
		// you use ownerThread.getOutObjStream() and ownerThread.getInObjStream()
		// to get the streams when needed
		streamOut.reset(); //<<<<<<<<<<<<<<<<<<<<< MAJOR IMPORTANCE
		// with this reset command, we force Java to re-serialize the
		// Customer object, which has new values in its accounts.
		// Without reset, Java just keeps the old serialized version
		// with the old balances.
		
		// use writeObject to send out ownerThread.getCustomer() 
		
		// use readObject to get the response. Declare the response
		// as class Object because we are not sure what it is.
		
		// if response is null just return ownerThread.CUSTOMER_SELECTED, the ATM
		// did an operation that did generate a transaction

		// if (response instanceof BankTransaction)
		// cast the response to a BankTransaction: (BankTransaction)response and 
		// pass it to the doTransaction method in ownerThread, 
		// return ownerThread.CUSTOMER_SELECTED
		
		// if (response instanceof String), cast the response to String str
		// and if str.equals("END") set doingTransactions to false in ownerTread,
		// return ownerThread.CUSTOMER_SELECTED;
		
		// this goes after the previous ifs. It is not expected to happen
		System.out.println("PROTOCOL IS BROKEN");
		return null;
	}
}
