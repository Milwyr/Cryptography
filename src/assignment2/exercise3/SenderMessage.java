package assignment2.exercise3;

import java.math.BigInteger;

public class SenderMessage {
	private BigInteger modulus;
	private BigInteger base;
	private BigInteger value;
	
	public SenderMessage(BigInteger modulus, BigInteger base, BigInteger value) {
		this.modulus = modulus;
		this.base = base;
		this.value = value;
	}
	
	public BigInteger getModulus() {
		return this.modulus;
	}
	
	public BigInteger getBase() {
		return this.base;
	}
	
	public BigInteger getValue() {
		return this.value;
	}
}
