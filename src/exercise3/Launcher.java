package exercise3;

import java.io.File;

public class Launcher {
	public static void main(String[] args) {
		OneTimePad.encrypt("Every cloud has a silver lining",
				"6dc72fc595e35dcd38c05dca2a0d2dbd8e2df20b129b2cfa29ad17972922a2");
		OneTimePad.decrypt("28b14ab7ecc33ea157b539ea426c5e9def0d81627eed498809c17ef9404cc5",
				"6dc72fc595e35dcd38c05dca2a0d2dbd8e2df20b129b2cfa29ad17972922a2");

		String textFileDirectory = System.getProperty("user.dir") + "\\src\\exercise3\\text\\";
		File textFolder = new File(textFileDirectory);
		if (!textFolder.exists()) {
			textFolder.mkdir();
		}

		OneTimePad.generateOneTimePad(textFileDirectory + "one time pad.txt");
		OneTimePad.mainProgram();
		OneTimePad.guess();
	}
}
