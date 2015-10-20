package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import common.Utility;

/**
 * Exercise 1: Caesar Cipher
 * 
 * @author Milton
 *
 */
public class CaesarCipher {
	/**
	 * Default constructor
	 */
	public CaesarCipher() {

	}

	/**
	 * This method decrypts the text by the given key using Caesar Cipher.
	 * 
	 * @param key
	 *            The decryption key, i.e. number of characters to shift
	 * @param encryptedTexts
	 *            The given encrypted text
	 * @return A decrypted text
	 */
	public List<String> decrypt(int key, List<String> encryptedTexts) {
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
	 * This method finds the encryption key used by Caesar Cipher.
	 * 
	 * @param encryptedTexts
	 * @return The encryption key used by Caesar Cipher
	 */
	public Character findEncryptionKey(List<String> encryptedTexts) {
		Character key = null;

		for (int i = (int) 'A'; i < (int) 'Z'; i++) {
			List<String> decryptedTexts = decrypt(i, encryptedTexts);
			Map<Character, Double> decryptedLettersFrequencies = letterFrequencies(decryptedTexts);
			if (Math.abs(decryptedLettersFrequencies.get((char) i)
					- Utility.getPubliclyKnownLettersFrequencies().get(Character.toLowerCase((char) i))) > 0.02) {
				// This is not the key
			}
		}

		return key;
	}

	/**
	 * This method counts the number of occurrences of each English letter. Note
	 * that the keys are all in capital letters.
	 * 
	 * @param texts
	 * @return A key value pair where key is the English letter and value is the
	 *         number of times the letter occurs
	 */
	public Map<Character, Integer> letterCounts(List<String> texts) {
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
	public Map<Character, Double> letterFrequencies(List<String> texts) {
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

	private Map<Character, Integer> initialisedLetterCounts() {
		Map<Character, Integer> initialisedLettersCounts = new TreeMap<Character, Integer>();
		for (int ascii = (int) 'A'; ascii <= (int) 'Z'; ascii++) {
			initialisedLettersCounts.put((char) ascii, (Integer) 0);
		}
		return initialisedLettersCounts;
	}

	private Map<Character, Double> initialisedLetterFrequencies() {
		Map<Character, Double> initialisedLettersFrequencies = new TreeMap<Character, Double>();
		for (int ascii = (int) 'A'; ascii <= (int) 'Z'; ascii++) {
			initialisedLettersFrequencies.put((char) ascii, (Double) 0.0);
		}
		return initialisedLettersFrequencies;
	}
}
