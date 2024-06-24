package actions.impl;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import actions.IWindowActions;
import webelement.ElementLocator;
import webelement.ObjectRepositoryReader;

/**
 * windowsActions class in Actions package contains methods to refresh window ,
 * to maximize window , to naviage back or forward ,
 * 
 * @author Toka.Ashraf
 *
 */
public class WindowActions implements IWindowActions {
	ObjectRepositoryReader objectRepositoryReader = new ObjectRepositoryReader();
	Logger log = LogManager.getLogger(WindowActions.class);
	String sep=File.separator;
	String screenShotfolder = "screenshots";

	// Refreshes the current window
	public void refreshWindow(WebDriver driver) {
		log.info("refressh window");
		driver.navigate().refresh();
	}
	// Maximizes the current window

	public void maxWindow(WebDriver driver) {

		log.info("maximize window");
		driver.manage().window().maximize();
	}
	// Navigates back in the browser history

	public void navigateBack(WebDriver driver) {
		log.info("navigate back");
		driver.navigate().back();

	}
	// Returns the current URL of the page

	public String getURL(WebDriver driver) {
		log.info("get url");
		return driver.getCurrentUrl();
	}
	// Navigates forward in the browser history

	public void navigateForward(WebDriver driver) {
		log.info("navigate forward");
		driver.navigate().forward();

	}
	// Navigates to the given URL

	public void navigateToURL(String url, WebDriver driver) {
		log.info("go to url : {}" , url);
		log.info("in windows actions");
		driver.get(url);

	}
	// Switches focus to the currently active element

	public void switchToActiveElement(WebDriver driver) {
		log.info("switch to active element ");
		driver.switchTo().activeElement();

	}
	// Scrolls to the given element

	public void scrolltoElement(WebDriver driver, ElementLocator element) {
		WebElement webElement = objectRepositoryReader.getWebElement(driver, element);
		log.info("scroll horizontal to element");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", webElement);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}

	// Takes a screenshot of the current page and saves it to a file

	public String takeScreenshot(WebDriver driver, String name) {
		log.info("take  screenshot of page ");
		String filePath = null;
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 StringBuffer screenshotPath = new StringBuffer()
			        .append(System.getProperty("user.dir"))
			        .append(sep)
			        .append(screenShotfolder)
			       .append(sep)
			       .append(name).append(".png");
			String screenshotFullPath=screenshotPath.toString();
		
		log.info(file.getName());
		try {
			File screenshotName = new File(
					screenshotFullPath);
			log.info(screenshotFullPath);
			FileUtils.copyFile(file, screenshotName);

			filePath = screenshotName.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;

	}

	// Takes a full-page screenshot of the current page and saves it to a file
	public String takeScreenshotforFullpage(WebDriver driver, String name) {
		log.info("take full screenshot of page ");
		Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).withName(name).save();
		 StringBuffer screenshotPath = new StringBuffer()
			        .append(System.getProperty("user.dir"))
			        .append(sep)
			        .append(screenShotfolder)
			        .append(sep)
			       .append(screenShotfolder)
			       .append(sep)
			       .append(name).append(".png");
		
		return screenshotPath.toString();

	}

}
