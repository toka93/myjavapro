package actions.impl;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import actions.IWaitActions;
import webelement.ElementLocator;
import webelement.ObjectRepositoryReader;

/**
 * WaitActions class in Actions package contains all wait metods ; wait for
 * element to be visible , to be clickable, wait for frame and switch to it ,
 * wait for alert 
 * 
 * @author Toka.Ashraf
 *
 */

public class WaitActions implements IWaitActions {
	ObjectRepositoryReader objectRepositoryReader = new ObjectRepositoryReader();

	Logger log = LogManager.getLogger(WaitActions.class);
	/**
	 * Wait for an element to be visible on the web page.
	 * @param driver The `WebDriver` instance to use.
	 * @param element The `ElementLocator` instance representing the element to wait for.
	 * @param timeoutInSeconds The maximum number of seconds to wait for the element to be visible.
	 */

	public void waitForElementToBeVisible(WebDriver driver, ElementLocator element, int timeoutInSeconds) {
	      WebElement webElement=objectRepositoryReader.getWebElement( driver, element);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		log.info("wait for element to be visible");
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	/**
	 * Wait for an element to be clickable on the web page.
	 * @param driver The `WebDriver` instance to use.
	 * @param element The `ElementLocator` instance representing the element to wait for.
	 * @param timeoutInSeconds The maximum number of seconds to wait for the element to be clickable.
	 */
	public void waitForElementToBeClickable(WebDriver driver, ElementLocator element, int timeoutInSeconds) {
	      WebElement webElement=objectRepositoryReader.getWebElement( driver, element);

		log.info("wait for element to be clickable");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

		wait.until(ExpectedConditions.elementToBeClickable(webElement));

	}
	/**
	 * Wait for an alert to be present on the web page.
	 * @param driver The `WebDriver` instance to use.
	 * @param timeoutInSeconds The maximum number of seconds to wait for the alert to be present.
	 */
	public void waitForAlertToBePresent(WebDriver driver, int timeoutInSeconds) {

		log.info("wait for alert to be present");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

		wait.until(ExpectedConditions.alertIsPresent());

	}
	/**
	 * Wait for a frame to be available and switch to it on the web page.
	 * @param driver The `WebDriver` instance to use.
	 * @param element The `ElementLocator` instance representing the frame to wait for.
	 * @param timeoutInSeconds The maximum number of seconds to wait for the frame to be available.
	 */
	public void waitForFrameAndSwichtoit(WebDriver driver, ElementLocator element, int timeoutInSeconds) {
		WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		log.info("wait for frame to be visible");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement));

	}
	/**
	 * Wait for a specified number of seconds.
	 * @param timeoutInSeconds The number of seconds to wait.
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	public void wait(int timeoutInSeconds) throws InterruptedException {

		Thread.sleep(timeoutInSeconds * 1000);
	}
	/**
	 * Wait for all elements matching the specified locator to be visible on the web page.
	 * @param driver The `WebDriver` instance to use.
	 * @param element The `ElementLocator` instance representing the elements to wait for.
	 * @param timeoutInSeconds The maximum number of seconds to wait for the elements to be visible.
	 */

	public void waitForElementsToBeVisible(WebDriver driver,ElementLocator element, int timeoutInSeconds) {
		WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		log.info("wait for elements to be visible");
		wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
	}

	
	
	
	

}
