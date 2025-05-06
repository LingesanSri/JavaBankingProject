package com.residentevil.banking.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.residentevil.banking.model.BankAccount;

abstract class BankingServiceImplementation extends BankAccount {
	static int loginCount = 0;
	protected static Scanner scanner = new Scanner(System.in);

	public BankingServiceImplementation(String accountHolderName, long phoneNumber) {
		super(accountHolderName, phoneNumber);

	}

	String line = "__________________________________________________________________________________________________________";

	@Override
	public void createBankAccount(String Name, long phoneNumber) {

		System.out.println("Details Required to open a new Bank Account\n" + "1. Your Full Name\n2. Phone Number");
		System.out
				.println("Once Account is created, your account and debit card details will be generated and shared.");
		System.out.println(line);
		System.out.println("Note: Name should start with UpperCase followed by lowercase and can contain spaces!");
		System.out.println("Note: Phone Number should be exactly 10 digits!");

		// Set the validated values
		setAccountHolderName(Name);
		setPhoneNumber(phoneNumber);

		// Generate account and card details
		accountGeneration();
		debitCardGeneration();

	}

	@Override
	public void viewDebitCardDetails() {

		System.out.println(ANSI_YELLOW + "\t\t\t\t\tDebit Card Details" + ANSI_RESET);
		System.out.println("Debit Card Number  = " + debitCardNumber);
		System.out.println("CVV Number         = " + cvvNumber);
		System.out.println("Card Pin           = " + cardPin);

	}

	@Override
	public void viewAccountDetails() {

		System.out.println(ANSI_YELLOW + "\t\t\t\t\tAccount Details" + ANSI_RESET);
		System.out.println("Account Holder Name     = " + getAccountHolderName());
		System.out.println("Account Number          = " + getAccountNumber());
		System.out.println("User name               = " + getUserName());
		System.out.println("Password                = " + getPassword());
		System.out.println("Phone Number            = " + getPhoneNumber());
		System.out.println("Bank Name               = " + Bank_Name);
		System.out.println("Branch Name             = " + Branch_Name);
		System.out.println("Bank Ifsc Code          = " + IFSC_CODE);
		System.out.println("Account balance         = " + getBalance());

	}

	@Override
	public boolean userLogin(String username, String password) {

	    System.out.println("As per banking rules, entering wrong credentials more than 3 times will lock your account for 24 hours.");

	    while (true) {
	        System.out.print(ANSI_CYAN + "Do you want to change your username or password? (y/n): " + ANSI_RESET);
	        String input = scanner.nextLine();
	        if (input.length() == 1) {
	            char userChoice = input.charAt(0);
	            if (userChoice == 'y' || userChoice == 'Y') {
	                System.out.println("Returning to home page...");
	                return false; // Stop login
	            } else if (userChoice == 'n' || userChoice == 'N') {
	                System.out.println(ANSI_YELLOW +
	                        "Note: Username should contain lowercase letters, one number, and one special character.\n"
	                        + "Password should contain lowercase letters, one uppercase letter, one number, and one special character."
	                        + ANSI_RESET);
	                break;
	            }
	        } else {
	            System.out.println(ANSI_RED + "Invalid input. Please enter 'y' or 'n' only." + ANSI_RESET);
	        }
	    }

	    int loginAttempts = 0;

	    while (loginAttempts < 3) {
	        System.out.print(ANSI_CYAN + "Enter the username: " + ANSI_RESET);
	        username = scanner.nextLine();
	        System.out.print(ANSI_CYAN + "Enter the password: " + ANSI_RESET);
	        password = scanner.nextLine();

	        if (username.isEmpty() || password.isEmpty()) {
	            System.out.println(ANSI_RED + "Username and password cannot be empty!" + ANSI_RESET);
	            continue;
	        }

	        loginAttempts++;

	        if (getUserName().equals(username) && getPassword().equals(password)) {
	            System.out.println(ANSI_GREEN + "Login Successful!" + ANSI_RESET);
	            return true;
	        } else {
	            if (loginAttempts < 3) {
	                System.out.println(ANSI_RED + "Invalid credentials. Remaining attempts: " + (3 - loginAttempts) + ANSI_RESET);
	            } else {
	                System.out.println(ANSI_RED + "Account locked due to too many failed login attempts. Try again after 24 hours." + ANSI_RESET);
	                System.exit(0);
	                
	                
	            }
	        }
	    }

	    return false;
	}



