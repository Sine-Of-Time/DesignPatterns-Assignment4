package assignment04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class Server {
	public static boolean DEBUG = true;
	private Map<Integer, Customer> customers = new TreeMap<>();
	private boolean listening = true;

	public Server () {
		// Make 5 test accounts. The last two only have one account
		// either a savings or a checking, so all the behaviors can
		// be tested
		for(int i = 1; i <= 3; i++) {
			Person p = new Person("name"+i, 1+"-"+i+"-20", "123-456-789"+i);
			SavingsAccount sAcc = new SavingsAccount(200*i); 
			CheckingAccount cAcc = new CheckingAccount(100*i);
			customers.put(i, new Customer(p, sAcc, cAcc, i));
		}
		Person p = new Person("name4", "1-4-20", "123-456-7894");
		SavingsAccount sAcc = null; 
		CheckingAccount cAcc = new CheckingAccount(400);
		customers.put(4, new Customer(p, sAcc, cAcc, 4));

		p = new Person("name5", "1-5-20", "123-456-7895");
		sAcc = new SavingsAccount(500); 
		cAcc = null;
		customers.put(5, new Customer(p, sAcc, cAcc, 5));

		try (ServerSocket serverSocket = new ServerSocket(6000)) {
			while (listening) {
				if(DEBUG) System.out.println("Server LISTENING");
				new AccountServerThread(this, serverSocket.accept()).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port 6000");
			System.exit(-1);
		}
	}
	public Customer getCustomer(int i) {
		return customers.get(i);
	}
	public static void main(String[] args) {
		new Server();
	}
}
