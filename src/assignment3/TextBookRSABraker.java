package assignment3;

import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * This class provides an implementation to break textbook RSA with exponent 3.
 * In this case, the ciphertext can be broken by simply finding the cube root of
 * the ciphertext.
 * 
 * @author Milton
 *
 */
public class TextBookRSABraker {

	public static void main(String[] args) {
		BigInteger cipherText = new BigInteger("674472526620593903800497637242400187916753185909");
		String stringCubeRoot = cubeRoot(cipherText).toString();

		String plainText = "";
		for (int index = 0; index < stringCubeRoot.length(); index += 2) {
			String stringAscii = stringCubeRoot.substring(index, index + 2);
			int intAscii = Integer.parseInt(stringAscii);
			plainText += (char) intAscii;
		}

		writeResultToFile("output.txt", plainText);
	}

	/**
	 * This method returns the cube root of the input BigInteger n.
	 * 
	 * @param n
	 *            The input BigInteger
	 * @return The cube root of the input BigInteger n
	 */
	private static BigInteger cubeRoot(BigInteger n) {
		BigInteger x = BigInteger.ZERO.setBit(n.bitLength() / 3 + 1);
		while (true) {
			BigInteger y = x.shiftLeft(1).add(n.divide(x.multiply(x))).divide(new BigInteger("3"));
			if (y.compareTo(x) >= 0) {
				break;
			}

			x = y;
		}

		return x;
	}

	/**
	 * This method writes the given result to a new file.
	 * 
	 * @param fileName
	 *            Name of the file to output
	 * @param result
	 *            The final result to output to the file
	 */
	private static void writeResultToFile(String fileName, String result) {
		try (PrintWriter writer = new PrintWriter("src\\assignment3\\" + fileName, "UTF-16")) {
			writer.print("The recovered text from the given ciphertext is " + result + ".");
		} catch (Exception ex) {

		}
		System.out.println("done");
	}
}
