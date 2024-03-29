package assignment1.exercise3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Launcher {
	private static final String ONE_TIME_PAD_TEXT_FILE_PATH = "src\\assignment1\\exercise3\\text\\one time pad.txt";
	private static final String ENCRYPTED_TEXT_FILE_PATH = "src\\assignment1\\exercise3\\text\\encrypted by one time pad.txt";
	private static final String DECRYPTED_TEXT_FILE_PATH = "src\\assignment1\\exercise3\\text\\decrypted by one time pad.txt";

	public static void main(String[] args) {
		OneTimePad.encrypt("Every cloud has a silver lining",
				"6dc72fc595e35dcd38c05dca2a0d2dbd8e2df20b129b2cfa29ad17972922a2", ENCRYPTED_TEXT_FILE_PATH);
		OneTimePad.decrypt("28b14ab7ecc33ea157b539ea426c5e9def0d81627eed498809c17ef9404cc5",
				"6dc72fc595e35dcd38c05dca2a0d2dbd8e2df20b129b2cfa29ad17972922a2", DECRYPTED_TEXT_FILE_PATH);

		generateOneTimePad();

		String oneTimePad = importOneTimePad(ONE_TIME_PAD_TEXT_FILE_PATH);
		OneTimePad.encrypt(
				"Sunderland moved off the bottom of the Premier League table with a record sixth consecutive win over north-east rivals and fellow strugglers Newcastle, who had Fabricio Coloccini sent off.",
				oneTimePad, ENCRYPTED_TEXT_FILE_PATH);
		OneTimePad.decrypt(importOneTimePad(ENCRYPTED_TEXT_FILE_PATH), oneTimePad, DECRYPTED_TEXT_FILE_PATH);

		OneTimePad.mainProgram();
		OneTimePad.guess();
		System.out.println("Finish");
	}

	private static String importOneTimePad(String path) {
		StringBuilder builder = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String text = reader.readLine();

			while (text != null) {
				builder.append(text);
				text = reader.readLine();
			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		return builder.toString();
	}

	private static void generateOneTimePad() {
		File textFolder = new File(ONE_TIME_PAD_TEXT_FILE_PATH);
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		OneTimePad.generateOneTimePad(ONE_TIME_PAD_TEXT_FILE_PATH);
	}
}
