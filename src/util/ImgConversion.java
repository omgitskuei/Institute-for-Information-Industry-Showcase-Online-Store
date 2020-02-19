package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgConversion {
	// Local fields

	// Constructors

	// Methods

	public byte[] jpgToByte(String filePath) {
		// Convert JPG to byte[]
		// Java provides ImageIO class for reading and writing an image.
		// To convert an image to a byte array –
		try {
			System.out.println("BEGIN: util.ImgConversion.jpgToByte(String)");
			System.out.println("Target file path: "+filePath);
			// Read the image using the read() method of the ImageIO class.
			BufferedImage buffImage = ImageIO.read(new File(filePath));
			// Create a ByteArrayOutputStream object.
			ByteArrayOutputStream byteArrayOStream = new ByteArrayOutputStream();
			// Write the image to the ByteArrayOutputStream object created above using the
			// write() method of the ImageIO class.
			ImageIO.write(buffImage, "jpg", byteArrayOStream);
			// Finally convert the contents of the ByteArrayOutputStream to a byte array
			// using the toByteArray() method.
			byte[] data = byteArrayOStream.toByteArray();
			System.out.println("byte[]: "+data);
			System.out.println("FINISH: util.ImgConversion.jpgToByte(String)");
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("EXCEPTION: Returning Null");
			System.out.println("FINISH: util.ImgConversion.jpgToByte(String)");
			return null;
		}
	}

	public void pngToByte() {
		System.out.println("");
	}

	// Executable
	public static void main(String[] args) {

		// Convert PNG to byte[]

	}

}
