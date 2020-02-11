package action;

import util.EncryptString;

public class TestUtil {

	public static void main(String[] args) {
		EncryptString s = new EncryptString();
		byte[] cipher = s.encryptGoogleTinkAEAD("omg wow", "salty steak");
		
		s.decryptGoogleTinkAEAD(cipher, "salty steak");
	}

}
