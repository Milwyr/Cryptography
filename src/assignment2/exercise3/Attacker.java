package assignment2.exercise3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Attacker {
	private final int MODULUS_LENGTH = 1024;

	private BigInteger senderSecretNumber;
	private BigInteger receiverSecretNumber;

	public Attacker() {
	}

	/**
	 * This method replaces the original sender's message with a new one for the
	 * receiver.
	 * 
	 * @param original
	 *            Sender's message before being intercepted by the attacker
	 * @return A newly computed message for sender
	 */
	public SenderMessage computeSenderMessage(SenderMessage original) {
		SecureRandom secureRandom = new SecureRandom();
		this.senderSecretNumber = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);

		BigInteger value = original.getBase().modPow(this.senderSecretNumber, original.getModulus());
		return new SenderMessage(original.getModulus(), original.getBase(), value);
	}

	/**
	 * This method replaces the original new message with a new message for the
	 * receiver.
	 * 
	 * @param original
	 *            Sender's message before being intercepted by the attacker
	 * @return A newly computed message for receiver
	 */
	public BigInteger computeReceiverMessage(SenderMessage original) {
		SecureRandom secureRandom = new SecureRandom();
		this.receiverSecretNumber = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);
		return original.getBase().modPow(this.receiverSecretNumber, original.getModulus());
	}

	/**
	 * This method computes sender's session key.
	 * 
	 * @param originalSenderMessage
	 *            Sender's message before being intercepted by the attacker
	 * @return (original receiver's message ^ Eve's receiver's secret) mod
	 *         modulus
	 */
	public BigInteger computeSenderSessionKey(SenderMessage originalSenderMessage) {
		return originalSenderMessage.getValue().modPow(this.receiverSecretNumber, originalSenderMessage.getModulus());
	}

	/**
	 * This method computes receiver's session key.
	 * 
	 * @param originalReceiverMessage
	 *            Receiver's message before being intercepted by the attacker
	 * @param modulus
	 * @return (original receiver's message ^ Eve's sender's secret) mod modulus
	 */
	public BigInteger computeReceiverSessionKey(BigInteger originalReceiverMessage, BigInteger modulus) {
		return originalReceiverMessage.modPow(this.senderSecretNumber, modulus);
	}

	public BigInteger getSenderSecretNumber() {
		return this.senderSecretNumber;
	}

	public BigInteger getReceiverSecretNumber() {
		return this.receiverSecretNumber;
	}
}
