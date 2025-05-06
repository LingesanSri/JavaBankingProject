package com.residentevil.banking.main;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.residentevil.banking.factory.BankAccountFactory;
import com.residentevil.banking.model.BankAccount;

public class Main {

	static boolean loginCheck = false;
	private static Scanner scanner = new Scanner(System.in);
	public static Map<String, BankAccount> customers = new HashMap<>();
	static BankAccount currentAccount;

	public static void main(String[] args) {
		customers.put("leon", new BankAccountFactory().createAccount("Leon.S.Kennedy", 963258740));
		customers.put("ashley", new BankAccountFactory().createAccount("Ashley Graham", 987456210));
		String line = "__________________________________________________________________________________________________________";
		System.out.print(
				BankAccount.ANSI_YELLOW + "\t\t\t****Welcome to Resident Evil Bank****\n" + BankAccount.ANSI_RESET);
		System.out.print("\t\t\t_____________________________________\n");
		System.out.print(BankAccount.ANSI_YELLOW + "\t\t\t\t****Bank Home Page****\n" + BankAccount.ANSI_RESET);
		System.out.print("\t\t\t\t______________________\n");
		System.out.println(
				"1. Create New Bank Account\n2. Login to your Account\n3. Account Details\n4. Debit Card Details\n5."
						+ " Deposit\n6. Withdrawal\n7. Amount Transfer\n8. Account Statements\n9. Account Help\n10. Account Settings\n11. Logout of your Account\n12. Exit Banking Portal");

		do {

			System.out.println(line);
			int accountOption = 0;

			try {
				while (true) {
					System.out.print(BankAccount.ANSI_CYAN
							+ "Enter the option in numeric keys from available options (1 to 12) = "
							+ BankAccount.ANSI_RESET);

					if (scanner.hasNextInt()) {
						accountOption = scanner.nextInt();
						scanner.nextLine();
						if (accountOption >= 1 && accountOption <= 12) {
							break;
						} else {
							System.out.println(BankAccount.ANSI_RED + "Please enter a number between 1 and 12."
									+ BankAccount.ANSI_RESET);
						}
					} else {
						System.out.println(
								BankAccount.ANSI_RED + "Please, Enter the numerics in the mentioned range of values..."
										+ BankAccount.ANSI_RESET);
						scanner.nextLine();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println(BankAccount.ANSI_RED
						+ "Please, Enter the numerics in the mentioned range of values..." + BankAccount.ANSI_RESET);
				scanner.nextLine();
				continue;
			}
			switch (accountOption) {

			case 1:

				BankAccountFactory accountFactory = new BankAccountFactory();

				String name = getValidName();
				long phoneNumber = getValidPhoneNumber();

				currentAccount = accountFactory.createAccount(name, phoneNumber);

				System.out.println("Dear User, your account has been successfully created!");
				System.out.println("Username, password, and debit card PIN have been auto-generated.");
				System.out.println("If you wish to change them, visit the user portal help section.");
				System.out.println(line);
				customers.put("ada", currentAccount);

				break;

			case 2:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}

				if (loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have already logged in..." + BankAccount.ANSI_RESET);
					break;
				}
				boolean isLoggedIn = currentAccount.userLogin("", "");
				if (isLoggedIn)
					loginCheck = true;
				else
					loginCheck = false;
				break;

			case 3:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}
				if (!loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have to do login first..." + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.viewAccountDetails();
				break;

			case 4:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}
				if (!loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have to do login first..." + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.viewDebitCardDetails();
				break;

			case 5:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}
				if (!loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have to do login first..." + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.deposit(0);
				break;

			case 6:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}
				if (!loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have to do login first..." + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.withdraw(0);
				break;

			case 7:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}
				if (!loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have to do login first..." + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.transferFunds(0, customers);
				break;

			case 8:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}
				if (!loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have to do login first..." + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.viewAccountStatement();
				break;

			case 9:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.showHelp();
				break;

			case 10:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}

				currentAccount.updateAccountSettings();
				break;

			case 11:

				if (currentAccount == null) {
					System.out
							.println(BankAccount.ANSI_RED + "Please, Create an account first" + BankAccount.ANSI_RESET);
					break;
				}
				if (!loginCheck) {
					System.out.println(BankAccount.ANSI_RED + "You have to do login first..." + BankAccount.ANSI_RESET);
					break;
				}

				boolean isloggedOut = currentAccount.userLogout('0');
				if (isloggedOut)
					loginCheck = false;
				else
					loginCheck = true;
				break;

			case 12:

				System.out.println(BankAccount.ANSI_YELLOW + "\t\t\t\t\t***Thanks for banking with us***"
						+ BankAccount.ANSI_RESET);
				System.exit(0);

			default:

				System.out.println(BankAccount.ANSI_RED + "Invalid option" + BankAccount.ANSI_RESET);
				break;

			}
		} while (true);

	}

	public static String getValidName() {
		String name = "";

		while (true) {
			System.out.print(BankAccount.ANSI_CYAN
					+ "Enter Full Name (Name should be between 8 and 20 characters, starting with an uppercase letter): "
					+ BankAccount.ANSI_RESET);
			try {
				name = scanner.nextLine();
				if (name.matches("^[A-Z][a-z]+( [A-Z][a-z]+)*$") && name.length() >= 8 && name.length() <= 20) {
					break;
				} else {
					System.out.println("Invalid name format. Please follow the rules.");
				}
			}

			catch (Exception e) {

			}
		}
		return name;

	}

	public static long getValidPhoneNumber() {
		long phoneNumber = 0;

		while (true) {

			System.out.print("Enter Phone Number (10 digits): ");
			try {
				phoneNumber = scanner.nextLong();
				scanner.nextLine(); // Consume the newline character
				if (String.valueOf(phoneNumber).length() == 10) {
					break;
				} else {
					System.out.println(BankAccount.ANSI_CYAN + "Phone number should be exactly 10 digits."
							+ BankAccount.ANSI_RESET);
				}
			} catch (Exception e) {
				scanner.nextLine(); // Clear invalid input
				System.out.println("Invalid phone number. Please enter digits only.");
			}
		}

		return phoneNumber;
	}
}
