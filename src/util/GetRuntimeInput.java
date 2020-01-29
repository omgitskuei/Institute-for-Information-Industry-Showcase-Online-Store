package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetRuntimeInput {
	// Local Fields
	BufferedReader bReader;
	String input;
	
	// Constructors
	public GetRuntimeInput() {
		System.out.println("BEGIN: util.GetRuntimeInput()");
	}
	
	// Methods
	public String getConsoleInputBR(String prompt) {
		try {
			// Local variables
			bReader = new BufferedReader(new InputStreamReader(System.in));
			// Print prompt
			System.out.println(prompt);
			// Input
			input = bReader.readLine();
			System.out.println("You entered: "+input);
			// Close
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("FINISH: util.GetRuntimeInput()");
		return input;
	}
}
