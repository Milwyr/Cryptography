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

	public static String findDecryptionKey(int keyLength, List<String> encryptedTexts) {
		String decryptionKey = "";

		for (int keyIndex = 0; keyIndex < keyLength; keyIndex++) {
			int shiftIndex = CaesarCipher.findDecryptionKey(encryptedTexts);
			if (shiftIndex != -1) {
				decryptionKey += (char)(shiftIndex + (int)'A');
			}
		}
		
		return decryptionKey;
	}
}