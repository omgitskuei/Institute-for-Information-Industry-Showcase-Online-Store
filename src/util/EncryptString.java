package util;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.KeyTemplate;

public class EncryptString {
	// Local fields
	String plainText;
	String ad;

	// Constructors
	public EncryptString() {
		System.out.println("BEGIN: util.EncryptString()");
	}

	// Methods
//	public Aead newCleartextAEADKeyset() {
//		try {
//			// Initializing Tink/AEAD
//			AeadConfig.register();
//			// Generating new key material/keysets
//			KeyTemplate keyTemplate = AeadKeyTemplates.AES256_GCM;
//			KeysetHandle keysetHandle = KeysetHandle.generateNew(keyTemplate);
//			// Write key material to file/store them
//			String keysetFilename = "keyset.json";
//			CleartextKeysetHandle.write(keysetHandle, JsonKeysetWriter.withFile(new File(keysetFilename)));
//			// Obtaining and using primitive AEAD
//			Aead aead = keysetHandle.getPrimitive(Aead.class);
//			return aead;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("EXCEPTION at util.EncryptString.newCleartextAEADKeyset: Returning NULL");
//			return null;
//		}
//	}

	public byte[] encryptGoogleTinkAEAD(String plainText, String ad) {
		System.out.println("	Encrypting "+plainText+" with AD "+ad+" into AES AEAD");
		try {
			// Initialize Tink/AEAD
			AeadConfig.register();
			// Loading existing keys from file
			String keysetFilename = "keyset.json";
			KeysetHandle keysetHandle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(new File(keysetFilename)));
			// Obtaining and using primitive AEAD
			Aead aead = keysetHandle.getPrimitive(Aead.class);

			// Encrypt
			byte[] ciphertext = aead.encrypt(plainText.getBytes(), ad.getBytes());
			System.out.println("	Result AEAD Cipher (byte[]): " + ciphertext);
			return ciphertext;
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			System.out.println("util.EncryptString.encryptGoogleTinkAEAD EXCEPTION: Returning NULL");
			return null;
		}
	}

	public String decryptGoogleTinkAEAD(byte[] cipher, String ad) {
		System.out.println("	Decrypting AEAD (byte[])"+cipher+" with AD "+ad+" into plainText");
		try {
			// Initialize Tink/AEAD
			AeadConfig.register();
			// Loading existing keys from file
			String keysetFilename = "keyset.json";
			KeysetHandle keysetHandle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(new File(keysetFilename)));
			// Obtaining and using primitive AEAD
			Aead aead = keysetHandle.getPrimitive(Aead.class);

			// Decrypt
			String decrypted = new String(aead.decrypt(cipher, ad.getBytes()));
			System.out.println("	Result Plaintext: " + decrypted);
			return decrypted;
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			System.out.println("EXCEPTION at util.EncryptString.decryptGoogleTinkAEAD: Returning null(string)");
			return "null";
		}
	}

	// Executable
	public static void main(String args[]) {
		// Get Input
		GetRuntimeInput util = new GetRuntimeInput();
		String plainText = util.getConsoleInputBR("Input exactly: \r\n"
				+ "1. Encrypt Yes(y)/No(n), \r\n"
				+ "2. Decrypt Yes(y)/No(n), \r\n"
				+ "3. Your query string.");

		EncryptString util1 = new EncryptString();
		// Refreshes the encryption keys in keyset.json.
		// WARNING: information encrypted with OLD key cannot be decrypted with new key
		//util1.newCleartextAEADKeyset();
		
		String commands = plainText.substring(0, 2); //End index is excluded from substring
		String query = plainText.substring(2);
		
		EncodeHexString s = new EncodeHexString();
		EncodeHexString t = new EncodeHexString();
		if (query.length()>0) {
			if (commands.equals("yy")) {
				byte[] cipher = util1.encryptGoogleTinkAEAD(query, "OMGiloveyou");
				String encrypted = s.byteArrayToHexString(cipher);
				
				byte[] bytearray = t.HexStringToByteArray(encrypted);
				String decrypted = util1.decryptGoogleTinkAEAD(cipher, "OMGiloveyou");
				
			} else if (commands.equals("yn")) {
				byte[] cipher = util1.encryptGoogleTinkAEAD(query, "OMGiloveyou");
				String encrypted = s.byteArrayToHexString(cipher);
				
			} else if (commands.equals("ny")) {
				byte[] bytearray = t.HexStringToByteArray(query);
				String decrypted = util1.decryptGoogleTinkAEAD(bytearray, "OMGiloveyou");
				
			} else if (commands.equals("nn")) {
				System.out.println("Nothing done.");
			} else {
				System.out.println("Invalid Commands.");;
			}
		} else {
			System.out.println("Empty query");;
		}
		
		
		
		// util1.newCleartextAEADKeyset();	// Uncomment to refresh key
		
		// decrypt	
		
	}
}
