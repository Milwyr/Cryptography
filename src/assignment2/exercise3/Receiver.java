package assignment2.exercise3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Receiver {
	private final int MODULUS_LENGTH = 1024;
	private BigInteger secretNumber;
	private BigInteger receiverMessage;

	public Receiver() {

	}

	/**
	 * This method computes a new message by using the sender's base and
	 * modulus, and its own key.
	 * 
	 * @param senderMessage
	 *            A message which contains a modulus, a base and a value
	 * @return The value calculated by the formula (base ^ key) mod modulus
	 */
	public BigInteger computeMessage(SenderMessage senderMessage) {
		SecureRandom secureRandom = new SecureRandom();
		this.secretNumber = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);
		this.receiverMessage = senderMessage.getBase().modPow(this.secretNumber, senderMessage.getModulus());
		return this.receiverMessage;
	}

	/**
	 * This method computes the secret with sender's message and receiver's key.
	 * 
	 * @param senderMessage
	 * @return (senderMessage ^ keyB) mod modulus
	 */
	public BigInteger computePublicKey(SenderMessage senderMessage) {
		return senderMessage.getValue().modPow(this.secretNumber, senderMessage.getModulus());
	}

	/**
	 * This method returns the secret number that is used as an exponent.
	 * 
	 * @return The secret number that is used as an exponent
	 */
	public BigInteger getSecretNumber() {
		return this.secretNumber;
	}
}
