package exercise2;

import java.util.ArrayList;
import java.util.List;

import exercise1.CaesarCipher;

/**
 * Exercise 2: Vigenère Cipher
 * 
 * @author Milton
 *
 */
public class VigenereCipher {
	/**
	 * This method encrypts the message with a given key.
	 * 
	 * @param key
	 *            The key to encrypt the message
	 * @param plainTexts
	 * @return The encrypted message
	 * @throws IOException
	 */
	public static List<String> encrypt(String key, List<String> plainTexts) {
		List<String> encryptedTexts = new ArrayList<String>();

		for (String plainText : plainTexts) {
			String encryptedText = "";

			int keyPosition = 0;
			for (char ch : plainText.toCharArray()) {
				char lowercase = Character.toLowerCase(ch);

				if (Character.isLowerCase(lowercase)) {
					int letterIndex = lowercase + key.charAt(keyPosition) - (2 * 'a');
					char encryptedLetter = (char) ((int) letterIndex % 26 + 'a');

					if (encryptedLetter > 'z') {
						encryptedLetter = (char) (encryptedLetter - 26);
					} else if (encryptedLetter < 'a') {
						encryptedLetter = (char) (encryptedLetter + 26);
					}

					encryptedText += Character.toString(encryptedLetter);
				} else {
					encryptedText += Character.toString(ch);
				}

				keyPosition++;

				if (keyPosition >= key.length()) {
					keyPosition = 0;
				}
			}

			encryptedTexts.add(encryptedText);
		}
		return encryptedTexts;
	}

	/**
	 * This method decrypts the message with a given key. Note that this
	 * implemented decryption is done by using a reverse key to encrypt, on the
	 * other words 'a' -> '{', 'b' -> 'z', 'c' -> 'y'
	 * 
	 * @param key
	 *            The key to decrypt the message
	 * @param encryptedTexts
	 *            The encrypted message
	 * @return The decrypted message
	 */
	public static List<String> decrypt(String key, List<String> encryptedTexts) {
		String decryptionKey = "";
		for (char ch : key.toCharArray()) {
			char lowercase = Character.toLowerCase(ch);
			int ascii = (int) 'a' + 26 - ((int) lowercase - (int) 'a');
			decryptionKey += Character.toString((char) ascii);
		}
		return encrypt(decryptionKey, encryptedTexts);
	}

	/**
	 * This method first finds the cipher text to analyse. It then applies
	 * Caesar Cipher in a brute force approach to find the decryption key
	 * (number of letters to shift). Finally, it converts the index to English
	 * letter based on the pattern 0->'a', 1->'b', 2->'c'.
	 * 
	 * @param keyLength
	 * @param encryptedTexts
	 * @return
	 */
	public static String findDecryptionKey(int keyLength, List<String> encryptedTexts) {
		String decryptionKey = "";

		for (int keyIndex = 0; keyIndex < keyLength; keyIndex++) {
			List<String> cipherToAnalyse = cipherToAnalyse(keyIndex, keyLength, encryptedTexts);

			int shiftIndex = CaesarCipher.findDecryptionKey(cipherToAnalyse);
			
			// Build up the decryption key if a decryption key is found
			if (shiftIndex != -1) {
				decryptionKey += (char) (shiftIndex + (int) 'a');
			}
		}

		return decryptionKey;
	}

	/**
	 * This method returns the appropriate cipher text. In Vigenère Cipher, the
	 * cipher text to analyse is skipped for keyLength's letter. For example, if
	 * you have a text 'newcastle' and key length is 3, the cipher texts to
	 * return are 'nct', 'eal', 'wse' for key index = 0,1,2 respectively.
	 * 
	 * @param keyIndex
	 *            The index of key in the loop
	 * @param keyLength
	 *            Key length for the decryption key
	 * @param encryptedTexts
	 * @return The appropriate cipher text
	 */
	private static List<String> cipherToAnalyse(int keyIndex, int keyLength, List<String> encryptedTexts) {
		List<String> cipherToAnalyse = new ArrayList<String>();
		String encryptedText = encryptedTexts.get(0);
		String output = "";

		for (int position = 0; position < encryptedText.length(); position++) {
			if (position % keyLength == keyIndex) {
				output += encryptedText.substring(position, position + 1);
			}
		}
		cipherToAnalyse.add(output);

		return cipherToAnalyse;
	}
}