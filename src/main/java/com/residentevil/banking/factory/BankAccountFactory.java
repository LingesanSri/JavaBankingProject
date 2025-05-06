package com.residentevil.banking.factory;

import com.residentevil.banking.model.BankAccount;
import com.residentevil.banking.service.impl.SavingsAccount;

public class BankAccountFactory{

	
	public BankAccount createAccount(String name, long phonenumber ) {
		
		SavingsAccount account = new SavingsAccount(name, phonenumber);
		
		account.accountGeneration();
		account.debitCardGeneration();
		
		return account;
		

	}
}
