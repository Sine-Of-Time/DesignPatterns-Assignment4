package assignment04;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AccountServerThread extends Thread {
	private Socket socket;
	private Customer customer;
	private boolean doingTransactions = true;
	private Server server;

	private ObjectOutputStream outObjectStream;
	private ObjectInputStream inObjectStream;

	final ServerState WAITING;
	final ServerState CUSTOMER_SELECTED;
	private ServerState state;

	// REMEMBER You need to construct the ObjectOutputStream 
	// before the ObjectInputStream, at both ends.
	// There is discussion at many places, e.g.
	// https://www.reddit.com/r/learnprogramming/comments/138w9vc/create_objectoutputstream_before/

	public AccountServerThread(Server serverIn, Socket socketIn) {
		super("AccountServerThread");
		server = serverIn;
		socket = socketIn;
		WAITING = new WaitingState(this);
		CUSTOMER_SELECTED = new CustomerSelectedState(this);
		state = WAITING;
	}
	public void run() {
		if (Server.DEBUG) System.out.println("RUNNING THREAD " + this);
		try {
			while(doingTransactions) {
				state = state.nextState();
			}
		} catch (Exception e) {
			System.out.println("CONNECTION TERMINATED");
		}
	}
	public Customer getCustomer() {
		return customer;
	}
	public void selectCustomer(int i) {
		customer = server.getCustomer(i);
	}
	public void setDoingTransactions(boolean bool) {
		doingTransactions = bool;
	}
	public ObjectInputStream getInObjStream() {
		if(inObjectStream == null)
			try {
				inObjectStream = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		return inObjectStream;
	}
	public ObjectOutputStream getOutObjStream() {
		if(outObjectStream == null)
			try {
				outObjectStream = new ObjectOutputStream(socket.getOutputStream());			
			} catch (IOException e) {
				e.printStackTrace();
			}
		return outObjectStream;
	}
	public void doTransaction(BankTransaction trIn) {
		BankAccount savings = customer.getSavings();
		BankAccount checking = customer.getChecking();
		switch(trIn.transactionCode()) {
		case 1: 
			// TODO: deposit trIn.transactionAmount() in savings
			break;
		case 2: 
			// TODO: deposit trIn.transactionAmount() in checking
			break;
		case 3: 
			// TODO do withdraw
			break;
		case 4: 
			// TODO do withdraw
			break;
		case 5: 
			// TODO withdraw from savings, deposit in checking
			break;
		case 6: 
			// TODO the opposite transfer from 5
		}
	}
}
