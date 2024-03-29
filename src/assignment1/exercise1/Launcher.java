package assignment1.exercise1;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import assignment1.common.Utility;

public class Launcher {
	private static String textFilesDirectory = System.getProperty("user.dir") + "\\src\\assignment1\\exercise1\\text\\";

	public static void main(String[] args) {
		File textFolder = new File(textFilesDirectory);
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		analysePlainTextFile();
		analyseEncryptedTextFile();
	}

	/**
	 * This method imports the given plain text file, and prints the statistics
	 * on the English letters in the console.
	 */
	private static void analysePlainTextFile() {
		String importFilePath = textFilesDirectory + "plain text.txt";
		List<String> plainTexts = Utility.importFile(importFilePath);
		System.out.println("Plain text frequency analysis");
		printLettersFrequencies(CaesarCipher.letterCounts(plainTexts), CaesarCipher.letterFrequencies(plainTexts));
	}

	/**
	 * This method imports the given encrypted text file, finds the decryption
	 * key and outputs the decyrpted text to a new file.
	 */
	private static void analyseEncryptedTextFile() {
		String importFilePath = textFilesDirectory + "encrypted by feng.txt";
		String exportFilePath = textFilesDirectory + "decrypted by milton.txt";
		
		List<String> encryptedTexts = Utility.importFile(importFilePath);
		List<String> decryptedTexts = CaesarCipher.decrypt(4, encryptedTexts);
		Utility.writeToFile(exportFilePath, decryptedTexts);
		System.out.println("\n\nDecryption key is " + CaesarCipher.findDecryptionKey(encryptedTexts));
	}

	/**
	 * This method prints number and frequency of each English letter, as well
	 * as total number of English letters, in the console. Also, this method
	 * outputs the decryption in the console.
	 * 
	 * @param counts
	 *            A key value pair where key is the English letter and value is
	 *            the number of times the letter occurs
	 * @param frequencies
	 *            A key value pair where key is the English letter and value is
	 *            the frequency of occurrence of the letter
	 */
	private static void printLettersFrequencies(Map<Character, Integer> counts, Map<Character, Double> frequencies) {
		int total = 0;
		for (Entry<Character, Integer> pair : counts.entrySet()) {
			total += pair.getValue();
			Double frequencyPercentage = frequencies.get(pair.getKey()) * 100;
			System.out.println(pair.getKey() + ": " + pair.getValue() + " letters - "
					+ String.format("%.3f", frequencyPercentage) + "%");
		}
		System.out.print("This file contains " + total + " English letters.");

	}
}
