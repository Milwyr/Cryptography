package assignment1.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import assignment1.common.Utility;

import java.util.TreeMap;

/**
 * Exercise 1: Caesar Cipher
 * 
 * @author Milton
 *
 */
public class CaesarCipher {
	/**
	 * This method decrypts the text by the given key using Caesar Cipher.
	 * 
	 * @param key
	 *            The decryption key, i.e. number of characters to shift
	 * @param encryptedTexts
	 *            The given encrypted text
	 * @return A decrypted text
	 */
	public static List<String> decrypt(int key, List<String> encryptedTexts) {
		List<String> decryptedTexts = new ArrayList<String>();
		for (String encryptedText : encryptedTexts) {
			String decryptedText = "";

			for (char ch : encryptedText.toCharArray()) {
				char lowerCase = Character.toLowerCase(ch);

				if (lowerCase >= 'a' && lowerCase <= 'z') {
					char decryptedCharacter = (char) ((int) ch - key % 26);

					if (decryptedCharacter > 'z') {
						decryptedCharacter = (char) (decryptedCharacter - 26);
					} else if (decryptedCharacter < 'a') {
						decryptedCharacter = (char) (decryptedCharacter + 26);
					}

					decryptedText += Character.toString(decryptedCharacter);
				}
				// Copy the encrypted text directly if it is not an English
				// letter
				else {
					decryptedText += Character.toString(ch);
				}
			}
			decryptedTexts.add(decryptedText);
		}

		return decryptedTexts;
	}

	/**
	 * This method finds the Caesar Cipher decryption key, i.e. number of
	 * letters shifted. For example, the key is 0 for 'A'->'A', 1 for 'B'->'A'.
	 * 
	 * @param encryptedTexts
	 * @return The encryption key used by Caesar Cipher, -1 if not found
	 */
	public static int findDecryptionKey(List<String> encryptedTexts) {
		for (int i = 0; i < 25; i++) {
			List<String> decryptedTexts = decrypt(i, encryptedTexts);
			if (isSuccessfulDecryption(decryptedTexts)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * This method analyses the frequency of the given texts, and compares it
	 * with the publicly known result. If they match, this decrypted message is
	 * likely to be readable, i.e. a successful decryption.
	 * 
	 * @param decryptedTexts
	 *            The given decrypted texts
	 * @return true if the decryption seems to be successful
	 */
	private static boolean isSuccessfulDecryption(List<String> decryptedTexts) {
		for (int ascii = (int) 'A'; ascii < (int) 'Z'; ascii++) {
			double decryptedLettersFrequency = letterFrequencies(decryptedTexts).get((char) ascii);
			double publiclyKnownLettersFrequency = Utility.getPubliclyKnownLettersFrequencies()
					.get(Character.toLowerCase((char) ascii));

			if (decryptedLettersFrequency - publiclyKnownLettersFrequency > 0.03) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method counts the number of occurrences of each English letter. Note
	 * that the keys are all in capital letters.
	 * 
	 * @param texts
	 * @return A key value pair where key is the English letter and value is the
	 *         number of times the letter occurs
	 */
	public static Map<Character, Integer> letterCounts(List<String> texts) {
		Map<Character, Integer> counts = initialisedLetterCounts();

		for (String text : texts) {
			for (char ch : text.toCharArray()) {
				char uppercase = Character.toUpperCase(ch);

				// Increment the frequency counter by one if the character is an
				// English letter
				if (uppercase >= 'A' && uppercase <= 'Z') {
					Integer count = counts.get(uppercase);
					count++;
					counts.put(uppercase, count);
				}
			}
		}

		return counts;
	}

	/**
	 * This method analyses the frequency of each English letter. Note that the
	 * keys are all in capital letters.
	 * 
	 * @param texts
	 * @return A key value pair where key is the English letter and value is the
	 *         frequency of occurrence of the letter
	 */
	public static Map<Character, Double> letterFrequencies(List<String> texts) {
		Map<Character, Double> frequencies = initialisedLetterFrequencies();
		Map<Character, Integer> lettersCounts = letterCounts(texts);

		// Count the total number of English alphabets
		int total = 0;
		for (Integer occurrence : lettersCounts.values()) {
			total += (int) occurrence;
		}

		// Calculate the percentage of occurrence of each English letter
		for (Entry<Character, Integer> pair : lettersCounts.entrySet()) {
			double percentage = ((double) pair.getValue() / (double) total);
			frequencies.put(pair.getKey(), (Double) percentage);
		}

		return frequencies;
	}

	private static Map<Character, Integer> initialisedLetterCounts() {
		Map<Character, Integer> initialisedLettersCounts = new TreeMap<Character, Integer>();
		for (int ascii = (int) 'A'; ascii <= (int) 'Z'; ascii++) {
			initialisedLettersCounts.put((char) ascii, (Integer) 0);
		}
		return initialisedLettersCounts;
	}

	private static Map<Character, Double> initialisedLetterFrequencies() {
		Map<Character, Double> initialisedLettersFrequencies = new TreeMap<Character, Double>();
		for (int ascii = (int) 'A'; ascii <= (int) 'Z'; ascii++) {
			initialisedLettersFrequencies.put((char) ascii, (Double) 0.0);
		}
		return initialisedLettersFrequencies;
	}
}
