package exercise3;

public class Launcher {
	public static void main(String[] args) {
		String inputText = "Every cloud has a silver lining";
		String hexadecimalKey = "6dc72fc595e35dcd38c05dca2a0d2dbd8e2df20b129b2cfa29ad17972922a2";
		String encryptedText = OneTimePad.encrypt(inputText, hexadecimalKey);

		if (encryptedText != null) {
			System.out.println("Input text: " + inputText);
			System.out.println("Hexadecimal key: " + hexadecimalKey);
			System.out.println("Encrypted text: " + encryptedText);
		} else {
			System.out.println("One of the inputs is invalid.");
		}
	}
}
