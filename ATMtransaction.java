package assignment04;

public enum ATMtransaction {
	DEPOSIT_SAVINGS(1) {
		@Override
		boolean doable(Double amountIn, Customer customer) {
			amount = amountIn;
			if(customer.getSavings() == null) {
				return false;
			}
			return true;
		}
		@Override
		double getAmount() {
			return amount;
		}
	},
	DEPOSIT_CHECKING(2) {
		@Override
		boolean doable(Double amountIn, Customer customer) {
			amount = amountIn;
			// TODO does the account exist?
		}
		@Override
		double getAmount() {
			return amount;
		}
	},
	WITHDRAW_SAVINGS(3) {
		@Override
		boolean doable(Double amountIn, Customer customer) {
			amount = amountIn;
			// TODO does the account exist and if so is there enough money?
			// make sure $1 will be left in the account
		}
		@Override
		double getAmount() {
			return amount;
		}
	},
	WITHDRAW_CHECKING(4) {
		@Override
		boolean doable(Double amountIn, Customer customer) {
			amount = amountIn;
			// TODO does the account exist and if so is there enough money?
			// make sure $1 will be left in the account
		}
		@Override
		double getAmount() {
			return amount;
		}
	},
	TRANSFER_SAVINGS_CHECKING(5) {
		@Override
		boolean doable(Double amountIn, Customer customer) {
			amount = amountIn;
			// TODO do both accounts exist and if so is there enough money
			// in the account that is withdrawn from?
			// make sure $1 will be left in that account
		}
		@Override
		double getAmount() {
			return amount;
		}
	},
	TRANSFER_CHECKING_SAVINGS(6) {
		@Override
		boolean doable(Double amountIn, Customer customer) {
			amount = amountIn;
			// TODO do both accounts exist and if so is there enough money
			// in the account that is withdrawn from?
			// make sure $1 will be left in that account
		}
		@Override
		double getAmount() {
			return amount;
		}
	};

	int transactionCode;
	double amount;
	ATMtransaction(int codeIn) {
		transactionCode = codeIn;
	}
	abstract boolean doable(Double amountIn, Customer custIn);
	BankTransaction getTransaction() {
		return new BankTransaction(getCode(), getAmount());		
	}
	int getCode() {
		return transactionCode;
	}
	abstract double getAmount();
}
