package util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSubstring {

	public int countSpecialCharacters(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Special characters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}

	public int countCapLetters(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Capital letters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[A-Z]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}

	public int countLowLetters(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Lower-case letters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[a-z]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}

	public int countSpaces(String checkThisString) {
		// Define counter
		int counter = 0;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to a space
			String letter = checkThisString.substring(index, index + 1);
			// If a space is found, add 1 to counter
			if (letter.equals(" ")) {
				counter++;
			}
		}
		return counter;
	}

	public int countNums(String checkThisString) {
		// Define counter
		int counter = 0;
		// Define "Capital letters" in regex pattern
		Pattern capitalLetters = Pattern.compile("[0-9]");
		Matcher m;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			m = capitalLetters.matcher(checkThisString.substring(index, index + 1));
			// If the letter is found within the regex pattern, add 1 to counter
			if (m.matches()) {
				counter++;
			}
		}
		return counter;
	}
	
	public int countAnyChar(String checkThisString, String countThis) {
		// Define counter
		int counter = 0;
		// For each letter of String checkThisString, ...
		for (int index = 0; index < checkThisString.length(); index++) {
			// ... Match the letter to the regex pattern
			;
			// If the letter is found within the regex pattern, add 1 to counter
			if (checkThisString.substring(index, index + 1).equals(countThis)) {
				counter++;
			}
		}
		return counter;
	}

	public ArrayList<String> delimitAtDot(String delimitThisString) {
		// Makes substrings, split at "."
		int dotIndex = delimitThisString.indexOf(".");
		String beforeDot = delimitThisString.substring(0, dotIndex);
		String afterDot = delimitThisString.substring(dotIndex + 1, delimitThisString.length());

		ArrayList<String> delimitedString = new ArrayList<String>();

		delimitedString.add(beforeDot);
		delimitedString.add(afterDot);
		return delimitedString;
	}

	public ArrayList<String> removeAnyChar(ArrayList<String> slicedStrings, String removeThisChar) { // For "" quotation marks, use \"

		for (int index = 0; index < slicedStrings.size(); index++) {
			String result = "";
			for (int index1 = 0; index1 < slicedStrings.get(index).length(); index1++) {
				if (!slicedStrings.get(index).substring(index1, index1 + 1).equals(removeThisChar)) {
					result = result + slicedStrings.get(index).substring(index1, index1 + 1);
				}
			}
			slicedStrings.set(index, result);
		}
		return slicedStrings;
	}

	public ArrayList<String> removeEmptyValues(ArrayList<String> targetArrayList) {
		ArrayList<String> result = new ArrayList<String>();
		for (int index=0; index<targetArrayList.size(); index++) {
			if (targetArrayList.get(index).length()!=0) {
				result.add(targetArrayList.get(index));
			}
		}
		return result;
	}
	
	public ArrayList<String> delimitAtAnyChar(String delimitThisString, String delimiterChar) {
		ArrayList<Integer> delimiterIndex = new ArrayList<Integer>();
		// Add first
		delimiterIndex.add(0);
		// Slice
		ArrayList<String> slicedStrings = new ArrayList<String>();
		for (int index = 0; index < delimitThisString.length() - 1; index++) {
			if (delimitThisString.substring(index, index + 1).equals(delimiterChar)) {
				delimiterIndex.add(index);
			}
		}
		// Add Last
		delimiterIndex.add(delimitThisString.length());
		for (int index = 0; index < delimiterIndex.size() - 1; index++) {
			String slice = delimitThisString.substring(delimiterIndex.get(index), delimiterIndex.get(index + 1));
			slicedStrings.add(slice);
		}
		return slicedStrings;
	}
}
