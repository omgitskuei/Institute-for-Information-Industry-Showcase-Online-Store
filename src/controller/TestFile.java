package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//圖片檔案,與 byte[] 互轉
public class TestFile {


	public static void main(String[] args) throws Exception {
		TestFile test = new TestFile();
		File imgFilePath = new File("C:\\Users\\User\\Pictures\\Saved Pictures\\111.jpg");
		byte[] img1 = test.jpgToByte(imgFilePath);
		test.byteToFile(img1);
		
		System.out.println("File path= "+imgFilePath);
		System.out.println("Byte="+img1);
		
		byte[] img2 = test.pngToByte(imgFilePath);
		test.byteToFile(img2);
	}

	public byte[] pngToByte(File imgFilePath) {
		System.out.println("BEGIN: util.pngToByte(File)");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			System.out.println("Target File path: "+imgFilePath);
			BufferedImage bi = ImageIO.read(imgFilePath);
			ImageIO.write(bi, "png", baos);
			byte[] bytes = baos.toByteArray();
			System.out.println("Result byte[]: "+bytes);
			baos.close();
			System.out.println("FINISH: util.pngToByte(File)");
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Conversion FAILED, return NULL");
			System.out.println("FINISH: util.pngToByte(File)");
			return null;
		}
	}
	
	public byte[] jpgToByte(File imgFilePath) {
		System.out.println("BEGIN: util.pngToByte(File)");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			System.out.println("Target File path: "+imgFilePath);
			BufferedImage bi = ImageIO.read(imgFilePath);
			ImageIO.write(bi, "jpeg", baos);
			byte[] bytes = baos.toByteArray();
			System.out.println("Result byte[]: "+bytes);
			baos.close();
			System.out.println("FINISH: util.pngToByte(File)");
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Conversion FAILED, return NULL");
			System.out.println("FINISH: util.pngToByte(File)");
			return null;
		}
	}
	
	public File byteToFileJPG(byte[] imgByteArray) { 
		ByteArrayInputStream bais = new ByteArrayInputStream(imgByteArray);   
        try {   
        	
        	 BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
        	 ByteArrayOutputStream bos = new ByteArrayOutputStream();
        	 byte [] data = bos.toByteArray();
             ImageIO.write(bImage, "jpg", bos );
             
            
             ByteArrayInputStream bis = new ByteArrayInputStream(data);
             BufferedImage bImage2 = ImageIO.read(bis);
             ImageIO.write(bImage2, "jpg", new File("output.jpg") );
             System.out.println("image created");
        	
        	
        	BufferedImage bi1 =ImageIO.read(bais); 
            File w2 = new File("C:\\Users\\User\\Pictures\\Saved Pictures\\111.jpg");//可以是jpg,png,gif格式   
            ImageIO.write(bi1, "jpg", w2);//不管輸出什麼格式圖片,此處不需改動   
            return w2;
        } catch (IOException e) {   
            e.printStackTrace(); 
            return null;	
        }
    }  
	
	public File byteToFilePNG(byte[] imgByteArray) { 
		ByteArrayInputStream bais = new ByteArrayInputStream(imgByteArray);   
        try {   
        	BufferedImage bi1 =ImageIO.read(bais); 
            File w2 = new File("C:\\Users\\User\\Pictures\\Saved Pictures\\111.jpg");//可以是jpg,png,gif格式   
            ImageIO.write(bi1, "png", w2);//不管輸出什麼格式圖片,此處不需改動   
            return w2;
        } catch (IOException e) {   
            e.printStackTrace(); 
            return null;	
        }
    } 

}