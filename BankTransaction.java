package assignment04;

import java.io.Serializable;

// not that the automatic public getter methods are 
// transactionCode() and transactionAmount()
// the constructor looks like:
// new BankTransaction(code, amount)
public record BankTransaction(int transactionCode, double transactionAmount) implements Serializable {
}
