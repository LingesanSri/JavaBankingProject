package com.residentevil.banking.utility;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class AccountUtility {
	private static final String BANK_CODE = "4000";
	private static final String BRANCH_CODE = "4545";


	private AccountUtility() {

	}

	public static String generateCardNumber() {

		int[] cardNumber = new int[16];

		Random random = new Random();

		for (int i = 0; i < 15; i++) {

			cardNumber[i] = random.nextInt(10);
		}

		int sum = 0;

		for (int i = 0; i < 15; i++) {

			int temp = cardNumber[14 - i];

			if (i % 2 == 0) {

				temp *= 2;

				if (temp > 9) {

					temp -= 9;

				}
			}
			sum += temp;
		}

		cardNumber[15] = (10 - (sum % 10)) % 10;

		StringBuilder card = new StringBuilder();

		for (int i : cardNumber) {

			card.append(i);
		}

		return card.toString();

	}

	public static int generateCvvNumber() {

		SecureRandom secureRandom = new SecureRandom();
		int cvvNumber = secureRandom.nextInt(900) + 100;
		return cvvNumber;

	}

	public static int generateCardPin() {

		SecureRandom secureRandom = new SecureRandom();
		int cardPin = secureRandom.nextInt(9000) + 1000;
		return cardPin;
	}

	public static String generateUsername(String name, Map<String, Integer> userNameData) {

		int count = userNameData.getOrDefault(name, 0) + 1;
		userNameData.put(name, count);
		return String.format("%s%03d", name.replaceAll("\\s+", "").toLowerCase(), count);
	}

	public static String generatePassword() {

		int limit = 10;
		SecureRandom random = new SecureRandom();
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String numeric = "1234567890";
		String specialChar = "!@$%^&*()_-+=?><";
		String addAll = upperCase + lowerCase + specialChar + numeric;
		List<Character> passwordData = new ArrayList<>();
		
		passwordData.add(upperCase.charAt(random.nextInt(upperCase.length())));
		passwordData.add(lowerCase.charAt(random.nextInt(lowerCase.length())));
		passwordData.add(numeric.charAt(random.nextInt(numeric.length())));
		passwordData.add(specialChar.charAt(random.nextInt(specialChar.length())));

		for (int i = 4; i < limit; i++) {
			passwordData.add(addAll.charAt(random.nextInt(addAll.length())));

		}

		Collections.shuffle(passwordData);
		StringBuilder pass = new StringBuilder();

		for (Character character : passwordData) {

			pass.append(character);
		}

		return pass.toString();

	}

	public static String generateAccountNumber() {

		Random random = new Random();

		String uniqueCode = String.valueOf(random.nextInt(9000) + 1000);

		return BANK_CODE + BRANCH_CODE + uniqueCode;
	}

}
