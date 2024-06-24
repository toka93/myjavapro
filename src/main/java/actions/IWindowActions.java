package actions;

import org.openqa.selenium.WebDriver;

import webelement.ElementLocator;

public interface IWindowActions {
	 public void refreshWindow(WebDriver driver);

	    public void maxWindow(WebDriver driver);

	    public void navigateBack(WebDriver driver);

	    public String getURL(WebDriver driver);

	    public void navigateForward(WebDriver driver);

	    public void navigateToURL(String url, WebDriver driver);

	    public void switchToActiveElement(WebDriver driver);

	    public void scrolltoElement(WebDriver driver, ElementLocator element);

	    public String takeScreenshot(WebDriver driver, String name);

	    public String takeScreenshotforFullpage(WebDriver driver, String name);

}