	@Override
	public boolean userLogout(char logoutChar) {

		System.out.println(ANSI_YELLOW + "\t\t\t\t\tLogout Page" + ANSI_RESET);

		while (true) {
			System.out.print(ANSI_CYAN + "Do you want to logout? (y/n): " + ANSI_RESET);
			logoutChar = scanner.next().charAt(0);
			scanner.nextLine(); 

			if (logoutChar == 'y' || logoutChar == 'Y') {
				System.out.println(ANSI_GREEN + "Logged out successfully." + ANSI_RESET);
				return true;
			} else if (logoutChar == 'n' || logoutChar == 'N') {
				System.out.println(ANSI_RED + "Logout cancelled." + ANSI_RESET);
				return false;
			} else {
				System.out.println(ANSI_RED + "Invalid input. Please enter 'y' or 'n' only." + ANSI_RESET);
			}
		}
	}

	@Override
	public void deposit(double depositAmount) {
		System.out.println(ANSI_YELLOW + "\t\t\t\t\tDeposit Page" + ANSI_RESET);

		System.out.println("Minimum Deposit Amount = " + Min_Deposit_Amount);
		System.out.println("Maximum Deposit Amount = " + Max_Deposit_Amount);
		System.out.println("Note: Enter the amount in numeric values only!");

		do {
			System.out.println(ANSI_CYAN + "Current Balance = " + getBalance() + ANSI_RESET);

			try {
				while (true) {
					System.out.print(ANSI_CYAN + "Enter the Amount to be deposited = " + ANSI_RESET);
					if (scanner.hasNextDouble()) {
						depositAmount = scanner.nextDouble();
						scanner.nextLine();

						if (depositAmount > 0) {
							balance += depositAmount;
							String deposit = String.format("%.2f", depositAmount);
							System.out.println(ANSI_PURPLE + "Updated Balance = " + getBalance() + ANSI_RESET);
							System.out.println(ANSI_GREEN + "Amount deposited Successfully..." + ANSI_RESET);
							statement.add(ANSI_GREEN
									+ String.format(getRowFormat(), DATE_TIME, "", deposit, getBalance()) + ANSI_RESET);
							break; 
						} else {
							System.out.println(ANSI_RED + "Amount should be greater than Rs. 0." + ANSI_RESET);
						}
					} else {
						System.out.println(ANSI_RED + "Enter only numeric values." + ANSI_RESET);
						scanner.nextLine();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED + "Input Mismatch! Please enter numeric values." + ANSI_RESET);
				scanner.nextLine();
			}

			System.out.print(ANSI_CYAN + "Do you wish to deposit again? (y/n): " + ANSI_RESET);
			char choice = scanner.next().charAt(0);
			scanner.nextLine();
			if (choice == 'y' || choice == 'Y') {
				continue;
			} else if (choice == 'n' || choice == 'N') {
				break;
			}
		} while (true);
	}

	@Override
	public void withdraw(double withdrawAmount) {
		System.out.println(ANSI_YELLOW + "\t\t\t\t\tWithdrawal Page" + ANSI_YELLOW);
		System.out.println("Note : Enter the amount in numeric values only!");

		do {
			System.out.println(ANSI_CYAN + "Current Balance = " + getBalance() + ANSI_RESET);

			try {
				while (true) {
					System.out.print(ANSI_CYAN + "Enter the Amount to be withdrawn = " + ANSI_RESET);

					if (scanner.hasNextDouble()) {
						withdrawAmount = scanner.nextDouble();
						scanner.nextLine();

						if (withdrawAmount >= 100 && getBalance() - withdrawAmount >= getMinimumBalance()) {
							balance -= withdrawAmount;
							String withdraw = String.format("%.2f", withdrawAmount);
							System.out.println(ANSI_PURPLE + "Updated Balance = " + getBalance() + ANSI_RESET);
							System.out.println(ANSI_GREEN + "Amount withdrawal is successful..." + ANSI_RESET);

							statement
									.add(ANSI_RED
											+ String.format(getRowFormat(),
													(LocalDateTime.now(ZoneId.systemDefault())
															.format(Date_Time_Formatter)),
													withdraw, "", getBalance())
											+ ANSI_RESET);

							break;
						} else {
							System.out.println(ANSI_RED + "Withdrawal amount should be greater than minimum balance."
									+ ANSI_RESET);
						}
					} else {
						System.out.println("Please enter a numeric value.");
						scanner.nextLine();
					}
				}

			} catch (InputMismatchException e) {
				System.out.println(ANSI_RED + "Input Mismatch! Please enter a numeric value." + ANSI_RESET);
				scanner.nextLine();
			}

			System.out.print(ANSI_CYAN + "Do you want to continue Withdrawal (y/n) : " + ANSI_RESET);
			char decision = scanner.next().charAt(0);
			scanner.nextLine();
			if (decision == 'Y' || decision == 'y') {
				continue;
			} else {
				break;
			}

		} while (true);
	}

	@Override
	public void transferFunds(double transferAmount, Map<String, BankAccount> accountMap) {
		System.out.println(ANSI_YELLOW + "\t\t\t\t\tAmount Transfer Page" + ANSI_RESET);

		boolean continueTransfer = true;

		while (continueTransfer) {
			System.out.print(ANSI_CYAN + "Enter the recipient name: " + ANSI_RESET);
			String AccountHolderName = scanner.nextLine().trim();

			BankAccount recipient = accountMap.get(AccountHolderName);

			if (recipient == null) {
				System.out.println(ANSI_RED + "Recipient account not found." + ANSI_RESET);
				break;
			}

			if (recipient.getAccountNumber().equals(this.getAccountNumber())) {
				System.out.println(ANSI_RED + "You cannot transfer to your own account." + ANSI_RESET);
				continue;
			}

			while (true) {
				System.out.print(ANSI_CYAN + "Enter the amount to transfer: " + ANSI_RESET);
				if (scanner.hasNextDouble()) {
					transferAmount = scanner.nextDouble();
					scanner.nextLine();

					if (transferAmount < 500) {
						System.out.println(ANSI_RED + "Minimum transfer amount is ₹500." + ANSI_RESET);
					} else if (this.getBalance() - transferAmount < this.getMinimumBalance()) {
						System.out.println(ANSI_RED + "Insufficient balance for transfer." + ANSI_RESET);
					} else {
						break;
					}
				} else {
					System.out.println(ANSI_RED + "Invalid input. Enter numeric amount." + ANSI_RESET);
					scanner.nextLine();
				}
			}

			// Perform transfer
			this.balance -= transferAmount;
			recipient.balance += transferAmount;

			String formattedAmount = String.format("%.2f", transferAmount);
			String timestamp = LocalDateTime.now(ZoneId.systemDefault()).format(Date_Time_Formatter);

			// Add statements
			this.statement.add(ANSI_RED
					+ String.format(getRowFormat(), timestamp, formattedAmount, "", this.getBalance()) + ANSI_RESET);
			recipient.statement.add(
					ANSI_GREEN + String.format(getRowFormat(), timestamp, "", formattedAmount, recipient.getBalance())
							+ ANSI_RESET);

			System.out.println(ANSI_GREEN + "₹" + formattedAmount + " transferred successfully to Account "
					+ recipient.getAccountNumber() + ANSI_RESET);
			System.out.println(ANSI_PURPLE + "Your updated balance: ₹" + this.getBalance() + ANSI_RESET);

			// Ask to continue
			System.out.print(ANSI_CYAN + "Do you want to make another transfer? (y/n): " + ANSI_RESET);
			char choice = scanner.next().charAt(0);
			scanner.nextLine(); // clear buffer

			if (choice != 'y' && choice != 'Y') {
				continueTransfer = false;
			}
		}
	}

	@Override
	public void viewAccountStatement() {
		System.out.println(ANSI_CYAN + "\t\t\tAccount Statement" + ANSI_RESET);
		System.out.println(ANSI_PURPLE + "Account Opening Balance : " + getMinimumBalance() + ANSI_RESET);
		System.out.printf(ANSI_YELLOW + getHeadFormat(), "Date | Time", "Withdrawals | Transfers(₹)", "Deposits(₹)",
				"Balance(₹)" + ANSI_RESET);
		statement.forEach(System.out::println);

	}

	@Override
	public void showHelp() {

		System.out.println("1. Account Username Change\n"
				+ "Go to Account Setting and click on Account username change option and Proceed to change.");
		System.out.println("2. Account Password Change\n"
				+ "Go to Account Setting and click on Account password change option and Proceed to change.");
		System.out.println("3. Account Debit card Pin Change\n"
				+ "Go to Account Setting and click on debit card pinchange option and Proceed to change.");

	}

	@Override
	public void updateAccountSettings() {

		System.out.println(ANSI_YELLOW + "\t\t\t\t\tUser Account Settings" + ANSI_RESET);
		boolean move = false;

		do {
			System.out.print(ANSI_CYAN + "Do you wish to change any account settings? ('y' for yes and 'n' for no): "
					+ ANSI_RESET);
			char decision = scanner.next().charAt(0);
			scanner.nextLine();

			if (decision == 'Y' || decision == 'y') {
				boolean validChoice = false;
				int option = 0;

				while (!validChoice) {
					System.out.println(ANSI_YELLOW + "\t\t\t\t\tChoose an Option" + ANSI_RESET);
					System.out.println(
							"1. Change Username\n2. Change Password\n3. Change Debit Card PIN\n4. Exit to Home Menu");
					System.out.print(ANSI_CYAN + "Enter your option (1 to 4): " + ANSI_RESET);

					if (scanner.hasNextInt()) {
						option = scanner.nextInt();
						scanner.nextLine();
						if (option >= 1 && option <= 4) {
							validChoice = true;
						} else {
							System.out.println(ANSI_RED + "Enter a number between 1 and 4." + ANSI_RESET);
						}
					} else {
						System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
						scanner.nextLine();
					}
				}

				switch (option) {
				case 1:
					while (true) {
						System.out.print(ANSI_CYAN + "Enter New Username: " + ANSI_RESET);
						String username = scanner.nextLine();
						if (username.matches("^(?=.*[0-9])(?=.*[!@#$%^&_+=])[a-z0-9!@#$%^&_+=]{8,16}$")) {
							setUserName(username);
							System.out.println(ANSI_GREEN + "Username updated: " + getUserName() + ANSI_RESET);
							break;
						} else {
							System.out.println(ANSI_RED + "Invalid username format." + ANSI_RESET);
						}
					}
					break;

				case 2:
					while (true) {
						System.out.print(ANSI_CYAN + "Enter New Password: " + ANSI_RESET);
						String password = scanner.nextLine();
						if (password.matches(
								"^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&_+=])[A-Za-z0-9!@#$%^&_+=]{8,16}$")) {
							setPassword(password);
							System.out.println(ANSI_GREEN + "Password updated." + getPassword() + ANSI_RESET);
							break;
						} else {
							System.out.println(ANSI_RED + "Invalid password format." + ANSI_RESET);
						}
					}
					break;

				case 3:
					while (true) {
						System.out.print(ANSI_CYAN + "Enter New 4-digit PIN: " + ANSI_RESET);
						if (scanner.hasNextInt()) {
							int pin = scanner.nextInt();
							scanner.nextLine();
							if (pin >= 1000 && pin <= 9999) {
								setCardPin(pin);
								System.out.println(ANSI_GREEN + "PIN updated successfully." + ANSI_RESET);
								break;
							} else {
								System.out.println(ANSI_RED + "PIN must be 4 digits." + ANSI_RESET);
							}
						} else {
							System.out.println(ANSI_RED + "Invalid input. Enter a 4-digit number." + ANSI_RESET);
							scanner.nextLine();
						}
					}
					break;

				case 4:
					move = true;
					break;
				}

			} else if (decision == 'N' || decision == 'n') {
				System.out.println("Returning to MainPage...");
				break;
			} else {
				System.out.println(ANSI_RED + "Only 'y' or 'n' are allowed." + ANSI_RESET);
			}
		} while (!move);

	}

}
