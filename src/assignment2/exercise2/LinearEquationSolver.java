package assignment2.exercise2;

import java.math.BigInteger;

public class LinearEquationSolver {
	public static void main(String[] args) {
		// Question 2a
		BigInteger a = new BigInteger(
				"34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
		BigInteger b = new BigInteger(
				"45292384209127917243621242398573220935835723464332452353464376432246757234546765745246457656354765878442547568543334677652352657235");
		BigInteger N = new BigInteger(
				"643808006803554439230129854961492699151386107534013432918073439524138264842370630061369715394739134090922937332590384720397133335969549256322620979036686633213903952966175107096769180017646161851573147596390153");

		System.out.println("First N is prime? " + isPrime(N));
		output(solve(a, b, N));

		// Question 2b
		a = new BigInteger(
				"34325464564574564564768795534569998743457687643234566579654234676796634378768434237897634345765879087764242354365767869780876543424");
		b = new BigInteger(
				"24243252873562935279236582385723952735639239823957923562835832582635283562852252525256882909285959238420940257295265329820035324646");
		N = new BigInteger(
				"342487235325934582350235837853456029394235268328294285895982432387582570234238487625923289526382379523573265963293293839298595072093573204293092705623485273893582930285732889238492377364284728834632342522323422");

		System.out.println("\nSecond N is prime? " + isPrime(N));
		output(solve(a, b, N));
	}

	/**
	 * This method solves the equation ax + b = 0 for a set of integers
	 * {0,1,...,N-1}.
	 * 
	 * @param a
	 * @param b
	 * @param N
	 * @return x
	 */
	public static BigInteger solve(BigInteger a, BigInteger b, BigInteger N) {
		try {
			BigInteger modInverse = a.modInverse(N);
			return (b.negate().multiply(modInverse)).mod(N);
		} catch (ArithmeticException ex) {
			return null;
		}
	}

	/**
	 * This method returns true if the given BigInteger is very likely to be a
	 * prime number.
	 * 
	 * @param number
	 *            The given BigInteger
	 * @return true if the given BigInteger is very likely to be a prime number
	 */
	public static boolean isPrime(BigInteger number) {
		if (number.isProbablePrime(1)) {
			return true;
		}
		return false;
	}

	public static void output(BigInteger result) {
		if (result != null)
			System.out.println("x = " + result);
		else
			System.out.println("The given integers are not invertible.");
	}
}