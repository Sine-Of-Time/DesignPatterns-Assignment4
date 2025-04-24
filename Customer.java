package assignment04;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = -5165742922982890044L;
	private Person person;
	private SavingsAccount savings;
	private CheckingAccount checking;
	private int id;
	public Customer(Person p, SavingsAccount s, CheckingAccount c, int i) {
		person = p;
		savings = s;
		checking = c;
		id = i;
	}
	public Person getPerson() {
		return person;
	}
	public SavingsAccount getSavings() {
		return savings;
	}
	public CheckingAccount getChecking() {
		return checking;
	}
	public int getId() {
		return id;
	}
	public String toString() {
		return "Customer " + id + " Checking: " + (checking==null?"N/A":""+checking.getBalance())
				+ " Savings: " + (savings==null?"N/A":""+savings.getBalance());
	}
}
