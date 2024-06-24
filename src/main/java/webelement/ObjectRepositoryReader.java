package webelement;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectRepositoryReader implements IObjectRepositoryReader{
	static Logger log = LogManager.getLogger("config.properties file");

	
	static StringBuffer stringBuffer = new StringBuffer()
     .append(System.getProperty("user.dir"))
             .append(File.separator)
             .append("src")
             .append(File.separator)
             .append("main")
             .append(File.separator)
             .append("resources")
             .append(File.separator)
             .append("objectrepository")
             .append(File.separator);

     String objectRepoPath2 = stringBuffer.toString();
	
	// Reads the JSON file for the given page and returns a Map of locator names and
	// values
	public  Map<String, String> readRepoPage(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> objectRepository = null;
		try {
			objectRepository = mapper.readValue(new File(fileName), new TypeReference<Map<String, String>>() {
			});
		} catch (IOException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}  
		return objectRepository;
	}

	// Parses the given locator string and returns a list containing its type and
	// value

	public  List<String> readLocatorTypeandValue(String value) {
		List<String> values = new LinkedList<>();
		int index = value.indexOf("=");
		if (index != -1) {
			String locType = value.substring(0, index);
			String locValue = value.substring(index + 1);

			values.add(locType);
			values.add(locValue);

		} else {
			log.info("Equal character not found in the string.");
		}

		return values;
	}

	// Reads the locators for the given page from the JSON file and returns a list
	// of ElementLocator objects
	public  List<ElementLocator> readLocators(String pageName) {

		Map<String, String> objectRepository = readRepoPage(objectRepoPath2 + pageName + ".json");

		List<ElementLocator> elements = new LinkedList<>();

		for (Map.Entry<String, String> entry : objectRepository.entrySet()) {
			ElementLocator ele = new ElementLocator();

			ele.setLocatorName(entry.getKey());

			List<String> values = readLocatorTypeandValue(entry.getValue());
			ele.setLocatorType(values.get(0));
			ele.setLocatorValue(values.get(1));

			elements.add(ele);
		}
		return elements;
	}

	// Returns a single WebElement based on the given locator and locator strategy
	// function
	public  WebElement webEl(WebDriver driver, Function<String, By> locatorStrategy, String locator) {
		return driver.findElement(locatorStrategy.apply(locator));
	}

	// Returns a list of WebElements based on the given locator and locator strategy
	// function
	public  List<WebElement> webEls(WebDriver driver, Function<String, By> locatorStrategy, String locator) {
		return driver.findElements(locatorStrategy.apply(locator));
	}

	// Returns the ElementLocator object with the given locator name from the list
	// of locators
	public  ElementLocator getWebElementObject(List<ElementLocator> locators, String locatorname) {

		return locators.stream()
				.filter(elementLocator -> elementLocator.getLocatorName().equals( locatorname)).findFirst().orElse(null);

	}

	// Returns a single WebElement based on the given ElementLocator object and
	// WebDriver instance
	public  WebElement getWebElement(WebDriver driver, ElementLocator elementlocatorObject) {
		WebElement webElement = null;

		String locatorType = elementlocatorObject.getLocatorType();
		switch (locatorType) {
		case "id":
			webElement = webEl(driver, By::id, elementlocatorObject.getLocatorValue());
			break;
		case "name":
			webElement = webEl(driver, By::name, elementlocatorObject.getLocatorValue());
			break;
		case "className":
			webElement = webEl(driver, By::className, elementlocatorObject.getLocatorValue());
			break;
		case "tagName":
			webElement = webEl(driver, By::tagName, elementlocatorObject.getLocatorValue());
			break;
		case "linkText":
			webElement = webEl(driver, By::linkText, elementlocatorObject.getLocatorValue());
			break;
		case "partialLinkText":
			webElement = webEl(driver, By::partialLinkText, elementlocatorObject.getLocatorValue());
			break;
		case "cssSelector":
			webElement = webEl(driver, By::cssSelector, elementlocatorObject.getLocatorValue());
			break;
		case "xpath":
			webElement = webEl(driver, By::xpath, elementlocatorObject.getLocatorValue());
			break;
		default:
			throw new IllegalArgumentException("Invalid locator type: " + locatorType);
		}

		return webElement;

	}
	// Returns a list of WebElements based on the given ElementLocator object and
	// WebDriver instance

	public  List<WebElement> getWebElements(WebDriver driver, ElementLocator elementlocatorObject) {
		List<WebElement> webElements;

		String locatorType = elementlocatorObject.getLocatorType();
		switch (locatorType) {
		case "id":
			webElements = webEls(driver, By::id, elementlocatorObject.getLocatorValue());
			break;
		case "name":
			webElements = webEls(driver, By::name, elementlocatorObject.getLocatorValue());
			break;
		case "className":
			webElements = webEls(driver, By::className, elementlocatorObject.getLocatorValue());
			break;
		case "tagName":
			webElements = webEls(driver, By::tagName, elementlocatorObject.getLocatorValue());
			break;
		case "linkText":
			webElements = webEls(driver, By::linkText, elementlocatorObject.getLocatorValue());
			break;
		case "partialLinkText":
			webElements = webEls(driver, By::partialLinkText, elementlocatorObject.getLocatorValue());
			break;
		case "cssSelector":
			webElements = webEls(driver, By::cssSelector, elementlocatorObject.getLocatorValue());
			break;
		case "xpath":
			webElements = webEls(driver, By::xpath, elementlocatorObject.getLocatorValue());
			break;
		default:
			throw new IllegalArgumentException("Invalid locator type: " + locatorType);
		}

		return webElements;
	}

}
