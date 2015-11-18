package assignment2.exercise3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Sender {
	private final int MODULUS_LENGTH = 1024;
	private BigInteger privateKey;
	private SenderMessage senderMessage;

	public Sender() {

	}

	/**
	 * This method first generates a modulus, a base and a key, then computes a
	 * new message by the formula (base ^ key) mod modulus.
	 * 
	 * @return The value calculated by the formula (base ^ key) mod modulus
	 */
	public SenderMessage computeMessage() {
		SecureRandom secureRandom = new SecureRandom();
		BigInteger modulus = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);
		BigInteger base = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);

		this.privateKey = BigInteger.probablePrime(MODULUS_LENGTH, secureRandom);

		BigInteger value = base.modPow(this.privateKey, modulus);
		this.senderMessage = new SenderMessage(modulus, base, value);

		return this.senderMessage;
	}

	/**
	 * This method computes the secret with receiver's message and sender's key.
	 * 
	 * @param receiverMessage
	 * @return (receiverMessage ^ keyA) mod modulus
	 */
	public BigInteger computePublicKey(BigInteger receiverMessage) {
		return receiverMessage.modPow(this.privateKey, this.senderMessage.getModulus());
	}

	/**
	 * This method returns the private key that is used as an exponent.
	 * 
	 * @return The private key that is used as an exponent
	 */
	public BigInteger getPrivateKey() {
		return this.privateKey;
	}
}