package encryption;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


/**
 * @author JiaBing
 * @date 2019-09-26 13:43:19 
 */
public class MD5Encryption {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest md5Encryption = MessageDigest.getInstance("MD5");
		// String plainText = "pa$$w0rd1";
		System.out.println("Please input:");
		Scanner in = new Scanner(System.in);
		String plainTxt = in.nextLine();
		in.close();
		md5Encryption.update(plainTxt.getBytes());
		byte[] digest = md5Encryption.digest();
		System.out.println("output: \n"+ new BigInteger(1, digest).toString(16));
		
	}

}
