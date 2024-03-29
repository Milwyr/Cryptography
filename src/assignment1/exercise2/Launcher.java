package assignment1.exercise2;

import java.io.File;
import java.util.List;

import assignment1.common.Utility;

public class Launcher {
	private static String textFilesDirectory = "src\\assignment1\\exercise2\\text\\";
	private static String key = "itisasimpleformofpolyalphabeticsubstitutionthatiseasytoimplement";

	public static void main(String[] args) {
		File textFolder = new File(textFilesDirectory);
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		encryptPlainTextFile();
		decryptTextFile();
		decryptTextFileEncryptedByFeng();
	}

	/**
	 * This method encrypts the given plain text file and outputs the encrypted
	 * text to a new file.
	 */
	private static void encryptPlainTextFile() {
		String importPath = textFilesDirectory + "plain text.txt";
		String exportPath = textFilesDirectory + "encrypted by milton.txt";

		List<String> plainTexts = Utility.importFile(importPath);
		List<String> encryptedTexts = VigenereCipher.encrypt(key, plainTexts);
		Utility.writeToFile(exportPath, encryptedTexts);
	}

	/**
	 * This method reads the encrypted text and decrypts with the same key as
	 * the encryption key. The recovered text is expected to be the same as the
	 * plain text before the encryption is taken place.
	 */
	private static void decryptTextFile() {
		String importPath = textFilesDirectory + "encrypted by milton.txt";
		String exportPath = textFilesDirectory + "decrypted text for milton's file.txt";

		List<String> encryptedTexts = Utility.importFile(importPath);
		List<String> decryptedTexts = VigenereCipher.decrypt(key, encryptedTexts);
		Utility.writeToFile(exportPath, decryptedTexts);
	}

	/**
	 * This method reads the text file encrypted by Feng. It then finds the key
	 * length of the decryption key, deduces the decryption key by brute forcing
	 * by Caesar Cipher and finally decrypt the message by the decryption key.
	 */
	private static void decryptTextFileEncryptedByFeng() {
		String importPath = textFilesDirectory + "encrypted by feng.txt";
		String exportPath = textFilesDirectory + "decrypted text for feng's file.txt";

		List<String> encryptedTexts = Utility.importFile(importPath);
		int keyLength = VigenereCipher.findKeyLength(encryptedTexts);
		String key = VigenereCipher.findDecryptionKey(keyLength, encryptedTexts);
		System.out.println("Decryption key is: " + key);
		Utility.writeToFile(exportPath, VigenereCipher.decrypt(key, encryptedTexts));
	}
}