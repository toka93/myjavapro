package configuration;

import static java.util.Objects.isNull;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigProperties {

	static Logger log = LogManager.getLogger(ConfigProperties.class);

	private static final String APP_URL = "AppURL";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String DB_CLASS = "DBclass";
	private static final String DBHost = "DBHost";
	private static final String DB_USER = "DBuser";
	private static final String DB_PASSWORD = "DBpassword";
	private static final String SCHEMA = "Schema";
	private static final String BROWSER = "Browser";
	private static final String TYPE = "Type";
	private static final String HEADLESS = "Headless";
	private static final String SELENIUM_HUB_HOST = "seleniumHubHost";
	private static final String ACCESS_KEY = "AccessKey";
	private static final String SECRET_KEY = "SecretKey";
	private static final String BUCKET = "Bucket";
	private static final String BASE_URL = "BaseURL";
	private static final String LOG_REQUEST = "logRequest";
	private static final String LOG_RESPONSE = "logResponse";
	private static final String Key = "Key";

	public static String securityKey() {
		String systemProperty = System.getProperty(Key);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(Key);
	}

	public static String appURL() {
		String systemProperty = System.getProperty(APP_URL);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(APP_URL);
	}

	public static String username() {
		String systemProperty = System.getProperty(USERNAME);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(USERNAME);
	}

	public static String userPass() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String systemProperty = System.getProperty(PASSWORD);
		//PropertiesReader.getProperty(AESUtils.decrypt(Key,PASSWORD))
		return !isNull(systemProperty) ? systemProperty : AESUtils.decrypt(securityKey(),PropertiesReader.getProperty(PASSWORD));
	}

	public static String dBClass() {
		String systemProperty = System.getProperty(DB_CLASS);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(DB_CLASS);
	}

	public static String dBurl() {
		String systemProperty = System.getProperty(DBHost);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(DBHost);
	}

	public static String dBuser() {
		String systemProperty = System.getProperty(DB_USER);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(DB_USER);
	}

	public static String dBpassword() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String systemProperty = System.getProperty(DB_PASSWORD);
		return !isNull(systemProperty) ? systemProperty : AESUtils.decrypt(securityKey(),PropertiesReader.getProperty(DB_PASSWORD));
	}

	public static String dBSchema() {
		String systemProperty = System.getProperty(SCHEMA);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(SCHEMA);
	}

	public static String browser() {
		String systemProperty = System.getProperty(BROWSER);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(BROWSER);
	}

	public static String type() {
		String systemProperty = System.getProperty(TYPE);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(TYPE);
	}

	public static String seleniumHub() {
		String systemProperty = System.getProperty(SELENIUM_HUB_HOST);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(SELENIUM_HUB_HOST);
	}

	public static String headlessState() {
		String systemProperty = System.getProperty(HEADLESS);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(HEADLESS);
	}

	public static String aWSAccessKey() {
		String systemProperty = System.getProperty(ACCESS_KEY);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(ACCESS_KEY);
	}

	public static String aWSSecretKey() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String systemProperty = System.getProperty(SECRET_KEY);
		return !isNull(systemProperty) ? systemProperty : AESUtils.decrypt(securityKey(),PropertiesReader.getProperty(DB_PASSWORD));
	}

	public static String bucket() {
		String systemProperty = System.getProperty(BUCKET);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(BUCKET);
	}

	public static String aPIbaseURL() {
		String systemProperty = System.getProperty(BASE_URL);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(BASE_URL);
	}

	public static String logReguest() {
		String systemProperty = System.getProperty(LOG_REQUEST);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(LOG_REQUEST);
	}

	public static String logResponse() {
		String systemProperty = System.getProperty(LOG_RESPONSE);
		return !isNull(systemProperty) ? systemProperty : PropertiesReader.getProperty(LOG_RESPONSE);
	}

}
