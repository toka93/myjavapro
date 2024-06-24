package actions.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.ITextActions;
import webelement.ElementLocator;
import webelement.ObjectRepositoryReader;
/**
 * TextActions class in the Actions package contains methods to enter text into a field and 
 * retrieve the text of an element.
 * 
 * Note: All methods take an ElementLocator and a WebDriver object as arguments to identify the 
 * WebElement and perform the action on it.
 * 
 * Note: This class assumes that the WebElement is a input field or a text element.
 * 
 * Note: This class assumes that the object repository file contains a key-value pair for the given locator.
 * 
 * Note: This class throws a RuntimeException if it is unable to read the object repository file.
 * 
 * Note: This class logs the actions performed on the WebElement.
 * 
 * Note: The `enterTextinField` method assumes that the WebElement is an input field.
 * 
 * Note: The `getElementText` method assumes that the WebElement is a text element.
 * 
 * @author Tok
 *
 */
	
public class TextActions implements ITextActions  {
	Logger log = LogManager.getLogger(TextActions.class);
	ObjectRepositoryReader objectRepositoryReader = new ObjectRepositoryReader();

	/**
	 * Enters text into the given WebElement.
	 * @param element the ElementLocator of the WebElement to enter text into.
	 * @param text the text to enter into the WebElement.
	 * @param driver the WebDriver object.
	 */
	public void enterTextinField(WebDriver driver,ElementLocator element, String text)

	{WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
		log.info("send text");
		webElement.sendKeys(text);

	}
	/**
	 * Retrieves the text of the given WebElement.
	 * @param element the ElementLocator of the WebElement to retrieve text from.
	 * @param driver the WebDriver object.
	 * @return the text of the given WebElement.
	 */

	public String getElementText(WebDriver driver,ElementLocator element)

	{
		WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
		log.info("get text of element");
		return webElement.getText();
	}

	

}
