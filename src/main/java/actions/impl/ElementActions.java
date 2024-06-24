package actions.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.IElementActions;
import webelement.ElementLocator;
import webelement.ObjectRepositoryReader;

/**
 * ElementActions class in Actions package contains methods to check if element
 * is displayed , check if element is enabled , get count of list of elements ,
 * get element attribute , get element color
 * 
 * @author Toka.Ashraf
 *
 */

public class ElementActions implements IElementActions {

	Logger log = LogManager.getLogger(ElementActions.class);

	ObjectRepositoryReader objectRepositoryReader = new ObjectRepositoryReader();

	/**
	 * check if element is displayed. returns boolean
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */

	public boolean isElementDisplayed(WebDriver driver, ElementLocator element) {
		log.info("check if elment is displayed");
		boolean displayed = false;

		log.info("return  boolean true if element is displayed");
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		displayed = webElement.isDisplayed();
		log.info("element is displayed : {}", displayed);
		return displayed;
	}

	/**
	 * check if element is enabled. returns boolean
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public boolean isElementEnabled(WebDriver driver, ElementLocator element) {
		log.info("check if elment is enabled");
		boolean enabled = false;
		log.info("return  boolean true if element is enabled");
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		enabled = webElement.isEnabled();
		log.info("elment enabled is : {}", enabled);

		return enabled;
	}

	/**
	 * returns count of elements with the same locator. returns int
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public int getCountOfElements(WebDriver driver, ElementLocator element) {
		int x = 0;
		List<WebElement> webElements = objectRepositoryReader.getWebElements(driver, element);

		x = webElements.size();
		log.info("the size of list of elements : {}", x);

		return x;
	}

	/**
	 * returns element attribute. returns string
	 * 
	 * @param element       the web element object
	 * @param driver        the web driver used to open current page.
	 * @param attributeName the value of this attribute will be returned.
	 */

	public String getAttribute(WebDriver driver, ElementLocator element, String attributeName)

	{
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		log.info("get elment attribute value");
		log.info("get element value for {}", attributeName);
		String attr = webElement.getAttribute(attributeName);
		log.info("attribute value is {}", attr);

		return attr;

	}

	/**
	 * returns element color. returns string
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */

	public String getElementColor(WebDriver driver, ElementLocator element)

	{
		log.info("get element background color");
		String color = getAttribute(driver, element, "background-color");
		String[] numbers = color.replace("rgba(", "").replace(")", "").split(",");
		log.info("split rgb to get color");
		int r = Integer.parseInt(numbers[0].trim());
		int g = Integer.parseInt(numbers[0].trim());
		int b = Integer.parseInt(numbers[0].trim());
		log.info("r: %s{0} and g: %s{1} and %sb:{2} ", r, g, b);
		String actualColor = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
		log.info("Element actual color is : {}", actualColor);

		return actualColor;

	}

	/**
	 * check if element is selected. returns boolean
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */

	public boolean isElementSelected(WebDriver driver, ElementLocator element) {
		log.info("check if elment is selected");
		boolean selected = false;

		log.info("return  boolean true if element is selected");
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		selected = webElement.isSelected();
		log.info("element is selected : {}", selected);
		return selected;
	}

}
