package assignment2.exercise1;

import java.math.BigInteger;

public class ExtendedEuclideanAlgorithm {
	public static void main(String[] args) {
		BigInteger[] results = ExtendedEuclidean(new BigInteger("1572855870797393"), new BigInteger("630065648824575"));

		char[] labels = { 'd', 's', 't' };

		System.out.println("For the equation xs + yt = d, x = 1572855870797393, y = 630065648824575.\n");
		for (int i = 0; i < results.length; i++) {
			System.out.println(labels[i] + ": " + results[i]);
		}
	}

	/**
	 * This method returns an array of size 3 which contains three values d,s,t
	 * such that d = gcd(x, y) and xs + yt = d.
	 * 
	 * 'd' represents the greatest common divisor, 's' represents the
	 * coefficient for the bigger integer, 't' represents the coefficient for
	 * the smaller integer.
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
			BigInteger t = answers[1].subtract(answers[2].multiply(quotient));
			answers[1] = answers[2];
			answers[2] = t;
		}

		return answers;
	}
}
