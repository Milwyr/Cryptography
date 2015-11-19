package assignment2.exercise3;

import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * This launcher program implements the Diffie Hellman key exchange. In this
 * program, sender refers to Alice, receiver refers to Bob.
 * 
 * @author Milton
 *
 */
public class Launcher {

	public static void main(String[] args) {
		Sender sender = new Sender();
		Receiver receiver = new Receiver();

		SenderMessage senderMessage = sender.computeMessage();
		BigInteger receiverMessage = receiver.computeMessage(senderMessage);
		BigInteger keyA = sender.computePublicKey(receiverMessage);
		BigInteger keyB = receiver.computePublicKey(senderMessage);

		String currentDirectory = System.getProperty("user.dir") + "\\src\\assignment2\\exercise3\\";
		try (PrintWriter writer = new PrintWriter(currentDirectory + "transcript.txt", "UTF-16")) {
			writer.println("Secret A = " + sender.getSecretNumber());
			writer.println();
			writer.println("Secret B = " + receiver.getSecretNumber());
			writer.println();
			writer.println("msg1.modulus = " + senderMessage.getModulus());
			writer.println();
			writer.println("msg1.base = " + senderMessage.getBase());
			writer.println();
			writer.println("msg1.valueA = " + senderMessage.getValue());
			writer.println();
			writer.println("msg2.valueB = " + receiverMessage);
			writer.println();
			writer.println("key A = " + keyA);
			writer.println();
			writer.print("key B = " + keyB);
		} catch (Exception ex) {

		}

		System.out.println("finish");
	}
}