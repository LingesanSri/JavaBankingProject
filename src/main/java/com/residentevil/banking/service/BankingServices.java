package com.residentevil.banking.service;

import java.util.Map;

import com.residentevil.banking.model.BankAccount;

public interface BankingServices {
	
	 void createBankAccount(String Name,long phoneNumber);
	 void viewAccountDetails();
	 void viewDebitCardDetails();
	 boolean userLogin(String userName, String password);
	 boolean userLogout(char logoutChar);
	 void deposit(double depositAmount);
	 void withdraw(double withdrawAmount);
	 void transferFunds(double transferAmount, Map<String, BankAccount> accountMap);
	 void viewAccountStatement();
	 void showHelp();
	 void updateAccountSettings();
	
}
