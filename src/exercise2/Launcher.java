package exercise2;

import java.io.File;
import java.util.List;

import common.Utility;

public class Launcher {
	private static String textFilesDirectory = System.getProperty("user.dir") + "\\src\\exercise2\\text\\";
	private static String key = "itisasimpleformofpolyalphabeticsubstitutionthatiseasytoimplement";

	public static void main(String[] args) {
		File textFolder = new File(textFilesDirectory);
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		encryptPlainTextFile();
		decryptTextFile();
		decryptTextFileEncryptedByFeng();

		System.out.println("done");
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
		String exportPath = textFilesDirectory + "decrypted by milton.txt";

		List<String> encryptedTexts = Utility.importFile(importPath);
		List<String> decryptedTexts = VigenereCipher.decrypt(key, encryptedTexts);
		Utility.writeToFile(exportPath, decryptedTexts);
	}

	private static void decryptTextFileEncryptedByFeng() {
		String importPath = textFilesDirectory + "encrypted by feng.txt";
		
		List<String> encryptedTexts = Utility.importFile(importPath);
		String key = VigenereCipher.findDecryptionKey(5, encryptedTexts);
	}
}