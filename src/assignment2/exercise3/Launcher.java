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
	private static final String currentDirectory = "src\\assignment2\\exercise3\\";
	private static Sender alice = new Sender();
	private static Receiver bob = new Receiver();

	public static void main(String[] args) {
		// Question 1: Only Alice and Bob
		SenderMessage aliceMessage = alice.computeMessage();
		BigInteger bobMessage = bob.computeMessage(aliceMessage);
		BigInteger keyA = alice.computeSessionKey(bobMessage);
		BigInteger keyB = bob.computeSessionKey(aliceMessage);

		try (PrintWriter writer = new PrintWriter(currentDirectory + "transcript.txt", "UTF-16")) {
			writer.println("Secret A = " + alice.getSecretNumber());
			writer.println();
			writer.println("Secret B = " + bob.getSecretNumber());
			writer.println();
			writer.println("msg1.modulus = " + aliceMessage.getModulus());
			writer.println();
			writer.println("msg1.base = " + aliceMessage.getBase());
			writer.println();
			writer.println("msg1.valueA = " + aliceMessage.getValue());
			writer.println();
			writer.println("msg2.valueB = " + bobMessage);
			writer.println();
			writer.println("key A = " + keyA);
			writer.println();
			writer.print("key B = " + keyB);
		} catch (Exception ex) {

		}

		// Question 4: Eve attacks the communication between Alice and Bob
		aliceMessage = alice.computeMessage();
		Attacker eve = new Attacker();
		SenderMessage eveSenderMessage = eve.computeSenderMessage(aliceMessage);
		bobMessage = bob.computeMessage(eveSenderMessage);
		BigInteger eveReceiverMessage = eve.computeReceiverMessage(eveSenderMessage);

		keyA = alice.computeSessionKey(eveReceiverMessage);
		BigInteger keyE = eve.computeSenderSessionKey(aliceMessage);
		try (PrintWriter writer = new PrintWriter(currentDirectory + "transcript (Alice and Eve).txt", "UTF-16")) {
			writer.println("Secret A = " + alice.getSecretNumber());
			writer.println();
			writer.println("Secret E = " + eve.getSenderSecretNumber());
			writer.println();
			writer.println("msg1.modulus = " + aliceMessage.getModulus());
			writer.println();
			writer.println("msg1.base = " + aliceMessage.getBase());
			writer.println();
			writer.println("msg1.valueA = " + eveSenderMessage.getValue());
			writer.println();
			writer.println("msg2.valueB = " + eveReceiverMessage);
			writer.println();
			writer.println("key A (between A and E) = " + keyA);
			writer.println();
			writer.print("key E (between A and E) = " + keyE);
		} catch (Exception ex) {

		}

		keyB = bob.computeSessionKey(eveSenderMessage);
		keyE = eve.computeReceiverSessionKey(bobMessage, aliceMessage.getModulus());
		try (PrintWriter writer = new PrintWriter(currentDirectory + "transcript (Bob and Eve).txt", "UTF-16")) {
			writer.println("Secret B = " + bob.getSecretNumber());
			writer.println();
			writer.println("Secret E = " + eve.getReceiverSecretNumber());
			writer.println();
			writer.println("msg1.modulus = " + aliceMessage.getModulus());
			writer.println();
			writer.println("msg1.base = " + aliceMessage.getBase());
			writer.println();
			writer.println("msg1.valueA = " + eveSenderMessage.getValue());
			writer.println();
			writer.println("msg2.valueB = " + eveReceiverMessage);
			writer.println();
			writer.println("key B (between B and E) = " + keyB);
			writer.println();
			writer.print("key E (between B and E) = " + keyE);
		} catch (Exception ex) {

		}

		System.out.println("finish");
	}
}