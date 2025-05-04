package assignment04;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class WaitingState implements ServerState {
	AccountServerThread ownerThread;
	
	public WaitingState(AccountServerThread owner) {
		ownerThread = owner;
	}

	public ServerState nextState() throws Exception {
		// you use ownerThread.getOutObjStream() and ownerThread.getInObjStream()
		// to get the streams
		// first use writeObject("ACCOUNT NUMBER") on the output stream
		// the ATM should send back the account id as a string, so
		// use readObject() to read the input stream, cast it to (String), then
		// use Integer.parseInt() to extract the int value i.

		ObjectOutputStream outStream = ownerThread.getOutObjStream();
		ObjectInputStream inStream = ownerThread.getInObjStream();
		outStream.writeObject("ACCOUNT NUMBER");
		String ret = (String) inStream.readObject();
		int i = Integer.parseInt(ret);

		ownerThread.selectCustomer(i);
		return ownerThread.CUSTOMER_SELECTED;
	}
}
