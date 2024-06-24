package connections;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.amazonaws.services.s3.AmazonS3;

public interface IAWSConnection {

	
	public AmazonS3 createS3Client() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException ;
	
	public List<String> listBuckets() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

    public List<String> getObjectsWithoutPrefix(Date dateToday, String rawbucketNameTest) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

    public List<String> getObjectsKeys(Date dateToday, String rawbucketNameTest) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

    public List<String> retrieveObjectKeysfromBucket(Date dateExpected, String bucketName) throws InterruptedException;
}
