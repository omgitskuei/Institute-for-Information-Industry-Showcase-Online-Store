package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Timestamp {
	
	// Generate current date 
	public Date generateDate() {
		// Generate current date 
		Date newDate = new Date();
		// Format the new date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Console trail
		System.out.println("Timestamp generated: "+dateFormat.format(newDate)); //prints out current Date
		return newDate;
	}
	
	// Generate current local date
	public LocalDate generateLocalDate() {
		// Generate current local date based on system clock
		LocalDate generatedLocalDate = LocalDate.now();
		// Format the new local date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Console trail
		System.out.println("Timestamp generated: "+dateFormat.format(generatedLocalDate)); //prints out current Date
		return generatedLocalDate;
	}
	
	// Take the passed LocalDate and convert it to Date, return Date type
	public Date convertLocalDateToDate(LocalDate thisLocalDate) {
		Date convertedDate = Date.from(thisLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		// Console trail
		System.out.println("Local Date (type) converted to Date (type)");
		System.out.println("Result: " + convertedDate);
		return convertedDate;
	}
	
	// Take the passed date and convert it to LocalDate, return LocalDate type
	public LocalDate convertDateToLocalDate(Date thisDate) {
		LocalDate convertedLocalDate = thisDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// Console trail
		System.out.println("Date (type) converted to Local Date (type)");
		System.out.println("Result: " + convertedLocalDate);
		return convertedLocalDate;
	}
}
