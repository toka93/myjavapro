package configuration;

import java.security.GeneralSecurityException;
import static configuration.ConfigProperties.securityKey;
public class TryEnc {

	public static void main(String[] args) throws GeneralSecurityException

	{

	
		 String  inputValue= "P@ssw0rd";
		 String val= AESUtils.encrypt(securityKey(), inputValue);
		  System.out.println(val);
		

	}

}
