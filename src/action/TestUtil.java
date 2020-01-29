package action;

import java.util.Date;

import util.GetRuntimeInput;
import util.GetDateOrTime;

public class TestUtil {
	public static void main(String[] args) {
		// Get Input
		GetRuntimeInput util = new GetRuntimeInput();
		String input = util.getConsoleInputBR("Input String:");
		
		GetDateOrTime util1 = new GetDateOrTime();
		for (int index=0;index< Integer.parseInt(input);index++) {
			Date d = util1.generateDate();
			System.out.println(d);
		}
		
	}
}
