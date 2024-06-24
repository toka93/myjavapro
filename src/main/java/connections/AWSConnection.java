package connections;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import actions.impl.Helpers;
import actions.impl.WaitActions;
import static configuration.ConfigProperties.*;
public class AWSConnection implements IAWSConnection {

	static Logger log = LogManager.getLogger(AWSConnection.class);
	 AmazonS3 s3client;
	Helpers helpers = new Helpers();

	WaitActions waitAc = new WaitActions();

	/**
	 * create amazon s3 client
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * 
	 */
//to create s3 client
	public AmazonS3 createS3Client() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		AWSCredentials credentials = new BasicAWSCredentials(aWSAccessKey(), aWSSecretKey());

		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.EU_CENTRAL_1)
				.build();

	}

	/**
	 * retrieve buckets available and accessible for the user.
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * 
	 * 
	 */

	public List<String> listBuckets() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		List<String> bucketNames = new LinkedList<>();
		s3client = createS3Client();
		try {
			List<Bucket> buckets = s3client.listBuckets();
			{
				for (Bucket bucket : buckets) {
					bucketNames.add(bucket.getName());
					log.info(bucket.getName());
				}
			}
		} catch (Exception e) {
			log.info(e);
		} finally {
			s3client.shutdown();
		}

		return bucketNames;
	}

	/**
	 * 
	 * Retrieves a list of S3 object keys from the specified bucket that were last
	 * modified on or after the specified date, without a prefix. The list is sorted
	 * and each object key is logged. If an exception occurs, it is logged. Returns
	 * a list of S3 object keys without a prefix
	 * 
	 * @param date
	 * @param bucketNameTest bucket name.
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public List<String> getObjectsWithoutPrefix(Date date, String bucketNameTest) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		List<String> objectKeyys = new LinkedList<>();
		s3client = createS3Client();

		try {
			Thread.sleep(10000);
			ObjectListing objectListing = s3client.listObjects(bucketNameTest);

			for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
				Date modifiedDate = os.getLastModified();
				if (modifiedDate.compareTo(date) >= 0) {
					String keyvalue = os.getKey();
					keyvalue = keyvalue.replaceAll(".+-", "").trim();
					log.info("object :{}" , os.getKey());
					log.info("object without prefix :{}" , keyvalue);
					if (objectKeyys.contains(keyvalue))
						log.info("already exists");
					else {
						log.info("not added yet");
						objectKeyys.add(keyvalue);

					}
				}
			}
			Collections.sort(objectKeyys);
			log.info("list retrieved is sorted");
		} catch (Exception e) {
			log.info("exception :{}" , e.getMessage());

		} finally {
			s3client.shutdown();
		}

		return objectKeyys;

	}

	// get object keys after specific date
	/**
	 * Retrieves a list of S3 object keys from the specified bucket that were last
	 * modified on or after the specified date. The list is sorted and each object
	 * key is logged. If an exception occurs, it is logged. Returns a list of S3
	 * object keys.
	 * 
	 * @param date
	 * @param bucketNameTest bucket name.
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public List<String> getObjectsKeys(Date date, String rawbucketNameTest) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		List<String> objectKeyys = new LinkedList<>();
		s3client = createS3Client();
		try {
			Thread.sleep(10000);
			ObjectListing objectListing = s3client.listObjects(rawbucketNameTest);

			for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
				Date modifiedDate = os.getLastModified();
				if (modifiedDate.compareTo(date) >= 0) {
					String keyvalue = os.getKey();
					log.info("object value :{}" , keyvalue);
					if (objectKeyys.contains(keyvalue))
						log.info("already exists");
					else {
						log.info("not added yet");
						objectKeyys.add(keyvalue);

					}
				}
			}
			Collections.sort(objectKeyys);
			log.info("list retrieved is sorted");
		} catch (Exception e) {
			log.info("exception :{}" ,e.getMessage());

		} finally {
			s3client.shutdown();
		}
		return objectKeyys;
	}

	/**
	 * Retrieves a list of S3 object keys from the specified bucket that were last
	 * modified on or after the specified date, waits for 30 seconds, and then sorts
	 * the list. If an exception occurs, it is logged. Returns a list of S3 object
	 * keys.
	 * 
	 * @param date
	 * @param bucketNameTest bucket name.
	 */

	// retrieve object keys from bucket
	public List<String> retrieveObjectKeysfromBucket(Date dateExpected, String bucketName) throws InterruptedException {

		log.info("retrieve List from bucket :%s{0} added after Date:%s{1} "  ,bucketName,dateExpected);
		List<String> actualObjects = new LinkedList<>();
		waitAc.wait(30);

		try {

			actualObjects = getObjectsKeys(dateExpected, bucketName);
			log.info("size of s3 rerieved: {}" , actualObjects.size());
			Collections.sort(actualObjects);
			log.info("list retrieved from s3 sorted ");
		} catch (Exception e) {
			log.info("exc:{}", e.toString());

		}
		return actualObjects;

	}

}
