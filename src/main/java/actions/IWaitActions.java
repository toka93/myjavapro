package actions;

import org.openqa.selenium.WebDriver;

import webelement.ElementLocator;

public interface IWaitActions {
	public void waitForElementToBeVisible(WebDriver driver, ElementLocator element, int timeoutInSeconds);

    public void waitForElementToBeClickable(WebDriver driver, ElementLocator element, int timeoutInSeconds);

    public void waitForAlertToBePresent(WebDriver driver, int timeoutInSeconds);

    public void waitForFrameAndSwichtoit(WebDriver driver, ElementLocator element, int timeoutInSeconds);

    public void wait(int timeoutInSeconds) throws InterruptedException;

    public void waitForElementsToBeVisible(WebDriver driver, ElementLocator element, int timeoutInSeconds);

}
