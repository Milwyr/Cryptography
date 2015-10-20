package exercise1;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import common.Utility;

public class Launcher {
	public static void main(String[] args) {
		String textFilesDirectory = System.getProperty("user.dir") + "\\src\\exercise1\\text\\";
		
		File textFolder = new File(textFilesDirectory);
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		CaesarCipher c1 = new CaesarCipher();
		String importFilePath = textFilesDirectory + "plain text.txt";
		List<String> plainTexts = Utility.importFile(importFilePath);
		System.out.println("Plain text frequency analysis");
		printLettersFrequencies(c1.letterCounts(plainTexts), c1.letterFrequencies(plainTexts));
		
		CaesarCipher c2 = new CaesarCipher();
		importFilePath = textFilesDirectory + "encrypted by feng.txt";
		List<String> encryptedTexts = Utility.importFile(importFilePath);
		List<String> decryptedTexts = c2.decrypt(4, encryptedTexts);
		String exportFilePath = textFilesDirectory + "decrypted by milton.txt";
		Utility.writeToFile(exportFilePath, decryptedTexts);
	}

	private static void printLettersFrequencies(Map<Character, Integer> counts, Map<Character, Double> frequencies) {
		for (Entry<Character, Integer> pair : counts.entrySet()) {
			Double frequencyPercentage = frequencies.get(pair.getKey()) * 100;
			System.out.println(pair.getKey()+ ": " + pair.getValue() + " letters - " + String.format("%.3f", frequencyPercentage) + "%");
		}
	}
}
