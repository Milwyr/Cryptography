package assignment2.exercise1;

import java.math.BigInteger;

public class ExtendedEuclideanAlgorithm {
	public static void main(String[] args) {
		BigInteger[] temp = ExtendedEuclidean(new BigInteger("1572855870797393"), new BigInteger("630065648824575"));
		
		char[] labels = {'d', 's', 't'};
		for (int i = 0; i < temp.length; i++) {
			System.out.println(labels[i] + ": " + temp[i]);
		}
	}

	/**
	 * This method returns an array of size 3 which contains three values d,s,t
	 * such that d = gcd(x, y) and xs + yt = d.
	 * 
	 * @param a
	 *            Big integer a
	 * @param b
	 *            Big integer b
	 * @return an array of size 3 which contains three values d,s,t
	 */
	public static BigInteger[] ExtendedEuclidean(BigInteger a, BigInteger b) {
		// [0]: d, [1]: s, [2]: t for xs + yt = d
		BigInteger[] answers = new BigInteger[3];

		if (b.compareTo(BigInteger.ZERO) == 0) {
			answers[0] = a;
			answers[1] = BigInteger.ONE;
			answers[2] = BigInteger.ZERO;
		} else {
			BigInteger quotient = a.divide(b);
			answers = ExtendedEuclidean(b, a.mod(b));
			BigInteger temp = answers[1].subtract(answers[2].multiply(quotient));
			answers[1] = answers[2];
			answers[2] = temp;
		}

		return answers;
	}
}
