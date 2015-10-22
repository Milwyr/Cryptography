package exercise3;

import java.util.Random;

public class OneTimePad {
	/**
	 * This method encrypts the given plain text with a generated key.
	 * 
	 * @param plainText
	 *            A plain text
	 * @return The encrypted text with a generated key
	 */
	public static String encrypt(String plainText) {
		return encrypt(plainText, randomKey(plainText.length()));
	}

	/**
	 * This method encrypts the given plain text with the given key.
	 * 
	 * @param plainText
	 *            A plain text
	 * @param key
	 *            A hexadecimal key
	 * @return The encrypted text, null if either the key is invalid
	 */
	public static String encrypt(String plainText, String key) {
		String binaryPlainText = textToBinary(plainText);
		String binaryKey = hexadecimalToBinary(key);

		if (binaryPlainText != null && binaryKey != null) {
			return binaryToHexidecimal(xor(binaryPlainText, binaryKey));
		}
		return null;
	}

	/**
	 * This method reads a binary and converts it to a hexadecimal string.
	 * 
	 * @param input
	 *            A binary string
	 * @return The hexadecimal value of the input binary string
	 */
	public static String binaryToHexidecimal(String input) {
		input = input.replace(" ", "");
		if (input.length() % 4 == 0) {
			String output = "";
			for (int i = 0; i < input.length(); i = i + 4) {
				int temp = Integer.parseInt(input.substring(i, i + 4), 2);
				output += Integer.toString(temp, 16);
			}
			return output;
		}
		return null;
	}

	/**
	 * This method reads a hexadecimal and converts it to a binary string.
	 * 
	 * @param input
	 *            A hexadecimal string
	 * @return The binary of the input string
	 */
	public static String hexadecimalToBinary(String input) {
		String output = "";

		for (int i = 0; i < input.length(); i++) {
			int decimal = Integer.parseInt(input.substring(i, i + 1), 16);
			String temp = Integer.toBinaryString(decimal);
			output += String.format("%4s", temp).replace(' ', '0');
		}

		return output;
	}

	/**
	 * This method reads a text and converts it to a binary string.
	 * 
	 * @param input
	 *            A string
	 * @return The binary of the input string
	 */
	public static String textToBinary(String input) {
		String output = "";

		for (int i = 0; i < input.length(); i++) {
			// Binary string of each input character
			String temp = Integer.toBinaryString((int) input.charAt(i));

			// Add a leading zero for every binary string
			output += String.format("%8s", temp).replace(' ', '0');
		}

		return output;
	}

	/**
	 * This method takes an exclusive or operation on the two binary strings.
	 * 
	 * @param b1
	 *            A binary string
	 * @param b2
	 *            A binary string
	 * @return The result after an exclusive or operation on the two binary
	 *         strings
	 */
	public static String xor(String b1, String b2) {
		if (b1.length() == b2.length()) {
			String output = "";
			for (int i = 0; i < b1.length(); i++) {
				output += Integer.toString((int) b1.charAt(i) ^ (int) b2.charAt(i));
			}
			return output;
		}
		return null;
	}

	/**
	 * This method generates key with the given key length.
	 * 
	 * @param length
	 *            Key length
	 * @return A key with a combination of lowercase English letters and numbers
	 */
	private static String randomKey(int length) {
		String key = "";
		String choice = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			key += choice.charAt(random.nextInt(length));
		}
		return key;
	}
}