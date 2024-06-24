package actions.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import actions.IAuthenticatingActions;
import webelement.ElementLocator;

/**
 * this is AuthenticatingActions class in Actions Package which contains all
 * methods needed to interact with alerts like (Accept Alert , Dismiss Alert ,
 * get text from alert , send text to alert , accept alert and click ok )
 * 
 * @author Toka.Ashraf
 *
 */
public class AuthenticatingActions implements IAuthenticatingActions{
	Logger log = LogManager.getLogger(AuthenticatingActions.class);

	ClickActions clickAC = new ClickActions();
	WaitActions waitAC = new WaitActions();
	WaitJS waitJs = new WaitJS();

	/**
	 * accepts alert.
	 * 
	 * @param driver the web driver used to open current page.
	 */
	public void acceptAlert(WebDriver driver) throws InterruptedException {
		try {
			Alert completealert = driver.switchTo().alert();
			log.info("switch to Alert");

			String alertText = completealert.getText();
			log.info("text in alert : {}" , alertText);
			completealert.accept();
			log.info("accept Alert");

		} catch (Exception e) {
			log.info("exception is: {}" , e.toString());

		}
	}

	/**
	 * dismiss alert.
	 * 
	 * @param driver the web driver used to open current page.
	 */
	public void dismissAlert(WebDriver driver) {

		log.info("Dismiss Alert");
		driver.switchTo().alert().dismiss();

	}

	/**
	 * returns alert text.
	 * 
	 * @param driver the web driver used to open current page.
	 */
	public String getAlertText(WebDriver driver) {
		log.info("get Alert text ");
		String text = driver.switchTo().alert().getText();
		log.info("text is : {}" , text);
		return text;
	}

	/**
	 * send text to alert.
	 * 
	 * @param driver the web driver used to open current page.
	 * @param text   the text entered to alert
	 */
	public void sendAlertText(WebDriver driver, String text) {
		log.info("send text to  Alert");
		driver.switchTo().alert().sendKeys(text);

	}

	/**
	 * accepts alert and clicks on ok.
	 * 
	 * @param driver  the web driver used to open current page.
	 * @param element the element object.
	 */
	public void acceptAlertandClickOk(WebDriver driver, ElementLocator element) throws InterruptedException {
		log.info("get the popup");
		String popup = driver.getWindowHandle();
		log.info("Switch to popup");
		driver.switchTo().window(popup);
		Thread.sleep(7000);
		log.info("wait for element to be clickable");
		waitAC.waitForElementToBeClickable(driver, element, 30);
		Thread.sleep(5000);
		log.info("click on elment");
		clickAC.clickOnElement(driver,element);
		log.info("wait until page is reloaded");
		waitJs.waitAllRequest(driver);

	}

}
