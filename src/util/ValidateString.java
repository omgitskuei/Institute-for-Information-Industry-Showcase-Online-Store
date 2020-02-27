package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;

public class ValidateString {
	// Local fields
	String aString;

	// Constructors
	public ValidateString() {
		System.out.println("BEGIN: util.ValidateString()");
	}

	public ValidateString(String aString) {
		System.out.println("BEGIN: util.ValidateString()");
		this.aString = aString;
	}

	// Executable
	public static void main(String[] args) {
		try {
			GetRuntimeInput util = new GetRuntimeInput();
			ValidateString util1 = new ValidateString();

			boolean result = true;
			int failsafe = 10;

			while (result && failsafe > 0) {
				String input = util.getConsoleInputBR("Input String:");

				// result = !(util1.validateEmail(input));
				result = !(util1.validatePwd(input));

				failsafe--;
				System.out.println("Tries left: " + failsafe);
			}
			System.out.println("Finished While-Loop: " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Methods
	public String validatePwdreturnErrors(String pwd) {
		System.out.println("BEGIN: validatePwd(String)");
		// A valid password must not be null
		String returnedString = "";
		if (pwd != null) {
			// A valid password must have >=8 characters
			if (pwd.length() >= 8) {
				// Partition pwd into substrings of each character
				ArrayList<String> pwdEachChar = new ArrayList<String>();
				for (int index = 0; index < pwd.length(); index++) {
					pwdEachChar.add(pwd.substring(index, index + 1));
				}
				// A valid password must NOT contain spaces
				if (!(pwdEachChar.contains(" "))) {
					// A valid password must contain >=1 special characters
					// A valid password must contain >=1 Capitalized letters
					// A valid password must contain >=1 Lower-case letters
					CheckSubstring util = new CheckSubstring();
					if (util.countCapLetters(pwd) >= 1 && util.countLowLetters(pwd) >= 1
							&& util.countSpecialCharacters(pwd) >= 1) {
						// A valid password must contain >=1 numbers
						if (util.countNums(pwd) >= 1) {
							System.out.println("Valid Input: Password is valid");
							returnedString = "VALID PASSWORD";
						} else {
							returnedString = "Invalid Input: Pwd must contain at least one number";
						}
					} else {
						returnedString = "Invalid Input: Pwd must contain at least one Special character, Capital letter, and Lower-case letter";
					}
				} else {
					returnedString = "Invalid Input: Pwd must not contain spaces";
				}
			} else {
				returnedString = "Invalid Input: Pwd must be longer than 8 characters";
			}
		} else {
			returnedString = "Invalid Input: Pwd cannot be null";
		}
		System.out.println("FINISH: validatePwd(String)");
		return returnedString;
	}
	
	public boolean validatePwd(String pwd) {
		System.out.println("BEGIN: validatePwd(String)");
		// A valid password must not be null
		if (pwd != null) {
			// A valid password must have >=8 characters
			if (pwd.length() >= 8) {
				// Partition pwd into substrings of each character
				ArrayList<String> pwdEachChar = new ArrayList<String>();
				for (int index = 0; index < pwd.length(); index++) {
					pwdEachChar.add(pwd.substring(index, index + 1));
				}
				// A valid password must NOT contain spaces
				if (!(pwdEachChar.contains(" "))) {
					// A valid password must contain >=1 special characters
					// A valid password must contain >=1 Capitalized letters
					// A valid password must contain >=1 Lower-case letters
					CheckSubstring util = new CheckSubstring();
					if (util.countCapLetters(pwd) >= 1 && util.countLowLetters(pwd) >= 1
							&& util.countSpecialCharacters(pwd) >= 1) {
						// A valid password must contain >=1 numbers
						if (util.countNums(pwd) >= 1) {
							System.out.println("Valid Input: Password is valid");
							return true;
						} else {
							System.out.println("Invalid Input: Pwd must contain at least one number");
						}
					} else {
						System.out.println(
								"Invalid Input: Pwd must contain at least one Special character, Capital letter, and Lower-case letter");
					}
				} else {
					System.out.println("Invalid Input: Pwd must not contain spaces");
				}
			} else {
				System.out.println("Invalid Input: Pwd must be longer than 8 characters");
			}
		} else {
			System.out.println("Invalid Input: Pwd cannot be null");
		}
		System.out.println("FINISH: validatePwd(String)");
		return false;
	}

	public String validateEmailreturnErrors(String email) {
		try {
			// email cant be empty, and must be longer than 5 (x@x.x)
			if (email != null && email.length() > 5) {
				// email must have "@"
				if (email.contains("@")) {
					// Partition email string based on email syntax; localpart@domain
					String localpart = email.substring(0, email.indexOf("@"));
					String domain = email.substring(email.indexOf("@") + 1, email.length());

					// Localpart and Domain each must not exceed 64 characters
					if (localpart.length() <= 64 && domain.length() <= 63) {
						// Create empty ArrayList for storing where "." appear in email String
						ArrayList<Integer> dotIndexes = new ArrayList<Integer>();
						// Create counter for "@"
						int countAt = 0;
						// Create flag for spaces, false will fail;
						boolean noSpaces = true;
						// Checking each character of email string for space, @, .
						for (int index = 0; index < email.length(); index++) {
							// Split email into substrings
							String substring = email.substring(index, index + 1);
							// Count how many "@"
							if (substring.equals("@")) {
								countAt++;
							}
							// Check if there are any spaces in the email
							if (substring.equals(" ")) {
								noSpaces = false;
							}
							// Note where "." dots appear in the email, store into ArrayList
							if (substring.equals(".")) {
								dotIndexes.add(index);
							}
						}
						// A valid email must have "@" and can only have one "@"
						if (countAt == 1) {
							// A valid email must have no spaces
							if (noSpaces) {
								// A valid email cannot begin or end on "."
								if (!(dotIndexes.contains(0) || dotIndexes.contains(email.length() - 1))) {
									// Email @ sign can't be next to "."
									boolean atNotNextToDot = true;
									for (int index = 0; index < dotIndexes.size(); index++) {
										// if "." is to the left or right of the "@", fail
										if (email.indexOf("@") - dotIndexes.get(index) == -1
												|| email.indexOf("@") - dotIndexes.get(index) == 1) {
											atNotNextToDot = false;
										}
									}
									if (atNotNextToDot) {
										// Domain must comply with LDH rule (letters, digits, hyphen)
										CheckSubstring util = new CheckSubstring();
										if (util.countSpecialCharacters(domain) == 0) {
											// Domain must contain one "."
											if (domain.contains(".")) {
												boolean noConsecutiveDotsFlag = true;
												// Domain cannot have any consecutive dots
												for (int index = 0; index < dotIndexes.size() - 1; index++) {
													if ((dotIndexes.get(index + 1) - dotIndexes.get(index)) == 1) {
														noConsecutiveDotsFlag = false;
													}
												}
												if (noConsecutiveDotsFlag) {
													return "VALID EMAIL";
												}
											} else {
												return "Invalid Input: Email domain missing . character";
											}
										} else {
											return "Invalid Input: Email may only use letters, digits, hyphen";
										}
									} else {
										return "Invalid Input: Email cannot have a . character next to a @";
									}
								} else {
									return "Invalid Input: Email may not end on a . character";
								}
							} else {
								return "Invalid Input: Email may not use any Spaces";
							}
						} else {
							return "Invalid Input: Email may only have one @ character";
						}
					} else {
						return "Invalid Input: Email localpart and domain must be under 64 characters";
					}
				} else {
					return "Invalid Input: Email must contain @";
				}
			} else {
				return "Invalid Input: Email is too short";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "EXCEPTION";
	}
	
	public boolean validateEmail(String email) {
		try {
			// email cant be empty, and must be longer than 5 (x@x.x)
			if (email != null && email.length() > 5) {
				// email must have "@"
				if (email.contains("@")) {
					// Partition email string based on email syntax; localpart@domain
					String localpart = email.substring(0, email.indexOf("@"));
					String domain = email.substring(email.indexOf("@") + 1, email.length());

					// Localpart and Domain each must not exceed 64 characters
					if (localpart.length() <= 64 && domain.length() <= 63) {
						// Create empty ArrayList for storing where "." appear in email String
						ArrayList<Integer> dotIndexes = new ArrayList<Integer>();
						// Create counter for "@"
						int countAt = 0;
						// Create flag for spaces, false will fail;
						boolean noSpaces = true;
						// Checking each character of email string for space, @, .
						for (int index = 0; index < email.length(); index++) {
							// Split email into substrings
							String substring = email.substring(index, index + 1);
							// Count how many "@"
							if (substring.equals("@")) {
								countAt++;
							}
							// Check if there are any spaces in the email
							if (substring.equals(" ")) {
								noSpaces = false;
							}
							// Note where "." dots appear in the email, store into ArrayList
							if (substring.equals(".")) {
								dotIndexes.add(index);
							}
						}
						// A valid email must have "@" and can only have one "@"
						if (countAt == 1) {
							// A valid email must have no spaces
							if (noSpaces) {
								// A valid email cannot begin or end on "."
								if (!(dotIndexes.contains(0) || dotIndexes.contains(email.length() - 1))) {
									// Email @ sign can't be next to "."
									boolean atNotNextToDot = true;
									for (int index = 0; index < dotIndexes.size(); index++) {
										// if "." is to the left or right of the "@", fail
										if (email.indexOf("@") - dotIndexes.get(index) == -1
												|| email.indexOf("@") - dotIndexes.get(index) == 1) {
											atNotNextToDot = false;
										}
									}
									if (atNotNextToDot) {
										// Domain must comply with LDH rule (letters, digits, hyphen)
										CheckSubstring util = new CheckSubstring();
										if (util.countSpecialCharacters(domain) == 0) {
											// Domain must contain one "."
											if (domain.contains(".")) {
												boolean noConsecutiveDotsFlag = true;
												// Domain cannot have any consecutive dots
												for (int index = 0; index < dotIndexes.size() - 1; index++) {
													if ((dotIndexes.get(index + 1) - dotIndexes.get(index)) == 1) {
														noConsecutiveDotsFlag = false;
													}
												}
												if (noConsecutiveDotsFlag) {
													System.out.println("Valid Input: [" + email + "] is a VALID EMAIL");
													return true;
												}
											} else {
												System.out.println("Invalid Input: Email domain missing . character");
											}
										} else {
											System.out.println(
													"Invalid Input: Email may only use letters, digits, hyphen");
										}
									} else {
										System.out
												.println("Invalid Input: Email cannot have a . character next to a @");
									}
								} else {
									System.out.println("Invalid Input: Email may not end on a . character");
								}
							} else {
								System.out.println("Invalid Input: Email may not use any Spaces");
							}
						} else {
							System.out.println("Invalid Input: Email may only have one @ character");
						}
					} else {
						System.out.println("Invalid Input: Email localpart and domain must be under 64 characters");
					}
				} else {
					System.out.println("Invalid Input: Email must contain @");
				}
			} else {
				System.out.println("Invalid Input: Email is too short");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
