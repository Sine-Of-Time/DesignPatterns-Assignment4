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
	public void doTransaction(BankTransaction tr) {
        double amount        = tr.transactionAmount();
        BankAccount savings  = customer.getSavings();
        BankAccount checking = customer.getChecking();

        switch (tr.transactionCode()) {
            case 1:  // deposit → savings
                if (savings != null) savings.deposit(amount);
                break;

            case 2:  // deposit → checking
                if (checking != null) checking.deposit(amount);
                break;

            case 3:  // withdraw ← savings
                if (savings != null) savings.withdraw(amount);
                break;

            case 4:  // withdraw ← checking
                if (checking != null) checking.withdraw(amount);
                break;

            case 5:  // transfer savings → checking
                if (savings != null && checking != null && savings.getBalance() >= amount + 1){
					savings.withdraw(amount);
					checking.deposit(amount);
				}
                break;

            case 6:  // transfer checking → savings
                if (checking != null && savings != null && checking.getBalance() >= amount + 1){
					checking.withdraw(amount);
					savings.deposit(amount);
				}
                break;

            default:
                if (Server.DEBUG)
                    System.out.println("Unknown transaction code: " + tr.transactionCode());
        }
    }
}
