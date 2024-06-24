package configuration;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
 
public class AESUtils {
	
	
	
	  private static final String ALGORITHM = "AES";
	    private static final String TRANSFORMATION = "AES";
	 
	    public static String encrypt(String key,  String inputValue)
	            throws  InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		   
		            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		    
		            byte[] outputBytes = cipher.doFinal(inputValue.getBytes());
		             
	                    
	    	return Base64.getEncoder().encodeToString(outputBytes);
	    	
	    }
	 
	    public static String decrypt(String key,  String inputValue)
	            throws  InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
			   
	            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	    
	          
	             
	            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(inputValue));
	            return new String(cipherText);
	    }
	 

	    
	    
	    
	

}