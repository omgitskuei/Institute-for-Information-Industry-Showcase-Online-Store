package util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import model.product.ProductBean;

public class ImgConversion {
	// Local fields

	// Constructors

	// Executable
	public static void main(String[] args) {

	}

	// 圖片存路徑
	public String addProductImg(MultipartFile file, HttpServletRequest request) {
		if (file == null) {
			return "";
		}
		if (file.getOriginalFilename().isEmpty()) {
			return "";
		}
		String fileType = file.getContentType(); // 獲得檔案型別
		if (fileType.equals("image/jpeg") || fileType.equals("image/gif")) {
			// generate random code thats 30 char long, using num and lower letters
			// total possible permutations: 17779336731917059409510400000
			GetCode gen = new GetCode(15, true, true, false);		
			String fileName = gen.generateCode();
			String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/resources/images/productsImage/");
			String fileExt;
			if (fileType.equals("image/jpeg")) {
				fileExt = ".jpg";
			} else {
				fileExt = ".png";
			}
			try {
				file.transferTo(new File(filePath + "/" + fileName + "." + fileExt));// 以絕對路徑儲存重名命後的圖片
			} catch (Exception e) {
				e.printStackTrace();
			}
			String path = "/images/" + filePath + "." + fileExt;// 把圖片儲存路徑儲存到資料庫
			return path;
		} else {	// fileType wrong, return preset.jpg
			System.out.println("	File type is wrong, must be JPG and PNG");
			String presetPic = "/images/productsImage/preset.png";
			return presetPic;
		}

	}

	// -- Convert JPG to byte[]
	public byte[] jpgToByte(String filePath) {
		// Java provides ImageIO class for reading and writing an image.
		// To convert an image to a byte array –
		try {
			System.out.println("BEGIN: util.ImgConversion.jpgToByte(String)");
			System.out.println("Target file path: " + filePath);
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
			System.out.println("byte[]: " + data);
			System.out.println("FINISH: util.ImgConversion.jpgToByte(String)");
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("EXCEPTION: Returning Null");
			System.out.println("FINISH: util.ImgConversion.jpgToByte(String)");
			return null;
		}
	}

	// -- Convert PNG to byte[]
	public byte[] pngToByte(String filePath) {
		// Java provides ImageIO class for reading and writing an image.
		// To convert an image to a byte array –
		try {
			System.out.println("BEGIN: util.ImgConversion.jpgToByte(String)");
			System.out.println("Target file path: " + filePath);
			// Read the image using the read() method of the ImageIO class.
			BufferedImage buffImage = ImageIO.read(new File(filePath));
			// Create a ByteArrayOutputStream object.
			ByteArrayOutputStream byteArrayOStream = new ByteArrayOutputStream();
			// Write the image to the ByteArrayOutputStream object created above using the
			// write() method of the ImageIO class.
			ImageIO.write(buffImage, "png", byteArrayOStream);
			// Finally convert the contents of the ByteArrayOutputStream to a byte array
			// using the toByteArray() method.
			byte[] data = byteArrayOStream.toByteArray();
			byteArrayOStream.close();

			System.out.println("byte[]: " + data);
			System.out.println("FINISH: util.ImgConversion.jpgToByte(String)");
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("EXCEPTION: Returning Null");
			System.out.println("FINISH: util.ImgConversion.jpgToByte(String)");
			return null;
		}
	}

	// Convert byte[] to PNG
	public void s() throws Exception {
		// Specify where to find the image
		File file = new File("/images/StarkrimsonPear.png");

		BufferedImage bImage = ImageIO.read(file);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos);
		byte[] data = bos.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		BufferedImage bImage2 = ImageIO.read(bis);
		System.out.println("image created");
	}
}
