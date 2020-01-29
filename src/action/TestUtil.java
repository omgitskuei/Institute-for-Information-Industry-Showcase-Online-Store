package action;

import java.util.Date;

import util.GetRuntimeInput;
import util.Timestamp;

public class TestUtil {
	public static void main(String[] args) {
		// Get Input
		GetRuntimeInput util = new GetRuntimeInput();
		String input = util.getConsoleInputBR("Input String:");
		
		Timestamp util1 = new Timestamp();
		for (int index=0;index< Integer.parseInt(input);index++) {
			Date d = util1.generateDate();
			System.out.println(d);
		}
		
	}
}
