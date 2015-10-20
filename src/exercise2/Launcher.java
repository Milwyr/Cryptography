package exercise2;

import java.io.File;
import java.util.List;

import common.Utility;

public class Launcher {
	public static void main(String[] args) {
		String textFilesDirectory = System.getProperty("user.dir") + "\\src\\exercise2\\text\\";
		String key = "itisasimpleformofpolyalphabeticsubstitutionthatiseasytoimplement";

		File textFolder = new File(textFilesDirectory);
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		VigenereCipher vc = new VigenereCipher();
		
		String importPath = textFilesDirectory + "plain text.txt";
		List<String> plainTexts = Utility.importFile(importPath);

		String exportPath = textFilesDirectory + "encrypted by milton.txt";
		List<String> encryptedTexts = vc.encrypt(key, plainTexts);
		Utility.writeToFile(exportPath, encryptedTexts);
		
		exportPath = textFilesDirectory + "decrypted by milton.txt";
		List<String> decryptedTexts = vc.decrypt(key, encryptedTexts);
		Utility.writeToFile(exportPath, decryptedTexts);
		
		System.out.println("done");
	}
}