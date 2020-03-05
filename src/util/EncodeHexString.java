package util;

public class EncodeHexString {
	// Local fields
	String hexString;
	byte[] byteArray;

	// Constructors
	public EncodeHexString() {
		System.out.println("BEGIN: util.EncodeHexString()");
	}

	public EncodeHexString(String aHexString) {
		System.out.println("BEGIN: util.EncodeHexString(String aHexString)");
		this.hexString = aHexString;
		//HexStringToByteArray(this.hexString);
	}

	public EncodeHexString(byte[] aByteArray) {
		System.out.println("BEGIN: util.EncodeHexString(byte[] aByteArray)");
		this.byteArray = aByteArray;
		//byteArrayToHexString(this.byteArray);
	}

	public static void main(String[] args) {

		// Create a new instance of this class to use its hexString --> byte[] method
		// Passes a String to the constructor, setting its local field hexstring
		EncodeHexString thisClass = new EncodeHexString("1254324567");
		// Check in console what the String hexString is
		System.out.println("aHexString: " + thisClass.hexString);
		byte[] resultByteArray = thisClass.HexStringToByteArray(thisClass.hexString);
		
		System.out.println("");
		
		// Create a new instance of this class to use its byte[] --> hexString method
		// Passes a byte[] to the constructor, setting its local field byteArray
		EncodeHexString thisClass1 = new EncodeHexString(resultByteArray);
		// Check in console what the byte[] byteArray is
		System.out.println("aByteArray: " + thisClass1.byteArray);
		String resultHexString = thisClass1.byteArrayToHexString(thisClass1.byteArray);
	}

	// Methods
	public String byteArrayToHexString(byte[] byteArray) {
		System.out.println("	Converting "+byteArray+" to HexString");
		String result = new String(byteArray);
		System.out.println("	Result HexString: "+result);
		return result;
	}

	public byte[] HexStringToByteArray(String hexString) {
		System.out.println("	Converting "+hexString+" to byte[]");
		byte[] bytes = hexString.getBytes();
		System.out.println("	Result byte[]: "+bytes);
		return bytes;
	}

	public String getHexString() {
		return hexString;
	}

	public void setHexString(String HexString) {
		this.hexString = HexString;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

}
