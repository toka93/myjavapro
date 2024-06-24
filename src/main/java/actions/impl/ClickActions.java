package actions.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import actions.IClickActions;
import webelement.ElementLocator;
import webelement.ObjectRepositoryReader;

/**
 * ClickActions class in Actions Package contains method such as (click on
 * element , click on elment using enter , click on elements using actions ,
 * double click on elment right click on element , drag and drop element , hover
 * and click on element , Click on Esc using actions ,
 * 
 * @author Toka.Ashraf
 *
 */

public class ClickActions implements IClickActions {
	Logger log = LogManager.getLogger(ClickActions.class);
	ObjectRepositoryReader objectRepositoryReader = new ObjectRepositoryReader();

	/**
	 * click on web element.
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public void clickOnElement(WebDriver driver,ElementLocator element) {
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);
		log.info("click on element");
		webElement.click();

	}

	/**
	 * click on web element using Enter key.
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public void clickOnElementUsingEnterKey(WebDriver driver,ElementLocator element) {

		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		log.info("click on element using enter key");
		webElement.sendKeys(Keys.ENTER);

	}

	/**
	 * click on web element using Actions class.
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public void clickOnElementUsingActions(WebDriver driver,ElementLocator element) {
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		log.info("click on element using Actions");
		Actions builder = new Actions(driver);

		builder.click(webElement).build().perform();

	}

	/**
	 * double click on web element using Actions class.
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public void doubleClickOnElement(WebDriver driver,ElementLocator element) {
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		log.info("double click on Element using Actions");
		Actions builder = new Actions(driver);

		builder.doubleClick(webElement);
		builder.perform();

	}

	/**
	 * right click on web element using Actions class.
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public void rightClickOnElement(WebDriver driver,ElementLocator element) {
		log.info("right click on element");
		Actions builder = new Actions(driver);
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		builder.contextClick(webElement);
		builder.perform();
	}

	/**
	 * hover and click on web element using Actions class.
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */
	public void hoverAndClickOnElement(WebDriver driver,ElementLocator element) {
		log.info("hover and click  on Element using Actions");
		Actions builder = new Actions(driver);
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		log.info("move to Element as hover then click  using Actions");
		builder.moveToElement(webElement).click().build().perform();

	}

	/**
	 * drag web element1 and drop it in web element2 using Actions class.
	 * 
	 * @param element1 the web element object needs to be dragged
	 * @param driver   the web driver used to open current page.
	 * @param element2 the web element object that the first element will be dropped
	 *                 at.
	 */
	public void dragAndDropElment(WebDriver driver,ElementLocator element1,  ElementLocator element2) {

		log.info("drag and drop Element using Actions");
		WebElement webElement1 = objectRepositoryReader.getWebElement(driver, element1);
		WebElement webElement2 = objectRepositoryReader.getWebElement(driver, element2);

		Actions builder = new Actions(driver);
		log.info("drag first elment and drop it next to second   Element using Actions");
		builder.dragAndDrop(webElement1, webElement2);
		builder.build().perform();

	}

	/**
	 * click on Esc using Actions class.
	 * 
	 * @param driver the web driver used to open current page.
	 */
	public void clickonEsc(WebDriver driver) {

		log.info("click on Esc");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	/**
	 * click on Enter using Actions class.
	 * 
	 * @param driver the web driver used to open current page.
	 */
	public void clickonEnter(WebDriver driver) {

		log.info("click on enter");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * drag and drop using pixels.
	 * 
	 * @param driver           the web driver used to open current page.
	 * @param from             element object that will be dragged.
	 * @param sourcePixel      pixels of the source.
	 * @param destinationPixel pixel of the destination.
	 */

	public void dragAndDropPixels(WebDriver driver, ElementLocator from, int sourcePixel, int destinationPixel) {
		WebElement webElement = objectRepositoryReader.getWebElement(driver, from);

		log.info("drag and drop using pixels");
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(webElement, sourcePixel, destinationPixel).build().perform();

	}

	/**
	 * click on web element using js actions.
	 * 
	 * @param element the web element object
	 * @param driver  the web driver used to open current page.
	 */

	public void clickOnElementUsingJavaScript(WebDriver driver,ElementLocator element) {
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);

		log.info("click on element using JavaScriptExecuutor");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", webElement);

	}

	
}
