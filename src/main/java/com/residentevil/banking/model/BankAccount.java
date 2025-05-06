package com.residentevil.banking.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.residentevil.banking.service.BankingServices;
import com.residentevil.banking.utility.AccountUtility;

public abstract class BankAccount implements BankingServices {

	private String accountHolderName;
	private String userName;
	private String password;
	private long phoneNumber;
	protected String accountNumber;
	public Double balance;
	final double Minimum_Balance = 500;
	public final static double Max_Deposit_Amount = 999999999;
	public final static double Min_Deposit_Amount = 1;
	protected static final String Bank_Name = "Resident Evil";
	protected static final String Branch_Name = "Raccoon City";
	protected static final String IFSC_CODE = "RE042005";

	protected String debitCardNumber;
	protected int cvvNumber;
	protected int cardPin;
	protected static final DateTimeFormatter Date_Time_Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
	protected static final String DATE_TIME = LocalDateTime.now(ZoneId.systemDefault()).format(Date_Time_Formatter);
	private static final String Row_Format = "%-22s | %24s | %20s | %16.2f";
	private static final String Head_Format = "%-22s | %26s | %20s | %16s%n";

	private Map<String, Integer> userNameData = new HashMap<>();

	public List<String> statement = new ArrayList<>();
	public final static String ANSI_RESET = "\u001B[0m";
	public final static String ANSI_GREEN = "\u001B[32m";
	public final static String ANSI_RED = "\u001B[31m";
	public final static String ANSI_CYAN = "\u001B[36m";
	public final static String ANSI_YELLOW = "\u001B[33m";
	public final static String ANSI_PURPLE = "\u001B[35m";

	public BankAccount(String accountHolderName, long phoneNumber) {

		this.accountHolderName = accountHolderName;
		this.phoneNumber = phoneNumber;

	}

	public void accountGeneration() {

		userName = AccountUtility.generateUsername(accountHolderName, userNameData);
		password = AccountUtility.generatePassword();
		balance = getMinimumBalance();
		phoneNumber = getPhoneNumber();
		accountNumber = AccountUtility.generateAccountNumber();
	}

	public void debitCardGeneration() {

		debitCardNumber = AccountUtility.generateCardNumber();
		cvvNumber = AccountUtility.generateCvvNumber();
		cardPin = AccountUtility.generateCardPin();

	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getDebitCardNumber() {
		return debitCardNumber;
	}

	public int getCvvNumber() {
		return cvvNumber;
	}

	public int getCardPin() {
		return cardPin;
	}

	public Double getBalance() {
		return balance;
	}

	public double getMinimumBalance() {
		return Minimum_Balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setCardPin(int cardPin) {
		this.cardPin = cardPin;

	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountHolderName(String accountHolderName) {

		this.accountHolderName = accountHolderName;
	}

	public void setPhoneNumber(long phoneNumber) {

		this.phoneNumber = phoneNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public static String getRowFormat() {
		return Row_Format;
	}

	public static String getHeadFormat() {
		return Head_Format;
	}

	public static String getDateTime() {
		return DATE_TIME;
	}

}
