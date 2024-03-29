package assignment2.exercise3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Sender {
	private final int MODULUS_LENGTH = 1024;
	private BigInteger secretNumber;
	private SenderMessage senderMessage;

	public Sender() {

	}

	/**
	 * This method first generates a modulus, a base and a key, then computes a
	 * new message by the formula (base ^ secret number) mod modulus.
	 * 
	 * @return The value calculated by the formula (base ^ secret) mod modulus
	 */
	public SenderMessage computeMessage() {
		SecureRandom secureRandom = new SecureRandom();
		BigInteger modulus = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);
		BigInteger base = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);

		this.secretNumber = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);

		BigInteger value = base.modPow(this.secretNumber, modulus);
		this.senderMessage = new SenderMessage(modulus, base, value);

		return this.senderMessage;
	}

	/**
	 * This method computes the session key by receiver's message and its secret
	 * number.
	 * 
	 * @param receiverMessage
	 * @return (receiverMessage ^ keyA) mod modulus
	 */
	public BigInteger computeSessionKey(BigInteger receiverMessage) {
		return receiverMessage.modPow(this.secretNumber, this.senderMessage.getModulus());
	}

	/**
	 * This secret number is generated by the sender and is used as an exponent.
	 * 
	 * @return The secret number that is used as an exponent
	 */
	public BigInteger getSecretNumber() {
		return this.secretNumber;
	}
}