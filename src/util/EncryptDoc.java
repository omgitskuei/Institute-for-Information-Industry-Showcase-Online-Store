package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.streamingaead.StreamingAeadKeyTemplates;

public class EncryptDoc {
	public static void main(String[] args) {

		try {
			
			String associatedData = "wowMagicWord";
			int CHUNK_SIZE = 8;
			String cipherTextFileRoot = System.getProperty("user.dir") + "\\CipherTextFile.txt";
			
			
			// when the data to be encrypted is too large to be processed in a single step,
			// we can use the streaming AEAD primitive:
			KeysetHandle streamingAEADkeysetHandle = KeysetHandle
					.generateNew(StreamingAeadKeyTemplates.AES128_CTR_HMAC_SHA256_4KB);

			StreamingAead streamingAead = streamingAEADkeysetHandle.getPrimitive(StreamingAead.class);

			@SuppressWarnings("resource")
			FileChannel cipherTextDestination = new FileOutputStream(cipherTextFileRoot).getChannel();
			WritableByteChannel encryptingChannel = streamingAead.newEncryptingChannel(cipherTextDestination,
					associatedData.getBytes());

			ByteBuffer buffer = ByteBuffer.allocate(CHUNK_SIZE);
			InputStream in = new FileInputStream("plainTextFile");

			while (in.available() > 0) {
				in.read(buffer.array());
				encryptingChannel.write(buffer);
			}

			in.close();
			encryptingChannel.close();
			cipherTextDestination.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
