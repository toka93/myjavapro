package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesReader {

	static	Logger log = LogManager.getLogger(PropertiesReader.class);

	static String path = System.getProperty("user.dir");

	

	private static StringBuffer propertyFilePath = new StringBuffer()
	        .append(path)
	        .append(File.separator)
	        .append("Profiles")
	        .append(File.separator)
	       .append("config.properties");
	/*
	 * comment lines from 19 to 24 and add line : 
	 * private final String propertyFilePath =System.getProperty("propertyFile");
     *  this way you can pass properties file from cmd
	 */
	
    public static String getProperty( String propertyName) {
        Properties prop = new Properties();
      
        try (FileInputStream fis = new FileInputStream((propertyFilePath).toString())) {
            prop.load(fis);
        } catch (IOException e) {
           log.info("exception :{}",e.getMessage());
        }
        return prop.getProperty(propertyName);
    }
}
