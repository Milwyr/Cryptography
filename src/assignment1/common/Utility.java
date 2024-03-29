package assignment1.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Utility {
	private static Map<Character, Double> publiclyKnownLetterFrequencies = new TreeMap<Character, Double>();

	/**
	 * This method reads a text file from the given path.
	 * 
	 * @param path
	 *            Full path of the file to import
	 * @return All the text contents in the file
	 * @throws IOException
	 */
	public static List<String> importFile(String path) {
		List<String> texts = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String text = reader.readLine();

			while (text != null) {
				texts.add(text);
				text = reader.readLine();
			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		return texts;
	}

	/**
	 * This method generates a text file in the given path.
	 * 
	 * @param exportPath
	 *            Full file path of the generated text file
	 * @param texts
	 *            A list of text to output in the file
	 */
	public static void writeToFile(String exportPath, List<String> texts) {
		try (PrintWriter writer = new PrintWriter(exportPath, "UTF-16")) {
			for (String text : texts) {
				writer.println(text);
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static Map<Character, Double> getPubliclyKnownLettersFrequencies() {
		if (publiclyKnownLetterFrequencies.size() == 0) {
			publiclyKnownLetterFrequencies.put('a', 0.08167);
			publiclyKnownLetterFrequencies.put('b', 0.01492);
			publiclyKnownLetterFrequencies.put('c', 0.02782);
			publiclyKnownLetterFrequencies.put('d', 0.04253);
			publiclyKnownLetterFrequencies.put('e', 0.12702);
			publiclyKnownLetterFrequencies.put('f', 0.02228);
			publiclyKnownLetterFrequencies.put('g', 0.02015);
			publiclyKnownLetterFrequencies.put('h', 0.06094);
			publiclyKnownLetterFrequencies.put('i', 0.06966);
			publiclyKnownLetterFrequencies.put('j', 0.00153);
			publiclyKnownLetterFrequencies.put('k', 0.00772);
			publiclyKnownLetterFrequencies.put('l', 0.04025);
			publiclyKnownLetterFrequencies.put('m', 0.02406);
			publiclyKnownLetterFrequencies.put('n', 0.06749);
			publiclyKnownLetterFrequencies.put('o', 0.07507);
			publiclyKnownLetterFrequencies.put('p', 0.01929);
			publiclyKnownLetterFrequencies.put('q', 0.00095);
			publiclyKnownLetterFrequencies.put('r', 0.05987);
			publiclyKnownLetterFrequencies.put('s', 0.06327);
			publiclyKnownLetterFrequencies.put('t', 0.09056);
			publiclyKnownLetterFrequencies.put('u', 0.02758);
			publiclyKnownLetterFrequencies.put('v', 0.00978);
			publiclyKnownLetterFrequencies.put('w', 0.02361);
			publiclyKnownLetterFrequencies.put('x', 0.00015);
			publiclyKnownLetterFrequencies.put('y', 0.01974);
			publiclyKnownLetterFrequencies.put('z', 0.00074);
		}

		return publiclyKnownLetterFrequencies;
	}
}