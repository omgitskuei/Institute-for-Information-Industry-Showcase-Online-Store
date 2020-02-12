package action;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TestUtil {

	public static void main(String[] args) {
		
//		EncryptString s = new EncryptString();
//		byte[] cipher = s.encryptGoogleTinkAEAD("omg wow", "salty steak");
//		
//		s.decryptGoogleTinkAEAD(cipher, "salty steak");
		TestUtil test = new TestUtil();
		
		test.welcomeEmail("farmvilletaiwan@gmail.com", "Jack");
		
	}
	
	public void welcomeEmail(String userEmail, String userName) {
		// String userName can be a user's Display name, Email, or Full name
		System.out.println("BEGIN: util.welcomeEmail");
		// Local Variables
		String fromEmail = "farmvilletaiwan@gmail.com";
		String toEmail = userEmail;
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("farmvilletaiwan@gmail.com", "Qq12345!"));
			email.setSSLOnConnect(true);
			email.setFrom(fromEmail);
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
			email.addTo(toEmail);
			email.send();
			System.out.println("Email to ["+toEmail+"] from ["+fromEmail+"] sent!");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR: TestUtil Email Exception");
		}
		System.out.println("FINISH: util.welcomeEmail");
	}
}
