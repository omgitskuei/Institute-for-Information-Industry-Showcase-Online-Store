package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUsers {
	// Local fields
	
	
	// Constructors
	
	
	// Executable
	public static void main(String[] args) {
		EmailUsers thisClass = new EmailUsers();
		// thisClass.sendWelcomeEmail("farmvilletaiwan@gmail.com", "Bob");
	}
	
	// Methods
	public void sendChangePwdEmail(String userEmail, String userName) {
		// String userName can be a user's Display name, Email, or Full name
		System.out.println("BEGIN: util.changePwdEmail");
		// Local Variables
		String fromEmail = "farmvilletaiwan@gmail.com";
		String toEmail = userEmail;
		try {
			// Instantiate SimpleEmail object
			Email email = new SimpleEmail();
			// Email sending settings
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			// Email sender's credentials
			email.setAuthenticator(new DefaultAuthenticator("farmvilletaiwan@gmail.com", "Qq12345!"));
			email.setSSLOnConnect(true);
			// Email TO / FROM
			email.setFrom(fromEmail);
			email.addTo(toEmail);
			// Email body
			email.setSubject("FarmVille Account Support: Your password changed");
			email.setMsg("Hi "+userName+","+" \r\n" 
					+ " \r\n" 
					+ "Your password has been successfully changed. "+"\r\n"
					+ " \r\n" 
					+ "If you didn’t change your password, contact our support email right away at farmvilletaiwan@gmail.com"+"\r\n"
					+ " \r\n" 
					+ "Just a reminder:"+"\r\n"
					+ "- Never share your password or security questions with anyone."+"\r\n"
					+ "- Create passwords that are hard to guess and don’t use personal information. Be sure to include uppercase and lowercase letters, numbers, and symbols."+"\r\n"
					+ "- Use different passwords for each of your online accounts."+"\r\n"
					+ " \r\n" 
					+ "Thanks,"+"\r\n"
					+ "The FarmVille Support Team");
			// Send
			email.send();
			System.out.println("Email to ["+toEmail+"] from ["+fromEmail+"] sent!");
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("ERROR: TestUtil Email Exception");
		}
		System.out.println("FINISH: util.changePwdEmail");
	}
	
	public void sendReceiptEmail(String userEmail, String userName) {
		// String userName can be a user's Display name, Email, or Full name
		System.out.println("BEGIN: util.EmailUsers.sendReceiptEmail");
		// Local Variables
		String fromEmail = "farmvilletaiwan@gmail.com";
		String toEmail = userEmail;
//		"Hi "+userName+","+" \r\n" 
//		+ " \r\n" 
//		+ "Thank you for your recent transaction on Farmville, Taiwan. "+"\r\n"
//		+ " \r\n" 
//		+ "If you didn’t change your password, contact our support email right away at farmvilletaiwan@gmail.com"+"\r\n"
//		+ " \r\n" 
//		+ "Just a reminder:"+"\r\n"
//		+ "- Never share your password or security questions with anyone."+"\r\n"
//		+ "- Create passwords that are hard to guess and don’t use personal information. Be sure to include uppercase and lowercase letters, numbers, and symbols."+"\r\n"
//		+ "- Use different passwords for each of your online accounts."+"\r\n"
//		+ " \r\n" 
//		+ "Thanks,"+"\r\n"
//		+ "The FarmVille Support Team");
		String bodyPart1 = "";
		String bodyPart2 = "";
		String bodyPart3 = "";
		String emailBody = bodyPart1+bodyPart2+bodyPart3;
		try {
			// Instantiate SimpleEmail object
			Email email = new SimpleEmail();
			// Email sending settings
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			// Email sender's credentials
			email.setAuthenticator(new DefaultAuthenticator("farmvilletaiwan@gmail.com", "Qq12345!"));
			email.setSSLOnConnect(true);
			// Email TO / FROM
			email.setFrom(fromEmail);
			email.addTo(toEmail);
			// Email body
			email.setSubject("FarmVille Account Support: Your password changed");
			email.setMsg(emailBody);
			// Send
			email.send();
			System.out.println("Email to ["+toEmail+"] from ["+fromEmail+"] sent!");
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("ERROR: sendReceiptEmail Email Exception");
		}
		System.out.println("FINISH: util.EmailUsers.sendReceiptEmail");
	}
	
	public void sendWelcomeEmail(String userEmail, String userName) {
		// String userName can be a user's Display name, Email, or Full name
		System.out.println("BEGIN: util.EmailUsers.sendWelcomeEmail");
		// Local variables
		String fromEmail = "farmvilletaiwan@gmail.com";
		String toEmail = userEmail;
		
		try {
			// Instantiate SimpleEmail object
			Email email = new SimpleEmail();
			// Email sending settings;
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			// Email (from)'s login credentials
			email.setAuthenticator(new DefaultAuthenticator("farmvilletaiwan@gmail.com", "Qq12345!"));
			email.setSSLOnConnect(true);
			// FROM / TO
			email.setFrom(fromEmail);
			email.addTo(toEmail);
			// Email body
			email.setSubject("Welcome to Farmville, Taiwan");
			email.setMsg("Hi "+userName+","+" \r\n" 
					+ "\r\n"
					+ "Great to have you with us. Farmville is here to make a difference in groceries shopping."+"\r\n"
					+ "Our website will continuously stock the best locally-sourced, GMO-free, and organic produce available."+"\r\n"
					+ "Here at Farmville, we take our 'We are what we eat' philosphy very seriously and will always put customers first!"+"\r\n"
					+ "\r\n"
					+ "This is the e-mail address you registered on Farmville, Taiwan. Use it and your password to log into your online account."+"\r\n"
					+ "For your 1st time sign-in, we recommend taking a moment and going over your account profile and settings." + "\r\n"
					+ "\r\n"
					+ "Thanks,"+"\r\n"
					+ "The FarmVille Support Team");
			// Send
			email.send();
			System.out.println("Email to ["+toEmail+"] from ["+fromEmail+"] sent!");
		} catch (EmailException e) {
			e.printStackTrace();
			System.out.println("ERROR: sendWelcomeEmail Email Exception");
		}
		System.out.println("FINISH: util.EmailUsers.sendWelcomeEmail");
	}
	
	public void sendMyselfTestEmail(String emailBody) throws Exception {
		System.out.println("BEGIN: util.EmailUsers.emailSelf");
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("farmvilletaiwan@gmail.com", "Qq12345!"));
			email.setSSLOnConnect(true);
			email.setFrom("farmvilletaiwan@gmail.com");
			email.setSubject("TestMail");
			email.setMsg(emailBody);
			email.addTo("farmvilletaiwan@gmail.com");
			email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
		System.out.println("FINISH: util.EmailUsers.............................................");
	}
}
