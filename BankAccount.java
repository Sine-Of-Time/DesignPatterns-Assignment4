package assignment04;

import java.io.Serializable;

/**
   A bank account has a balance that can be changed by 
   deposits and withdrawals.
 */
public abstract class BankAccount implements Serializable {  
	private static final long serialVersionUID = 6192856551553735012L;
	private double balance;

	/**
      Constructs a bank account with a zero balance.
	 */
	public BankAccount() {   
		this(0);
	}

	/**
      Constructs a bank account with a given balance.
      @param initialBalance the initial balance
	 */
	public BankAccount(double initialBalance) {   
		balance = initialBalance;
	}

	/**
      Deposits money into the bank account.
      @param amount the amount to deposit
	 */
	public void deposit(double amount) {  
		balance += amount;
	}

	/**
      Withdraws money from the bank account.
      @param amount the amount to withdraw
	 */
	public void withdraw(double amount) {   
		balance -= amount;
	}

	/**
      Gets the current balance of the bank account.
      @return the current balance
	 */
	public double getBalance() {   
		return balance;
	}
}